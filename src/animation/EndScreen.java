/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-18
 */
package animation;

import biuoop.DrawSurface;
import levels.Background;
import other.Counter;

import java.awt.Color;

/**
 * Once the game is over (either the player run out of lives or managed to
 * clear all the levels), the animation will display the final score and
 * announce winning / losing.
 */
public class EndScreen implements Animation {
    private boolean closeGame;
    private int score;
    private Counter numOfBalls;
    private Background background;

    /**
     * constructor.
     *
     * @param score      the score of the player
     */
    public EndScreen(int score, Counter numOfBalls) {
        this.closeGame = false;
        this.score = score;
        this.numOfBalls = numOfBalls;
        this.background = createBackground();
    }

    /**
     * the background of endScreen.
     * @return the background of endScreen
     */
    private Background createBackground() {
        return new Background(new Color(0, 50, 50), null);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        background.drawOn(d);
        d.setColor(Color.BLACK);
        if (numOfBalls.getValue() == 0) {
            d.drawText(160, d.getHeight() / 2 - 100, "Game Over. Press space to exit.", 32);

        } else {
            d.drawText(160, d.getHeight() / 2 - 100, "You Win! Press space to exit.", 32);
        }
        d.drawText(240, d.getHeight() / 2, "Your score is: " + score, 28);
    }

    @Override
    public boolean shouldStop() {
        return closeGame;
    }
}
