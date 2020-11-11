package ai.conga.console;

import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Copy;
import ai.conga.core.domain.Move;

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
