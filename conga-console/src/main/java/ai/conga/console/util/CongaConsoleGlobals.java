package ai.conga.console.util;

import ai.conga.console.game.CongaTile;

public final class CongaConsoleGlobals {
    public static final String DIVIDER_TILE_LANE = "|";
    public static final String UPPER_TILE_LANE = "*****************************";
    public static final String EMPTY_CHAR = "";
    public static final String NEW_LINE = "\n";
    public static final int CONGA_BOARD_ROW_SIZE = 4;
    public static final int CONGA_BOARD_COLUMN_SIZE = CONGA_BOARD_ROW_SIZE;
    public static final CongaTile INVALID_TILE = new CongaTile(0,0);

    private CongaConsoleGlobals() {
    }
}
