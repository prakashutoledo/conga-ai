package ai.conga.console;

import ai.conga.console.game.CongaBoard;
import ai.conga.console.game.CongaGame;

public class CongaConsoleApplication {
    public static void main(String... args) {
        CongaBoard board = new CongaBoard();
        new CongaGame(board).start();
    }
}
