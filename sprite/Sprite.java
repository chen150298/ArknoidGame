package sprite;

import biuoop.DrawSurface;
import game.GameLevel;

/**
 * The interface sprite.Sprite.
 */
public interface Sprite {

    /**
     * Draw on.
     * draw the sprite to the screen
     *
     * @param d the d
     */
    void drawOn(DrawSurface d);

    /**
     * Time passed.
     * notify the sprite that time has passed
     */
    void timePassed();

    /**
     * Add to game.
     *
     * @param g the g
     */
    void addToGame(GameLevel g);
}
