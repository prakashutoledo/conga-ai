package ai.conga.core.domain;

/**
 *
 */
public enum MoveDirection {
    EAST("East"),
    NORTH_EAST("North East"),
    NORTH("North"),
    NORTH_WEST("North West"),
    WEST("West"),
    SOUTH_WEST("South West"),
    SOUTH("South"),
    SOUTH_EAST("South EAST"),
    INVALID("Invalid");

    private String textId;

    MoveDirection(String textId) {
        this.textId = textId;
    }

    private String getTextId() {
        return textId;
    }
}
