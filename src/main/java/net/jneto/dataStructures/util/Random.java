package net.jneto.dataStructures.util;


/**
 * A simple random number generator using Linear Congruential Generator (LCG).
 */
public class Random {

    private long seed;

    // java.util.Random constants
    private static final long a = 25214903917L;  // Multiplier
    private static final long c = 11;            // Increment
    private static final long m = (1L << 48);    // 2^48, modulus

    /**
     * Constructs a new Random object with a given seed.
     *
     */
    public Random() {
        this.seed = (System.currentTimeMillis() ^ a) & (m - 1); // Ensure seed is within valid range
    }

    /**
     * Returns the next pseudo-random, uniformly distributed int value.
     *
     * @return The next pseudo-random non-negative int value.
     */
    public int nextInt() {
        seed = (a * seed + c) & (m - 1); // Generate the next seed value
        int result = (int)(seed >>> 16); // Return the upper bits as the random number

        // Make sure the result is non-negative
        return result >= 0 ? result : -result;
    }

    /**
     * Returns a pseudo-random, uniformly distributed int value between 0 (inclusive) and the specified value (exclusive).
     *
     * @param bound The upper bound (exclusive) for the random number.
     * @return A random int between 0 (inclusive) and bound (exclusive).
     * @throws IllegalArgumentException If bound is not positive.
     */
    public int nextInt(int bound) {
        if (bound <= 0) {
            throw new IllegalArgumentException("Bound must be positive");
        }
        return nextInt() % bound;
    }

    /**
     * Returns a pseudo-random, uniformly distributed double value between 0.0 and 1.0.
     *
     * @return A random double between 0.0 (inclusive) and 1.0 (exclusive).
     */
    public double nextDouble() {
        return (nextInt() & ((1L << 53) - 1)) / (double)(1L << 53);
    }

    /**
     * Returns the next pseudo-random, uniformly distributed boolean value.
     *
     * @return A random boolean value.
     */
    public boolean nextBoolean() {
        return nextInt() % 2 == 0;
    }

    /**
     * Sets the seed of this random number generator.
     *
     * @param seed The initial seed for the random number generator.
     */
    public void setSeed(long seed) {
        this.seed = (seed ^ a) & (m - 1);
    }
}
