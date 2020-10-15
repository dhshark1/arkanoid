/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-18
 */
package geometry;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * geometric circle.
 */
public class Circle implements DrawableShape {
    private Point center;
    private int radius;
    private boolean isFilled;
    private Color color;

    /**
     * constructor.
     *
     * @param center   the center of the circle
     * @param radius   the radius of the circle
     * @param isFilled indicates if the circle is filled or not
     * @param color    the color of the circle
     */
    public Circle(Point center, int radius, boolean isFilled, Color color) {
        this.center = center;
        this.radius = radius;
        this.isFilled = isFilled;
        this.color = color;
    }

    @Override
    public void draw(DrawSurface d) {
        d.setColor(color);
        if (isFilled) {
            d.fillCircle((int) center.getX(), (int) center.getY(), radius);
        } else {
            d.drawCircle((int) center.getX(), (int) center.getY(), radius);
        }
    }
}