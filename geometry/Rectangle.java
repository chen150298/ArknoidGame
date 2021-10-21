package geometry;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The type geometry.Rectangle.
 */
public class Rectangle {
    /**
     * The constant DEFAULT_COLOR.
     */
    public static final Color DEFAULT_COLOR = Color.BLACK;

    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Instantiates a new geometry.Rectangle.
     * <p>
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Up line.
     *
     * @return the line
     */
    public Line up() {
        Point start = getUpperLeft();
        Point end = new Point(getUpperLeft().getX() + getWidth(), getUpperLeft().getY());
        return new Line(start, end);
    }

    /**
     * Down line.
     *
     * @return the line
     */
    public Line down() {
        Point start = new Point(getUpperLeft().getX(), getUpperLeft().getY() + getHeight());
        Point end = new Point(getUpperLeft().getX() + getWidth(), getUpperLeft().getY() + getHeight());
        return new Line(start, end);
    }

    /**
     * Right line.
     *
     * @return the line
     */
    public Line right() {
        Point start = new Point(getUpperLeft().getX() + getWidth(), getUpperLeft().getY());
        Point end = new Point(getUpperLeft().getX() + getWidth(), getUpperLeft().getY() + getHeight());
        return new Line(start, end);
    }

    /**
     * Left line.
     *
     * @return the line
     */
    public Line left() {
        Point start = getUpperLeft();
        Point end = new Point(getUpperLeft().getX(), getUpperLeft().getY() + getHeight());
        return new Line(start, end);
    }

    /**
     * Intersection points list.
     *
     * @param line the line
     * @return a (possibly empty) List of intersection points with the specified line.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> points = new LinkedList<>();
        if (line.isIntersecting(up())) {
            points.add(line.intersectionWith(up()));
        }
        if (line.isIntersecting(down())) {
            points.add(line.intersectionWith(down()));
        }
        if (line.isIntersecting(right())) {
            points.add(line.intersectionWith(right()));
        }
        if (line.isIntersecting(left())) {
            points.add(line.intersectionWith(left()));
        }
        return points;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left.
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * defines the string output.
     *
     * @return the string output.
     */
    public String toString() {
        return "upper left point: " + getUpperLeft() + " width: " + getWidth() + " height: " + getHeight();
    }
}


