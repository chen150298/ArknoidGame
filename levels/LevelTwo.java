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
import java.util.Random;

/**
 * The type Level two.
 */
public class LevelTwo implements LevelInformation {
    /**
     * The Height.
     */
    static final int HEIGHT = GameLevel.HEIGHT;
    /**
     * The Width.
     */
    static final int WIDTH = GameLevel.WIDTH;
    /**
     * The Border width.
     */
    static final int BORDER_WIDTH = GameLevel.BORDER_WIDTH;
    /**
     * The Ball speed.
     */
    static final int BALL_SPEED = 3;
    /**
     * The Block height.
     */
    static final int BLOCK_HEIGHT = 30;
    /**
     * The Number of blocks.
     */
    static final int NUMBER_OF_BLOCKS = 10;

    private int numberOfBalls;
    private List<Velocity> ballVelocities = new ArrayList<>();
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks = new ArrayList<>();
    private int numberOfBlocksToRemove = 0;

    /**
     * Instantiates a new Level two.
     */
    public LevelTwo() {
        //number of balls
        numberOfBalls = 10;
        //balls velocity
        generateVelocities();
        //paddle speed
        paddleSpeed = 10;
        //paddle width
        paddleWidth = 550;
        //level name
        levelName = "level 2";
        //background
        background = createBackground();
        //blocks and number of blocks to remove
        generateBlocks();
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
     * Generate velocities.
     */
    public void generateVelocities() {
        double angle = 90 + 180 / (numberOfBalls + 1);
        for (int i = 0; i < numberOfBalls; i++) {
            ballVelocities.add(Velocity.fromAngleAndSpeed(angle, BALL_SPEED));
            angle += 180 / (numberOfBalls + 1);
        }
    }

    /**
     * Generate blocks.
     */
    public void generateBlocks() {
        double blocksWidth = (WIDTH - 2 * BORDER_WIDTH) / NUMBER_OF_BLOCKS;
        double currentStart = BORDER_WIDTH;
        for (int i = 0; i < NUMBER_OF_BLOCKS; i++) {
            Block block = new Block(new Point(currentStart, HEIGHT / 2), blocksWidth, BLOCK_HEIGHT);
            currentStart += blocksWidth;
            block.setColor(randomColor().brighter());
            blocks.add(block);
            numberOfBlocksToRemove++;
        }
    }

    /**
     * Random color color.
     *
     * @return the color
     */
    public Color randomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }

    /**
     * Create background sprite.
     *
     * @return the sprite
     */
    public Sprite createBackground() {
        List<Sprite> sprites = new ArrayList<>();
        Point center = new Point(WIDTH / 5, HEIGHT / 4);
        //create the lines
        for (int i = 0; i < 100; i++) {
            Line line = new Line(center, new Point(BORDER_WIDTH + i * 7.4, HEIGHT / 2));
            line.setColor(Color.ORANGE);
            sprites.add(line);
        }
        //the Sun
        int r = 242, g = 187, b = 114;
        int radius = 40 + 20 * 2;
        for (int i = 0; i < 3; i++) {
            Circle circle = new Circle(center, radius, true);
            circle.setColor(new Color(r, g, b));
            sprites.add(circle);
            g += 20;
            b -= 20;
            radius -= 10;
        }
        return new Background(sprites);
    }
}
