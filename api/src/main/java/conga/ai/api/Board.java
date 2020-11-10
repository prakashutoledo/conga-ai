package conga.ai.api;

/**
 * Abstract board class which defines basic board information
 *
 * @param <T> type of Tile
 */
public abstract class Board<T extends Tile, P extends Player> {
    protected int rows;
    protected int columns;
    protected T[][] board;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.initializeBoardProperties();
    }

    protected abstract void initializeBoardProperties();

    protected abstract boolean hasNextMove();

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public abstract void nextRandomMove();
}
