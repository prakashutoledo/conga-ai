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

public class MiniMaxAgent extends Player<CongaBoard, CongaPlayerMove, MiniMaxAgent> {
    MiniMax<MiniMaxAgent, CongaPlayerMove> miniMax;
    LoadingCache<String, Tuple<CongaPlayerMove, Tuple<Integer, Long>>> usedMoveCache;
    private int nodesVisited;
    private long totalTimeElapsed;

    private MiniMaxAgent() {
        super();
    }

    public MiniMaxAgent(Colour playerColour, CongaBoard board) {
        super(playerColour, board);
        this.pastMove = new ArrayDeque<>();
        this.miniMax = new MiniMax<>(this);
        this.usedMoveCache = CacheBuilder.newBuilder().build(new CacheLoader<String, Tuple<CongaPlayerMove, Tuple<Integer, Long>>>() {
            @Override
            public  Tuple<CongaPlayerMove, Tuple<Integer, Long>>load(String key) throws Exception {
                return miniMax.bestMove();
            }
        });
        this.nodesVisited = 0;
        this.totalTimeElapsed = 0;
        this.totalTimeElapsed = 0;
    }

    @Override
    public void makeMove() {
        Tuple<CongaPlayerMove, Tuple<Integer, Long>> moveTuple = miniMax.bestMove();
        nodesVisited += moveTuple.getY().getX();
        totalTimeElapsed += moveTuple.getY().getY();
        board.updateBoard(moveTuple.getX(), true);
    }

    public int getNodesVisited() {
        return nodesVisited;
    }

    public long getTotalTimeElapsed() {
        return totalTimeElapsed;
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
