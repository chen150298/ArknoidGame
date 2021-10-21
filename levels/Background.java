package levels;

import biuoop.DrawSurface;
import game.GameLevel;
import sprite.Sprite;

import java.util.List;

/**
 * The type Background.
 */
public class Background implements Sprite {
    private List<Sprite> sprites;

    /**
     * Instantiates a new Background.
     *
     * @param sprites the sprites
     */
    public Background(List<Sprite> sprites) {
        this.sprites = sprites;
    }

    @Override
    public void drawOn(DrawSurface d) {
        List<Sprite> copy = this.sprites;
        for (Sprite s : copy) {
            s.drawOn(d);
        }
    }

    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void addToGame(GameLevel g) {
        return;
    }
}
