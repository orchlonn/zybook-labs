import java.util.*;
import java.io.PrintWriter;

public class StaticSet<T> {
    private HashSet<T> set = new HashSet<>();

    // constructing an empty staticSet
    public StaticSet() {

    }

    // Constructing the static set by adding all the arrayList
    // items
    public StaticSet(ArrayList<T> items) {
        for (T item : items) {
            set.add(item);
        }
    }

    // Constructing the StaticSet by copying itemsToCopy's items
    public StaticSet(HashSet<T> itemsToCopy) {
        this.set = new HashSet<>(itemsToCopy);
    }

    public boolean contains(T item) {
        return set.contains(item);
    }

    public int getSize() {
        return set.size();
    }

    public void print(PrintWriter output, String separator, String prefix, String suffix) {
        output.write(prefix);
        boolean firstItem = false;
        for (T item : set) {
            if (firstItem) {
                output.write(item.toString());
                firstItem = false;
            } else {
                output.write(separator + item.toString());
            }
        }
        output.write(suffix);
    }

    /**
     * Returns the staticSet<T> containing each element from this set that is not
     * in the other set
     *
     * @param otherSet
     * @return
     */
    public StaticSet<T> difference(StaticSet<T> otherSet) {
        // creating a difference set
        HashSet<T> differenceSet = new HashSet<>();
        for (T item : set) {
            // if the otherset doesn't containt the item present in the current set
            // then add the item to the difference set
            if (!otherSet.contains(item)) {
                // add the item to the difference set
                differenceSet.add(item);
            }
        }

        // return a new static set object
        return new StaticSet<>(differenceSet);
    }

    /**
     * Returns a StaticSet<T> containing each element from this set that satisfies
     * the predicate
     * - If predicate(item) returns true (1), item satisfies the predicate
     * - If predicate(item) returns false (0), item doesn't satisfy the predicate
     *
     * @param predicate
     * @return
     */
    public StaticSet<T> filter(Predicate<T, Integer> predicate) {
        // creating a filtered hash set, based on the predicate
        HashSet<T> filteredHashSet = new HashSet<T>();
        for (T currentItem : set) {
            // if the predicate is true, it will return 1,
            // if it returns 1 then add the item to the filtered hash set
            if (predicate.predicate(currentItem) == 1) {
                filteredHashSet.add(currentItem);
            }
        }

        // return a new static set object
        return new StaticSet<>(filteredHashSet);
    }

    /**
     * Retursn a StaticSet<T> containing each element from this set is also in the
     * otherSet
     *
     * @param otherSet
     * @return
     */
    public StaticSet<T> intersection(StaticSet<T> otherSet) {
        // intersectionSet will store the items that are present both in current set
        // and the other set
        HashSet<T> intersectionSet = new HashSet<>();
        for (T item : set) {
            // if the otherSet contains the item, then add the
            // item to the intersectionSet
            if (otherSet.contains(item)) {
                intersectionSet.add(item);
            }
        }

        // return a new static set object
        return new StaticSet<>(intersectionSet);
    }

    /**
     * Calls mapMethod(item) for each item in this set and then adds the
     * returned item to a StaticSet<U>. After the mapMethod is called for each item
     * in this set, the StaticSet<U> object is returned
     *
     * @param <U>
     * @param mapMethod
     * @return
     */
    public <U> StaticSet<U> map(Predicate<T, U> mapMethod) {
        HashSet<U> mappedHashSet = new HashSet<>();

        // return a new StaticSet that contains the item as the result of
        // the mappedMethod
        for (T item : set) {
            U mappedItem = mapMethod.predicate(item);
            mappedHashSet.add(mappedItem);
        }
        return new StaticSet<>(mappedHashSet);
    }

    /**
     * Returns a StaticSet<T> containing every element from this set and
     * every element from otherSet
     */
    public StaticSet<T> union(StaticSet<T> otherSet) {

        // return the new StaticSet, that is the union of two sets
        // union means that items that are present either in one set
        // or in the other set
        HashSet<T> unionSet = new HashSet<>();

        // first adding the item from the current set to the unionSet
        for (T item : set) {
            unionSet.add(item);
        }

        // second adding the item from the otherSet to the unionSet
        for (T item : otherSet.set) {
            unionSet.add(item);
        }

        // return a new static set object
        return new StaticSet<>(unionSet);
    }
}
