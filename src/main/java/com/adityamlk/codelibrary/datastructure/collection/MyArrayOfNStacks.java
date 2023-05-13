package com.adityamlk.codelibrary.datastructure.collection;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

/**
 * Represents an Array of Stacks implementation. Tracks multiple stacks in single array by inserting/removing from
 * indices based on stack index. Resizes the array anytime any of the stacks reach the maximum length. Indices organized
 * so that first index of each stack is next to the others, same for the second, and so on. This makes resizing trivial.
 * Limitation is that index insertion and removal is not available, only basic insert and removal supported by Stacks.
 * <p>
 * Insertion is O(1) despite resizing due to amortized insertion. Inserts to the end of the collection.
 * Deletion is O(1); removal from the end of the collection.
 * Search is O(N).
 *
 * @param <T> Generic data type supported by the list.
 */
@Log4j2
@EqualsAndHashCode
public class MyArrayOfNStacks<T> {

    private static final int RESIZE_FACTOR = 2;

    /*
     * Tracks how many values have been written to the internal collection for each of the stacks to support. Provides
     * an index into the next write and a difference from the length of the internal collection, which represents empty
     * indices as well, for the particular stack.
     */
    private final int[] arrayOfSizes;

    /*
     * Stores the values inserted into the data structure. Grows over time to accommodate additional values. Supports
     * multiple stacks by reserving indices for the stacks. In other words, if this needs to support three stacks, then
     * indices 0-2 will represent the first open index for each stack, 3-5 will represent the next, and so forth. This
     * means that resizing the array will resize all supported stacks as well and introduce sparseness.
     */
    private Object[] arrayOfStacks;

    /**
     * Constructor that requires the number of stacks expected for support.
     *
     * @param numberOfStacks Indicates how many stacks this data structure needs to support.
     */
    public MyArrayOfNStacks(@NonNull final Integer numberOfStacks) {
        this.arrayOfStacks = new Object[0];
        this.arrayOfSizes = new int[numberOfStacks];
    }

    /**
     * Inserts given value to the top of the given stack. Will resize the collection based on the configured resize
     * factor.
     *
     * @param stackNumber   Stack to update.
     * @param valueToInsert {@link T}
     */
    public void insertIntoStack(@NonNull final Integer stackNumber, @NonNull final T valueToInsert) {
        final int stackIndex = stackNumber - 1;
        final int sizeOfStack = getSizeOfStack(stackIndex);
        final int numberOfStacks = getNumberOfStacks();

        if (isInternalCollectionFull()) {
            resizeInternalCollection();
        }

        // Index to insert at depends on number of stacks, the size of the stack, and the index of the stack.
        final int indexToInsert = sizeOfStack * numberOfStacks + stackIndex;
        arrayOfStacks[indexToInsert] = valueToInsert;
        arrayOfSizes[stackIndex] = sizeOfStack + 1;
    }

    /**
     * Removes value from the top of the given stack.
     *
     * @param stackNumber Stack to update.
     */
    public void removeFromStack(@NonNull final Integer stackNumber) {
        final int stackIndex = stackNumber - 1;
        final int sizeOfStack = getSizeOfStack(stackIndex);
        final int newSizeOfStack = sizeOfStack - 1;
        final int numberOfStacks = getNumberOfStacks();

        // Index to remove from depends on number of stacks, the new size of the stack, and the index of the stack.
        final int indexToRemove = newSizeOfStack * numberOfStacks + stackIndex;
        arrayOfStacks[indexToRemove] = null;
        arrayOfSizes[stackIndex] = newSizeOfStack;
    }

