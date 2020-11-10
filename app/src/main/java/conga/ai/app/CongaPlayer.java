package conga.ai.app;

import conga.ai.api.Copy;
import conga.ai.api.Player;
import conga.ai.api.Colour;

/**
 *
 */
public class CongaPlayer extends Player<Colour> implements Copy<CongaPlayer> {
    private Colour colour;

    public CongaPlayer(Colour colour) {
        this.colour = colour;
    }

    @Override
    public Colour getPlayerColour() {
        return colour;
    }

    @Override
    public CongaPlayer deepCopyOf() {
        if(this == null) {
            return null;
        }
        CongaPlayer congaPlayer = new CongaPlayer(this.colour);
        return congaPlayer;
    }
}
