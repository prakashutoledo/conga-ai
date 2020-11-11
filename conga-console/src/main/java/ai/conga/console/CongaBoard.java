package ai.conga.console;

import ai.conga.core.domain.Board;
import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Copy;

import static java.lang.System.out;

/**
 *
 */
public class CongaBoard extends Board<CongaTile> implements Copy<CongaBoard> {
    public static final String DIVIDER_TILE_LANE = "|";
    public static final String UPPER_TILE_LANE = "-----------------------------";
    public static final String EMPTY_CHAR = "";
    public static final String NEW_LINE = "\n";

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
        board[0][1].setTileColour(Colour.BLACK);
        board[0][1].setStoneCount(2);
        CongaTile congaTile = board[2][1];
        congaTile.setStoneCount(2);
        congaTile.setTileColour(Colour.WHITE);
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
    public CongaBoard deepCopyOf() {
        CongaBoard congaBoard = new CongaBoard(rows, columns);
        for(int row = 0; row < rows; row++) {
            for(int column = 0; column < columns; column++) {
                congaBoard.board[row][column] = board[row][column].deepCopyOf();
            }
        }
        return congaBoard;
    }
}
