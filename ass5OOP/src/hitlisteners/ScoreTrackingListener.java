/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-01
 */
package hitlisteners;

import gamesprites.Block;
import geometry.Ball;
import other.Counter;

/**
 * this class updates the counter when blocks get removed.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor.
     *
     * @param scoreCounter the counter for the score in the game
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * this method increases the score by 5 for each block removed.
     *
     * @param beingHit the block being hit
     * @param hitter the ball hitting the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}
