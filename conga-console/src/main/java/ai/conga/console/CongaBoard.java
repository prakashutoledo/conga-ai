package ai.conga.console;

import ai.conga.core.domain.Board;
import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Move;
import ai.conga.core.util.Tuple;

import static ai.conga.console.util.CongaConsoleGlobals.*;
import static java.lang.System.out;

public class CongaBoard extends Board<CongaTile, CongaBoard> {

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
        board[0][0].setStoneCount(10);
        board[0][0].setTileColour(Colour.BLACK);
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
    public <M extends Move<CongaTile, CongaBoard, M>> void updateBoard(Move<CongaTile, CongaBoard, M> playerMove) {
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
