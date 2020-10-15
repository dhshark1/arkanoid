/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-01
 */
package gamesprites;

import game.GameLevel;
import geometry.Ball;
import hitlisteners.HitListener;
import other.Counter;


/**
 * in charge of removing blocks and updating number of blocks counter.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Constructor.
     *
     * @param gameLevel       the game level
     * @param remainingBlocks the remaining blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitsCounter() == 0) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(gameLevel);
            remainingBlocks.decrease(1);
        }
    }
}