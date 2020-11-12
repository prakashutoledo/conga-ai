package ai.conga.console;

import ai.conga.core.util.Tuple;
import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Move;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;

public class CongaPlayerMove extends Move<CongaTile, CongaBoard, CongaPlayerMove> {


    private CongaPlayerMove() {
    }

    public CongaPlayerMove(CongaTile fromTile, Tuple<CongaTile, Integer>... tileTuples) {
        requireNonNull(fromTile, "From tile should not be null");
        requireNonNull(tileTuples, "Tuples of tile should not be null");

        this.checkAndUpdateTiles(fromTile, tileTuples);
    }

    private void checkAndUpdateTiles(CongaTile fromTile, Tuple<CongaTile, Integer>... tileTuples) {
        if (tileTuples.length < 1 || tileTuples.length > 3) {
            throw new IllegalArgumentException("Not a valid tuple size. Size should be between 1 and 3 inclusive");
        }

        this.fromTile = fromTile;
        this.toTiles = new CongaTile[tileTuples.length];

        for (int index = 0; index < tileTuples.length; index++) {
            CongaTile tile = tileTuples[index].getX();
            Integer movedStones = tileTuples[index].getY();
            if (tile.getTileColour() == this.fromTile.getTileColour()) {
                tile.setStoneCount(movedStones + tile.getStoneCount());
            } else {
                tile.setStoneCount(movedStones);
                tile.setTileColour(this.fromTile.getTileColour());
            }
            toTiles[index] = tile;
        }
    }


    @Override
    public CongaTile[] getToTiles() {
        return this.toTiles;
    }

    @Override
    public CongaBoard getCurrentlyMovedBoard(CongaBoard currentPlayerBoard) {
        CongaBoard movedBoard = currentPlayerBoard.deepCopyOf();
        movedBoard.updateBoard(this);
        return movedBoard;
    }

    @Override
    public CongaTile getFromTile() {
        return fromTile;
    }


    @Override
    public CongaPlayerMove deepCopyOf() {
        CongaPlayerMove congaPlayerMove = new CongaPlayerMove();
        congaPlayerMove.fromTile = fromTile.deepCopyOf();
        congaPlayerMove.toTiles = Arrays.stream(toTiles).toArray(CongaTile[]::new);
        return congaPlayerMove;
    }

    @Override
    public String toString() {
        return toTiles.toString();
    }
}
