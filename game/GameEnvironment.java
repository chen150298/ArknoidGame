package game;

import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import sprite.Collidable;
import sprite.CollisionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * The type game.GameLevel environment.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Instantiates a new game.GameLevel environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Gets collidables.
     *
     * @return the collidables
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
// add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Gets closest collision.
     * <p>
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Rectangle block;
        Point collisionPoint;
        Point minCollisionPoint = null;
        Collidable minCollisionObject = null;
        double minDistance = Math.pow(1000, 1000);
        List<Collidable> collidableCollection = new ArrayList<>(this.collidables);
        //finding closest
        for (Collidable c : collidableCollection) {
            block = c.getCollisionRectangle();
            if (trajectory.closestIntersectionToStartOfLine(block) == null) {
                continue;
            }
            collisionPoint = trajectory.closestIntersectionToStartOfLine(block);
            if (minDistance > trajectory.start().distance(collisionPoint)) {
                minDistance = trajectory.start().distance(collisionPoint);
                minCollisionPoint = collisionPoint;
                minCollisionObject = c;
            }
        }
        //returning collision info
        if (minCollisionPoint == null) {
            return null;
        }
        return new CollisionInfo(minCollisionPoint, minCollisionObject);
    }
}
