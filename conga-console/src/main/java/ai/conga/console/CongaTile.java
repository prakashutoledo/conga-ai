package ai.conga.console;

import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Tile;
import ai.conga.core.util.Tuple;

import static ai.conga.core.domain.Colour.*;

public class CongaTile extends Tile<CongaTile> {
    private int stoneCount;
    private Colour tileColour;

    public CongaTile(int rowIndex, int columnIndex) {
        this(new Tuple<>(rowIndex, columnIndex));
    }

    public CongaTile(Tuple<Integer, Integer> index) {
        super(index);
        this.stoneCount = 0;
        this.tileColour = NONE;
    }

    @Override
    public void emptyTile() {
        this.tileColour = NONE;
        this.stoneCount = 0;
    }

    @Override
    public void updateTile(CongaTile tile) {
        this.stoneCount = tile.stoneCount;
        this.tileColour = tile.tileColour;
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
        CongaTile congaTile = new CongaTile(index);
        congaTile.stoneCount = stoneCount;
        congaTile.tileColour = tileColour;
        return congaTile;
    }

    @Override
    public String toString() {
        return String.format("%s %s",stoneCount == 0 ? "" : Integer.toString(stoneCount) ,tileColour);
    }
}
