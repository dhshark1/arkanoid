/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-01
 */
package hitlisteners;

import game.Game;
import gamesprites.Block;
import geometry.Ball;
import other.Counter;

/**
 * in charge of removing balls, and updating an available-balls counter.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * constructor.
     *
     * @param game      the game level of this ballRemover
     * @param remainingBalls the counter of remainingBalls
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.addHitListener(this);
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }

}