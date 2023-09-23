package net.jneto.dataStructures;

/**
 * Comons methods used by data structures
 * @param <ITEM> The type of elements implemented.
 */
public class Common<ITEM> {


    /**
     * Default constructor to use not statics Common method
     */
    public Common(){
        //dont need initialization
    }

    /**
     *
     * The resize method is a common method used by data Structures
     * to resize the Array size of a structure
     * @param structure - data Structure typed
     * @param increase  - if True - increase Array, if false decrease Array
     * @return structure - new sized structure
     */
    public ITEM[] resize(ITEM[] structure, boolean increase) {
        if (structure == null) { // error
            return null;
        }
        if (increase) { //increase
            int newSize = structure.length * 2;
            ITEM[] aux = (ITEM[]) new Object[newSize];
            for (int i = 0; i < structure.length; i++) {
                aux[i] = structure[i];
            }
            structure = aux;
        } else { //decrease
            ITEM[] aux;
            int newSize;
            if (2 % structure.length == 0) {
                newSize = structure.length / 2 + 1;
            } else {
                newSize = structure.length / 2;
            }
            aux = (ITEM[]) new Object[newSize];
            for (int i = 0; i < aux.length; i++) {
                aux[i] = structure[i];
            }
            structure = (ITEM[]) new Object[newSize];
            for (int i = 0; i < aux.length; i++) {
                structure[i] = aux[i];
            }
        }
        return structure;
    }
}
