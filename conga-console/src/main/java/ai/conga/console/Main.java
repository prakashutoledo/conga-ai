package ai.conga.console;

import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Move;
import ai.conga.core.domain.MoveDirection;
import ai.conga.core.util.Tuple;

import java.util.Arrays;
import java.util.List;

import static ai.conga.console.util.CongaConsoleGlobals.INVALID_TILE;

public class Main {
    public static void main(String... args) {
        CongaBoard board = new CongaBoard();
        board.display();

        CongaTile tile =  board.getTile(0, 0);
        CongaTile tile1 =  board.getTile(0, 1);
        CongaTile tile2 =  board.getTile(0, 2);
        CongaTile tile3 =  board.getTile(0, 3);
        List<CongaPlayerMove> list = board.getAllPossibleMoves(Colour.BLACK);

        for(var move: list) {
            move.getCurrentlyMovedBoard(board).display();
        }

        var tile111 = board.getNextMove(tile1, MoveDirection.EAST);
        var list1 = board.getAllMovedTiles(1, tile111);
        CongaPlayerMove move = new CongaPlayerMove(tile1, list1);
        //var n = move.getCurrentlyMovedBoard(board);
        //n.display();

        /*Arrays.stream(MoveDirection.values()).forEach( direction -> {
            Tuple<CongaTile, MoveDirection> tuple = board.getNextMove(tile1, direction);
            if(tuple.getX() != INVALID_TILE && tuple.getY() != MoveDirection.INVALID) {
                System.out.println(direction);
            }
        });*/

        //playerMove.getCurrentlyMovedBoard(board).display();*/

        //tile
    }
}
