package animations;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprite.Sprite;
import sprite.SpriteCollection;

import java.awt.Color;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private Sprite background;
    private SpriteCollection gameScreen;
    private Boolean stop;
    private int currentCount;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     * @param background   the background
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, Sprite background) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.background = background;
        stop = false;
        currentCount = this.countFrom;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();
        background.drawOn(d);
        gameScreen.drawAllOn(d);
        d.setColor(Color.RED);
        if (currentCount > 0) {
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, currentCount + "...", 32);
        } else {
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, "GO!", 32);
        }
        //skip the first waiting
        if (currentCount != countFrom) {
            sleeper.sleepFor((int) (numOfSeconds * 1000) / (countFrom + 1));
        }
        //nothing left to count
        if (currentCount == -1) {
            this.stop = true;
        }
        currentCount--;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}