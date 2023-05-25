package com.adityamlk.codelibrary.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

/**
 * Represents a Heap implementation as a Complete Binary Tree with the minimal element on the top. This is a complete
 * tree, which means elements are filled in left to right. Tracks all elements using an array with indices for parent,
 * left-child, and right-child elements identified using relative position in the array. Insertion and removal ensure
 * the tree maintains the minimal element on the top.
 * <p>
 * Insertion is O(logN); inserts to the end of the tree and bubbles up the minimum element.
 * Deletion is O(logN); removes from the top of the tree, replaces with left-most value, and bubbles down the value.
 * Search is O(N) since exact place of the node is not guaranteed, which means traversing all nodes may be necessary.
 *
 * @param <T> Generic data type supported by the tree.
 */
@Log4j2
@EqualsAndHashCode
public class MyMinHeap<T extends Comparable<T>> {

    private static final int DEFAULT_INITIAL_CAPACITY = 0;

    private static final int DEFAULT_RESIZE_FACTOR = 2;

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

    public MyMinHeap() {
        this.internalCollection = new Object[DEFAULT_INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Inserts the given value. Will ensure the root of the tree remains on top.
     *
     * @param valueToInsert {@link T}
     */
    @SuppressWarnings("unchecked")
    public void insert(@NonNull final T valueToInsert) {
        if (isInternalCollectionFull()) {
            resizeCollection();
        }

        internalCollection[size] = valueToInsert;
        size += 1;

        int currentIndex = size - 1;

        // After inserting to the end of the heap bubble up this value by comparing against the parent. If the value is
        // smaller, then replace with the parent and repeat the check. This will ensure the smallest value is at the top
        // of the heap and each sub-heap.
        while (0 != currentIndex) {
            final T currentValue = (T) internalCollection[currentIndex];
            final int parentIndex = getParentIndex(currentIndex);
            final T parentValue = (T) internalCollection[parentIndex];
            final boolean didPerformSwap = swapIfLessThan(currentValue, currentIndex, parentValue, parentIndex);

            if (!didPerformSwap) {
                break;
            }

            currentIndex = parentIndex;
        }
    }

    /**
     * Remove the minimum value from the tree. Will ensure the root of the tree remains on top. Will fail if the value
     * cannot be found.
     *
     * @return Minimum value that was extracted.
     */
    @SuppressWarnings("unchecked")
    public T extract() {
        final T valueToExtract = (T) internalCollection[0];
        size -= 1;

        // Replace the top of the heap with the last value in the heap and mark this last value as null.
        final T valueToReplace = (T) internalCollection[size];
        internalCollection[0] = valueToReplace;
        internalCollection[size] = null;
        int currentIndex = 0;

        // After replacing the top of the heap bubble down this value by comparing against either the left or right
        // child. Repeat until the value cannot move further down. This will ensure the smallest value remains at the
        // top of the heap and at each sub-heap.
        while (currentIndex < size) {
            final int leftChildIndex = getLeftChildIndex(currentIndex);
            final int rightChildIndex = getRightChildIndex(currentIndex);
            final T leftChildValue = getChildValue(leftChildIndex);
            final T rightChildValue = getChildValue(rightChildIndex);
            T valueToSwap;
            int valueToSwapIndex;

            // Three options: (1) if neither child exists, stop bubbling, (2) if left or right child exists, then check
            // whether to swap with that child, and (3) if both children exist, then find the smaller of the two and
            // check whether to swap with the child.
            if (null == leftChildValue && null == rightChildValue) {
                break;
            } else if (null != leftChildValue && null == rightChildValue) {
                valueToSwap = leftChildValue;
                valueToSwapIndex = leftChildIndex;
            } else if (null == leftChildValue) {
                valueToSwap = rightChildValue;
                valueToSwapIndex = rightChildIndex;
            } else {
                final boolean isLessThan = isValueLessThan(leftChildValue, rightChildValue);

                valueToSwap = isLessThan ? leftChildValue : rightChildValue;
                valueToSwapIndex = isLessThan ? leftChildIndex : rightChildIndex;
            }

            final boolean didPerformSwap = swapIfLessThan(valueToSwap, valueToSwapIndex, valueToReplace, currentIndex);

            // If there wasn't a swap, then bubbling can stop. Otherwise, continue with the next index the value moved
            // to.
            if (!didPerformSwap) {
                break;
            }

            currentIndex = valueToSwapIndex;
        }

        return valueToExtract;
    }

    /**
     * Checks for the given value.
     *
     * @param valueToSearch {@link T}
     * @return True if the data structure contains this value, false otherwise.
     */
    @SuppressWarnings("unchecked")
    public boolean contains(@NonNull final T valueToSearch) {
        if (0 == size) {
            return false;
        }

        final Queue<Integer> searchQueue = new LinkedList<>();
        searchQueue.add(0);

        // Performs Breadth-First Search (BFS) to check whether the given value is in the tree. Checks one level at a
        // time in the heap. If the value is greater than an entry in the heap, then adds the children to the queue for
        // processing. If not, then ignores the sub-heap entirely. Stops once matched value found, continues to the end
        // otherwise.
        while (!searchQueue.isEmpty()) {
            final int currentIndex = searchQueue.poll();
            final T currentValue = (T) internalCollection[currentIndex];
            final int valueComparison = valueComparison(valueToSearch, currentValue);

            if (0 == valueComparison) {
                return true;
            } else if (0 < valueComparison) {
                final int leftChildIndex = getLeftChildIndex(currentIndex);
                final int rightChildIndex = getRightChildIndex(currentIndex);
                final T leftChildValue = getChildValue(leftChildIndex);
                final T rightChildValue = getChildValue(rightChildIndex);

                if (null != leftChildValue) {
                    searchQueue.add(leftChildIndex);
                }

                if (null != rightChildValue) {
                    searchQueue.add(rightChildIndex);
                }
            }
        }

        return false;
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
            internalCollection = new Object[currentCollectionSize * DEFAULT_RESIZE_FACTOR];

            // Copy the entries into the new collection.
            System.arraycopy(tempArray, 0, internalCollection, 0, tempArray.length);
        }
    }

    /*
     * If the first value is less than the second value, then swaps the two in the internal collection and return true.
     * Returns false otherwise without updating the internal collection.
     */
    private boolean swapIfLessThan(final T value1, final int value1Index, final T value2, final int value2Index) {
        if (isValueLessThan(value1, value2)) {
            internalCollection[value1Index] = value2;
            internalCollection[value2Index] = value1;
            return true;
        }

        return false;
    }

    /*
     * Checks whether the first value is less than the second value.
     */
    private boolean isValueLessThan(final T value1, final T value2) {
        return 0 > valueComparison(value1, value2);
    }

    /*
     * Compares two values using their compareTo implementation.
     */
    private int valueComparison(final T value1, final T value2) {
        return value1.compareTo(value2);
    }

    /*
     * Returns child value for the child index. If the index is appropriate compared to size, then returns the value in
     * the array. Returns null otherwise.
     */
    @SuppressWarnings("unchecked")
    private T getChildValue(final int childIndex) {
        T childValue = null;

        if (childIndex < size) {
            childValue = (T) internalCollection[childIndex];
        }

        return childValue;
    }

    /*
     * Determines whether the index belongs to a left child in a binary tree. Left children always have odd indices.
     */
    private boolean isLeftChild(final int elementIndex) {
        return 0 != elementIndex % 2;
    }

    /*
     * Returns left child index for the parent index.
     */
    private int getLeftChildIndex(final int parentIndex) {
        return 2 * parentIndex + 1;
    }

    /*
     * Returns right child index for the parent index.
     */
    private int getRightChildIndex(final int parentIndex) {
        return 2 * parentIndex + 2;
    }

    /*
     * Returns parent index for the child index. Depending on the child, calls the appropriate method.
     */
    private int getParentIndex(final int childIndex) {
        final boolean isLeftChild = isLeftChild(childIndex);

        return isLeftChild ? getParentIndexFromLeftChild(childIndex) : getParentIndexFromRightChild(childIndex);
    }

    /*
     * Returns parent index for the left child index.
     */
    private int getParentIndexFromLeftChild(final int leftChildIndex) {
        return (leftChildIndex - 1) / 2;
    }

    /*
     * Returns parent index for the right child index.
     */
    private int getParentIndexFromRightChild(final int rightChildIndex) {
        return (rightChildIndex - 2) / 2;
    }
}
