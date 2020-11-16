package ai.conga.console.agent;

import ai.conga.console.game.CongaBoard;
import ai.conga.console.game.CongaPlayerMove;
import ai.conga.core.algorithm.AlphaBetaPruning;
import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Player;
import ai.conga.core.util.Tuple;

import java.util.ArrayDeque;

public class AlphaBetaAgent extends Player<CongaBoard, CongaPlayerMove, AlphaBetaAgent> {
    AlphaBetaPruning<AlphaBetaAgent, CongaPlayerMove> alphaBetaPruning;

    private AlphaBetaAgent() {
        super();
    }

    public AlphaBetaAgent(Colour playerColour, CongaBoard board) {
        super(playerColour, board);
        this.alphaBetaPruning = new AlphaBetaPruning<>(this);
        this.pastMove = new ArrayDeque<>();
    }

    @Override
    public void makeMove() {
        Tuple<CongaPlayerMove, Integer> moveTuple = alphaBetaPruning.bestMove();
        board.updateBoard(moveTuple.getX(), true);
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
