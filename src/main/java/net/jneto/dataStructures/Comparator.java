package net.jneto.dataStructures;

public interface Comparator<ITEM>{
    int compare(ITEM t1, ITEM t2);
}

/**
 * EXAMPLE USAGE - String
 * Priority: More length more Priority
 */
class BiggerStringLengthComparator implements Comparator<String> {
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
/**
 * EXAMPLE USAGE - String
 * Priority: More length more Priority
 */
class LessStringLengthComparator implements Comparator<String> {
    @Override
    public int compare(String t1, String t2) {
        if (t1.length() < t2.length()) {
            return -1;
        } else if (t1.length() > t2.length()) {
            return 1;
        }
        return 0;
    }
}

/**
 * EXAMPLE USAGE - Integer
 * Priority: Bigger value more Priority
 */
class BiggerIntegerComparator implements Comparator<Integer> {
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
/**
 * EXAMPLE USAGE - Integer
 * Priority: Less value more Priority
 */
class LessIntegerComparator implements Comparator<Integer> {
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