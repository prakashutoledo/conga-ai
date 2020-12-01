package ai.conga.console.agent;

import ai.conga.console.game.CongaBoard;
import ai.conga.console.game.CongaPlayerMove;
import ai.conga.core.algorithm.AlphaBetaPruning;
import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Player;
import ai.conga.core.util.Tuple;

import java.util.ArrayDeque;

public class AlphaBetaAgent extends Player<CongaBoard, CongaPlayerMove, AlphaBetaAgent> {
    private AlphaBetaPruning<AlphaBetaAgent, CongaPlayerMove> alphaBetaPruning;
    private int nodesVisited;
    private long totalTimeElapsed;

    private AlphaBetaAgent() {
        super();
    }

    public AlphaBetaAgent(Colour playerColour, CongaBoard board) {
        super(playerColour, board);
        this.alphaBetaPruning = new AlphaBetaPruning<>(this);
        this.pastMove = new ArrayDeque<>();
        this.nodesVisited = 0;
        this.totalTimeElapsed = 0;
    }

    @Override
    public void makeMove() {
        Tuple<CongaPlayerMove, Tuple<Integer,Long>> moveTuple = alphaBetaPruning.bestMove();
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
    public AlphaBetaAgent deepCopyOf() {
        AlphaBetaAgent alphaBetaAgent = new AlphaBetaAgent();
        alphaBetaAgent.board = board.deepCopyOf();
        alphaBetaAgent.playerColour = playerColour;
        alphaBetaAgent.pastMove = new ArrayDeque<>(pastMove);
        return alphaBetaAgent;
    }
}
