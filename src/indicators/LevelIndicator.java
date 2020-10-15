/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-18
 */
package indicators;

import biuoop.DrawSurface;
import game.GameLevel;
import gamesprites.Sprite;
import levels.LevelInformation;

import java.awt.Color;

/**
 * this class indicates the score in the game.
 */
public class LevelIndicator implements Sprite {
    private LevelInformation level;

    /**
     * Constructor.
     *
     * @param level the score of the game
     */
    public LevelIndicator(LevelInformation level) {
        this.level = level;
    }


    @Override
    public void drawOn(DrawSurface d) {
        //write the level in the top left corner in black
        int fontSize = 16;
        d.setColor(Color.BLACK);
        d.drawText(30, 75,
                "Level: " + level.levelName(), fontSize);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}