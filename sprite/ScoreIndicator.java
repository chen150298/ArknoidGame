package sprite;

import biuoop.DrawSurface;
import game.GameLevel;

import java.awt.Color;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    /**
     * The Width.
     */
    static final int WIDTH = GameLevel.WIDTH;
    /**
     * The Border width.
     */
    static final int BORDER_WIDTH = GameLevel.BORDER_WIDTH;
    /**
     * The Text color.
     */
    static final Color TEXT_COLOR = Color.black;

    private Counter score;

    /**
     * Instantiates a new Score indicator.
     *
     * @param score the score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(TEXT_COLOR);
        d.drawText(WIDTH / 2, BORDER_WIDTH - 2, "Score: " + score.getValue(), 20);
    }
}
