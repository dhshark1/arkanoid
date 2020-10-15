/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-01
 */
package hitlisteners;

/**
 * this is the HitNotifier interface.
 * it requires all hitNotifiers to have these methods
 */
public interface HitNotifier {
    /**
     * this method adds a hitListener to hit events.
     *
     * @param hl hitListener to be added
     */
    void addHitListener(HitListener hl);

    /**
     * this method removes a hitListener to hit events.
     *
     * @param hl hitListener to be removed
     */
    void removeHitListener(HitListener hl);
}
