package conga.ai.api;

import static conga.ai.api.Colour.*;

/**
 * Abstract Tile class
 * @author Prakash Khadka
 */
public abstract class Tile {
    protected int rowIndex;
    protected int columnIndex;
    protected Colour colour;

    public Tile(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.colour = NONE;
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

    public Colour getPlayerColour() {
        return colour;
    }

    public void setPlayerColour(Colour colour) {
        this.colour = colour;
    }
}
