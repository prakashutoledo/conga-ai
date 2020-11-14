package ai.conga.core.algorithm;

import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Move;
import ai.conga.core.domain.Player;
import ai.conga.core.util.Tuple;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("unchecked")
public class AlphaBetaPruning <P extends Player, M extends Move> {
    private final P player;
    private int maxTreeDepth;

    public AlphaBetaPruning(@NotNull P player, int maxTreeDepth) {
        this.player = player;
        this.maxTreeDepth = maxTreeDepth;
    }
    public AlphaBetaPruning(@NotNull P player) {
        this.player = player;
    }

    public Tuple<M, Integer> makeMove() {
        return alphaBetaPruning(maxTreeDepth, player.getPlayerColour(), Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private Tuple<M, Integer> alphaBetaPruning(int depth, Colour colour, int alpha, int beta) {
        int currentScore;
        List<M> availableMoves = player.getAllPossibleMoves();
        M bestMove = RandomUtil.randomElement(availableMoves).orElse(null);
        if(availableMoves.isEmpty() || depth == 0) {
            currentScore = player.getBoard().evaluateHeuristics();
            return new Tuple<>(bestMove, currentScore);
        }

        for(M move : availableMoves) {
            player.updateMove(move);
            if(player.getPlayerColour() == colour) {
                currentScore = alphaBetaPruning(depth - 1, colour.nextTurn(), alpha, beta).getY();
                if(currentScore > alpha) {
                    alpha = currentScore;
                    bestMove = move;
                }
            }
            else {
                currentScore = alphaBetaPruning(depth - 1, colour.nextTurn(), alpha, beta).getY();
                if(currentScore < beta) {
                    beta = currentScore;
                    bestMove = move;
                }
            }
            player.undoMove();

            if(alpha >= beta) {
                break;
            }
        }

        return new Tuple<>(bestMove, player.getPlayerColour() == colour ? alpha : beta);
    }
}
