package geometry;

import biuoop.DrawSurface;
import game.GameLevel;
import sprite.Sprite;

import java.awt.Color;

/**
 * The type Circle.
 */
public class Circle implements Sprite {
    private int x;
    private int y;
    private int radius;
    private Color color = Color.blue;
    private Boolean fill;

    /**
     * Instantiates a new Circle.
     *
     * @param center the center
     * @param radius the radius
     * @param fill   the fill
     */
    public Circle(Point center, int radius, Boolean fill) {
        this.x = (int) center.getX();
        this.y = (int) center.getY();
        this.radius = radius;
        this.fill = fill;
    }

    /**
     * Set color.
     *
     * @param c the c
     */
    public void setColor(Color c) {
        this.color = c;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawCircle(this.x, this.y, this.radius);
        if (this.fill) {
            d.fillCircle(this.x, this.y, this.radius);
        }
    }

    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void addToGame(GameLevel g) {
        return;
    }
}
