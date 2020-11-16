package ai.conga.console;

import ai.conga.console.agent.AlphaBetaAgent;
import ai.conga.console.agent.MiniMaxAgent;
import ai.conga.console.agent.RandomAgent;
import ai.conga.console.game.CongaBoard;
import ai.conga.console.game.CongaGame;
import ai.conga.console.game.CongaPlayerMove;
import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Game;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class CongaConsoleApplication {
    public static void main(String... args) {
        CongaBoard board = new CongaBoard();
        new CongaGame(board).start();
        /*var a =  board.getAllPossibleMoves(Colour.BLACK);
        for (CongaPlayerMove congaPlayerMove : a) {
            System.out.println(congaPlayerMove);
        }
        a = a.stream().filter(ab -> ab.getOriginalTileTuples().get(0).getX().getTileColour() != ab.getFromTile().getTileColour()).collect(Collectors.toList());
        var b = board.getAllPossibleMoves(Colour.WHITE).stream().filter(ab -> ab.getOriginalTileTuples().get(0).getX().getTileColour() != ab.getFromTile().getTileColour()).collect(Collectors.toList());
        for (CongaPlayerMove congaPlayerMove : a) {
            System.out.println(congaPlayerMove);
        }
        System.out.println(a.size());
        board.display();
        System.out.println(board.evaluateHeuristics());
        System.out.println(a.size() - b.size());
        //new CongaGame(board).start();
        /*board.display();*/

        /*MiniMaxAgent miniMax = new MiniMaxAgent(Colour.BLACK, board.deepCopyOf());
        AlphaBetaAgent alphaBetaAgent = new AlphaBetaAgent(Colour.BLACK, board.deepCopyOf());
        miniMax.makeMove();
        miniMax.getBoard().display();

        alphaBetaAgent.makeMove();
        alphaBetaAgent.getBoard().display();*/
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
        board.display();*/
    }
}
