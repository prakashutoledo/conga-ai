package ai.conga.core.algorithm;

import ai.conga.core.domain.Colour;
import ai.conga.core.domain.Move;
import ai.conga.core.domain.Player;
import ai.conga.core.util.Tuple;
import org.jetbrains.annotations.NotNull;

public class AlphaBetaPruning <P extends Player, M extends Move> {
    private P player;
    private int maxTreeDepth;

    public AlphaBetaPruning(@NotNull P player, int maxTreeDepth) {
        this.player = player;
        this.maxTreeDepth = maxTreeDepth;
    }
    public AlphaBetaPruning(@NotNull P player) {
        this.player = player;
    }

    public Tuple<M, Integer> makeMove() {
        return alphaBetaPruning(maxTreeDepth, player.getPlayerColour());
    }

    private Tuple<M, Integer> alphaBetaPruning(int depth, Colour colour) {
        return null;
    }
}
