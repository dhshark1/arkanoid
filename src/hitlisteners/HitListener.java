/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-01
 */
package hitlisteners;

import gamesprites.Block;
import geometry.Ball;

/**
 * this is the hitListener interface.
 * all hitListeners must have these methods
 */
public interface HitListener {
    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the Ball that's doing the hitting.

    /**
     * this method is called whenever the beingHit is hit.
     * the hitter parameter is the Ball that;s doing the hitting.
     *
     * @param beingHit the block being hit
     * @param hitter the ball hitting the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
