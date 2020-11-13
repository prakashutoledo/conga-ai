package ai.conga.console;

import ai.conga.console.agent.MiniMaxAgent;
import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Player;

import java.util.List;

public class Main {
    public static void main(String... args) {
        CongaBoard board = new CongaBoard();
        board.display();
        System.out.println(board.evaluateHeuristics());
        List<CongaPlayerMove> list = board.getAllPossibleMoves(Colour.BLACK);
        /*CongaTile tile  = board.getTile(0,3);
        var moved =  board.getNextMove(tile, MoveDirection.WEST, Colour.BLACK);
        var te = board.getAllMovedTiles(7, moved, Colour.BLACK);*/

        for(var move: list) {
            move.getCurrentlyMovedBoard(board).display();
        }
    }
}
