package com.adityamlk.codelibrary.algorithm.sorting;

import java.util.List;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

/**
 * Sorting class that implements Selection Sort to sort provided list of generic data type. As per selection sort,
 * compares pairs of values and swaps the largest value with the end of the list. Keeps performing these comparisons on
 * each pass and swaps at the end of each pass to the end of the list, which moves the largest values to the end and
 * smaller values closer to the top.
 * <p>
 * One optimization this implements is that this only swaps the two values at the end of a pass if the indices are
 * different from each other.
 * <p>
 * Sorting is O(N^2) since there are N-1 passes and for each pass there are N-K-1 comparisons, where K is the pass.
 *
 * @param <T> Generic data type supported by the list.
 */
@Log4j2
@NoArgsConstructor
public class MySelectionSort<T extends Comparable<T>> {

    /**
     * Sorts the provided list, in-order, using Selection Sort approach. Uses built-in comparison implementation of the
     * type to compare one value with the next.
     *
     * @param listToSort List to sort.
     * @return Sorted list, changed in-place.
     */
    public List<T> doSort(@NonNull final List<T> listToSort) {
        final int listSize = listToSort.size();

        for (int counter = 0; counter < listSize; counter++) {
            final int swapIndex = listSize - counter - 1;
            int largestIndex = 0;

            for (int currentIndex = 1; currentIndex <= swapIndex; currentIndex++) {
                final T largestValue = listToSort.get(largestIndex);
                final T valueToCompare = listToSort.get(currentIndex);
                final int compareResult = largestValue.compareTo(valueToCompare);

                if (0 > compareResult) {
                    largestIndex = currentIndex;
                }
            }

            // Only swap if the indices are different.
            if (swapIndex != largestIndex) {
                final T tempValue = listToSort.get(largestIndex);
                listToSort.set(largestIndex, listToSort.get(swapIndex));
                listToSort.set(swapIndex, tempValue);
            }
        }

        return listToSort;
    }
}
