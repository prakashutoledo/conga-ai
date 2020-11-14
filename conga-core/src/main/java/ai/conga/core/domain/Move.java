package ai.conga.core.domain;

import ai.conga.core.util.Tuple;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 *
 * @param <T>
 * @param <B>
 * @param <M>
 */
public abstract class Move<T extends Tile<T>, B extends Board<T,M,B>, M extends Move<T,B,M>> implements Copy<M> {
    protected T fromTile;
    protected List<Tuple<T, Integer>>  originalTileTuples;

    protected Move(){}

    protected Move(@NotNull T fromTile, @NotNull List<Tuple<T, Integer>> originalTileTuples) {
        this.validateTiles(fromTile, originalTileTuples);
        this.fromTile = fromTile;
        this.originalTileTuples = originalTileTuples;
    }

    protected abstract void validateTiles(T fromTile, List<Tuple<T, Integer>> originalTileTuples);

    public abstract T[] getToTiles();

    public List<Tuple<T, Integer>> getOriginalTileTuples() {
        return originalTileTuples;
    }

    public abstract B getCurrentlyMovedBoard(@NotNull B currentPlayerBoard);

    public  T getFromTile() {
        return fromTile;
    }

    @Override
    public abstract M deepCopyOf();
}
