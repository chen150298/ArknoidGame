package sprite;

import biuoop.KeyboardSensor;
import biuoop.GUI;

import java.awt.Color;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

/**
 * The type sprite.Paddle.
 */
public class Paddle implements Sprite, Collidable {
    /**
     * The Width.
     */
    static final int WIDTH = GameLevel.WIDTH;
    /**
     * The Height.
     */
    static final int HEIGHT = GameLevel.HEIGHT;
    /**
     * The Border width.
     */
    static final int BORDER_WIDTH = GameLevel.BORDER_WIDTH;

    /**
     * The Default color.
     */
    static final Color DEFAULT_COLOR = Color.orange;
    /**
     * The Default paddle height.
     */
    public static final int PADDLE_HEIGHT = GameLevel.PADDLE_HEIGHT;

    private KeyboardSensor keyboard;
    private Rectangle player;
    private Color color;
    private int speed;

    /**
     * Instantiates a new sprite.Paddle.
     *
     * @param speed the speed
     * @param width the width
     */
    public Paddle(int speed, int width) {
        Point defaultPoint = new Point(WIDTH / 2 - width / 2, HEIGHT - BORDER_WIDTH - PADDLE_HEIGHT);
        this.player = new Rectangle(defaultPoint, width, PADDLE_HEIGHT);
        this.color = DEFAULT_COLOR;
        this.speed = speed;
    }

    /**
     * Sets keyboard.
     *
     * @param gui the gui
     */
    public void setKeyboard(GUI gui) {
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * move left.
     */
    private void moveLeft() {
        double x = getCollisionRectangle().getUpperLeft().getX();
        if (getCollisionRectangle().getUpperLeft().getX() > 0 + BORDER_WIDTH) {
            double newX = player.getUpperLeft().getX() - speed;
            if (newX < 0 + BORDER_WIDTH) {
                newX = 0 + BORDER_WIDTH;
            }
            double y = player.getUpperLeft().getY();
            double width = player.getWidth();
            double height = player.getHeight();
            this.player = new Rectangle(new Point(newX, y), width, height);
        }
    }

    /**
     * move right.
     */
    private void moveRight() {
        if (getCollisionRectangle().getUpperLeft().getX() + player.getWidth() < WIDTH - BORDER_WIDTH) {
            double newX = player.getUpperLeft().getX() + speed;
            if (newX + player.getWidth() > WIDTH - BORDER_WIDTH) {
                newX = WIDTH - BORDER_WIDTH - player.getWidth();
            }
            double y = player.getUpperLeft().getY();
            double width = player.getWidth();
            double height = player.getHeight();
            this.player = new Rectangle(new Point(newX, y), width, height);
        }
    }

    // sprite.Sprite
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
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
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.player;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double paddleWidth = getCollisionRectangle().up().length();
        double y = getCollisionRectangle().getUpperLeft().getY();
        //spliting into 5 sections
        //section 1
        Point point1 = getCollisionRectangle().getUpperLeft();
        Point point2 = new Point(point1.getX() + (0.2 * paddleWidth), y);
        Line area1 = new Line(point1, point2);
        //section 2
        point1 = point2;
        point2 = new Point(point1.getX() + (0.2 * paddleWidth), y);
        Line area2 = new Line(point1, point2);
        //section 3
        point1 = point2;
        point2 = new Point(point1.getX() + (0.2 * paddleWidth), y);
        Line area3 = new Line(point1, point2);
        //section 4
        point1 = point2;
        point2 = new Point(point1.getX() + (0.2 * paddleWidth), y);
        Line area4 = new Line(point1, point2);
        //section 5
        point1 = point2;
        point2 = new Point(point1.getX() + (0.2 * paddleWidth), y);
        Line area5 = new Line(point1, point2);
        //calculating new velocity
        Velocity newVelocity = currentVelocity;
        if (getCollisionRectangle().up().isPointInRange(collisionPoint)) {
            //in what section the hit happened?
            if (area1.isPointInRange(collisionPoint)) {
                newVelocity = Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
            }
            if (area2.isPointInRange(collisionPoint)) {
                newVelocity = Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
            }
            if (area3.isPointInRange(collisionPoint)) {
                newVelocity = Velocity.fromAngleAndSpeed(0, currentVelocity.getSpeed());
            }
            if (area4.isPointInRange(collisionPoint)) {
                newVelocity = Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
            }
            if (area5.isPointInRange(collisionPoint)) {
                newVelocity = Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
            }
        }
        return new Velocity(newVelocity.getDx(), (-1) * newVelocity.getDy());
    }

    // Add this paddle to the game.
    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}