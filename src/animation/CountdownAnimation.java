/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-18
 */
package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import gamesprites.SpriteCollection;

import java.awt.Color;

/**
 * animation that countdown before each turn starts.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private int dynamicCount;


    /**
     * constructor.
     *
     * @param numOfSeconds the num of seconds the animation will last
     * @param countFrom    countdown from countFrom to 1
     * @param gameScreen   gameScreen to display in the background
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.dynamicCount = countFrom;
    }

    /**
     * display each number between countFrom to 1, then GO.
     * each number appears on the screen for (numOfSeconds / countFrom) seconds.
     *
     * @param d the game drawSurface
     */
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        d.setColor(Color.BLACK);
        if (dynamicCount > 0) {
            d.drawText(d.getWidth() / 2, d.getHeight() / 2,
                    String.valueOf(dynamicCount), 32);
        } else if (dynamicCount == 0) {
            d.drawText(d.getWidth() / 2, d.getHeight() / 2,
                    "Go", 32);
        }
        Sleeper sleeper = new Sleeper();
        if (dynamicCount != countFrom) {
            sleeper.sleepFor((long) (numOfSeconds * 1000) / countFrom);
        }
        dynamicCount -= 1;
    }

    /**
     * stop the animation after "GO" is displayed.
     *
     * @return the stopping condition
     */
    public boolean shouldStop() {
        return dynamicCount < 0;
    }
}