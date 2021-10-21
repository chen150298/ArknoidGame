package game;

import animations.AnimationRunner;
import animations.EndScreen;
import animations.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import sprite.Counter;

import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private GUI gui;
    private Counter score;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar the ar
     * @param ks the ks
     * @param g  the g
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI g) {
        animationRunner = ar;
        keyboardSensor = ks;
        gui = g;
        score = new Counter();
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean win = true;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, keyboardSensor, animationRunner, gui, score);
            level.initialize();
            while (level.blocksLeft() > 0 && level.ballsLeft() > 0) {
                level.run();
            }
            if (level.ballsLeft() == 0) {
                win = false;
                break;
            }
        }
        animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
                new EndScreen(win, score)));
        gui.close();
    }
}
