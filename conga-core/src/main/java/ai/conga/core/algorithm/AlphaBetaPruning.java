package ai.conga.core.algorithm;

import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Move;
import ai.conga.core.domain.Player;
import ai.conga.core.util.Tuple;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("unchecked")
public class AlphaBetaPruning <P extends Player, M extends Move> {
    private static final int MAX_MINIMAX_TREE_DEPTH = 3;
    private final P player;
    private int maxTreeDepth;

    public AlphaBetaPruning(P player) {
        this(player, MAX_MINIMAX_TREE_DEPTH);
    }

    public AlphaBetaPruning(@NotNull P player, int maxTreeDepth) {
        this.player = player;
        this.maxTreeDepth = maxTreeDepth;
    }

    public Tuple<M, Integer> bestMove() {
        return alphaBetaPruning(maxTreeDepth, player.getPlayerColour(), Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private Tuple<M, Integer> alphaBetaPruning(int depth, Colour colour, int alpha, int beta) {
        int currentScore;
        List<M> availableMoves = player.getAllPossibleMoves();
        M bestPossibleMove = RandomUtil.randomElement(availableMoves).orElse(null);

        if(availableMoves.isEmpty() || depth == 0) {
            currentScore = player.getBoard().evaluateHeuristics();
            return new Tuple<>(bestPossibleMove, currentScore);
        }

        for(M move : availableMoves) {
            player.updateMove(move);
            if(player.getPlayerColour() == colour) {
                currentScore = alphaBetaPruning(depth - 1, colour.nextTurn(), alpha, beta).getY();
                if(currentScore > alpha) {
                    alpha = currentScore;
                    bestPossibleMove = move;
                }
            }
            else {
                currentScore = alphaBetaPruning(depth - 1, colour.nextTurn(), alpha, beta).getY();
                if(currentScore < beta) {
                    beta = currentScore;
                    bestPossibleMove = move;
                }
            }
            player.undoMove();
            if(alpha >= beta) {
                break;
            }
        }
        return new Tuple<>(bestPossibleMove, player.getPlayerColour() == colour ? alpha : beta);
    }
}
