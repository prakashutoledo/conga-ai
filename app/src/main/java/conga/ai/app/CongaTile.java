package conga.ai.app;

import conga.ai.api.Copy;
import conga.ai.api.Tile;

public class CongaTile extends Tile implements Copy<CongaTile> {
    private int stoneCount;

    public CongaTile(int rowIndex, int columnIndex) {
        super(rowIndex, columnIndex);
        this.stoneCount = 0;
    }

    @Override
    public CongaTile deepCopyOf() {
        CongaTile congaTile = new CongaTile(this.rowIndex, this.columnIndex);
        congaTile.stoneCount = stoneCount;
        congaTile.colour = this.colour;
        return congaTile;
    }
}
