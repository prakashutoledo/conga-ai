package conga.ai.api;

public abstract class Move<T extends Tile> {
    protected T toTile;

    public abstract T getToTile();
}
