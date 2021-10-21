package sprite;

/**
 * The type Counter.
 */
public class Counter {
    private int counter;

    /**
     * Instantiates a new Counter.
     */
    public Counter() {
        counter = 0;
    }

    /**
     * add number to current count.
     *
     * @param number the number
     */
    public void increase(int number) {
        counter = counter + number;
    }

    /**
     * subtract number from current count.
     *
     * @param number the number
     */
    public void decrease(int number) {
        counter = counter - number;
    }

    /**
     * get current count.
     *
     * @return the value
     */
    public int getValue() {
        return this.counter;
    }
}
