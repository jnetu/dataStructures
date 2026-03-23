package net.jneto.dataStructures;

/**
 * This interface represents a Set data structure that stores unique objects.
 * It extends DataStructure and adds common Set operations.
 *
 * @param <ITEM> The type of objects to be stored in the Set.
 */
public interface Set<ITEM> extends DataStructure<ITEM> {

    /**
     * Checks if the set contains the specified object.
     *
     * @param item The object to be checked.
     * @return True if the object exists in the set, false otherwise.
     */
    boolean contains(ITEM item);

    /**
     * Removes the specified object from the set.
     *
     * @param item The object to be removed.
     * @return True if the object was removed, false otherwise.
     */
    boolean remove(ITEM item);
}
