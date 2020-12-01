package ai.conga.console.agent;

import ai.conga.console.game.CongaBoard;
import ai.conga.console.game.CongaPlayerMove;
import ai.conga.core.algorithm.EnhancedAlphaBeta;
import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Player;
import ai.conga.core.util.Tuple;

import java.util.ArrayDeque;

public class EnhancedAgent extends Player<CongaBoard, CongaPlayerMove, EnhancedAgent> {
    private EnhancedAlphaBeta<EnhancedAgent, CongaPlayerMove> enhancedAlphaBeta;
    private long totalTimeElapsed;

    private EnhancedAgent() {
        super();
        this.totalTimeElapsed = 0;
    }

    public EnhancedAgent(Colour playerColour, CongaBoard board) {
        super(playerColour, board);
        this.enhancedAlphaBeta = new EnhancedAlphaBeta<>(this);
        this.pastMove = new ArrayDeque<>();
    }

    public long getTotalTimeElapsed() {
        return totalTimeElapsed;
    }

    @Override
    public void makeMove() {
        Tuple<CongaPlayerMove, Long> moveTuple = enhancedAlphaBeta.bestMove();
        totalTimeElapsed += moveTuple.getY();
        board.updateBoard(moveTuple.getX(), true);
    }

    @Override
    public EnhancedAgent deepCopyOf() {
        EnhancedAgent enhancedAgent = new EnhancedAgent();
        enhancedAgent.board = board.deepCopyOf();
        enhancedAgent.playerColour = playerColour;
        enhancedAgent.pastMove = new ArrayDeque<>(pastMove);
        return enhancedAgent;
    }
}
