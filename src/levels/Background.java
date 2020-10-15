/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-18
 */
package levels;

import biuoop.DrawSurface;
import game.GameLevel;
import gamesprites.Sprite;
import geometry.DrawableShape;

import java.awt.Color;
import java.util.List;

/**
 * Background.
 */
public class Background implements Sprite {
    private Color backgroundColor;
    private List<DrawableShape> elements;

    /**
     * Instantiates a new Background.
     *
     * @param backgroundColor the background color
     * @param elements        the elements of the background
     */
    public Background(Color backgroundColor, List<DrawableShape> elements) {
        this.backgroundColor = backgroundColor;
        this.elements = elements;
    }

    @Override
    public void drawOn(DrawSurface d) {
        int gInfoHeight = GameLevel.INFO_HEIGHT;
        int gFrameHeight = GameLevel.FRAME_HEIGHT;
        // first, draw background color
        d.setColor(backgroundColor);
        d.fillRectangle(gInfoHeight, gFrameHeight, d.getWidth() - gFrameHeight,
                d.getHeight() - gInfoHeight);
        // then, draw lines / rectangle / circles
        if (elements != null) {
            for (DrawableShape element : elements) {
                element.draw(d);
            }
        }
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void removeFromGame(GameLevel g) {
        // no need
    }
}
