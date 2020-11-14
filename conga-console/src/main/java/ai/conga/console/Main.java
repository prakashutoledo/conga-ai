package ai.conga.console;

import ai.conga.console.agent.MiniMaxAgent;
import ai.conga.core.domain.Colour;
import java.util.List;

public class Main {
    public static void main(String... args) {
        CongaBoard board = new CongaBoard();
        board.display();
        System.out.println("Start Game------------------------");
        MiniMaxAgent agent1 = new MiniMaxAgent(Colour.BLACK, board);
        MiniMaxAgent agent2 = new MiniMaxAgent(Colour.WHITE, board);
        agent1.makeMove();
        board.display();
        agent1.getBoard().display();
        agent1.getBoard().display();
        /*agent2.updateMove(agent2.getBoard().getAllPossibleMoves(agent2.getBoard().getTile(3,3)).get(0));
        agent2.getBoard().display();
        agent1.getBoard().display();*/

        /*System.out.println(board.evaluateHeuristics());
        List<CongaPlayerMove> list = board.getAllPossibleMoves(Colour.BLACK);
        CongaTile tile  = board.getTile(0,3);
        var moved =  board.getNextMove(tile, MoveDirection.WEST, Colour.BLACK);
        var te = board.getAllMovedTiles(7, moved, Colour.BLACK);

        for(var move: list) {
            move.getCurrentlyMovedBoard(board).display();
        }*/

    }
}
