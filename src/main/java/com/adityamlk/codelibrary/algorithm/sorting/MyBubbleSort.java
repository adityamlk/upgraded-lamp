package com.adityamlk.codelibrary.algorithm.sorting;

import java.util.List;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

/**
 * Sorting class that implements Bubble Sort to sort provided list of generic data type. As per bubble sort, swaps pairs
 * of values to bubble the largest value to the end of the list. Keeps performing these comparisons and swaps on each
 * pass, which bubbles the largest values to the end and smaller values closer to the top.
 * <p>
 * One optimization this implements is that once this bubbles a value to the end of the list it does not visit that
 * value again. This means that on each pass the number of values to compare against goes down by one.
 * <p>
 * Sorting is O(N^2) since there are N-1 passes and for each pass there are N-K-1 swaps, where K is the pass.
 *
 * @param <T> Generic data type supported by the list.
 */
@Log4j2
@NoArgsConstructor
public class MyBubbleSort<T extends Comparable<T>> {

    /**
     * Sorts the provided list, in-order, using Bubble Sort approach. Uses built-in comparison implementation of the
     * type to compare one value with the next.
     *
     * @param listToSort List to sort.
     * @return Sorted list, changed in-place.
     */
    public List<T> doSort(@NonNull final List<T> listToSort) {
        final int listSize = listToSort.size();

        for (int counter = 0; counter < listSize; counter++) {
            final int swapIndex = listSize - counter - 1;

            for (int currentIndex = 0; currentIndex < swapIndex; currentIndex++) {
                final int nextIndex = currentIndex + 1;
                final T currentValue = listToSort.get(currentIndex);
                final T valueToCompare = listToSort.get(nextIndex);
                final int compareResult = currentValue.compareTo(valueToCompare);

                if (0 < compareResult) {
                    listToSort.set(currentIndex, valueToCompare);
                    listToSort.set(nextIndex, currentValue);
                }
            }
        }

        return listToSort;
    }
}
