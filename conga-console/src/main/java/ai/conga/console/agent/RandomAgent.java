package ai.conga.console.agent;

import ai.conga.console.CongaBoard;
import ai.conga.core.domain.Copy;
import ai.conga.core.domain.Player;
import ai.conga.core.domain.Colour;

/**
 *
 */
public class RandomAgent extends Player<Colour, CongaBoard> implements Copy<RandomAgent> {
    private Colour playerColour;
    private CongaBoard board;

    public RandomAgent(Colour playerColour, CongaBoard board) {
        this.playerColour = playerColour;
        this.board = board;
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
    public boolean hasNextMove() {
        return false;
    }

    @Override
    public void nextMove() {
    }

    @Override
    public RandomAgent deepCopyOf() {
        RandomAgent randomAgent = new RandomAgent(playerColour, board.deepCopyOf());
        return randomAgent;
    }
}
