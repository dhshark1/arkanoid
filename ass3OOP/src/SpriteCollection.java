import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * this class creates a list of all the sprites in the game.
 */
public class SpriteCollection {
    //class fields
    private final List<Sprite> sprites;

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
     * call time passed on all sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite s: this.sprites) {
            s.timePassed();
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
