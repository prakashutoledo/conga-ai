package ai.conga.core.algorithm;

import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Move;
import ai.conga.core.domain.Player;
import ai.conga.core.util.Tuple;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("unchecked")
public class MiniMax<P extends Player, M extends Move> {
    private static final int MAX_MINIMAX_TREE_DEPTH = 3;

    private final P player;
    private final int maxDepth;

    public MiniMax(P player) {
        this(player, MAX_MINIMAX_TREE_DEPTH);
    }

    public MiniMax(@NotNull P player, int maxDepth) {
        this.player = player;
        this.maxDepth = maxDepth;
    }

    public Tuple<M, Integer> bestMove() {
        return minimax(maxDepth, player.getPlayerColour());
    }

    private Tuple<M, Integer> minimax(int depth, @NotNull Colour colour) {
        int bestScore = player.getPlayerColour() == colour ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        List<M> availableMoves = player.getAllPossibleMoves();
        M bestMove = RandomUtil.randomElement(availableMoves).orElse(null);
        int currentScore;

        if(availableMoves.isEmpty() || depth == 0) {
            bestScore = player.getBoard().evaluateHeuristics();
            return new Tuple<>(bestMove, bestScore);
        }

        for(M move : availableMoves) {
            player.updateMove(move);
            currentScore = minimax(depth - 1, colour.nextTurn()).getY();
            if(player.getPlayerColour() == colour) {
                if(currentScore > bestScore) {
                    bestScore =  currentScore;
                    bestMove = move;
                }
            }
            else {
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