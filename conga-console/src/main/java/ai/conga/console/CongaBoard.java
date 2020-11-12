package ai.conga.console;

import ai.conga.core.domain.Board;
import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Move;
import ai.conga.core.domain.MoveDirection;
import ai.conga.core.util.Tuple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ai.conga.console.util.CongaConsoleGlobals.*;
import static java.lang.System.out;

public class CongaBoard extends Board<CongaTile, CongaBoard, CongaPlayerMove> {

    public CongaBoard() {
        this(CONGA_BOARD_ROW_SIZE, CONGA_BOARD_COLUMN_SIZE);
    }

    public CongaBoard(int rows, int columns) {
        super(rows, columns);
    }

    @Override
    protected void initializeBoardProperties() {
        this.board = new CongaTile[this.rows][this.columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                board[row][column] = new CongaTile(row, column);
            }
        }
        board[0][1].setStoneCount(1);
        board[0][1].setTileColour(Colour.BLACK);
        board[0][2].setStoneCount(2);
        board[0][2].setTileColour(Colour.BLACK);
        board[0][3].setStoneCount(7);
        board[0][3].setTileColour(Colour.BLACK);
        board[3][3].setStoneCount(10);
        board[3][3].setTileColour(Colour.WHITE);
    }

    @Override
    public int evaluateHeuristics() {
        return 0;
    }

    @Override
    public void display() {
        StringBuilder builder = new StringBuilder();
        builder.append(UPPER_TILE_LANE).append(NEW_LINE);
        for (int row = 0; row < rows; row++) {
            builder.append(DIVIDER_TILE_LANE);
            for (int column = 0; column < columns; column++) {
                builder.append(String.format("%1s%4s%2s", EMPTY_CHAR, board[row][column], DIVIDER_TILE_LANE));
            }
            builder.append(String.format("%s%s%s", NEW_LINE, UPPER_TILE_LANE, NEW_LINE));
        }
        out.println(builder.toString());
    }

    @Override
    public void updateBoard(CongaPlayerMove playerMove) {
        if (playerMove != null) {
            for (CongaTile tile : playerMove.getToTiles()) {
                board[tile.getRowIndex()][tile.getColumnIndex()].updateTile(tile);
            }
            Tuple<Integer, Integer> fromTileIndex = playerMove.getFromTile().getIndex();
            CongaTile tile = board[fromTileIndex.getX()][fromTileIndex.getX()];
            tile.emptyTile();
        }
    }

    @Override
    public List<CongaPlayerMove> getAllPossibleMoves(final Colour playerColour) {

        return Arrays.stream(board).flatMap(Arrays::stream).filter(tile -> tile.getTileColour() == playerColour)
                .map(this::getAllPossibleMoves)
                .flatMap(List::stream).collect(Collectors.toList());
    }

    private List<CongaPlayerMove> getAllPossibleMoves(final CongaTile tile) {
       return Arrays.stream(MoveDirection.values()).map(direction -> getNextMove(tile, direction))
                .filter(tuple -> tuple.getX() != INVALID_TILE && tuple.getY() != MoveDirection.INVALID)
                .map(movedTuple -> new CongaPlayerMove(tile, this.getAllMovedTiles(tile.getStoneCount(), movedTuple))).collect(Collectors.toList());
    }

    public Tuple<CongaTile, MoveDirection> getNextMove(final CongaTile tile, MoveDirection direction) {
        Tuple<CongaTile, MoveDirection> moveDirectionTuple =  new Tuple<>(INVALID_TILE, MoveDirection.INVALID);
        switch (direction) {
            case EAST:
                if(tile.getColumnIndex() != columns - 1 &&
                        (board[tile.getRowIndex()][tile.getColumnIndex() + 1].getTileColour() == tile.getTileColour() ||
                                board[tile.getRowIndex()][tile.getColumnIndex() + 1].getTileColour() == Colour.NONE)) {
                    moveDirectionTuple = new Tuple<>(getTile(tile.getRowIndex(),tile.getColumnIndex() + 1), direction);
                }
                break;

            case NORTH_EAST:
                if ((tile.getRowIndex() != 0 && tile.getColumnIndex() != rows - 1) &&
                        (board[tile.getRowIndex() - 1][tile.getColumnIndex() + 1].getTileColour() == tile.getTileColour() ||
                                board[tile.getRowIndex() - 1][tile.getColumnIndex() + 1].getTileColour() == Colour.NONE)) {
                    moveDirectionTuple = new Tuple<>(getTile(tile.getRowIndex() - 1, tile.getColumnIndex() + 1), direction);
                }
                break;
            case NORTH:
                if (tile.getRowIndex() != 0 &&
                        (board[tile.getRowIndex() - 1][tile.getColumnIndex()].getTileColour() == tile.getTileColour() ||
                                board[tile.getRowIndex() - 1][tile.getColumnIndex()].getTileColour() == Colour.NONE)) {
                    moveDirectionTuple = new Tuple<>(getTile(tile.getRowIndex() - 1, tile.getColumnIndex()), direction);
                }
                break;
            case NORTH_WEST:
                if ((tile.getRowIndex() != 0 && tile.getColumnIndex() != 0) &&
                        (board[tile.getRowIndex() - 1][tile.getColumnIndex() - 1].getTileColour() == tile.getTileColour() ||
                                board[tile.getRowIndex() - 1][tile.getColumnIndex() - 1].getTileColour() == Colour.NONE)) {
                    moveDirectionTuple = new Tuple<>(getTile(tile.getRowIndex() - 1,tile.getColumnIndex() - 1), direction);
                }
                break;
            case WEST:
                if (tile.getColumnIndex() != 0 &&
                        (board[tile.getRowIndex()][tile.getColumnIndex() - 1].getTileColour() == tile.getTileColour() ||
                                board[tile.getRowIndex()][tile.getColumnIndex() - 1].getTileColour() == Colour.NONE)) {
                    moveDirectionTuple =  new Tuple<>(getTile(tile.getRowIndex(),tile.getColumnIndex() - 1), direction);
                }
                break;
            case SOUTH:
                if (tile.getRowIndex() != rows - 1 &&
                        (board[tile.getRowIndex() + 1][tile.getColumnIndex()].getTileColour() == tile.getTileColour() ||
                                board[tile.getRowIndex() + 1][tile.getColumnIndex()].getTileColour() == Colour.NONE)) {
                    moveDirectionTuple = new Tuple<>(getTile(tile.getRowIndex() + 1,tile.getColumnIndex()), direction);
                }
                break;
            case SOUTH_WEST:
                if ((tile.getRowIndex() != rows - 1 && tile.getColumnIndex() != 0) &&
                        (board[tile.getRowIndex() + 1][tile.getColumnIndex() - 1].getTileColour() == tile.getTileColour() ||
                                board[tile.getRowIndex() + 1][tile.getColumnIndex() - 1].getTileColour() == Colour.NONE)) {
                    moveDirectionTuple = new Tuple<>(getTile(tile.getRowIndex() + 1,tile.getColumnIndex() - 1), direction);
                }
                break;
            case SOUTH_EAST:
                if ((tile.getRowIndex() != rows - 1 && tile.getColumnIndex() != columns- 1) &&
                        (board[tile.getRowIndex() + 1][tile.getColumnIndex() + 1].getTileColour() == tile.getTileColour() ||
                                board[tile.getRowIndex() + 1][tile.getColumnIndex() + 1].getTileColour() == Colour.NONE)) {
                    moveDirectionTuple = new Tuple<>(getTile(tile.getRowIndex() + 1,tile.getColumnIndex() + 1), direction);
                }
                break;
        }
        return moveDirectionTuple;
    }

    private List<Tuple<CongaTile, Integer>> getAllMovedTiles(int movedStones, Tuple<CongaTile, MoveDirection> movedTuple) {
        List<Tuple<CongaTile, Integer>> allMovedList = new ArrayList<>();
        List<CongaTile> orderedTiles = new ArrayList<>(Arrays.asList(movedTuple.getX()));

        while((movedTuple = getNextMove(movedTuple.getX(), movedTuple.getY())).getY() != MoveDirection.INVALID) {
            orderedTiles.add(movedTuple.getX());
        }

        switch (orderedTiles.size()) {
            case 1:
                allMovedList.add(new Tuple<>(orderedTiles.get(0), movedStones));
                break;
            case 2:
                allMovedList.add(new Tuple<>(orderedTiles.get(0), 1));
                allMovedList.add(new Tuple<>(orderedTiles.get(1), movedStones - 1));
                break;
            case 3:
                allMovedList.add(new Tuple<>(orderedTiles.get(0), 1));
                allMovedList.add(new Tuple<>(orderedTiles.get(1), 2));
                allMovedList.add(new Tuple<>(orderedTiles.get(2), movedStones - 3));
                break;
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
}
