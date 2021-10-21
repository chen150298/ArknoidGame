package game;

import animations.Animation;
import animations.AnimationRunner;
import animations.CountdownAnimation;
import animations.KeyPressStoppableAnimation;
import animations.PauseScreen;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;

import biuoop.DrawSurface;
import geometry.Point;
import levels.LevelInformation;
import sprite.Ball;
import sprite.Block;
import listeners.BallRemover;
import listeners.BlockRemover;
import sprite.Collidable;
import sprite.Counter;
import listeners.HitListener;
import sprite.Paddle;
import sprite.ScoreIndicator;
import listeners.ScoreTrackingListener;
import sprite.Sprite;
import sprite.SpriteCollection;


/**
 * The type game.GameLevel.
 */
public class GameLevel implements Animation {
//gui
    /**
     * The constant WIDTH.
     */
    public static final int WIDTH = 800;
    /**
     * The Height.
     */
    public static final int HEIGHT = 600;
    //static final GUI gui = new GUI("game", WIDTH, HEIGHT);
//border
    /**
     * The constant BORDER_WIDTH.
     */
    public static final int BORDER_WIDTH = 20;
    /**
     * The Border color.
     */
    static final Color BORDER_COLOR = Color.lightGray;
//balls
    /**
     * The BALLS_COLOR.
     */
    static final Color BALLS_COLOR = Color.RED;
    /**
     * The Radius.
     */
    static final int BALLS_RADIUS = 5;
    //paddle
    /**
     * The Default paddle height.
     */
    public static final int PADDLE_HEIGHT = 10;

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blocks;
    private Counter balls;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation level;
    private GUI gui;

    /**
     * Instantiates a new game.GameLevel.
     *
     * @param levelInformation the level information
     * @param ks               the ks
     * @param ar               the ar
     * @param g                the g
     * @param score            the score
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor ks, AnimationRunner ar, GUI g, Counter score) {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        blocks = new Counter();
        balls = new Counter();
        this.score = score;
        runner = ar;
        keyboard = ks;
        level = levelInformation;
        gui = g;
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initialize.
     * Initialize a new game: create the Blocks and sprite.Ball (and sprite.Paddle) and add them to the game.
     */
    public void initialize() {
        createBallsOnTopOfPaddle(); // or a similar method
        //create border
        HitListener ballRemover = new BallRemover(this, balls);
        createBorder(ballRemover);
        //create blocks
        createPaddle();
        generateBlocks();
        //score
        Sprite scoreIndicator = new ScoreIndicator(score);
        scoreIndicator.addToGame(this);
    }

    /**
     * create border.
     *
     * @param ballRemover the hit listener.
     */
    private void createBorder(HitListener ballRemover) {
        //death spots
        Block dieUp = new Block(new Point(0, 0), WIDTH, BORDER_WIDTH / 2);
        dieUp.setColor(BORDER_COLOR);
        dieUp.addHitListener(ballRemover);
        dieUp.addToGame(this);

        Block dieLeft = new Block(new Point(0, BORDER_WIDTH), BORDER_WIDTH / 2, HEIGHT - BORDER_WIDTH);
        dieLeft.setColor(BORDER_COLOR);
        dieLeft.addHitListener(ballRemover);
        dieLeft.addToGame(this);

        Block dieRight = new Block(new Point(WIDTH - BORDER_WIDTH / 2, BORDER_WIDTH), BORDER_WIDTH / 2,
                HEIGHT - BORDER_WIDTH);
        dieRight.setColor(BORDER_COLOR);
        dieRight.addHitListener(ballRemover);
        dieRight.addToGame(this);

        //down- falling
        Block down = new Block(new Point(0, HEIGHT), WIDTH, BORDER_WIDTH);
        down.setColor(BORDER_COLOR);
        down.addHitListener(ballRemover);
        down.addToGame(this);

        //creating border
        Block up = new Block(new Point(0, 0), WIDTH, BORDER_WIDTH);
        up.setColor(BORDER_COLOR);
        up.addToGame(this);
        Block left = new Block(new Point(0, BORDER_WIDTH), BORDER_WIDTH, HEIGHT - BORDER_WIDTH);
        left.setColor(BORDER_COLOR);
        left.addToGame(this);
        Block right = new Block(new Point(WIDTH - BORDER_WIDTH, BORDER_WIDTH), BORDER_WIDTH, HEIGHT - BORDER_WIDTH);
        right.setColor(BORDER_COLOR);
        right.addToGame(this);
    }

    /**
     * create blocks.
     */
    private void generateBlocks() {
        HitListener blockRemover = new BlockRemover(this, blocks);
        HitListener gameScore = new ScoreTrackingListener(score);
        for (Block block : level.blocks()) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(gameScore);
        }
        blocks.increase(level.numberOfBlocksToRemove());
    }

    /**
     * create the paddle.
     */
    private void createPaddle() {
        Paddle paddle = new Paddle(level.paddleSpeed(), level.paddleWidth());
        paddle.addToGame(this);
        paddle.setKeyboard(gui);
    }

    /**
     * create balls on top of paddle.
     */
    private void createBallsOnTopOfPaddle() {
        Point ballsCenter = new Point(WIDTH / 2, HEIGHT - BORDER_WIDTH - PADDLE_HEIGHT - BALLS_RADIUS);
        for (int i = 0; i < level.numberOfBalls(); i++) {
            Ball ball = new Ball(ballsCenter, BALLS_RADIUS, BALLS_COLOR);
            ball.setVelocity(level.initialBallVelocities().get(i));
            ball.addToGame(this);
            ball.setGameEnvironment(environment);
        }
        balls.increase(level.numberOfBalls());
    }

    /**
     * Run.
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, sprites, level.getBackground())); // countdown before turn starts.
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        if (environment.getCollidables().contains(c)) {
            environment.getCollidables().remove(c);
        }
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        if (sprites.getSprites().contains(s)) {
            sprites.getSprites().remove(s);
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        level.getBackground().drawOn(d);
        this.sprites.drawAllOn(d);
        if (blocks.getValue() == 0) {
            score.increase(100);
            this.running = false;
        }
        if (balls.getValue() == 0) {
            this.running = false;
        }
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
        d.setColor(Color.black);
        d.drawText(10, BORDER_WIDTH - 2, "level: " + level.levelName(), 20);
    }

    /**
     * Blocks left int.
     *
     * @return the int
     */
    public int blocksLeft() {
        return blocks.getValue();
    }

    /**
     * Balls left int.
     *
     * @return the int
     */
    public int ballsLeft() {
        return balls.getValue();
    }

}
