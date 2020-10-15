/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-04-19
 */

import java.util.List;

/**
 * A class that configures the object Line.
 */
public class Line {
    //constant used to compare double values
    static final double EPSILON = Math.pow(10, -10);
    //start point x coordinate
    private double x1;
    //start point y coordinate
    private double y1;
    //end point x coordinate
    private double x2;
    //end point y coordinate
    private double y2;

    /**
     * Constructor with configurable start and end points.
     *
     * @param start is the point where the line starts
     * @param end   is the point where the line ends
     */
    public Line(Point start, Point end) {
        //assigning each given coordinate to this line's coordinates
        this.x1 = start.getX();
        this.y1 = start.getY();
        this.x2 = end.getX();
        this.y2 = end.getY();
    }

    /**
     * Another constructor with different configurable parameters.
     * 4 coordinates instead of 2 points given
     *
     * @param x1 starting point x coordinate
     * @param y1 starting point y coordinate
     * @param x2 end point x coordinate
     * @param y2 end point y coordinate
     */
    public Line(double x1, double y1, double x2, double y2) {
        //assigning each given coordinate to this line's coordinates
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * A function that returns the length of the line.
     * it calculates the distance from the start to end point
     *
     * @return double value of the distance
     */
    public double length() {
        //calculate distance from start to end point
        return start().distance(end());
    }

    /**
     * A function that returns the middle point of a line.
     *
     * @return new Point that is the middle point
     */
    public Point middle() {
        //middle x coordinate
        double x = (this.x1 + this.x2) / 2;
        //middle y coordinate
        double y = (this.y1 + this.y2) / 2;
        return new Point(x, y);
    }

    /**
     * A function that returns the starting point of a line.
     *
     * @return new Point that is the starting point
     */
    public Point start() {
        //get starting coordinates of this line
        double x = this.x1;
        double y = this.y1;
        return new Point(x, y);
    }

    /**
     * A function that returns the end point of a line.
     *
     * @return new Point that is the end point
     */
    public Point end() {
        //get starting coordinates of this line
        double x = this.x2;
        double y = this.y2;
        return new Point(x, y);
    }

    /**
     * A function that determines if a line is vertical.
     *
     * @return 'true' if line is vertical, false otherwise
     */
    public boolean isVertical() {
        //are x values of end and beginning equal?
        return this.x1 == this.x2;
    }

    /**
     * A function that finds the slope of a line.
     * If line is vertical return max value of a double
     *
     * @return double value of the slope
     */
    public double findSlope() {
        //is the line a vertical line
        if (!this.isVertical()) {
            //if line isn't vertical calculate slope
            return ((this.y2 - this.y1) / (this.x2 - this.x1));
        }
        return Double.MAX_VALUE;
    }

    /**
     * A function that finds the y-int of a line.
     * This is done with the equation y=mx+b
     *
     * @param slope is the slope of this line
     * @return double value of the y-int
     */
    public double findYInt(double slope) {
        //is the line a vertical line
        if (!this.isVertical()) {
            //calculate y-int
            return (this.y1 - (slope * this.x1));
        }
        return Double.MAX_VALUE;
    }

    /**
     * This function finds the x coordinate of a potential intersection point.
     *
     * @param b1 this line's y-int
     * @param b2 other line's y-iny
     * @param m1 this line's slope
     * @param m2 other line's slope
     * @return double value of the x coordinate
     */
    public double findXCoordinate(double b1, double b2, double m1, double m2) {
        //equation to find x coordinate
        return (b2 - b1) / (m1 - m2);
    }

    /**
     * This function finds the y coordinate of a potential intersection point.
     *
     * @param m this line's slope
     * @param x intersection point x coordinate
     * @param b this line's y-int
     * @return double value of u coordinate
     */
    public double findYCoordinate(double m, double x, double b) {
        //equation to find y coordinate
        return (m * x + b);
    }

    /**
     * Function that determines if point lies on line segment.
     * this is done using the distance function
     *
     * @param p the point we are given to test the function
     * @return 'true' if point is on line, false otherwise
     */
    public boolean pointInSegment(Point p) {
        /*
        return true if the sum of the distance
        from point to start and point to end
        is equal to the distance from start to end
         */
        return (this.start().distance(p) + this.end().distance(p)
                - this.start().distance(end()) <= EPSILON);
    }

    /**
     * A function that determines if two lines intersect.
     *
     * @param other the other line to determine if it intersects with this line
     * @return 'true' if lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        Point point = this.intersectionWith(other);
        return (point != null && this.pointInSegment(point) && other.pointInSegment(point));
    }

    /**
     * A function that returns intersection point of two lines.
     *
     * @param other the other line that is maybe intersecting with this line
     * @return new Point if lines intersect, null otherwise
     */
    public Point intersectionWith(Line other) {
        //find slope of this line
        double m1 = this.findSlope();
        //find slope of other line
        double m2 = other.findSlope();
        //find y-int of this line
        double b1 = this.findYInt(m1);
        //find y-int of other line
        double b2 = other.findYInt(m2);
        //return intersection in case this line is vertical
        if (this.isVertical()) {
            double y = m2 * this.x1 - m2 * other.x1 + other.y1;
            //x coordinate is constant and return y coordinate
            return new Point(this.x1, y);
            //return intersection in case other line is vertical
        } else if (other.isVertical()) {
            double y = m1 * other.x1 - m1 * this.x1 + this.y1;
            //x coordinate is constant and return y coordinate
            return new Point(other.x1, y);
        } else {
                /*
                find x and y coordinates of intersection point
                return them as a new point
                 */
            double x = findXCoordinate(b1, b2, m1, m2);
            double y = findYCoordinate(m1, x, b1);
            return new Point(x, y);
        }
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect the rectangle to be examined for intersection with the line
     * @return Point the closest point out of the array of points intersected
     * with rectangle
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //get array of all intersection points
        List<Point> interPoints = rect.intersectionPoints(this);
        //find closest intersection point
        return getClosestPoint(interPoints);
    }

    /**
     * return the closest intersection point (to the start point of this line)
     * from list of points given.
     *
     * @param points list of points to find the closest from
     * @return the closest point or null if there isn't one
     */
    public Point getClosestPoint(List<Point> points) {
        //if there are no intersection points
        if (points.isEmpty()) {
            return null;
        }
        Point minPoint = null;
        double minDistance = Double.MAX_VALUE;
        /*
        this loop checks for all the points in the array
        what is the distance between the start of the line to the point
        if it is the minimum distance so far, update the min distance variable
         */
        for (Point p : points) {
            double potentialMin = this.start().distance(p);
            if (potentialMin < minDistance) {
                minDistance = potentialMin;
                minPoint = p;
            }
        }
        return minPoint;
    }

    /**
     * A function that checks if lines are equal.
     *
     * @param other the other line to compare coordinates to
     * @return 'true' if lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        //initialize start and end points assuming they aren't initialized
        Point start1 = new Point(this.x1, this.y1);
        Point end1 = new Point(this.x2, this.y2);
        Point start2 = new Point(other.x1, other.y1);
        Point end2 = new Point(other.x2, other.y2);
        //determine if start and end are the same in both lines
        return ((start1.distance(start2) == 0) && (end1.distance(end2) == 0));
    }
}
