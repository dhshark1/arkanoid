/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-18
 */
package geometry;

import biuoop.DrawSurface;

/**
 * interface for shapes you can draw.
 */
public interface DrawableShape {

    /**
     * the draw method. draw this shape on drawSurface d.
     *
     * @param d the drawsurface to draw on
     */
    void draw(DrawSurface d);
}
