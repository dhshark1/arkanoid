import java.util.ArrayList;
import java.util.List;

/**
 * This class defines the Rectangle object.
 */
public class Rectangle {
    //fields for each rectangle
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * constructor.
     *
     * @param upperLeft start point on he rectangle
     * @param width width of the rectangle
     * @param height height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * return a possibly empty list of intersection points.
     * the list can also be empty
     *
     * @param line the line intersecting with all the rectangle sides.
     * @return List<Point> array of intersections with rectangle lines.
     */
    public List<Point> intersectionPoints(Line line) {
        //create a list with all of the sides forming the rectangle
        Line[] recLines = this.rectangleLines();
        List<Point> interPoints = new ArrayList<Point>();
        /*
        this loop check if the line intersects
        each line of the rectangle. if it does
        then add the intersection point to interPoints array
         */
        for (Line recLine : recLines) {
            if (recLine.isIntersecting(line)) {
                interPoints.add(recLine.intersectionWith(line));
            }
        }
        return interPoints;
    }

    /**
     * this method returns the width of the rectangle.
     *
     * @return double width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * this method returns the height of the rectangle.
     *
     * @return double height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * this method returns the starting point of the rectangle.
     *
     * @return Point upperLeft
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * this method returns the upper right point of the rectangle.
     *
     * @return Point upperRight
     */
    public Point getUpperRight() {
        return new Point(this.upperLeft.getX() + width, this.upperLeft.getY());
    }

    /**
     * this method returns the lower right point of the rectangle.
     *
     * @return Point lowerRight
     */
    public Point getLowerRight() {
        return new Point(this.upperLeft.getX() + width, this.upperLeft.getY() + height);
    }

    /**
     * this method returns the lower left point of the rectangle.
     *
     * @return Point lowerLeft
     */
    public Point getLowerLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY() + height);
    }

    /**
     * this method sets the starting point of the rectangle.
     *
     * @param newUpperLeft the upperLeft point of the rectangle
     */
    public void setUpperLeft(Point newUpperLeft) {
        this.upperLeft = newUpperLeft;
    }

    /**
     * this method returns the top line of the rectangle.
     *
     * @return Line the line that starts at the upperLeft point and ends
     * at the upperRight point
     */
    public Line topLine() {
        return new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.upperLeft.getX() + width, this.upperLeft.getY());
    }

    /**
     * this method returns the left line of the rectangle.
     *
     * @return Line the line that starts at the upperLeft point and ends
     * at the lowerLeft point
     */
    public Line leftLine() {
        return new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.upperLeft.getX(), this.upperLeft.getY() + height);
    }

    /**
     * this method returns the bottom line of the rectangle.
     *
     * @return Line the line that starts at the lowerLeft point and ends
     * at the lowerRight point
     */
    public Line bottomLine() {
        return new Line(this.upperLeft.getX(), this.upperLeft.getY() + height,
                this.upperLeft.getX() + width, this.upperLeft.getY() + height);
    }

    /**
     * this method returns the right line of the rectangle.
     *
     * @return Line the line that starts at the upperRight point and ends
     * at the lowerRight point
     */
    public Line rightLine() {
        return new Line(this.upperLeft.getX() + width, this.upperLeft.getY(),
                this.upperLeft.getX() + width, this.upperLeft.getY() + height);
    }

    /**
     * this method returns an array of lines that form the rectangle.
     *
     * @return Line[] the lines of the rectangle
     */
    public Line[] rectangleLines() {
        //initialize an array of lines with a size of 4
        Line[] lines = new Line[4];
        lines[0] = topLine();
        lines[1] = leftLine();
        lines[2] = bottomLine();
        lines[3] = rightLine();
        return lines;
    }
}
