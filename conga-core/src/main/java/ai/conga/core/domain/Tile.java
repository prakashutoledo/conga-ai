package ai.conga.core.domain;

import ai.conga.core.util.Tuple;

/**
 * Abstract Tile class
 * @author Prakash Khadka
 */
public abstract class Tile<T extends Tile<T>> implements Copy<T>{
    protected Tuple<Integer, Integer> index;

    public Tile(Tuple<Integer, Integer> index) {
        this.index = index;
    }

    public int getRowIndex() {
        return index.getX();
    }

    public int getColumnIndex() {
        return index.getY();
    }

    public Tuple<Integer, Integer> getIndex() {
        return index;
    }

    public abstract void emptyTile();

    public abstract void updateTile(T tile);

    @Override
    public abstract T deepCopyOf();
}