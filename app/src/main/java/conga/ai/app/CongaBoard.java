package conga.ai.app;

import conga.ai.api.Board;
import conga.ai.api.Copy;

/**
 *
 */
public class CongaBoard extends Board<CongaTile, CongaPlayer> implements Copy<CongaBoard> {
    public CongaBoard(int rows, int columns) {
        super(rows, columns);
    }

    @Override
    protected void initializeBoardProperties() {
        this.board = new CongaTile[this.rows][this.columns];
        for(int row = 0; row < this.rows; row++) {
            for(int column = 0; column < this.columns; column++) {
                this.board[row][column] = new CongaTile(row, column);
            }
        }
    }

    @Override
    protected boolean hasNextMove() {
        return false;
    }

    @Override
    public void nextRandomMove() {
    }

    @Override
    public CongaBoard deepCopyOf() {
        if(this == null) {
            return null;
        }

        CongaBoard congaBoard = new CongaBoard(this.rows, this.columns);
        for(int row = 0; row < this.rows; row++) {
            for(int column = 0; column < this.columns; column++) {
                congaBoard.board[row][column] = this.board[row][column].deepCopyOf();
            }
        }
        return congaBoard;
    }
}
