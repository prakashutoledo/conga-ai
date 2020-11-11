package conga.ai.app;

import conga.ai.api.Copy;
import conga.ai.api.Player;
import conga.ai.api.Colour;

/**
 *
 */
public class RandomPlayer extends Player<Colour, CongaBoard> implements Copy<RandomPlayer> {
    private Colour playerColour;
    private CongaBoard board;

    public RandomPlayer(Colour playerColour, CongaBoard board) {
        this.playerColour = playerColour;
        this.board = board;
    }

    @Override
    public Colour getPlayerColour() {
        return playerColour;
    }

    @Override
    public CongaBoard getBoard() {
        return null;
    }

    @Override
    public boolean hasNextMove() {
        return false;
    }

    @Override
    public void nextMove() {
    }

    @Override
    public RandomPlayer deepCopyOf() {
        RandomPlayer randomPlayer = new RandomPlayer(playerColour, board.deepCopyOf());
        return randomPlayer;
    }
}
