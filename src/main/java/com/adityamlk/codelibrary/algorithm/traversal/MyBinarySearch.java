package com.adityamlk.codelibrary.algorithm.traversal;

import java.util.List;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

/**
 * Traversal class that can perform Binary Search on a sorted list of integers. Uses efficient traversal mechanism to
 * determine whether the provided value is in the list or not.
 * <p>
 * Traversal is O(logN) since size of list is cut in half for each subsequent traversal.
 */
@Log4j2
@NoArgsConstructor
public class MyBinarySearch {

    /**
     * Searches for the provided value in the sorted list using binary search.
     *
     * @param valueToSearch Value to search in the list.
     * @param sortedList    Sorted list to search.
     * @return True if the value is in the sorted list, false otherwise.
     */
    public boolean doesContainValue(@NonNull final Integer valueToSearch, @NonNull final List<Integer> sortedList) {
        return doBinarySearch(valueToSearch, 0, sortedList.size() - 1, sortedList);
    }

    /*
     * Recursive method that performs binary search on smaller and smaller lists until matching value found or this
     * reaches the end of search. Cuts the search in half depending on how the value compares against the midpoint. If
     * the search reaches the final index and the value still does not match, then returns false.
     *
     * For the comparison, performs a diff. If this is not the final index, then checks the diff. If diff is 0, then the
     * value matches and returns true. If diff is greater than 0, then current index is greater than value and need to
     * search lower-half of indices. Otherwise, need to search upper-half of indices.
     */
    private boolean doBinarySearch(final int value, final int start, final int end, final List<Integer> sortedList) {
        final int midpoint = (int) Math.ceil((end - start) / 2.0);
        final int midIndex = start + midpoint;
        final int midValue = sortedList.get(midIndex);
        final int diff = midValue - value;

        // If this is the final index (start = end) and the values are not the same, then value is not in the list.
        if (0 == midpoint && 0 != diff) {
            return false;
        }

        // If the values are the same, then value is in the list and return. Otherwise, keep looking.
        if (0 == diff) {
            return true;
        } else if (0 < diff) {
            return doBinarySearch(value, start, midIndex - 1, sortedList);
        } else {
            return doBinarySearch(value, midIndex + 1, end, sortedList);
        }
    }
}
