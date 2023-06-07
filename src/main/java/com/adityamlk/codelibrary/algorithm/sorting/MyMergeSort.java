package com.adityamlk.codelibrary.algorithm.sorting;

import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

/**
 * Sorting class that implements Merge Sort to sort provided list of generic data type. As per merge sort, splits the
 * list and recursively sorts the left and right sub-list. Once done splitting, uses the merge functionality to put the
 * two sub-lists together in a sorted manner. Keeps performing these merges when bubbling back up the recursive call
 * stack.
 * <p>
 * Sorting is O(N*logN) on average; the call stack is halved as elements are sorted over time.
 * <p>
 * Space complexity is O(N) since merging uses a helper list to set the values in the list before they are sorted at
 * that level.
 *
 * @param <T> Generic data type supported by the list.
 */
@Log4j2
@NoArgsConstructor
public class MyMergeSort<T extends Comparable<T>> {

    /**
     * Sorts the provided list, in-place, using Merge Sort approach. Uses built-in comparison implementation of the
     * type to compare one value with the next.
     *
     * @param listToSort List to sort.
     * @return Sorted list, changed in-place.
     */
    public List<T> doSort(@NonNull final List<T> listToSort) {
        final List<T> helper = new ArrayList<>(listToSort);
        final int leftIndex = 0;
        final int rightIndex = listToSort.size() - 1;


        handleMergeSort(listToSort, helper, leftIndex, rightIndex);

        return listToSort;
    }

    /*
     * Recursive method that performs merge sort on the list using the specified low and high indices. Sorts the
     * sub-lists and then merges them  together to sort the full list at this level.
     */
    private void handleMergeSort(final List<T> lts, final List<T> helper, final int low, final int high) {
        if (low < high) {
            final int mid = (low + high) / 2;

            // Sort the left half, if possible, the right half, if possible, and then merge the two sides.
            handleMergeSort(lts, helper, low, mid);
            handleMergeSort(lts, helper, mid + 1, high);
            handleMerge(lts, helper, low, mid, high);
        }
    }

    /*
     * Uses the provided indices to merge the two sides of lts together. Uses the helper to set the initial set of
     * values in the range [low, high] and then extracts values from this list to then correctly populate lts in that
     * same index range. By picking the smaller value at each comparison between left and right side, lts will become
     * sorted in the [low, high] range.
     *
     * Note that the sub-lists in lts are already sorted, which means in case only the right side has leftovers after
     * processing, then lts will already have those values in the correct positions.
     */
    private void handleMerge(final List<T> lts, final List<T> helper, final int low, final int mid, final int high) {
        for (int index = low; index <= high; index++) {
            helper.set(index, lts.get(index));
        }

        int leftIndex = low;
        int rightIndex = mid + 1;
        int currentIndex = low;

        // For each side in the helper, check the value at left index against the value at right index. Depending on the
        // result set the current index of lts to the smaller value. Continue until one of the indices reaches its
        // bound.
        while (leftIndex <= mid && rightIndex <= high) {
            final T leftValue = helper.get(leftIndex);
            final T rightValue = helper.get(rightIndex);

            // If left is strictly grater, then use the value on the right and increment that index. If left is less
            // than or equal to, then use the value on the left and increment that index.
            if (0 < leftValue.compareTo(rightValue)) {
                lts.set(currentIndex, rightValue);
                rightIndex += 1;
            } else {
                lts.set(currentIndex, leftValue);
                leftIndex += 1;
            }

            currentIndex += 1;
        }

        // If there are leftovers from the left side, then add them to the next available slots in lts. Note that
        // leftovers on the right are not considered because lts already has those same values set from the merge sort
        // at the lower level.
        while (0 <= mid - leftIndex) {
            lts.set(currentIndex, helper.get(leftIndex));
            currentIndex += 1;
            leftIndex += 1;
        }
    }
}
