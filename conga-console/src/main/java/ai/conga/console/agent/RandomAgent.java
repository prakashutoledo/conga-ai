package ai.conga.console.agent;

import ai.conga.console.CongaBoard;
import ai.conga.console.CongaPlayerMove;
import ai.conga.core.domain.Copy;
import ai.conga.core.domain.Player;
import ai.conga.core.domain.Colour;

import java.util.ArrayDeque;

public class RandomAgent extends Player<Colour, CongaBoard, CongaPlayerMove, RandomAgent> {
    private Colour playerColour;
    private CongaBoard board;

    private RandomAgent() {}

    public RandomAgent(Colour playerColour, CongaBoard board) {
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
    public RandomAgent deepCopyOf() {
        RandomAgent randomAgent = new RandomAgent();
        randomAgent.playerColour = playerColour;
        randomAgent.board = board.deepCopyOf();
        randomAgent.pastMove = new ArrayDeque<>(pastMove);
        return randomAgent;
    }
}
