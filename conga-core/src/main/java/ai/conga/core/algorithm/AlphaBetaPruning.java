package ai.conga.core.algorithm;

import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Move;
import ai.conga.core.domain.Player;
import ai.conga.core.util.Tuple;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("unchecked")
public class AlphaBetaPruning <P extends Player, M extends Move> {
    private static final int MAX_MINIMAX_TREE_DEPTH = 2;
    protected final P player;
    private int maxTreeDepth;
    private int visitedNodes;

    public AlphaBetaPruning(P player) {
        this(player, MAX_MINIMAX_TREE_DEPTH);
    }

    public AlphaBetaPruning(@NotNull P player, int maxTreeDepth) {
        this.player = player;
        this.maxTreeDepth = maxTreeDepth;
        this.empty();
    }

    public Tuple<M, Tuple<Integer,Long>> bestMove() {
        this.empty();
        long start = System.currentTimeMillis();
        Tuple<M, Integer> bestMove = alphaBetaPruning(maxTreeDepth, player.getPlayerColour(),
                                                      Integer.MIN_VALUE, Integer.MAX_VALUE);
        long end = System.currentTimeMillis();
        MiniMax miniMax = new MiniMax(player.deepCopyOf(), maxTreeDepth);
        Tuple<M, Tuple<Integer,Long>> temp = miniMax.bestMove();
        System.out.printf("Alpha-Beta Nodes Visited: %d, Nodes Pruned: %d with elapsed time: %d \n",
                visitedNodes, temp.getY().getX() - visitedNodes, end - start);
        return new Tuple<>(bestMove.getX(), new Tuple<>(visitedNodes, end - start));
    }

    private Tuple<M, Integer> alphaBetaPruning(int depth, Colour colour, int alpha, int beta) {
        List<M> availableMoves = player.getAllPossibleMoves();
        M bestPossibleMove = RandomUtil.randomElement(availableMoves).orElse(null);

        if(availableMoves.isEmpty() || depth == 0) {
            return new Tuple<>(bestPossibleMove, player.getBoard().evaluateHeuristics());
        }

        for(M move : availableMoves) {
            player.updateMove(move);
            int currentScore = alphaBetaPruning(depth - 1, colour.nextTurn(), alpha, beta).getY();
            if(player.getPlayerColour() == colour) {
                if(currentScore > alpha) {
                    alpha = currentScore;
                    bestPossibleMove = move;
                }
            }
            else {
                if(currentScore < beta) {
                    beta = currentScore;
                    bestPossibleMove = move;
                }
            }
            player.undoMove();
            if(alpha >= beta) {
                break;
            }
            ++visitedNodes;
        }
        return new Tuple<>(bestPossibleMove, player.getPlayerColour() == colour ? alpha : beta);
    }

    private void empty() {
        this.visitedNodes = 0;
    }
}
