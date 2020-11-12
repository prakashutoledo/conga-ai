package ai.conga.console;

import ai.conga.core.domain.Colour;
import ai.conga.core.util.Tuple;

public class Main {
    public static void main(String... args) {
        CongaBoard board = new CongaBoard();
        board.display();

        CongaTile tile =  board.getTile(0, 0);
        CongaTile tile1 =  board.getTile(0, 1);
        CongaTile tile2 =  board.getTile(0, 2);
        CongaTile tile3 =  board.getTile(0, 3);
        CongaPlayerMove playerMove = new CongaPlayerMove(tile, new Tuple<>(tile1, 1), new Tuple<>(tile2, 2), new Tuple<>(tile3, 7));

        playerMove.getCurrentlyMovedBoard(board).display();
    }
}
