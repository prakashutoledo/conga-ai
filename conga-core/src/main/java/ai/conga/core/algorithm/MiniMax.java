package ai.conga.core.algorithm;

import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Move;
import ai.conga.core.domain.Player;
import ai.conga.core.util.Tuple;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("unchecked")
public class MiniMax<P extends Player, M extends Move> {
    private static final int MAX_MINIMAX_TREE_DEPTH = 2;

    private final P player;
    private final int maxDepth;
    private int nodeCount= 0;


    public MiniMax(P player) {
        this(player, MAX_MINIMAX_TREE_DEPTH);
    }

    public MiniMax(@NotNull P player, int maxDepth) {
        this.player = player;
        this.maxDepth = maxDepth;
    }


    public Tuple<M, Tuple<Integer,Long>> bestMove() {
        this.nodeCount = 0;
        long start = System.currentTimeMillis();
        Tuple<M, Integer> bestMove = minimax(maxDepth, player.getPlayerColour());
        long end = System.currentTimeMillis();
        System.out.printf("\nMinimax Nodes Visited: %d with elapsed time: %d milliseconds\n",
                nodeCount, end - start);
        return new Tuple<>(bestMove.getX(), new Tuple<>(nodeCount, end - start));
    }

    public Tuple<M, Tuple<Integer,Long>> best() {
        this.nodeCount = 0;
        long start = System.currentTimeMillis();
        Tuple<M, Integer> bestMove = minimax(maxDepth, player.getPlayerColour());
        long end = System.currentTimeMillis();
        return new Tuple<>(bestMove.getX(), new Tuple<>(nodeCount, end - start));
    }

    private Tuple<M, Integer> minimax(int depth, @NotNull Colour colour) {
        int bestScore = player.getPlayerColour() == colour ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        List<M> availableMoves = player.getAllPossibleMoves();
        M bestMove = RandomUtil.randomElement(availableMoves).orElse(null);

        if(availableMoves.isEmpty() || depth == 0) {
            bestScore = player.getBoard().evaluateHeuristics();
            return new Tuple<>(bestMove, bestScore);
        }

        for(M move : availableMoves) {
            player.updateMove(move);
            int currentScore = minimax(depth - 1, colour.nextTurn()).getY();
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
            nodeCount++;
        }
        return new Tuple<>(bestMove, bestScore);
    }
}