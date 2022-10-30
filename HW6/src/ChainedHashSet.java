import java.util.LinkedList;

public class ChainedHashSet<E> implements AmhHashSet<E> {

    private LinkedList<E>[] storage;
    private int             m;
    private int             n;
    private int             collisions;
    
    public ChainedHashSet (int capacity) {
        storage = new LinkedList[capacity];
    } // ChainedHashSet ()

    public boolean insert (E key) {
        int f = hash(key) % storage.length;
        if (storage[f] == null){
            storage[f] = new LinkedList<E>();
            storage[f].add(key);
        } else{// the array index is not empty
            if (storage[f].contains(key)) {
                return false;
            }
            storage[f].add(key);
            collisions++;
        }
        return true;

    } // insert ()

    public boolean lookup (E key) {
        int f = hash(key) % storage.length;
        if (storage[f] == null)
            return false;
        return storage[f].contains(key);
    } // lookup ()

    public boolean remove (E key) {
        int f = hash(key) % storage.length;
        if (storage[f] == null)
            return false;
        return storage[f].remove(key);
    }

    public int size () {
	return n;
    }

    public int getNumberCollisions () {

	return collisions;

    }

    private int hash (E key) {

	return key.hashCode();
	
    }

} // class ChainedHashSet
