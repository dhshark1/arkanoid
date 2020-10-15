/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-18
 */
package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * this class implements a characteristic stoppable key press animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param keyboard the keyboard sensor
     * @param key            the key
     * @param animation      the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboard, Animation animation, String key) {
        this.keyboardSensor = keyboard;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (keyboardSensor.isPressed(key)) {
            if (!isAlreadyPressed) {
                stop = true;
            }
        } else {
            isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }
}
