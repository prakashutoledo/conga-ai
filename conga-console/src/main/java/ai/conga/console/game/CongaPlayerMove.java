package ai.conga.console.game;

import ai.conga.core.domain.Move;
import ai.conga.core.util.Tuple;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CongaPlayerMove extends Move<CongaTile, CongaBoard, CongaPlayerMove> {
    private CongaPlayerMove() {
        super();
    }

    public CongaPlayerMove(CongaTile fromTile, List<Tuple<CongaTile, Integer>> originalTileTuples) {
        super(fromTile, originalTileTuples);
    }

    @Override
    protected void validateTiles(CongaTile fromTile, List<Tuple<CongaTile, Integer>> originalTileTuples) {
        if (originalTileTuples.size() < 1 || originalTileTuples.size() > 3) {
            throw new IllegalArgumentException("Not a valid tuple size. Size should be between 1 and 3 inclusive");
        }
    }

    @Override
    public CongaTile[] getToTiles() {
        /*CongaTile[] toTiles = new CongaTile[originalTileTuples.size()];

        for (int index = 0; index < originalTileTuples.size(); index++) {
            CongaTile tile = originalTileTuples.get(index).getX();
            Integer movedStones = originalTileTuples.get(index).getY();
            if (tile.getTileColour() == fromTile.getTileColour()) {
                tile.setStoneCount(movedStones + tile.getStoneCount());
            } else {
                tile.setStoneCount(movedStones);
                tile.setTileColour(fromTile.getTileColour());
            }
            toTiles[index] = tile;
        }
        return toTiles;*/
        return originalTileTuples.stream().map(this::mapToTile).toArray(CongaTile[]::new);
    }

    private CongaTile mapToTile(Tuple<CongaTile, Integer> tileTuple) {
        CongaTile originalTile = tileTuple.getX();
        int movedStones = tileTuple.getY();
        CongaTile newTile = originalTile.deepCopyOf();

        if (originalTile.getTileColour() == fromTile.getTileColour()) {
            newTile.setStoneCount(movedStones + originalTile.getStoneCount());
        }
        else {
            newTile.setStoneCount(movedStones);
        }
        newTile.setTileColour(fromTile.getTileColour());
        return newTile;
    }

    @Override
    public CongaBoard getCurrentlyMovedBoard(@NotNull CongaBoard currentPlayerBoard) {
        CongaBoard movedBoard = currentPlayerBoard.deepCopyOf();
        movedBoard.updateBoard(this, false);
        return movedBoard;
    }


    @Override
    public CongaPlayerMove deepCopyOf() {
        CongaPlayerMove congaPlayerMove = new CongaPlayerMove();
        congaPlayerMove.fromTile = fromTile.deepCopyOf();
        congaPlayerMove.originalTileTuples = new ArrayList<>(originalTileTuples);
        return congaPlayerMove;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("From tile: %s to tiles: ", fromTile.description()));
        for(var tile: this.originalTileTuples) {
            builder.append(tile.getX().description()).append(",");
        }
        return builder.toString();
    }
}
