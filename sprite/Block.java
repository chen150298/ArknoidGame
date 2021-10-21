package sprite;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import listeners.HitListener;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * The type sprite.Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    /**
     * The constant DEFAULT_COLOR.
     */
    public static final Color DEFAULT_COLOR = Color.black;
    /**
     * The constant DEFAULT_COVER_COLOR.
     */
    public static final Color DEFAULT_COVER_COLOR = Color.black;

    private Rectangle block;
    private Color color;
    private List<HitListener> hitListeners = new ArrayList<>();

    /**
     * Instantiates a new sprite.Block.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Block(Point upperLeft, double width, double height) {
        this.block = new Rectangle(upperLeft, width, height);
        this.color = DEFAULT_COLOR;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        if (block.up().isPointInRange(collisionPoint) || block.down().isPointInRange(collisionPoint)) {
            dy = (-1) * dy;
        }
        if (block.left().isPointInRange(collisionPoint) || block.right().isPointInRange(collisionPoint)) {
            dx = (-1) * dx;
        }
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    /**
     * Sets color.
     *
     * @param c the color
     */
    public void setColor(Color c) {
        this.color = c;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        Point upperLeft = getCollisionRectangle().getUpperLeft();
        int width = (int) getCollisionRectangle().getWidth();
        int height = (int) getCollisionRectangle().getHeight();
        d.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), width, height);
        d.setColor(DEFAULT_COVER_COLOR);
        d.drawRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), width, height);
    }

    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Remove from gameLevel.
     *
     * @param gameLevel the gameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * notify when a hit event.
     *
     * @param hitter the ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        if (hitListeners.contains(hl)) {
            hitListeners.remove(hl);
        }
    }
}
