package geometry;

import biuoop.DrawSurface;
import game.GameLevel;
import sprite.Sprite;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The type geometry.Line.
 */
public class Line implements Sprite {
    private Point start;
    private Point end;
    private Color color = Color.blue;

    /**
     * Instantiates a new geometry.Line.
     *
     * @param start the start
     * @param end   the end
     */
// constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new geometry.Line.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Length double.
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Middle point.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double middleX = (this.start.getX() + this.end.getX()) / 2.0;
        double middleY = (this.start.getY() + this.end.getY()) / 2.0;
        return new Point(middleX, middleY);
    }

    /**
     * Start point.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * End point.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }


    /**
     * Is intersecting boolean.
     *
     * @param other the line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (intersectionWith(other) == null) {
            return false;
        }
        return true;
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if ((this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start))) {
            return true;
        }
        return false;
    }


    /**
     * Intersection with point.
     *
     * @param other the other
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        Point point = intersectionPoint(other);
        if (point == null) {
            return null;
        }
        if (isPointInRange(point) && other.isPointInRange(point)) {
            return point;
        }
        return null;
    }


    /**
     * Intersection point point.
     *
     * @param other the other
     * @return the intersection point between 2 geometry lines and null if there is not
     */
    private Point intersectionPoint(Line other) {
        //info
        Double m1 = findM();
        Double m2 = other.findM();
        Double b1 = findB();
        Double b2 = other.findB();
        double x = 0, y = 0;
        // parallels lines- m1==m2
        if (((m1 != null) && (m2 != null) && (m1.equals(m2))) || ((m1 == null) && (m2 == null))) {
            if ((b1 != null) && (b2 != null)) {
                //checking if different lines
                if (!b1.equals(b2)) {
                    return null;
                }
            }
            if (isPointInRange(this.start) && other.isPointInRange(this.start)) {
                return this.start;
            } else {
                return this.end;
            }
        }
        //normal lines
        if ((m1 != null) && (b1 != null) && (m2 != null) && (b2 != null) && (!m1.equals(m2))) {
            x = (double) (b2 - b1) / (m1 - m2);
            y = m1 * x + b1;
        }
        //one line has no incline
        if ((m1 == null) && (m2 != null)) {
            x = this.start.getX();
            y = m2 * x + b2;
        }
        if ((m2 == null) && (m1 != null)) {
            x = other.start.getX();
            y = m1 * x + b1;
        }
        return new Point(x, y);
    }

    /**
     * Find m double.
     *
     * @return the double
     */
    private Double findM() {
        if (this.start().getX() == this.end.getX()) {
            return null;
        }
        return (double) (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
    }

    /**
     * Find b double.
     *
     * @return the double
     */
    private Double findB() {
        if (findM() == null) {
            return null;
        }
        return this.start.getY() - findM() * this.start.getX();
    }

    /**
     * Is point in range boolean.
     *
     * @param point the point
     * @return the boolean
     */
    public boolean isPointInRange(Point point) {
        if (isXInRange(point) && isYInRange(point)) {
            return true;
        }
        return false;
    }

    /**
     * Is x in range boolean.
     *
     * @param point the point
     * @return the boolean
     */
    private boolean isXInRange(Point point) {
        if ((this.start.getX() <= ((int) point.getX()) + 1) && (((int) point.getX()) <= this.end.getX())) {
            return true;
        }
        if ((this.end.getX() <= ((int) point.getX()) + 1) && (((int) point.getX()) <= this.start.getX())) {
            return true;
        }
        return false;
    }

    /**
     * Is y in range boolean.
     *
     * @param point the point
     * @return the boolean
     */
    private boolean isYInRange(Point point) {
        if ((this.start.getY() <= ((int) point.getY()) + 1) && (((int) point.getY()) <= this.end.getY())) {
            return true;
        }
        if ((this.end.getY() <= ((int) point.getY()) + 1) && (((int) point.getY()) <= this.start.getY())) {
            return true;
        }
        return false;
    }

    /**
     * defines the string output.
     *
     * @return the string output.
     */
    public String toString() {
        return "start line: " + this.start() + " end line: " + this.end();
    }

    /**
     * Closest intersection to start of line point.
     * <p>
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rectangle the rectangle
     * @return the point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rectangle) {
        List<Point> intersectionPoints = rectangle.intersectionPoints(this);
        if (intersectionPoints.isEmpty()) {
            return null;
        }
        Point point = ((LinkedList<Point>) intersectionPoints).removeLast();
        Point minPoint = point;
        double minDistance = this.start.distance(point);
        while (!intersectionPoints.isEmpty()) {
            point = ((LinkedList<Point>) intersectionPoints).removeLast();
            if (minDistance > this.start.distance(point)) {
                minDistance = this.start.distance(point);
                minPoint = point;
            }
        }
        return minPoint;
    }

    /**
     * Set color.
     *
     * @param c the c
     */
    public void setColor(Color c) {
        color = c;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY());
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
