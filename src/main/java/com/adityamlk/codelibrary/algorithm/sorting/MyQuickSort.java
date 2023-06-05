package com.adityamlk.codelibrary.algorithm.sorting;

import java.util.List;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

/**
 * Sorting class that implements Quick Sort to sort provided list of generic data type. As per quick sort, picks a pivot
 * and sorts the sub-list with swaps between the left and right indices. Keeps generating pivots with smaller sub-lists
 * and performs these swaps where appropriate. Each recursive call processes a smaller sub-list and the array becomes
 * sorted over time.
 * <p>
 * Sorting is O(N*logN) on average and O(N^2) on worst; average: the call stack grows smaller as elements are sorted
 * over time. Worst: call stack does not get smaller and the same elements require processing each time.
 * <p>
 * Space complexity is O(logN) to manage data for the sub-lists processed in the call stacks during the recursive calls.
 *
 * @param <T> Generic data type supported by the list.
 */
@Log4j2
@NoArgsConstructor
public class MyQuickSort<T extends Comparable<T>> {

    /**
     * Sorts the provided list, in-place, using Quick Sort approach. Uses built-in comparison implementation of the
     * type to compare one value with the next.
     *
     * @param listToSort List to sort.
     * @return Sorted list, changed in-place.
     */
    public List<T> doSort(@NonNull final List<T> listToSort) {
        final int leftIndex = 0;
        final int rightIndex = listToSort.size() - 1;

        handleQuickSort(listToSort, leftIndex, rightIndex);

        return listToSort;
    }

    /*
     * Recursive method that performs quick sort on the list using the specified left index and right index. Uses the
     * result to recursively sort the sub-lists.
     */
    private void handleQuickSort(final List<T> listToSort, final int leftIndex, final int rightIndex) {
        final int index = getIndexAfterPartition(listToSort, leftIndex, rightIndex);

        // More of the array to go through on the left side, so sort the left side.
        if (leftIndex < index - 1) {
            handleQuickSort(listToSort, leftIndex, index - 1);
        }

        // More of the array to go through on the right side, so sort the right side.
        if (rightIndex > index) {
            handleQuickSort(listToSort, index, rightIndex);
        }
    }

    /*
     * Partitions the list based on the midpoint of the two indices and attempts to swap values that are on either side
     * of this pivot value. Keeps swapping until the left index surpasses the right index. At the end, this returns the
     * left index so the algorithm can partition the list based on this index and process the sub-arrays.
     */
    private int getIndexAfterPartition(final List<T> listToSort, final int leftIndex, final int rightIndex) {
        final T pivotValue = listToSort.get((leftIndex + rightIndex) / 2);
        int currentLeftIndex = leftIndex;
        int currentRightIndex = rightIndex;

        while (currentLeftIndex <= currentRightIndex) {
            // Increment left index until there is a value on the left that should be on the right side of the pivot.
            while (0 > getCompareResultForPivot(listToSort, currentLeftIndex, pivotValue)) {
                currentLeftIndex += 1;
            }

            // Increment right index until there is a value on the right that should be on the left side of the pivot.
            while (0 < getCompareResultForPivot(listToSort, currentRightIndex, pivotValue)) {
                currentRightIndex -= 1;
            }

            // Swap the two elements that should be on the opposite side of the pivot and move the indices for the next
            // iteration.
            if (currentLeftIndex <= currentRightIndex) {
                swapElements(listToSort, currentLeftIndex, currentRightIndex);
                currentLeftIndex += 1;
                currentRightIndex -= 1;
            }
        }

        // Return one of the changed indices to drive the next partition. Either index is fine since quick sort will
        // likely take place on both sides regardless.
        return currentLeftIndex;
    }

    /*
     * Swaps two elements in the provided list based on the two indices provided. Swap is in-place and modifies the
     * list.
     */
    private void swapElements(final List<T> listToSort, final int firstIndex, final int secondIndex) {
        final T tempValue = listToSort.get(firstIndex);
        listToSort.set(firstIndex, listToSort.get(secondIndex));
        listToSort.set(secondIndex, tempValue);
    }

    /*
     * Returns result of compare between the value at the provided index and the provided pivot value. Uses the provided
     * list to retrieve the value to compare.
     */
    private int getCompareResultForPivot(final List<T> listToSort, final int indexToCompare, final T pivotValue) {
        final T valueToCompare = listToSort.get(indexToCompare);

        return valueToCompare.compareTo(pivotValue);
    }
}
