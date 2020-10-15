import biuoop.DrawSurface;

/**
 * this is the sprite interface.
 * it requires all sprites to have these methods.
 */
public interface Sprite {

    /**
     * this method draws the sprites on the screen.
     *
     * @param d the DrawSurface to be drawn on
     */
    void drawOn(DrawSurface d);

    /**
     * this method moves the sprites.
     */
    void timePassed();
}