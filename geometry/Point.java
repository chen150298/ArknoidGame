package geometry;

/**
 * The type geometry.Point.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Instantiates a new geometry.Point.
     *
     * @param x the x
     * @param y the y
     */
// constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance double.
     *
     * @param other the other
     * @return the the distance of this point to the other point
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(other.getX() - this.x, 2) + Math.pow(other.getY() - this.y, 2));
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if ((this.x == other.getX()) && (this.y == other.getY())) {
            return true;
        }
        return false;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return this.y;
    }

    /**
     * defines the string output.
     *
     * @return the string output.
     */
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

}
