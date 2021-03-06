package ai.conga.console.agent;

import ai.conga.console.game.CongaBoard;
import ai.conga.console.game.CongaPlayerMove;
import ai.conga.core.algorithm.RandomUtil;
import ai.conga.core.domain.Player;
import ai.conga.core.domain.Colour;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class RandomAgent extends Player<CongaBoard, CongaPlayerMove, RandomAgent> {
    private RandomAgent() {}

    public RandomAgent(Colour playerColour, CongaBoard board) {
        super(playerColour, board);
        this.pastMove = new ArrayDeque<>();
    }

    @Override
    public void makeMove() {
        RandomUtil.randomElement(possibleMovesSupplier(playerColour).get())
                .ifPresent(randomMove -> board.updateBoard(randomMove, true));
    }

    @Override
    public RandomAgent deepCopyOf() {
        RandomAgent randomAgent = new RandomAgent();
        randomAgent.playerColour = playerColour;
        randomAgent.board = board.deepCopyOf();
        randomAgent.pastMove = new ArrayDeque<>(pastMove);
        return randomAgent;
    }
}
