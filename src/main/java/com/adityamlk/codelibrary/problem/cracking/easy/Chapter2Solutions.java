package com.adityamlk.codelibrary.problem.cracking.easy;

import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Covers problems from Chapter 2: Linked Lists
 */
public class Chapter2Solutions {

    /*
     * Problem 1: Remove duplicates from an unsorted linked list.
     */

    /**
     * Run through the linked list twice, once tracking the original node and for each running through the nodes to remove
     * duplicates. Track the previous node when running in nested loop. If there is a duplicate, then point the previous
     * node to the next. If there is no duplicate, then just move previous. Either way, move runner node. Repeat until
     * all nodes have been processed.
     * <p>
     * Alternatives that take up more space are using a hash table, tracking counts for elements, and removing those that
     * are duplicates. Alternatively, consider sorting the nodes and removing the duplicates from the sorted collection.
     * <p>
     * Time Complexity: O(N^2); for each node we check rest of the collection to see if duplicates exist. Alternatives
     * would be O(N) and O(N*log(N)) for hash table approach and sorting approach, respectively.
     * <p>
     * Space Complexity: O(1); no additional storage necessary and nodes changed in-place. Alternatives would be O(N)
     * for hash table approach and possibly sorting approach.
     *
     * @param linkedListToModify Linked list head that needs to be updated to remove duplicates.
     * @param <T>                Generic data type supported by the Node.
     * @return Updated linked list head with duplicates removed.
     */
    public static <T> LocalTestNode<T> removeDuplicates(@NonNull final LocalTestNode<T> linkedListToModify) {
        LocalTestNode<T> current = linkedListToModify;

        if (null != current.next) {
            while (null != current) {
                LocalTestNode<T> runner = current.next;
                LocalTestNode<T> previous = current;

                while (null != runner) {
                    if (current.value.equals(runner.value)) {
                        previous.next = runner.next;
                    } else {
                        previous = previous.next;
                    }

                    runner = runner.next;
                }

                current = current.next;
            }
        }

        return linkedListToModify;
    }

    /*
     * Problem 2: Retrieve the kth to last element from the singly linked list.
     */

    /**
     * Run through enough nodes in the linked list to hit k, then move two pointers with k distance apart until the
     * runner hits the end of the list. Here, the pointer k distance away is the node with the value to return.
     * <p>
     * Alternatives that take up more space are using recursion.
     * <p>
     * Time Complexity: O(N); for each node we traverse until there are k nodes traversed, then traverse the rest.
     * Alternatives would be O(N) for the recursive approach.
     * <p>
     * Space Complexity: O(1); no additional storage necessary. Alternatives would be O(N) for the stack that supports
     * recursion.
     *
     * @param linkedListToCheck Linked list head that contains the values.
     * @param <T>               Generic data type supported by the Node.
     * @return Value in the kth to last position in the provided list.
     */
    public static <T> T getKthToLast(@NonNull final LocalTestNode<T> linkedListToCheck, final int position) {
        LocalTestNode<T> current = linkedListToCheck;
        LocalTestNode<T> runner = linkedListToCheck;
        int elementsTraversed = 0 == position ? 0 : 1;

        while (elementsTraversed <= position) {
            if (null == runner) {
                throw new IllegalArgumentException("Position to use is too large compared to the nodes.");
            }

            runner = runner.next;
            elementsTraversed += 1;
        }

        while (null != runner) {
            runner = runner.next;
            current = current.next;
        }

        return current.value;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @RequiredArgsConstructor
    public static class LocalTestNode<T> {
        @NonNull
        private final T value;

        private LocalTestNode<T> next;

        public void addElements(@NonNull Collection<T> elementsToAdd) {
            LocalTestNode<T> current = this;

            while (null != current.next) {
                current = current.next;
            }

            for (final T element : elementsToAdd) {
                current.next = new LocalTestNode<>(element);
                current = current.next;
            }
        }
    }
}
