package levels;

import game.GameLevel;
import geometry.Circle;
import geometry.Line;
import geometry.Point;
import sprite.Block;
import sprite.Sprite;
import sprite.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level one.
 */
public class LevelOne implements LevelInformation {
    /**
     * The Height.
     */
    static final int HEIGHT = GameLevel.HEIGHT;
    /**
     * The Width.
     */
    static final int WIDTH = GameLevel.WIDTH;
    /**
     * The Block width.
     */
    static final int BLOCK_WIDTH = 40;
    /**
     * The Block height.
     */
    static final int BLOCK_HEIGHT = BLOCK_WIDTH;

    private int numberOfBalls;
    private List<Velocity> ballVelocities = new ArrayList<>();
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks = new ArrayList<>();
    private int numberOfBlocksToRemove;

    /**
     * Instantiates a new Level one.
     */
    public LevelOne() {
        //number of balls
        numberOfBalls = 1;
        //balls velocity
        ballVelocities.add(new Velocity(0, -6));
        //paddle speed
        paddleSpeed = 10;
        //paddle width
        paddleWidth = 150;
        //level name
        levelName = "level 1";
        //background
        this.background = createBackground();
        //blocks
        Block block = new Block(new Point(WIDTH / 2 - BLOCK_WIDTH / 2, HEIGHT / 3), BLOCK_WIDTH, BLOCK_HEIGHT);
        block.setColor(Color.cyan);
        blocks.add(block);
        //number of blocks to remove
        numberOfBlocksToRemove = 1;
    }

    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return ballVelocities;
    }

    @Override
    public int paddleSpeed() {
        return paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return paddleWidth;
    }

    @Override
    public String levelName() {
        return levelName;
    }

    @Override
    public Sprite getBackground() {
        return background;
    }

    @Override
    public List<Block> blocks() {
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return numberOfBlocksToRemove;
    }

    /**
     * Create background sprite.
     *
     * @return the sprite
     */
    public Sprite createBackground() {
        List<Sprite> sprites = new ArrayList<>();
        //background color
        Block b = new Block(new Point(0, 0), WIDTH, HEIGHT);
        b.setColor(Color.black);
        sprites.add(b);
        //circles
        Point center = new Point(WIDTH / 2, HEIGHT / 3 + BLOCK_HEIGHT / 2);
        for (int i = 1; i <= 3; i++) {
            Circle c = new Circle(center, BLOCK_WIDTH + i * 30, false);
            c.setColor(Color.BLUE);
            sprites.add(c);
        }
        //lines
        Line line1 = new Line(center.getX(), center.getY() + 6 * 30, center.getX(), center.getY() - 6 * 30);
        line1.setColor(Color.BLUE);
        sprites.add(line1);
        Line line2 = new Line(center.getX() - 6 * 30, center.getY(), center.getX() + 6 * 30, center.getY());
        line2.setColor(Color.BLUE);
        sprites.add(line2);
        return new Background(sprites);
    }

}
