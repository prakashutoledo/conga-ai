package ai.conga.console.agent;

import ai.conga.console.CongaBoard;
import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Copy;
import ai.conga.core.domain.Player;

public class MiniMaxAgent extends Player<Colour, CongaBoard> implements Copy<MiniMaxAgent> {

    @Override
    public Colour getPlayerColour() {
        return null;
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
    public MiniMaxAgent deepCopyOf() {
        return null;
    }
}
