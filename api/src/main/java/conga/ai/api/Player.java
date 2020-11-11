package conga.ai.api;

import java.util.List;

public abstract class Player<T extends Colour, B extends Board> {
    public abstract T getPlayerColour();
    public abstract B getBoard();
    public abstract boolean hasNextMove();
    public abstract void nextMove();
}
