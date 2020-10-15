/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-04-19
 */

/**
 * A class that creates the object Point.
 * The class supports basic requirements for points.
 */
public class Point {
    //the x and y coordinates of the point
    private double x;
    private double y;

    /**
     * constructor with configurable x and y coordinates.
     * @param x is the x coordinate
     * @param y is the y coordinate
     */
    public Point(double x, double y) {
        //initialize both coordinates
        this.x = x;
        this.y = y;
    }

    /**
     * return the distance of this point to the other point.
     *
     * @param other the other point we compare to
     * @return double value of the distance
     */
    public double distance(Point other) {
        /*using distance formula return the
        distance between this point and other point
         */
        return Math.sqrt((Math.pow((this.x - other.x), 2)
                + (Math.pow((this.y - other.y), 2))));
    }

    /**
     * a boolean function that checks if points are equal.
     *
     * @param other the other point we compare to
     * @return true is the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        /*return true if distance between
        this point and other point is zero
         */
        return (this.distance(other) == 0);
    }

    /**
     * a function that returns the x coordinate of the point.
     *
     * @return double value of x coordinate
     */
    public double getX() {
        //return x value of this point
        return this.x;
    }

    /**
     * a function that returns the y coordinate of the point.
     *
     * @return double value of y coordinate
     */
    public double getY() {
        //return y value of this point
        return this.y;
    }
}
