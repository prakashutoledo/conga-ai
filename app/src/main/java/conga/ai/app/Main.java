package conga.ai.app;

import conga.ai.api.Colour;

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
