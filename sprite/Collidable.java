package sprite;

import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;

/**
 * The interface sprite.Collidable.
 */
public interface Collidable {

    /**
     * Gets collision rectangle.
     * <p>
     * Return the "collision shape" of the object.
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Hit velocity.
     * <p>
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param hitter          the hitter
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * Add to game.
     *
     * @param g the g
     */
    void addToGame(GameLevel g);
}
