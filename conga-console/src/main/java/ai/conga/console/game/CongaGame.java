package ai.conga.console.game;

import ai.conga.console.agent.AlphaBetaAgent;
import ai.conga.console.agent.RandomAgent;
import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Game;
import ai.conga.core.domain.Player;
import static java.lang.System.*;

public class CongaGame extends Game<CongaBoard> {
    private AlphaBetaAgent blackPlayer;
    private RandomAgent whitePlayer;
    int totalBlackMove;
    long totalElapsedTime;

    public CongaGame(CongaBoard board) {
        super(board);
    }

    @Override
    protected void initializePlayers(CongaBoard board) {
        this.blackPlayer = new AlphaBetaAgent(Colour.BLACK, board);
        this.whitePlayer = new RandomAgent(Colour.WHITE, board);
        this.totalBlackMove = 0;
        this.totalElapsedTime = 0;
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
                if(currentPlayer.getPlayerColour() == Colour.BLACK) {
                    out.println(String.format("AI Agent(Alpha-Beta) is winner!!!", Colour.BLACK));
                    out.println(String.format("Average nodes visited : %d", blackPlayer.getNodesVisited()/totalBlackMove));
                    out.println(String.format("Average nodes elapsed time : %d", blackPlayer.getTotalTimeElapsed()/totalBlackMove));
                } else {
                    out.println(String.format("Random Agent(%d) is winner!!!", Colour.WHITE));
                    out.println("You Rock!!");
                }
                break;
            }
            if(currentPlayer == blackPlayer) {
                currentPlayer = whitePlayer;
                ++totalBlackMove;
                ++totalElapsedTime;
            }
            else {
                currentPlayer = blackPlayer;
            }
        }
    }
}
