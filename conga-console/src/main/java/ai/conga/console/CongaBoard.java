package ai.conga.console;

import ai.conga.core.domain.Board;
import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Copy;

import static ai.conga.console.util.CongaConsoleGlobals.*;
import static java.lang.System.out;

/**
 *
 */
public class CongaBoard extends Board<CongaTile> implements Copy<CongaBoard> {

    public CongaBoard() {
        this(CONGA_BOARD_ROW_SIZE, CONGA_BOARD_COLUMN_SIZE);
    }

    public CongaBoard(int rows, int columns) {
        super(rows, columns);
    }

    @Override
    protected void initializeBoardProperties() {
        this.board = new CongaTile[this.rows][this.columns];
        for(int row = 0; row < rows; row++) {
            for(int column = 0; column < columns; column++) {
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
        for(int row = 0; row < rows; row++) {
            builder.append(DIVIDER_TILE_LANE);
            for(int column = 0; column < columns; column++) {
                builder.append(String.format("%1s%4s%2s",EMPTY_CHAR, this.board[row][column], DIVIDER_TILE_LANE));
            }
            builder.append(String.format("%s%s%s",NEW_LINE, UPPER_TILE_LANE, NEW_LINE));
        }
        out.println(builder.toString());
    }

    @Override
    public void updateBoard(CongaTile tileToUpdate) {
        if(tileToUpdate != null) {
            board[tileToUpdate.getRowIndex()][tileToUpdate.getColumnIndex()] = tileToUpdate.deepCopyOf();
        }
    }

    @Override
    public CongaTile getTile(int rowIndex, int columnIndex) {
        if(!isValidRowIndex(rowIndex)) {
            throw new IllegalArgumentException(String.format("Invalid row index value : %d", rowIndex));
        }

        if(!isValidColumnIndex(columnIndex)) {
            throw new IllegalArgumentException(String.format("Invalid row index value : %d", columnIndex));
        }

        return board[rowIndex][columnIndex].deepCopyOf();
    }

    @Override
    public CongaBoard deepCopyOf() {
        CongaBoard congaBoard = new CongaBoard(rows, columns);
        for(int row = 0; row < rows; row++) {
            for(int column = 0; column < columns; column++) {
                congaBoard.board[row][column] = board[row][column].deepCopyOf();
            }
        }
        return congaBoard;
    }

    private boolean isValidRowIndex(int rowIndex) {
        return rowIndex >= 0 && rowIndex < rows;
    }

    private boolean isValidColumnIndex(int columnIndex) {
        return columnIndex >= 0 && columnIndex < columns;
    }
}
