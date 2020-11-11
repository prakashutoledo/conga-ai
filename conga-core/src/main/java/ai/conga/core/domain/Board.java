package ai.conga.core.domain;

/**
 * Abstract board class which defines basic board information
 *
 * @param <T> type of Tile
 */
public abstract class Board<T extends Tile> {
    protected int rows;
    protected int columns;
    protected T[][] board;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.initializeBoardProperties();
    }

    protected abstract void initializeBoardProperties();

    public abstract int evaluateHeuristics();

    public abstract void display();

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
}
