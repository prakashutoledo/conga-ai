package conga.ai.api;

/**
 *
 */
public enum Colour {
    NONE("None"),
    BLACK("Black"),
    WHITE("White");

    private String textId;

    Colour(String textId) {
        this.textId = textId;
    }

    public String getTextId() {
        return textId;
    }
}
