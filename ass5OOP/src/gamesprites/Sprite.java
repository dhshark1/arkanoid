/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-01
 */
package gamesprites;

import biuoop.DrawSurface;
import game.Game;

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

    /**
     * remove sprite from the game given.
     *
     * @param g the game to remove sprite from
     */
    void removeFromGame(Game g);

    /**
     * add sprite to the game given.
     *
     * @param g the game to add sprite to
     */
    void addToGame(Game g);
}