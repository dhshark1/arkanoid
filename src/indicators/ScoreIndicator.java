/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-01
 */
package indicators;

import biuoop.DrawSurface;
import game.GameLevel;
import gamesprites.Sprite;
import other.Counter;

import java.awt.Color;

/**
 * this class indicates the score in the game.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Constructor.
     *
     * @param score the score of the game
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }


    @Override
    public void drawOn(DrawSurface d) {
        //write the score in the top left corner in black
        int fontSize = 16;
        d.setColor(Color.BLACK);
        d.drawText(30, 50,
                "Score: " + score.getValue(), fontSize);
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
