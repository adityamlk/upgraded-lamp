package com.adityamlk.codelibrary.datastructure.collection;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

/**
 * Represents an ArrayList implementation. Maintains set capacity, auto-resizes by configured factor to accommodate
 * additional elements.
 * <p>
 * Insertion is O(1) despite resizing due to amortized insertion. Inserts to the end of the collection.
 * Deletion is O(N) where one loop will find the index and another will then overwrite the value.
 * Search is O(N).
 *
 * @param <T> Generic data type supported by the list.
 */
@Log4j2
@EqualsAndHashCode
public class MyArrayList<T> {

    private static final int DEFAULT_INITIAL_CAPACITY = 0;

    private static final int DEFAULT_RESIZE_FACTOR = 2;

    /*
     * Tracks the multiplier used for resizing the internal collection.
     */
    @NonNull
    private final Integer resizeFactor;

    /*
     * Tracks how many values have been written to the internal collection. Provides an index into the next write and a
     * difference from the length of the internal collection, which represents empty indices as well.
     */
    @NonNull
    private Integer size;

    /*
     * Stores the values inserted into the data structure. Grows over time to accommodate additional values.
     */
    private Object[] internalCollection;

    /**
     * Default constructor.
     */
    public MyArrayList() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_RESIZE_FACTOR);
    }

    /**
     * Constructor that sets the initial capacity of the data structure.
     *
     * @param initialCapacity Value used for setting the initial size of the data structure.
     */
    public MyArrayList(@NonNull final Integer initialCapacity) {
        this(initialCapacity, DEFAULT_RESIZE_FACTOR);
    }

    /**
     * Constructor that sets the initial capacity and resize factor of the data structure.
     *
     * @param initialCapacity Value used for setting the initial size of the data structure.
     * @param resizeFactor    Value used for specifying how much the data structure will grow when it is full.
     */
    public MyArrayList(@NonNull final Integer initialCapacity, @NonNull final Integer resizeFactor) {
        if (1 < resizeFactor) {
            this.resizeFactor = resizeFactor;
        } else {
            throw new IllegalArgumentException("Expected resize factor of at least 2.");
        }

        if (0 > initialCapacity) {
            throw new IllegalArgumentException("Expected initial capacity of at least 0.");
        }

        this.internalCollection = new Object[initialCapacity];
        this.size = 0;
    }

    /**
     * Inserts the given value. Will resize the collection based on the configured resize factor.
     *
     * @param valueToInsert {@link T}
     */
    public void insert(@NonNull final T valueToInsert) {
        if (isInternalCollectionFull()) {
            resizeCollection();
        }

        internalCollection[size] = valueToInsert;
        size += 1;
    }

    /**
     * Finds the index of the given value.
     *
     * @param valueToSearch {@link T}
     * @return Index of the value in the data structure, or -1 if the value does not exist.
     */
    public int indexOf(@NonNull final T valueToSearch) {
        int indexToReturn = -1;

        if (0 != size) {
            // If there are entries to search, then iterate through the indices in the internal collection.
            for (int index = 0; index < size; index++) {
                final Object valueAtIndex = internalCollection[index];

                // If the value in array is non-null and meets the value to search, then store the index and stop
                // searching.
                if (null != valueAtIndex && valueAtIndex.equals(valueToSearch)) {
                    indexToReturn = index;
                    break;
                }
            }
        }

        return indexToReturn;
    }

    /**
     * Removes the given value. Will fail if the value cannot be found.
     *
     * @param valueToRemove {@link T}
     * @return Value that was removed.
     */
    public T remove(@NonNull final T valueToRemove) {
        final int indexToRemove = indexOf(valueToRemove);

        if (-1 == indexToRemove) {
            return null;
        } else {
            size -= 1;

            // Removal in this case will overwrite the portion of collection starting at the index with the rest of the
            // collection after the index. Effectively, every entry after the found index will move up by one index.
            System.arraycopy(internalCollection, indexToRemove + 1, internalCollection, indexToRemove, size - indexToRemove);
            internalCollection[size] = null;
        }

        return valueToRemove;
    }

    /**
     * Checks for the given value.
     *
     * @param valueToSearch {@link T}
     * @return True if the data structure contains this value, false otherwise.
     */
    public boolean contains(@NonNull final T valueToSearch) {
        return indexOf(valueToSearch) != -1;
    }

    /**
     * @return Number of values in the data structure.
     */
    public int getSize() {
        return size;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns string version of the data structure. Uses brackets to identify start and end of collection. Separates
     * the values using comma and space.
     */
    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();

        if (0 == size) {
            stringBuilder.append("[]");
        } else {
            stringBuilder.append("[");

            for (int index = 0; index < size; index++) {
                stringBuilder.append(internalCollection[index]);

                if (size - index != 1) {
                    stringBuilder.append(",").append(" ");
                }
            }

            stringBuilder.append("]");
        }

        return stringBuilder.toString();
    }

    /*
     * Retrieves the length of the internal collection.
     */
    private int getInternalCollectionSize() {
        return internalCollection.length;
    }

    /*
     * Checks whether the internal collection's length is equal to the number of values written.
     */
    private boolean isInternalCollectionFull() {
        return size == getInternalCollectionSize();
    }

    /*
     * Resizes the internal collection.
     *
     * If the current length is 0, then initializes a new array with size of 1.
     *
     * Otherwise, uses a temporary array to track the values, creates a new array with new size using the configured
     * resize factor, and copies the temporary array into the new array.
     */
    private void resizeCollection() {
        final int currentCollectionSize = getInternalCollectionSize();

        if (0 == currentCollectionSize) {
            // If the collection is empty, then initialize a new collection with a single entry.
            internalCollection = new Object[1];
        } else {
            // Otherwise, point a temporary variable to the current collection and create a new collection with resize
            // factor.

            final Object[] tempArray = internalCollection;
            internalCollection = new Object[currentCollectionSize * resizeFactor];

            // Copy the entries into the new collection.
            System.arraycopy(tempArray, 0, internalCollection, 0, tempArray.length);
        }
    }
}
