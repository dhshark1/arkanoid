/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-18
 */
package animation;
import biuoop.DrawSurface;

/**
 * this is the animation interface.
 */
public interface Animation {
    /**
     * this method runs one frame of an animation object.
     *
     * @param d the drawsurface the animation runs on
     */
    void doOneFrame(DrawSurface d);

    /**
     * this method indicates when the animation should stop.
     *
     * @return true or false whether animation should stop
     */
    boolean shouldStop();
}
