/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-01
 */
package gamesprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * this class creates a list of all the sprites in the game.
 */
public class SpriteCollection {
    //class fields
    private List<Sprite> sprites;

    /**
     * constructor.
     */
    public SpriteCollection() {
        //initializes the list of sprites
        this.sprites = new ArrayList<>();
    }

    /**
     * this method adds a sprite to the sprites list.
     *
     * @param s sprite to be added
     */
    public void addSprite(Sprite s) {
        //adds a sprite to the array list
        this.sprites.add(s);
    }

    /**
     * this method removes a sprite from the sprites list.
     *
     * @param s the sprite to be removed
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * call time passed on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < sprites.size(); i++) {
            this.sprites.get(i).timePassed();
        }
    }

    /**
     * call drawOn on all sprites.
     *
     * @param d DrawSurface the surface to be drawn on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s: this.sprites) {
            s.drawOn(d);
        }
    }
}
