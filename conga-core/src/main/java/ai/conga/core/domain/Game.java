package ai.conga.core.domain;

public abstract class Game<B extends Board> {
    public Game(B board) {
        this.initializePlayers(board);
    }

    protected abstract void initializePlayers(B board);

    public abstract void start();
}
