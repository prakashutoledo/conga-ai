package ai.conga.core.domain;

import ai.conga.core.util.Tuple;

/**
 *
 * @param <T>
 * @param <B>
 * @param <M>
 */
public abstract class Move<T extends Tile<T>, B extends Board<T,M,B>, M extends Move<T,B,M>> implements Copy<M> {
    protected T[] toTiles;
    protected T fromTile;

    public abstract T[] getToTiles();

    /**
     *
     * @param currentPlayerBoard
     * @return
     */
    public abstract B getCurrentlyMovedBoard(B currentPlayerBoard);

    public abstract T getFromTile();

    @Override
    public abstract M deepCopyOf();
}
