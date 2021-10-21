package animations;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Pause screen.
 */
public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * Instantiates a new Pause screen.
     */
    public PauseScreen() {
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.darkGray);
        d.fillCircle(420, 250, 130);
        d.setColor(new Color(31, 122, 113));
        d.fillCircle(420, 250, 120);
        d.setColor(new Color(0, 50, 30));
        d.fillCircle(420, 250, 110);
        d.setColor(Color.gray);
        d.fillCircle(420, 250, 109);
        d.setColor(Color.lightGray);
        d.fillCircle(420, 250, 108);
        d.setColor(new Color(132, 225, 197));
        d.fillRectangle(370, 200, 30, 110);
        d.fillRectangle(440, 200, 30, 110);
        d.drawText(240, d.getHeight() / 2 + 200, " press space to continue", 32);
        d.setColor(Color.black);
        d.drawText(242, d.getHeight() / 2 + 200, " press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}