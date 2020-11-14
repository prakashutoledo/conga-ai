package ai.conga.console;

import ai.conga.console.agent.MiniMaxAgent;
import ai.conga.console.agent.RandomAgent;
import ai.conga.console.game.CongaBoard;
import ai.conga.console.game.CongaGame;
import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Game;

import java.util.Random;

public class CongaConsoleApplication {
    public static void main(String... args) {
        CongaBoard board = new CongaBoard();
        new CongaGame(board).start();
        /*board.display();
        System.out.println("Start Game------------------------");
        MiniMaxAgent agent1 = new MiniMaxAgent(Colour.BLACK, board);
        MiniMaxAgent agent2 = new MiniMaxAgent(Colour.WHITE, board);
        RandomAgent randomAgent = new RandomAgent(Colour.BLACK, board);
        randomAgent.makeMove();
        board.display();
        randomAgent.makeMove();
        board.display();
        randomAgent.makeMove();
        board.display();


        //agent1.makeMove();
        board.display();
        agent1.getBoard().display();
        agent2.getBoard().display();

        Random random =  new Random();
        System.out.println(random.nextInt(10));
        System.out.println(random.nextInt(8));*/
    }
}
