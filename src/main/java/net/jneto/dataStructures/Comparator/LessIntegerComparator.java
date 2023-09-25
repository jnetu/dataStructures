package net.jneto.dataStructures.Comparator;

/**
 * EXAMPLE USAGE - Integer
 * Priority: Less value more Priority
 */
public class LessIntegerComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer t1, Integer t2) {
        if (t1 < t2) {
            return 1;
        } else if (t1 > t2) {
            return -1;
        }
        return 0;
    }
}
