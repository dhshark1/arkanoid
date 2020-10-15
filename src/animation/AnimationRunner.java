/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-18
 */
package animation;
import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * this class is responsible for running the animation.
 */
public class AnimationRunner {
    private GUI gui;
    private final int framesPerSecond = 60;

    /**
     * constructor.
     *
     * @param gui the GUI of the game.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
    }

    /**
     * this method runs the animation.
     *
     * @param animation animation to be run
     */
    public void run(Animation animation) {
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
