package ai.conga.core.domain;

import ai.conga.core.algorithm.RandomUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.function.Supplier;

import static java.util.Collections.*;

@SuppressWarnings("unchecked")
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

    public void undoMove() {
        if(RandomUtil.isEmptyCollection(pastMove)) {
            return;
        }
        M lastMove = pastMove.pop();
        board.revertBoard(lastMove);
    }

    public Colour getPlayerColour() {
        return playerColour;
    }

    public B getBoard() {
        return board;
    }

    public boolean isWinner() {
        return possibleMovesSupplier(playerColour.nextTurn()).get().isEmpty();
    }

    /**
     * Update the current board for this player with given move sequence. This method is only used for to store the
     * the current move for this player to stack and if this player wanted to undo the past move.
     * @param move current move to update board
     */
    @SuppressWarnings("unchecked")
    public void updateMove(@NotNull M move) {
        if(!RandomUtil.isNullCollection(pastMove)) {
            pastMove.push(move);
        }

        board.updateBoard(move, false);
    }

    @NotNull
    public List<M> getAllPossibleMoves() {
        Supplier<List<M>> playerMoves =  possibleMovesSupplier(playerColour);

        if(isWinner() || playerMoves.get().isEmpty()) {
            return emptyList();
        }

        return playerMoves.get();
    }

    protected Supplier<List<M>> possibleMovesSupplier(@NotNull Colour colour) {
        return () -> board.getAllPossibleMoves(colour);
    }

    @Override
    public abstract P deepCopyOf();
}
