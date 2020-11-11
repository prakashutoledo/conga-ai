package conga.ai.app;

import conga.ai.api.Colour;
import conga.ai.api.Copy;
import conga.ai.api.Tile;

import static conga.ai.api.Colour.*;

/**
 *
 */
public class CongaTile extends Tile implements Copy<CongaTile> {
    private int stoneCount;
    private Colour tileColour;

    public CongaTile(int rowIndex, int columnIndex) {
        super(rowIndex, columnIndex);
        this.stoneCount = 0;
        this.tileColour = NONE;
    }

    @Override
    public void emptyTile() {
        this.tileColour = NONE;
        this.stoneCount = 0;
    }

    public int getStoneCount() {
        return stoneCount;
    }

    public void setStoneCount(int stoneCount) {
        this.stoneCount = stoneCount;
    }

    public Colour getTileColour() {
        return tileColour;
    }

    public void setTileColour(Colour tileColour) {
        this.tileColour = tileColour;
    }

    @Override
    public CongaTile deepCopyOf() {
        CongaTile congaTile = new CongaTile(rowIndex, columnIndex);
        congaTile.stoneCount = stoneCount;
        congaTile.tileColour = tileColour;
        return congaTile;
    }

    @Override
    public String toString() {
        return String.format("%s %s",stoneCount == 0 ? "" : Integer.toString(stoneCount) ,tileColour);
    }
}
