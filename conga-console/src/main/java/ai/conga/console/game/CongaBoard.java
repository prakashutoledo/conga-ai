package ai.conga.console.game;

import ai.conga.core.domain.Board;
import ai.conga.core.domain.Colour;
import ai.conga.core.domain.MoveDirection;
import ai.conga.core.util.Tuple;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

import static ai.conga.console.util.CongaConsoleGlobals.*;
import static java.lang.System.out;

public class CongaBoard extends Board<CongaTile, CongaPlayerMove, CongaBoard> {

    public CongaBoard() {
        this(CONGA_BOARD_ROW_SIZE, CONGA_BOARD_COLUMN_SIZE);
    }

    public CongaBoard(int rows, int columns) {
        super(rows, columns);
    }

    @Override
    protected void initializeBoardProperties() {
        this.board = new CongaTile[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                board[row][column] = new CongaTile(row, column);
            }
        }

        board[0][0].setTileColour(Colour.WHITE);
        board[0][0].setStoneCount(10);

        board[3][3].setTileColour(Colour.BLACK);
        board[3][3].setStoneCount(10);

    }

/*    @Override
    public int evaluateHeuristics() {
        int blackCount =  getAllPossibleMoves(Colour.BLACK).size();
        int whiteCount = getAllPossibleMoves(Colour.WHITE).size();

        if(whiteCount == 0) {
            return Integer.MAX_VALUE;
        }

        if(blackCount == 0) {
            return Integer.MIN_VALUE;
        }

        return (blackCount - whiteCount);
    }*/


