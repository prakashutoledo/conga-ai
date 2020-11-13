package ai.conga.core.algorithm;

import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Move;
import ai.conga.core.domain.Player;
import ai.conga.core.util.Tuple;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MiniMax<P extends Player, M extends Move> {
    private final P player;

    public MiniMax(@NotNull P player) {
        this.player = player;
    }

    @NotNull
    public Tuple<M, Integer> bestMove() {
        return minimax(3, player.getPlayerColour());
    }

    public Tuple<M, Integer> minimax(int depth, @NotNull Colour colour) {
        M bestMove = null;
        int bestScore = player.getPlayerColour() == colour ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        List<M> availableMoves = player.getAllPossibleMoves();
        int currentScore;

        if(availableMoves.isEmpty() || depth == 0) {
            bestScore = player.getBoard().evaluateHeuristics();
        }

        for(M move : availableMoves) {
            player.updateMove(move);
            if(player.getPlayerColour() == colour) {
                currentScore = minimax(depth - 1, colour.nextTurn()).getY();

                if(currentScore > bestScore) {
                    bestScore =  currentScore;
                    bestMove = move;
                }
            }
            else {
                currentScore = minimax(depth - 1, colour.nextTurn()).getY();
                bestMove = move;

                if(currentScore < bestScore) {
                    bestScore = currentScore;
                    bestMove = move;
                }
            }
            player.undoMove();
        }

        return new Tuple<>(bestMove, bestScore);
    }
}
