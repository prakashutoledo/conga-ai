package ai.conga.console;

import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Copy;
import ai.conga.core.domain.Move;

public class CongaPlayerMove extends Move<CongaTile, CongaBoard> implements Copy<CongaPlayerMove> {

    public CongaPlayerMove(CongaTile toTile, int movedStones, Colour playerColour) {
        this.toTile = toTile;
        this.checkAndUpdateTile(movedStones, playerColour);
    }

    private void checkAndUpdateTile(int movedStones, Colour playerColour) {
        if(toTile.getTileColour() == playerColour) {
            toTile.setStoneCount(movedStones + toTile.getStoneCount());
        }
        else {
            toTile.setStoneCount(movedStones);
            toTile.setTileColour(playerColour);
        }
    }

    @Override
    public CongaTile getToTile() {
        return this.toTile;
    }

    @Override
    public CongaBoard getCurrentlyMovedBoard(CongaBoard currentPlayerBoard) {
        CongaBoard movedBoard = currentPlayerBoard.deepCopyOf();
        movedBoard.updateBoard(toTile);
        return movedBoard;
    }

    @Override
    public CongaPlayerMove deepCopyOf() {
        return new CongaPlayerMove(toTile.deepCopyOf(), 0, toTile.getTileColour());
    }

    @Override
    public String toString() {
        return toTile.toString();
    }
}
