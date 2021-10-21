import animations.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameFlow;
import levels.LevelOne;
import levels.LevelTwo;
import levels.LevelThree;
import levels.LevelFour;
import levels.LevelInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Ass 6 game.
 */
public class Ass6Game {
    /**
     * The constant WIDTH.
     */
    public static final int WIDTH = 800;
    /**
     * The Height.
     */
    public static final int HEIGHT = 600;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("game", WIDTH, HEIGHT);
        AnimationRunner ar = new AnimationRunner(gui);
        KeyboardSensor ks = gui.getKeyboardSensor();
        //creating levels array
        List<LevelInformation> levels = new ArrayList<>();
        //with arguments
        if (args.length != 0) {
            for (String level : args) {
                try {
                    int number = Integer.parseInt(level);
                    if (number == 1) {
                        levels.add(new LevelOne());
                    }
                    if (number == 2) {
                        levels.add(new LevelTwo());
                    }
                    if (number == 3) {
                        levels.add(new LevelThree());
                    }
                    if (number == 4) {
                        levels.add(new LevelFour());
                    }
                } catch (Exception e) {
                    //do nothing
                    System.out.print("");
                }
            }
        }
        //no arguments
        if (levels.size() == 0) {
            levels.add(new LevelOne());
            levels.add(new LevelTwo());
            levels.add(new LevelThree());
            levels.add(new LevelFour());
        }
        //game flow
        GameFlow gameFlow = new GameFlow(ar, ks, gui);
        gameFlow.runLevels(levels);
    }
}
