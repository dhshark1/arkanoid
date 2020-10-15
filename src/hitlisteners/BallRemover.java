/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-01
 */
package hitlisteners;

import game.GameLevel;
import gamesprites.Block;
import geometry.Ball;
import other.Counter;

/**
 * in charge of removing balls, and updating an available-balls counter.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * constructor.
     *
     * @param gameLevel      the game level of this ballRemover
     * @param remainingBalls the counter of remainingBalls
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.addHitListener(this);
        hitter.removeFromGame(gameLevel);
        remainingBalls.decrease(1);
    }

}