package levels;

import game.GameLevel;
import geometry.Circle;
import geometry.Point;
import sprite.Block;
import sprite.Sprite;
import sprite.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Level three.
 */
public class LevelThree implements LevelInformation {

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

    private int numberOfBalls;
    private List<Velocity> ballVelocities = new ArrayList<>();
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks = new ArrayList<>();
    private int numberOfBlocksToRemove = 0;

    /**
     * Instantiates a new Level three.
     */
    public LevelThree() {
        //number of balls
        numberOfBalls = 3;
        //balls velocity
        ballVelocities.add(new Velocity(-5, -2));
        ballVelocities.add(new Velocity(-4, -3));
        ballVelocities.add(new Velocity(3, -2));
        //paddle speed
        paddleSpeed = 10;
        //paddle width
        paddleWidth = 250;
        //level name
        levelName = "level 3";
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
     * Generate blocks.
     */
    public void generateBlocks() {
        Color randomColor;
        for (int j = 0; j < 5; j++) {
            randomColor = randomColor().brighter();
            for (int i = 1; i <= 12 - j; i++) {
                Block block = new Block(new Point(WIDTH - BORDER_WIDTH - i * 50,
                        WIDTH / 10 + j * 20), 50, 20);
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
        //background
        //background color
        Block b = new Block(new Point(0, 0), WIDTH, HEIGHT);
        b.setColor(new Color(20, 145, 36));
        objects.add(b);
        //create the tower
        Block tower = new Block(new Point(WIDTH / 8, WIDTH / 2), WIDTH / 4, HEIGHT + 10);
        tower.setColor(Color.black);
        objects.add(tower);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                Block window = new Block(new Point(WIDTH / 8 + 15 + (j * 20) + (j * 10),
                        WIDTH / 2 + 10 + (i * 40) + (i * 10)), 20, 40);
                window.setColor(Color.white);
                objects.add(window);
            }
        }
        //
        Block steeple1 = new Block(new Point(WIDTH / 8 + 50, WIDTH / 2 - 30), 100, 30);
        steeple1.setColor(Color.darkGray);
        objects.add(steeple1);
        Block steeple2 = new Block(new Point(WIDTH / 8 + 95, WIDTH / 2 - 160), 10, 130);
        steeple2.setColor(Color.gray);
        objects.add(steeple2);
        //
        Point center = new Point(WIDTH / 8 + 100, WIDTH / 2 - 170);
        Circle circle1 = new Circle(center, 15, true);
        circle1.setColor(new Color(255, 149, 27));
        objects.add(circle1);
        Circle circle2 = new Circle(center, 10, true);
        circle2.setColor(new Color(255, 215, 116));
        objects.add(circle2);
        Circle circle3 = new Circle(center, 5, true);
        circle3.setColor(new Color(255, 255, 19));
        objects.add(circle3);
        return new Background(objects);
    }
}
