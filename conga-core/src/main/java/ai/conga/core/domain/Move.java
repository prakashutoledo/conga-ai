package ai.conga.core.domain;

public abstract class Move<T extends Tile, B extends Board<T>> {
    protected T toTile;

    public abstract T getToTile();

    public abstract B getCurrentlyMovedBoard(B currentPlayerBoard);
}
