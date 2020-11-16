package ai.conga.console.agent;

import ai.conga.console.game.CongaBoard;
import ai.conga.console.game.CongaPlayerMove;
import ai.conga.core.algorithm.MiniMax;
import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Player;
import ai.conga.core.util.Tuple;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.ArrayDeque;
import java.util.concurrent.ExecutionException;

/**
 *
 */
public class MiniMaxAgent extends Player<CongaBoard, CongaPlayerMove, MiniMaxAgent> {
    MiniMax<MiniMaxAgent, CongaPlayerMove> miniMax;
    LoadingCache<String, Tuple<CongaPlayerMove, Integer>> usedMoveCache;

    private MiniMaxAgent() {
        super();
    }

    public MiniMaxAgent(Colour playerColour, CongaBoard board) {
        super(playerColour, board);
        this.pastMove = new ArrayDeque<>();
        this.miniMax = new MiniMax<>(this);
        this.usedMoveCache = CacheBuilder.newBuilder().build(new CacheLoader<String, Tuple<CongaPlayerMove, Integer>>() {
            @Override
            public Tuple<CongaPlayerMove, Integer> load(String key) throws Exception {
                return miniMax.bestMove();
            }
        });
    }

    @Override
    public void makeMove() {
        Tuple<CongaPlayerMove, Integer> moveTuple = miniMax.bestMove();
        board.updateBoard(moveTuple.getX(), true);
    }

    @Override
    public MiniMaxAgent deepCopyOf() {
        MiniMaxAgent miniMaxAgent = new MiniMaxAgent();
        miniMaxAgent.board = board.deepCopyOf();
        miniMaxAgent.playerColour = playerColour;
        miniMaxAgent.pastMove = new ArrayDeque<>(pastMove);
        return miniMaxAgent;
    }
}
