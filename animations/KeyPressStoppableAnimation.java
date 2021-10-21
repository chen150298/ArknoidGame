package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    /**
     * The Stop.
     */
    private boolean stop = false;
    // If the key has been pressed.
    private boolean keyHasBeenPressed = false;
    // If the key is pressed right now.
    private boolean isAlreadyPressed = true;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    - the function gets the keyboard of the game.
     * @param key       - the function gets the key which should press to exit.
     * @param animation - the function holds animation member.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (sensor.isPressed(key) && !isAlreadyPressed) {
            keyHasBeenPressed = true;
            isAlreadyPressed = false;
        }
        if (!sensor.isPressed(key)) {
            isAlreadyPressed = false;
            keyHasBeenPressed = false;
        }
        if (keyHasBeenPressed) {
            // For next generation.
            keyHasBeenPressed = false;
            stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }
}