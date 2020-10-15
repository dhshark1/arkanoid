/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-01
 */
package geometry;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class defines the geometry.Rectangle object.
 */
public class Rectangle implements DrawableShape {
    //fields for each rectangle
    private Point upperLeft;
    private double width;
    private double height;
    private Color color;
    private boolean isFilled;

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
        this.color = createColor();
    }

    /**
     * this method creates a random color with rgb.
     *
     * @return a new Color type color
     */
    public static Color createColor() {
        //creating new random color
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        return new Color(r, g, b);
    }

    /**
     * return a possibly empty list of intersection points.
     * the list can also be empty
     *
     * @param line the line intersecting with all the rectangle sides.
     * @return List<geometry.Point> array of intersections with rectangle lines.
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
     * @return geometry.Point upperLeft
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * this method returns the upper right point of the rectangle.
     *
     * @return geometry.Point upperRight
     */
    public Point getUpperRight() {
        return new Point(this.upperLeft.getX() + width, this.upperLeft.getY());
    }

    /**
     * this method returns the lower right point of the rectangle.
     *
     * @return geometry.Point lowerRight
     */
    public Point getLowerRight() {
        return new Point(this.upperLeft.getX() + width, this.upperLeft.getY() + height);
    }

    /**
     * this method returns the lower left point of the rectangle.
     *
     * @return geometry.Point lowerLeft
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
     * @return geometry.Line the line that starts at the upperLeft point and ends
     * at the upperRight point
     */
    public Line topLine() {
        return new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.upperLeft.getX() + width, this.upperLeft.getY());
    }

    /**
     * this method returns the left line of the rectangle.
     *
     * @return geometry.Line the line that starts at the upperLeft point and ends
     * at the lowerLeft point
     */
    public Line leftLine() {
        return new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.upperLeft.getX(), this.upperLeft.getY() + height);
    }

    /**
     * this method returns the bottom line of the rectangle.
     *
     * @return geometry.Line the line that starts at the lowerLeft point and ends
     * at the lowerRight point
     */
    public Line bottomLine() {
        return new Line(this.upperLeft.getX(), this.upperLeft.getY() + height,
                this.upperLeft.getX() + width, this.upperLeft.getY() + height);
    }

    /**
     * this method returns the right line of the rectangle.
     *
     * @return geometry.Line the line that starts at the upperRight point and ends
     * at the lowerRight point
     */
    public Line rightLine() {
        return new Line(this.upperLeft.getX() + width, this.upperLeft.getY(),
                this.upperLeft.getX() + width, this.upperLeft.getY() + height);
    }

    /**
     * this method returns an array of lines that form the rectangle.
     *
     * @return geometry.Line[] the lines of the rectangle
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

    /**
     * set rectangle's shape.
     *
     * @param recColor the color of the rectangle
     */
    public void setColor(Color recColor) {
        this.color = recColor;
    }

    /**
     * set isFilled member- true if the rectangle is filled, false if not.
     *
     * @param filled the boolean that indicates if filled or not
     */
    public void setFilled(boolean filled) {
        isFilled = filled;
    }

    @Override
    public void draw(DrawSurface d) {
        d.setColor(color);
        if (isFilled) {
            d.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(),
                    (int) width, (int) height);
        } else {
            d.drawRectangle((int) upperLeft.getX(), (int) upperLeft.getY(),
                    (int) width, (int) height);
        }
    }
}
