package ai.conga.console;

import ai.conga.core.domain.Colour;

public class Main {
    public static void main(String... args) {
        CongaBoard board = new CongaBoard();
        board.display();

        CongaTile tile =  board.getTile(0, 1);
        CongaPlayerMove playerMove = new CongaPlayerMove(tile, 1, Colour.BLACK);

        playerMove.getCurrentlyMovedBoard(board).display();
    }
}
