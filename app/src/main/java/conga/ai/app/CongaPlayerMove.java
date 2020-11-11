package conga.ai.app;

import conga.ai.api.Colour;
import conga.ai.api.Copy;
import conga.ai.api.Move;

public class CongaPlayerMove extends Move<CongaTile> implements Copy<CongaPlayerMove> {

    public CongaPlayerMove(CongaTile toTile, int movedStones, Colour playerColour) {
        this.toTile = toTile;
        toTile.setStoneCount(movedStones + this.toTile.getStoneCount());
        toTile.setTileColour(playerColour);
    }

    @Override
    public CongaTile getToTile() {
        return this.toTile;
    }

    @Override
    public CongaPlayerMove deepCopyOf() {
        return new CongaPlayerMove(toTile.deepCopyOf(), 0, toTile.getTileColour());
    }
}
