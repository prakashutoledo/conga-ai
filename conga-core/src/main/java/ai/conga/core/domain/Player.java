package ai.conga.core.domain;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.function.Supplier;

public abstract class Player<B extends Board, M extends Move, P extends Player<B,M,P>> implements Copy<P> {
    protected Deque<M> pastMove;
    protected Colour playerColour;
    protected B board;

    protected Player() {
    }

    public Player(@NotNull Colour playerColour, @NotNull B board) {
        this.playerColour = playerColour;
        this.board = board;
    }

    public abstract void makeMove();
    public abstract void undoMove();

    public Colour getPlayerColour() {
        return playerColour;
    }

    public B getBoard() {
        return board;
    }

    public boolean isWinner() {
        return toSupplier(playerColour.nextTurn()).get().isEmpty();
    }

    public void updateMove(@NotNull M move) {
        pastMove.push(move);
        board.updateBoard(move);
    }

    @NotNull
    public List<M> getAllPossibleMoves() {
        Supplier<List<M>> playerMoves =  toSupplier(playerColour);

        if(isWinner() || playerMoves.get().isEmpty()) {
            return Collections.emptyList();
        }

        return playerMoves.get();
    }

    protected Supplier<List<M>> toSupplier(@NotNull Colour colour) {
        return () -> board.getAllPossibleMoves(colour);
    }

    @Override
    public abstract P deepCopyOf();
}
