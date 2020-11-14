package ai.conga.console.game;

import ai.conga.console.agent.MiniMaxAgent;
import ai.conga.console.agent.RandomAgent;
import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Game;
import ai.conga.core.domain.Player;

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
            System.out.print(String.format("[Move: %d] ", moveCount++));
            currentPlayer.makeMove();
            currentPlayer.getBoard().display();
            if(currentPlayer.isWinner()) {
                if(currentPlayer.getPlayerColour() == Colour.BLACK) {
                    System.out.println(String.format("Random Agent(%s) you suck in this game.", Colour.WHITE));
                    System.out.println(String.format("Minimax Agent(%s) is winner!!!", Colour.BLACK));
                } else {
                    System.out.println(String.format("Random Agent(%d) is winner!!!", Colour.BLACK));
                    System.out.println("You Rock!!");
                }


                break;
            }
            /*try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            currentPlayer = currentPlayer == whitePlayer ? blackPlayer : whitePlayer;
        }
    }
}