/*    @Override
    public int evaluateHeuristics() {

        long blackCount =  getAllPossibleMoves(Colour.BLACK).stream().filter(tiles -> tiles.getOriginalTileTuples().get(0).getX().getTileColour()
                != tiles.getFromTile().getTileColour()).count();

        long whiteCount = getAllPossibleMoves(Colour.WHITE).stream().filter(tiles -> tiles.getOriginalTileTuples().get(0).getX().getTileColour()
                != tiles.getFromTile().getTileColour()).count();

        if(whiteCount == 0) {
            return Integer.MAX_VALUE;
        }

        if(blackCount == 0) {
            return Integer.MIN_VALUE;
        }

        return (int) (blackCount - whiteCount);
    }*/

    public int evaluateHeuristics() {
        double blackCount = getAllPossibleMoves(Colour.BLACK).stream().mapToDouble(this::bestHeuristic).sum();
        double whiteCount = getAllPossibleMoves(Colour.WHITE).stream().mapToDouble(this::bestHeuristic).sum();

        if(blackCount == 0) {
            return Integer.MIN_VALUE;
        }

        if(whiteCount == 0) {
            return Integer.MAX_VALUE;
        }

        return (int) (blackCount - 1.1 * whiteCount);
    }

    private double bestHeuristic(@NotNull CongaPlayerMove congaPlayerMove) {
        double entropy = 0;
        List<Tuple<CongaTile, Integer>> originalTileTuples = congaPlayerMove.getOriginalTileTuples();
        entropy += congaPlayerMove.getFromTile().getTileColour() != originalTileTuples.get(0).getX().getTileColour() ? 1 : 0.9;

        if(originalTileTuples.size() == 2) {
            entropy += congaPlayerMove.getFromTile().getTileColour() != originalTileTuples.get(1).getX().getTileColour() ? 1 : 0.8;
        }

        if(originalTileTuples.size() == 3) {
            entropy += congaPlayerMove.getFromTile().getTileColour() != originalTileTuples.get(2).getX().getTileColour() ? 1 : 0.7;
        }
        return (int) entropy;
    }

    @Override
    public void display() {
        out.println(toString());
    }

    @Override
    public void updateBoard(@NotNull final CongaPlayerMove playerMove, boolean showDetails) {
        Tuple<Integer, Integer> fromTileIndex = playerMove.getFromTile().getIndex();

        StringBuilder builder =  new StringBuilder();
        builder.append(String.format("Player: '%s' moved from tile (%d,%d) '%d' stones to tiles: ",
                playerMove.getFromTile().getTileColour(),
                fromTileIndex.getX(), fromTileIndex.getY(), playerMove.getFromTile().getStoneCount()));

        for (CongaTile tile : playerMove.getToTiles()) {
            builder.append(String.format("(%d,%d) '%d' stones, ", tile.getRowIndex(), tile.getColumnIndex(), tile.getStoneCount()));
            board[tile.getRowIndex()][tile.getColumnIndex()].updateTile(tile);
        }

        CongaTile tile = board[fromTileIndex.getX()][fromTileIndex.getY()];
        tile.emptyTile();
        if(showDetails) {
            out.println(builder.toString().replaceAll(",\\s$", ""));
        }
    }

    @Override
    public void revertBoard(@NotNull final CongaPlayerMove playerMove) {
        CongaTile fromTile = playerMove.getFromTile();
        board[fromTile.getRowIndex()][fromTile.getColumnIndex()].updateTile(fromTile);

        for(var tuple : playerMove.getOriginalTileTuples()) {
            CongaTile congaTile = tuple.getX();
            board[congaTile.getRowIndex()][congaTile.getColumnIndex()].updateTile(congaTile);
        }
    }


    @Override
    public List<CongaPlayerMove> getAllPossibleMoves(@NotNull final Colour playerColour) {
        return Arrays.stream(board).flatMap(Arrays::stream).filter(tile -> tile.getTileColour() == playerColour)
                .map(this::getAllPossibleMoves).flatMap(List::stream).collect(Collectors.toList());
    }

    public List<CongaPlayerMove> getAllPossibleMoves(final CongaTile tile) {
       return Arrays.stream(MoveDirection.values()).map(direction -> getNextMove(tile, direction, tile.getTileColour()))
                .filter(tuple -> tuple.getX() != INVALID_TILE && tuple.getY() != MoveDirection.INVALID)
                .map(movedTuple -> new CongaPlayerMove(tile.deepCopyOf(), this.getAllMovedTiles(tile.getStoneCount(), movedTuple, tile.getTileColour()))).collect(Collectors.toList());
    }
    @NotNull
    public Tuple<CongaTile, MoveDirection> getNextMove(@NotNull CongaTile tile, MoveDirection direction, @NotNull Colour colour) {
        Tuple<CongaTile, MoveDirection> moveDirectionTuple =  new Tuple<>(INVALID_TILE, MoveDirection.INVALID);
        switch (direction) {
            case EAST:
                if(tile.getColumnIndex() != columns - 1 &&
                        (board[tile.getRowIndex()][tile.getColumnIndex() + 1].getTileColour() == colour ||
                                board[tile.getRowIndex()][tile.getColumnIndex() + 1].getTileColour() == Colour.NONE)) {
                    moveDirectionTuple = new Tuple<>(getTile(tile.getRowIndex(),tile.getColumnIndex() + 1), direction);
                }
                break;

            case NORTH_EAST:
                if ((tile.getRowIndex() != 0 && tile.getColumnIndex() != rows - 1) &&
                        (board[tile.getRowIndex() - 1][tile.getColumnIndex() + 1].getTileColour() == colour ||
                                board[tile.getRowIndex() - 1][tile.getColumnIndex() + 1].getTileColour() == Colour.NONE)) {
                    moveDirectionTuple = new Tuple<>(getTile(tile.getRowIndex() - 1, tile.getColumnIndex() + 1), direction);
                }
                break;
            case NORTH:
                if (tile.getRowIndex() != 0 &&
                        (board[tile.getRowIndex() - 1][tile.getColumnIndex()].getTileColour() == colour ||
                                board[tile.getRowIndex() - 1][tile.getColumnIndex()].getTileColour() == Colour.NONE)) {
                    moveDirectionTuple = new Tuple<>(getTile(tile.getRowIndex() - 1, tile.getColumnIndex()), direction);
                }
                break;
            case NORTH_WEST:
                if ((tile.getRowIndex() != 0 && tile.getColumnIndex() != 0) &&
                        (board[tile.getRowIndex() - 1][tile.getColumnIndex() - 1].getTileColour() == colour ||
                                board[tile.getRowIndex() - 1][tile.getColumnIndex() - 1].getTileColour() == Colour.NONE)) {
                    moveDirectionTuple = new Tuple<>(getTile(tile.getRowIndex() - 1,tile.getColumnIndex() - 1), direction);
                }
                break;
            case WEST:
                if (tile.getColumnIndex() != 0 &&
                        (board[tile.getRowIndex()][tile.getColumnIndex() - 1].getTileColour() == colour ||
                                board[tile.getRowIndex()][tile.getColumnIndex() - 1].getTileColour() == Colour.NONE)) {
                    moveDirectionTuple =  new Tuple<>(getTile(tile.getRowIndex(),tile.getColumnIndex() - 1), direction);
                }
                break;
            case SOUTH:
                if (tile.getRowIndex() != rows - 1 &&
                        (board[tile.getRowIndex() + 1][tile.getColumnIndex()].getTileColour() == colour ||
                                board[tile.getRowIndex() + 1][tile.getColumnIndex()].getTileColour() == Colour.NONE)) {
                    moveDirectionTuple = new Tuple<>(getTile(tile.getRowIndex() + 1,tile.getColumnIndex()), direction);
                }
                break;
            case SOUTH_WEST:
                if ((tile.getRowIndex() != rows - 1 && tile.getColumnIndex() != 0) &&
                        (board[tile.getRowIndex() + 1][tile.getColumnIndex() - 1].getTileColour() == colour ||
                                board[tile.getRowIndex() + 1][tile.getColumnIndex() - 1].getTileColour() == Colour.NONE)) {
                    moveDirectionTuple = new Tuple<>(getTile(tile.getRowIndex() + 1,tile.getColumnIndex() - 1), direction);
                }
                break;
            case SOUTH_EAST:
                if ((tile.getRowIndex() != rows - 1 && tile.getColumnIndex() != columns- 1) &&
                        (board[tile.getRowIndex() + 1][tile.getColumnIndex() + 1].getTileColour() == colour ||
                                board[tile.getRowIndex() + 1][tile.getColumnIndex() + 1].getTileColour() == Colour.NONE)) {
                    moveDirectionTuple = new Tuple<>(getTile(tile.getRowIndex() + 1,tile.getColumnIndex() + 1), direction);
                }
                break;
            default:
        }
        return moveDirectionTuple;
    }

    public List<Tuple<CongaTile, Integer>> getAllMovedTiles(int movedStones, @NotNull Tuple<CongaTile, MoveDirection> movedTuple, @NotNull Colour colour) {
        List<Tuple<CongaTile, Integer>> allMovedList = new ArrayList<>();
        List<CongaTile> orderedTiles = new ArrayList<>(Collections.singletonList(movedTuple.getX()));

        while((movedTuple = getNextMove(movedTuple.getX(), movedTuple.getY(), colour)).getY() != MoveDirection.INVALID) {
            if(orderedTiles.size() == 1) {
                if(movedStones <= 1) {
                    break;
                }
            } else {
                if(movedStones <= 3) {
                    break;
                }
            }
            orderedTiles.add(movedTuple.getX());
        }

        switch (orderedTiles.size()) {
            case 1:
                allMovedList.add(new Tuple<>(orderedTiles.get(0), movedStones));
                break;
            case 2:
                allMovedList.addAll(Arrays.asList(new Tuple<>(orderedTiles.get(0), 1),
                        new Tuple<>(orderedTiles.get(1), movedStones - 1)));
                break;
            case 3:
                allMovedList.addAll(Arrays.asList(new Tuple<>(orderedTiles.get(0), 1),
                        new Tuple<>(orderedTiles.get(1), 2), new Tuple<>(orderedTiles.get(2), movedStones - 3)));

        }
        return allMovedList;
    }

    @Override
    public CongaTile getTile(int rowIndex, int columnIndex) {
        if (!isValidRowIndex(rowIndex)) {
            throw new IllegalArgumentException(String.format("Invalid row index value : %d", rowIndex));
        }

        if (!isValidColumnIndex(columnIndex)) {
            throw new IllegalArgumentException(String.format("Invalid row index value : %d", columnIndex));
        }

        return board[rowIndex][columnIndex].deepCopyOf();
    }

    private boolean isValidRowIndex(int rowIndex) {
        return rowIndex >= 0 && rowIndex < rows;
    }

    private boolean isValidColumnIndex(int columnIndex) {
        return columnIndex >= 0 && columnIndex < columns;
    }

    @Override
    public CongaBoard deepCopyOf() {
        CongaBoard congaBoard = new CongaBoard(rows, columns);
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                CongaTile tile = board[row][column];
                congaBoard.board[row][column] = tile.deepCopyOf();
            }
        }
        return congaBoard;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(UPPER_TILE_LANE).append(NEW_LINE);
        for (int row = 0; row < rows; row++) {
            builder.append(DIVIDER_TILE_LANE);
            for (int column = 0; column < columns; column++) {
                builder.append(String.format("%1s%4s%2s", EMPTY_CHAR, board[row][column], DIVIDER_TILE_LANE));
            }
            builder.append(String.format("%s%s%s", NEW_LINE, UPPER_TILE_LANE, NEW_LINE));
        }
        return builder.toString();
    }
}
