package com.adityamlk.codelibrary.datastructure.collection;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

/**
 * Represents a StringBuilder implementation. Auto-resizes by configured factor or string length to accommodate for
 * additional elements. Tracks values at a character-level.
 * <p>
 * Insertion is O(1) despite resizing due to amortization. Inserts to the end of the collection.
 * Search is O(N).
 */
@Log4j2
@EqualsAndHashCode
public class MyStringBuilder {

    private static final int RESIZE_FACTOR = 2;

    /*
     * Tracks how many values have been written to the internal collection. Provides an index into the next write and a
     * difference from the length of the internal collection, which represents empty indices as well.
     */
    @NonNull
    private Integer size;

    /*
     * Stores the characters inserted into the data structure. Grows over time to accommodate additional values.
     */
    private char[] internalCollection;

    /**
     * Default constructor.
     */
    public MyStringBuilder() {
        this.internalCollection = new char[0];
        this.size = 0;
    }

    /**
     * Appends the given character. Will resize the collection based on the configured resize factor.
     *
     * @param characterToAppend {@link Character}
     * @return Pointer to the object updated with the character.
     */
    public MyStringBuilder append(@NonNull final Character characterToAppend) {
        if (isInternalCollectionFull()) {
            resizeCollection();
        }

        internalCollection[size] = characterToAppend;
        size += 1;

        return this;
    }

    /**
     * Appends the given string. Will resize the collection based on the configured resize factor and the length of the
     * string.
     *
     * @param stringToAppend {@link String}
     * @return Pointer to the object updated with the string.
     */
    public MyStringBuilder append(@NonNull final String stringToAppend) {
        final char[] arrayToAppend = stringToAppend.toCharArray();
        final int stringLength = arrayToAppend.length;
        final int newSize = stringLength + size;

        if (newSize > getInternalCollectionSize()) {
            resizeCollection(newSize * RESIZE_FACTOR);
        }

        System.arraycopy(arrayToAppend, 0, internalCollection, size, stringLength);
        size = newSize;

        return this;
    }

    /**
     * Finds the index of the given character.
     *
     * @param characterToSearch {@link String}
     * @return Index of the character in the data structure, or -1 if the character does not exist.
     */
    public int indexOf(@NonNull final Character characterToSearch) {
        int indexToReturn = -1;

        for (int index = 0; index < size; index++) {
            final Character valueAtIndex = internalCollection[index];

            if (characterToSearch.equals(valueAtIndex)) {
                indexToReturn = index;
                break;
            }
        }

        return indexToReturn;
    }

    /**
     * Checks for the given character.
     *
     * @param characterToSearch {@link Character}
     * @return True if the data structure contains this character, false otherwise.
     */
    public boolean contains(@NonNull final Character characterToSearch) {
        return -1 != indexOf(characterToSearch);
    }

    /**
     * @return Number of characters in the data structure.
     */
    public int getLength() {
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new String(internalCollection, 0, size);
    }

    /*
     * Retrieves the length of the internal collection.
     */
    private int getInternalCollectionSize() {
        return internalCollection.length;
    }

    /*
     * Checks whether the internal collection's length is equal to the number of characters written.
     */
    private boolean isInternalCollectionFull() {
        return size == getInternalCollectionSize();
    }

    /*
     * Resizes the internal collection using the current length of the internal collection and the configured resize
     * factor.
     */
    private void resizeCollection() {
        resizeCollection(getInternalCollectionSize() * RESIZE_FACTOR);
    }

    /*
     * Resizes the internal collection based on the specified new size.
     *
     * If the new size is 0, then initializes a new array with size of 1.
     *
     * Otherwise, uses a temporary array to track the characters, creates a new array with the new size provided, and
     * copies the temporary array into the new array.
     */
    private void resizeCollection(final int newSize) {
        if (0 == newSize) {
            // If the collection is empty, then initialize a new collection with a single entry.
            internalCollection = new char[1];
        } else {
            // Otherwise, point a temporary variable to the current collection and create a new collection with
            // specified size.
            final char[] tempArray = internalCollection;
            internalCollection = new char[newSize];

            // Copy the entries into the new collection.
            System.arraycopy(tempArray, 0, internalCollection, 0, tempArray.length);
        }
    }
}
