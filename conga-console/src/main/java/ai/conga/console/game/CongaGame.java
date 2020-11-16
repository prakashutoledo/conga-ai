package ai.conga.console.game;

import ai.conga.console.agent.MiniMaxAgent;
import ai.conga.console.agent.RandomAgent;
import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Game;
import ai.conga.core.domain.Player;
import static java.lang.System.*;

public class CongaGame extends Game<CongaBoard> {
    private MiniMaxAgent blackPlayer;
    private RandomAgent whitePlayer;

    public CongaGame(CongaBoard board) {
        super(board);
    }

    @Override
    protected void initializePlayers(CongaBoard board) {
        this.blackPlayer = new MiniMaxAgent(Colour.BLACK, board);
        this.whitePlayer = new RandomAgent(Colour.WHITE, board);
    }

    @Override
    public void start() {
        Player currentPlayer = whitePlayer;
        currentPlayer.getBoard().display();
        int moveCount = 1;
        while(true) {
            out.print(String.format("[Move: %d] ", moveCount++));
            currentPlayer.makeMove();
            currentPlayer.getBoard().display();
            if(currentPlayer.isWinner()) {
                System.out.println(currentPlayer.getBoard().evaluateHeuristics());
                if(currentPlayer.getPlayerColour() == Colour.BLACK) {
                    out.println(String.format("Random Agent(%s) you suck in this game.", Colour.WHITE));
                    out.println(String.format("Minimax Agent(%s) is winner!!!", Colour.BLACK));
                } else {
                    out.println(String.format("Random Agent(%d) is winner!!!", Colour.BLACK));
                    out.println("You Rock!!");
                }
                break;
            }
            currentPlayer = currentPlayer == whitePlayer ? blackPlayer : whitePlayer;
        }
    }
}
