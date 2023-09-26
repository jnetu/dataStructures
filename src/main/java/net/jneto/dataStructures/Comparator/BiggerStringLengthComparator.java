package net.jneto.dataStructures.Comparator;

/**
 * EXAMPLE USAGE - String
 * Priority: More length more Priority
 */
public class BiggerStringLengthComparator implements Comparator<String> {
    @Override
    public int compare(String t1, String t2) {
        if (t1.length() < t2.length()) {
            return 1;
        } else if (t1.length() > t2.length()) {
            return -1;
        }
        return 0;
    }
}
