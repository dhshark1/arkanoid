/**
 * @author Daniel Haber 322230020
 * @version 1.6
 * @since 2020-06-01
 */
package other;
/**
 * this class represents a counter object in the game.
 */
public class Counter {
    private int value;

    /**
     * Constructor.
     *
     * @param value the value
     */
    public Counter(int value) {
        this.value = value;
    }

    /**
     * Increase.
     * add number to current count.
     *
     * @param number the number to add
     */
    public void increase(int number) {
        value += number;
    }

    /**
     * Decrease.
     * subtract number from current count.
     *
     * @param number the number to subtract
     */
    public void decrease(int number) {
        value -= number;
    }

    /**
     * Gets value of the counter.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}