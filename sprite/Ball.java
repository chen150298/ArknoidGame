package sprite;

import biuoop.DrawSurface;
import game.GameEnvironment;
import game.GameLevel;
import geometry.Line;
import geometry.Point;

import java.awt.Color;

/**
 * The type sprite.Ball.
 */
public class Ball implements Sprite {
    /**
     * The Default velocity.
     */
    static final Velocity DEFAULT_VELOCITY = new Velocity(12, 10);

    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment = new GameEnvironment();

    /**
     * Instantiates a new sprite.Ball.
     *
     * @param center the center
     * @param r      the r
     * @param color  the color
     */
// constructors
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = DEFAULT_VELOCITY;
    }

    /**
     * Instantiates a new sprite.Ball.
     *
     * @param x     the x
     * @param y     the y
     * @param r     the r
     * @param color the color
     */
    public Ball(int x, int y, int r, Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * Gets x.
     *
     * @return the x
     */
// accessors
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets center.
     *
     * @return the center
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Gets obstacles.
     *
     * @return the obstacles
     */
    public GameEnvironment getObstacles() {
        return this.gameEnvironment;
    }

    /**
     * Sets game environment.
     *
     * @param g the game environment
     */
    public void setGameEnvironment(GameEnvironment g) {
        this.gameEnvironment = g;
    }

    /**
     * Draw on.
     *
     * @param surface the surface
     */
// draw the ball on the given DrawSurface
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    /**
     * Sets velocity.
     *
     * @param v the v
     */
//velocity actions
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Move one step.
     */
    public void moveOneStep() {
        //creating trajectory
        Point nextCenter = new Point(getX() + getVelocity().getDx(), getY() + getVelocity().getDy());
        Line trajectory = new Line(getCenter(), nextCenter);
        //checking collision with obstacles
        CollisionInfo collisionInfo = getObstacles().getClosestCollision(trajectory);
        //if there is no collision
        if (collisionInfo == null) {
            this.center = getVelocity().applyToPoint(getCenter());
        } else {
            //if there is a collision
            this.velocity = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), getVelocity());
            this.center = getVelocity().applyToPoint(getCenter());
        }
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Remove from gameLevel.
     *
     * @param gameLevel the gameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}
