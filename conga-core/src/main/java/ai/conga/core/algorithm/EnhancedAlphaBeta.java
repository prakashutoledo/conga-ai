package ai.conga.core.algorithm;

import ai.conga.core.algorithm.exception.SearchTimeoutException;
import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Move;
import ai.conga.core.domain.Player;
import ai.conga.core.util.Tuple;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("unchecked")
public class EnhancedAlphaBeta<P extends Player, M extends Move>  {
    private static final int MAX_MINIMAX_TREE_DEPTH = 3;
    private static final long MAX_SEARCH_TIME_OUT_MILLIS = 600000;
    private final P player;
    private int maxTreeDepth;
    private long searchTimeoutMillis;
    private M bestMove;
    private int visitedNodesCount;
    private long searchStartTimeMillis;

    public EnhancedAlphaBeta(P player) {
        this(player, MAX_MINIMAX_TREE_DEPTH, MAX_SEARCH_TIME_OUT_MILLIS);
    }

    public EnhancedAlphaBeta(@NotNull P player, int maxTreeDepth) {
        this(player, maxTreeDepth, MAX_SEARCH_TIME_OUT_MILLIS);
    }

    @SuppressWarnings("unchecked")
    public EnhancedAlphaBeta(@NotNull P player, int maxTreeDepth, long timeOutMillis) {
        this.player = player;
        this.maxTreeDepth = maxTreeDepth;
        this.searchTimeoutMillis = timeOutMillis;
        this.searchStartTimeMillis = 0;
    }

    @SuppressWarnings("unchecked")
    public Tuple<M, Long> bestMove() {
        long start = System.currentTimeMillis();
        this.searchStartTimeMillis = System.currentTimeMillis();
        Tuple<M,Integer> bestMove = enhancedAlphaBeta();
        long end = System.currentTimeMillis();
        System.out.printf("Alpha-Beta IDS Timed with elapsed time: %d \n", end - start);
        return new Tuple<>(bestMove.getX(), end - start);
    }

    @SuppressWarnings("unchecked")
    private Tuple<M, Integer> enhancedAlphaBeta() {
        int depth = maxTreeDepth;
        try {
            while(true) {
                Tuple<M, Integer> tempBestMove = alphaBetaPruning(depth, player.getPlayerColour(),
                        Integer.MIN_VALUE, Integer.MAX_VALUE);
                bestMove = tempBestMove.getX();
                depth++;
                if(!isTimeOut()) {
                    return new Tuple<>(bestMove, 0);
                }
            }
        } catch (SearchTimeoutException ste) {
            System.out.println(depth);
            return new Tuple<>(bestMove, 0);
        }
    }

    private Tuple<M, Integer> alphaBetaPruning(int depth, Colour colour, int alpha, int beta) {
        List<M> availableMoves = player.getAllPossibleMoves();
        M bestPossibleMove = RandomUtil.randomElement(availableMoves).orElse(null);

        if(availableMoves.isEmpty() || depth == 0) {
            this.bestMove = bestPossibleMove;
            return new Tuple<>(bestPossibleMove, player.getBoard().evaluateHeuristics());
        }

        for(M move : availableMoves) {
            throwSearchTimeoutException();
            player.updateMove(move);
            if(player.getPlayerColour() == colour) {
                int currentScore = alphaBetaPruning(depth - 1, colour.nextTurn(), alpha, beta).getY();
                if(currentScore > alpha) {
                    alpha = currentScore;
                    bestPossibleMove = move;
                }
            }
            else {
                int currentScore = alphaBetaPruning(depth - 1, colour.nextTurn(), alpha, beta).getY();
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
        this.bestMove = bestPossibleMove;
        return new Tuple<>(bestPossibleMove, player.getPlayerColour() == colour ? alpha : beta);
    }

    private void throwSearchTimeoutException() {
        if(isTimeOut()) {
            throw new SearchTimeoutException("Search time out");
        }
    }

    private boolean isTimeOut() {
        return System.currentTimeMillis() >= this.searchStartTimeMillis + this.searchTimeoutMillis;
    }
}
