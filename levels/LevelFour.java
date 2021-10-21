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
 * The type Level four.
 */
public class LevelFour implements LevelInformation {

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
     * The Blocks in a row.
     */
    static final int BLOCKS_IN_A_ROW = 10;
    /**
     * The Block height.
     */
    static final int BLOCK_HEIGHT = 30;

    private int numberOfBalls;
    private List<Velocity> ballVelocities = new ArrayList<>();
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks = new ArrayList<>();
    private int numberOfBlocksToRemove = 0;

    /**
     * Instantiates a new Level four.
     */
    public LevelFour() {
        //number of balls
        numberOfBalls = 3;
        //balls velocity
        generateVelocities();
        //paddle speed
        paddleSpeed = 10;
        //paddle width
        paddleWidth = 100;
        //level name
        levelName = "level 4";
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
        double blocksWidth = (WIDTH - 2 * BORDER_WIDTH) / BLOCKS_IN_A_ROW;
        Color randomColor;
        for (int j = 0; j < 6; j++) {
            randomColor = randomColor().brighter();
            for (int i = 1; i <= BLOCKS_IN_A_ROW; i++) {
                Block block = new Block(new Point(WIDTH - BORDER_WIDTH - i * blocksWidth,
                        WIDTH / 10 + j * BLOCK_HEIGHT), blocksWidth, BLOCK_HEIGHT);
                block.setColor(randomColor);
                blocks.add(block);
                numberOfBlocksToRemove++;
            }
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
        List<Sprite> objects = new ArrayList<>();
        //background color
        Block b = new Block(new Point(0, 0), WIDTH, HEIGHT);
        b.setColor(new Color(29, 190, 255));
        objects.add(b);
        //rain&clouds 1
        Point center = new Point(WIDTH / 4, HEIGHT / 1.5);
        //rain
        for (int i = 0; i < 5; i++) {
            Line rain = new Line(new Point(center.getX() - (10 * i), center.getY()),
                    new Point(center.getX() - (30 * i), HEIGHT));
            rain.setColor(Color.WHITE);
            objects.add(rain);
        }
        //create the first cloud
        Circle circle1Cloud1 = new Circle(new Point(center.getX() - 30, center.getY()), 30, true);
        circle1Cloud1.setColor(new Color(140, 140, 140));
        objects.add(circle1Cloud1);
        Circle circle2Cloud1 = new Circle(new Point(center.getX() - 50, center.getY()), 30, true);
        circle2Cloud1.setColor(new Color(110, 110, 110));
        objects.add(circle2Cloud1);
        Circle circle3Cloud1 = new Circle(new Point(center.getX() - 30, center.getY() + 15), 30, true);
        circle3Cloud1.setColor(new Color(140, 140, 140));
        objects.add(circle3Cloud1);
        Circle circle4Cloud1 = new Circle(new Point(center.getX() + 15, center.getY() + 20), 30, true);
        circle4Cloud1.setColor(new Color(156, 156, 156));
        objects.add(circle4Cloud1);
        Circle circle5Cloud1 = new Circle(center, 30, true);
        circle5Cloud1.setColor(new Color(156, 156, 156));
        objects.add(circle5Cloud1);
        //rain&clouds 2
        center = new Point(WIDTH / 1.2, HEIGHT / 1.3);
        //rain
        for (int i = 0; i < 5; i++) {
            Line rain = new Line(new Point(center.getX() - (10 * i), center.getY()),
                    new Point(center.getX() - (20 * i), HEIGHT));
            rain.setColor(Color.WHITE);
            objects.add(rain);
        }
        //create the second cloud
        Circle circle1Cloud2 = new Circle(new Point(center.getX() - 20, center.getY()), 30, true);
        circle1Cloud2.setColor(new Color(140, 140, 140));
        objects.add(circle1Cloud2);
        Circle circle2Cloud2 = new Circle(new Point(center.getX() - 30, center.getY()), 30, true);
        circle2Cloud2.setColor(new Color(110, 110, 110));
        objects.add(circle2Cloud2);
        Circle circle3Cloud2 = new Circle(new Point(center.getX() - 40, center.getY() + 15), 30, true);
        circle3Cloud2.setColor(new Color(140, 140, 140));
        objects.add(circle3Cloud2);
        Circle circle4Cloud2 = new Circle(new Point(center.getX() + 20, center.getY() + 20), 30, true);
        circle4Cloud2.setColor(new Color(156, 156, 156));
        objects.add(circle4Cloud2);
        Circle circle5Cloud2 = new Circle(new Point(center.getX(), center.getY() + 5), 30, true);
        circle5Cloud2.setColor(new Color(156, 156, 156));
        objects.add(circle5Cloud2);
        return new Background(objects);
    }
}
