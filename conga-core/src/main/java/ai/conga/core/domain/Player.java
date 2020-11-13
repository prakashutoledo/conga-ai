package ai.conga.core.domain;

import java.util.Deque;

public abstract class Player<C extends Colour, B extends Board, M extends Move, P extends Player<C,B,M,P>> implements Copy<P> {
    protected Deque<M> pastMove;
    public abstract C getPlayerColour();
    public abstract B getBoard();
    public abstract boolean isWinner();
    public abstract void makeMove();
    public abstract void undoMove();

    @Override
    public abstract P deepCopyOf();
}
