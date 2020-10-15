/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-01
 */
package gamesprites;

import game.Game;
import geometry.Ball;
import hitlisteners.HitListener;
import other.Counter;


/**
 * in charge of removing blocks and updating number of blocks counter.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructor.
     *
     * @param game       the game level
     * @param remainingBlocks the remaining blocks
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitsCounter() == 0) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(game);
            remainingBlocks.decrease(1);
        }
    }
}