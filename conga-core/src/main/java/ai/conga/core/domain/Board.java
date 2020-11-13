package ai.conga.core.domain;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * Abstract board class which defines basic board information
 *
 * @param <T> type of Tile
 */
public abstract class Board<T extends Tile<T>, M extends Move<T,B,M>, B extends Board<T,M,B>> implements Copy<B> {
    protected int rows;
    protected int columns;
    protected T[][] board;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.initializeBoardProperties();
    }

    protected abstract void initializeBoardProperties();

    public abstract int evaluateHeuristics();

    public abstract void display();

    public abstract void updateBoard(final M playerMove);

    public abstract List<M> getAllPossibleMoves(final Colour playerColour);

    /**
     * Returns the deep copy of {@link T} if given row and column index are valid index and are matched
     *
     * @param rowIndex a row index of board to look at
     * @param columnIndex a row column index of board to look at
     * @return deep copy of {@link T} of matching row and column index from board
     */
    public abstract T getTile(int rowIndex, int columnIndex);

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }


    @Override
    public abstract B deepCopyOf();
}
