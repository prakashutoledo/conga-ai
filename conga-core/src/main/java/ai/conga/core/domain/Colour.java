package ai.conga.core.domain;

public enum Colour {
    NONE("None"," ") {
        @Override
        public Colour nextTurn() {
            return this;
        }
    },

    BLACK("Black","B") {
        @Override
        public Colour nextTurn() {
            return WHITE;
        }
    },

    WHITE("White", "W") {
        @Override
        public Colour nextTurn() {
            return BLACK;
        }
    };

    private String textId;
    private String abbreviation;

    Colour(String textId, String abbreviation) {
        this.textId = textId;
        this.abbreviation = abbreviation;
    }

    public String getTextId() {
        return textId;
    }
    
    public abstract Colour nextTurn();

    @Override
    public String toString() {
        return this.abbreviation;
    }
}