    /**
     * Checks for the given value in the given stack.
     *
     * @param stackNumber   Stack to check.
     * @param valueToSearch {@link T}
     * @return True if the specified stack contains this value, false otherwise.
     */
    public boolean containsInStack(@NonNull final Integer stackNumber, @NonNull final T valueToSearch) {
        final int stackIndex = stackNumber - 1;
        final int sizeOfStack = getSizeOfStack(stackIndex);
        final int numberOfStacks = getNumberOfStacks();
        boolean isValueInStack = false;

        // Loop through the array of stacks using the indices of the specified stack.
        for (int relativeIndex = 0; relativeIndex < sizeOfStack; relativeIndex++) {
            final int indexToSearch = relativeIndex * numberOfStacks + stackIndex;

            if (valueToSearch.equals(arrayOfStacks[indexToSearch])) {
                isValueInStack = true;
                break;
            }
        }

        return isValueInStack;
    }

    /**
     * @param stackNumber Stack to use.
     * @return Number of values in the data structure.
     */
    public int getStackSize(@NonNull final Integer stackNumber) {
        final int stackIndex = stackNumber - 1;
        return getSizeOfStack(stackIndex);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns string version of the specified stack. Uses brackets to identify start and end of collection. Separates
     * the values using comma and space.
     */
    public String getStackAsString(@NonNull final Integer stackNumber) {
        final int stackIndex = stackNumber - 1;
        final int sizeOfStack = getSizeOfStack(stackIndex);
        final int numberOfStacks = getNumberOfStacks();
        final StringBuilder stringBuilder = new StringBuilder();

        if (0 == sizeOfStack) {
            stringBuilder.append("[]");
        } else {
            stringBuilder.append("[");

            // Loop through the array of stacks using the indices of the specified stack.
            for (int relativeIndex = 0; relativeIndex < sizeOfStack; relativeIndex++) {
                final int indexToTraverse = relativeIndex * numberOfStacks + stackIndex;
                stringBuilder.append(arrayOfStacks[indexToTraverse]);

                if (sizeOfStack - relativeIndex != 1) {
                    stringBuilder.append(",").append(" ");
                }
            }

            stringBuilder.append("]");
        }

        return stringBuilder.toString();
    }

    /*
     * Retrieves the number of stacks using the length of the array of sizes, which will not resize after
     * initialization.
     */
    private int getNumberOfStacks() {
        return arrayOfSizes.length;
    }

    /*
     * Retrieves the size of a stack using the provided stack index.
     */
    private int getSizeOfStack(final int stackIndex) {
        return arrayOfSizes[stackIndex];
    }

    /*
     * Retrieves the length of the internal collection.
     */
    private int getInternalCollectionLength() {
        return arrayOfStacks.length;
    }

    /*
     * Checks whether the internal collection is full by checking whether any of the stacks have the same size as their
     * expected length. The expected length of a stack is the total length divided by the number of stacks. Since the
     * stacks are always sized evenly the stack lengths will always be the same even if the internal collection is
     * sparse.
     */
    private boolean isInternalCollectionFull() {
        final int numberOfStacks = getNumberOfStacks();
        final int stackLength = getInternalCollectionLength() / numberOfStacks;
        boolean isInternalCollectionFull = false;

        for (final int sizeOfStack : arrayOfSizes) {
            if (sizeOfStack == stackLength) {
                isInternalCollectionFull = true;
                break;
            }
        }

        return isInternalCollectionFull;
    }

    /*
     * Resizes the internal collection.
     *
     * If the current length is 0, then initializes a new array with size of number of stacks.
     *
     * Otherwise, uses a temporary array to track the values, creates a new array with new size using the configured
     * resize factor, and copies the temporary array into the new array.
     */
    private void resizeInternalCollection() {
        final int numberOfElements = getInternalCollectionLength();

        if (0 == numberOfElements) {
            arrayOfStacks = new Object[getNumberOfStacks()];
        } else {
            final Object[] tempStorage = arrayOfStacks;
            arrayOfStacks = new Object[numberOfElements * RESIZE_FACTOR];

            System.arraycopy(tempStorage, 0, arrayOfStacks, 0, tempStorage.length);
        }
    }
}
