package ai.conga.console.agent;

import ai.conga.console.CongaBoard;
import ai.conga.console.CongaPlayerMove;
import ai.conga.core.domain.Player;
import ai.conga.core.domain.Colour;

import java.util.ArrayDeque;

public class RandomAgent extends Player<CongaBoard, CongaPlayerMove, RandomAgent> {
    private RandomAgent() {}

    public RandomAgent(Colour playerColour, CongaBoard board) {
        super(playerColour, board);
        this.pastMove = new ArrayDeque<>();
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
