package conga.ai.api;

/**
 * Abstract Tile class
 * @author Prakash Khadka
 */
public abstract class Tile {
    protected int rowIndex;
    protected int columnIndex;

    public Tile(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public abstract void emptyTile();
}