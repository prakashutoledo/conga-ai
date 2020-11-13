package ai.conga.console.agent;

import ai.conga.console.CongaBoard;
import ai.conga.console.CongaPlayerMove;
import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Copy;
import ai.conga.core.domain.Player;

import java.util.ArrayDeque;

public class MiniMaxAgent extends Player<Colour, CongaBoard, CongaPlayerMove,MiniMaxAgent> {
    private Colour playerColour;
    private CongaBoard board;

    private MiniMaxAgent() {}

    public MiniMaxAgent(Colour playerColour, CongaBoard board) {
        this.playerColour = playerColour;
        this.board = board;
        this.pastMove = new ArrayDeque<>();
    }

    @Override
    public Colour getPlayerColour() {
        return playerColour;
    }

    @Override
    public CongaBoard getBoard() {
        return board;
    }

    @Override
    public boolean isWinner() {
        return false;
    }

    @Override
    public void makeMove() {
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
