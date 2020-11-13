package ai.conga.console.agent;

import ai.conga.console.CongaBoard;
import ai.conga.console.CongaPlayerMove;
import ai.conga.core.algorithm.MiniMax;
import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Player;
import ai.conga.core.util.Tuple;

import java.util.ArrayDeque;

public class MiniMaxAgent extends Player<CongaBoard, CongaPlayerMove, MiniMaxAgent> {
    MiniMax<MiniMaxAgent, CongaPlayerMove> miniMax;

    private MiniMaxAgent() {
        super();
    }

    public MiniMaxAgent(Colour playerColour, CongaBoard board) {
        super(playerColour, board);
        this.pastMove = new ArrayDeque<>();
        this.miniMax = new MiniMax<>(this);
    }

    @Override
    public void makeMove() {
        Tuple<CongaPlayerMove, Integer> moveTuple = miniMax.bestMove();
        board.updateBoard(moveTuple.getX());
    }

    @Override
    public void undoMove() {
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
