package ai.conga.console;

import ai.conga.core.domain.Colour;

public class Main {
    public static void main(String... args) {
        CongaBoard board = new CongaBoard(4, 4);
        board.display();
        Colour test = Colour.BLACK;
        test = test.nextTurn();
        test = Colour.NONE;
        System.out.println(test.nextTurn().ordinal());
    }
}
