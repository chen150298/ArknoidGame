package sprite;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The type sprite.Sprite collection.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Instantiates a new sprite.Sprite collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Get sprites list.
     *
     * @return the list
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Notify all time passed.
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spriteCollection = new ArrayList<>(this.sprites);
        for (Sprite s : spriteCollection) {
            s.timePassed();
        }
    }

    /**
     * Draw all on.
     * call drawOn(d) on all sprites.
     *
     * @param d the d
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> spriteCollection = new ArrayList<>(this.sprites);
        for (Sprite s : spriteCollection) {
            s.drawOn(d);
        }
    }
}
