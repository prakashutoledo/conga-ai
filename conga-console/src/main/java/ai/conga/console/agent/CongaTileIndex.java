package ai.conga.console.agent;

import java.util.HashMap;
import java.util.Map;

public enum CongaTileIndex {
    INVALID(-1, -1),
    ZERO_ZERO(0, 0),
    ZERO_ONE(0, 1),
    ZERO_TWO(0, 2),
    ZERO_THREE(0, 3),
    ONE_ZERO(1, 0),
    ONE_ONE(1, 1),
    ONE_TWO(2, 2),
    ONE_THREE(1, 3),
    TWO_ZERO(2, 0),
    TWO_ONE(2, 1),
    TWO_TWO(2, 2),
    TWO_THREE(2, 3),
    THREE_ZERO(3, 0),
    THREE_ONE(3, 1),
    THREE_TWO(3, 2),
    THREE_THREE(3, 3);

    private static final Map<String, CongaTileIndex> CONGA_TILE_INDEX_MAP = new HashMap<>();

    static {
        for(CongaTileIndex index : CongaTileIndex.values()) {
            CONGA_TILE_INDEX_MAP.put(String.format("%d%d",index.getRowIndex(), index.getColumnIndex()), index);
        }
    }

    private final int rowIndex;
    private final int columnIndex;

    CongaTileIndex(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public CongaTileIndex from(int rowIndex, int columnIndex) {
        return CONGA_TILE_INDEX_MAP.getOrDefault(String.format("%d,%d", rowIndex, columnIndex), INVALID);
    }
}
