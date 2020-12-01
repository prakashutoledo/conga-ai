package ai.conga.console.game;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CongaTile congaTile = (CongaTile) o;

        if (stoneCount != congaTile.stoneCount) return false;
        return tileColour == congaTile.tileColour;
    }

    @Override
    public int hashCode() {
        int result = stoneCount;
        result = 31 * result + tileColour.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s %s",stoneCount == 0 ? "" : Integer.toString(stoneCount) ,tileColour);
    }

    public String description() {
        return String.format("(%d,%d):%d:%s", index.getX(), index.getY(), stoneCount, tileColour);
    }
}
