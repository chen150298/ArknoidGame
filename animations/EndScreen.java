package animations;

import biuoop.DrawSurface;
import sprite.Counter;

import java.awt.Color;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {
    private boolean stop;
    private boolean win;
    private Counter score;

    /**
     * Instantiates a new End screen.
     *
     * @param win   the win
     * @param score the score
     */
    public EndScreen(boolean win, Counter score) {
        this.stop = false;
        this.win = win;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (win) {
            d.setColor(Color.black);
            d.drawText(175, d.getHeight() / 2 - 200, "Congratulations You Win! ", 40);
            d.setColor(new Color(132, 225, 197));
            d.drawText(178, d.getHeight() / 2 - 200, "Congratulations You Win! ", 40);
            d.setColor(new Color(132, 225, 197));
            d.drawText(230, d.getHeight() / 2, "Your score is: " + score.getValue(), 40);
            d.setColor(Color.black);
            d.drawText(233, d.getHeight() / 2, "Your score is: " + score.getValue(), 40);
        } else {
            d.setColor(Color.black);
            d.drawText(175, d.getHeight() / 2 - 200, "You Lose! Game Over.", 40);
            d.setColor(new Color(132, 225, 197));
            d.drawText(178, d.getHeight() / 2 - 200, "You Lose! Game Over.", 40);
            d.setColor(new Color(132, 225, 197));
            d.drawText(230, d.getHeight() / 2, "Your score is: " + score.getValue(), 40);
            d.setColor(Color.black);
            d.drawText(233, d.getHeight() / 2, "Your score is: " + score.getValue(), 40);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
