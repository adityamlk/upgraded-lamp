package com.adityamlk.codelibrary.datastructure.collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Represents a singly LinkedList implementation. Maintains head of the list to track starting point.
 * <p>
 * Insertion is O(N) when inserting to end of collection and O(1) when inserting to the head.
 * Deletion is O(N) when using the provided index and O(1) when removing from the head.
 * Search is O(N) when using the provided value and O(1) when retrieving from the head.
 *
 * @param <T> Generic data type supported by the list.
 */
@Log4j2
@EqualsAndHashCode
public class MySingleLinkedList<T> {

    /*
     * Pointer to the head of the structure.
     */
    private OneWayNode<T> head;

    /*
     * Tracks how many values have been written to the internal collection.
     */
    @NonNull
    private Integer size;

    /**
     * Default constructor.
     */
    public MySingleLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Inserts the given value to the head of the data structure.
     *
     * @param valueToInsert {@link T}
     */
    public void insertToHead(@NonNull final T valueToInsert) {
        final OneWayNode<T> nodeToInsert = new OneWayNode<>(valueToInsert);
        nodeToInsert.next = head;

        head = nodeToInsert;
        size += 1;
    }

    /**
     * Inserts the given value to the tail of the data structure.
     *
     * @param valueToInsert {@link T}
     */
    public void insertToTail(@NonNull final T valueToInsert) {
        if (0 == getSize()) {
            insertToHead(valueToInsert);
        } else {
            // Since there is no pointer to the tail retrieve the last node before setting the new tail.
            final OneWayNode<T> tail = getLastNode();
            tail.next = new OneWayNode<>(valueToInsert);
            size += 1;
        }
    }

    /**
     * Removes the given value. Will fail if the value cannot be found.
     *
     * @param valueToRemove {@link T}
     * @return Value that was removed, or null if the data structure is empty.
     */
    public T remove(@NonNull final T valueToRemove) {
        // Short-circuit if the size is zero.
        if (0 == getSize()) {
            return null;
        }

        // Short-circuit if the head node needs to be removed.
        if (valueToRemove.equals(head.value)) {
            return removeFromHead();
        }

        OneWayNode<T> previous = head;
        OneWayNode<T> current = head;

        // Loop through the nodes and track the previous and the current nodes. If found, then update the previous node
        // to point to the next node and sever the current node. Otherwise, keep moving the nodes.
        while (null != current) {
            if (valueToRemove.equals(current.value)) {
                previous.next = current.next;
                current.next = null;
                size -= 1;

                break;
            } else {
                previous = current;
                current = current.next;
            }
        }

        // If there were no matches, then fail.
        if (null == current) {
            throw new IllegalArgumentException("Value not found in the list.");
        }

        return valueToRemove;
    }

    /**
     * Removes the head of the data structure.
     *
     * @return Value that was removed from the head, or null if the data structure is empty.
     */
    public T removeFromHead() {
        // Short-circuit if the size is zero.
        if (0 == getSize()) {
            return null;
        }

        OneWayNode<T> current = head;
        head = current.next;
        current.next = null;
        size -= 1;

        return current.value;
    }

    /**
     * Removes the tail of the data structure.
     *
     * @return Value that was removed from the tail, or null if the data structure is empty.
     */
    public T removeFromTail() {
        // Short-circuit if the size is zero.
        if (0 == getSize()) {
            return null;
        }

        OneWayNode<T> previous = head;
        OneWayNode<T> current = head;

        // Loop through the nodes to get to the end of the data structure. Track the previous and current nodes in the
        // process.
        while (null != current.next) {
            previous = current;
            current = current.next;
        }

        previous.next = null;
        size -= 1;

        return current.value;
    }

    /**
     * Returns the value at the given index. Will fail if the index is too big.
     *
     * @param index Integer value used for finding the stored value.
     * @return Value at the specified index, or null if the data structure is empty.
     */
    public T get(@NonNull final Integer index) {
        // Short-circuit if the size is zero.
        if (0 == getSize()) {
            return null;
        }

        OneWayNode<T> current = head;
        int count = 0;

        while (null != current) {
            if (index == count) {
                return current.value;
            }

            current = current.next;
            count += 1;
        }

        throw new IndexOutOfBoundsException("Index not found in the list.");
    }

    /**
     * Returns the value at the head of the data structure.
     *
     * @return Value that is at the head, or null if the data structure is empty.
     */
    public T getFromHead() {
        // Short-circuit if the size is zero.
        if (0 == getSize()) {
            return null;
        }

        return head.value;
    }

    /**
     * Returns the value at the tail of the data structure.
     *
     * @return Value that is at the tail, or null if the data structure is empty.
     */
    public T getFromTail() {
        // Short-circuit if the size is zero.
        if (0 == getSize()) {
            return null;
        }

        final OneWayNode<T> tail = getLastNode();

        return tail.value;
    }

    /**
     * Checks for the given value.
     *
     * @param valueToSearch {@link T}
     * @return True if the data structure contains this value, false otherwise.
     */
    public boolean contains(@NonNull final T valueToSearch) {
        // Short-circuit if the size is zero.
        if (0 == getSize()) {
            return false;
        }

        OneWayNode<T> current = head;

        // Loop through the nodes and look for one where the value matches.
        while (null != current) {
            if (valueToSearch.equals(current.value)) {
                return true;
            }

            current = current.next;
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

            OneWayNode<T> current = head;

            while (null != current) {
                stringBuilder.append(current.value);

                if (null != current.next) {
                    stringBuilder.append(",").append(" ");
                }

                current = current.next;
            }

            stringBuilder.append("]");
        }

        return stringBuilder.toString();
    }

    /*
     * Retrieves the last node in the data structure. Helpful for operations that require work with the tail.
     */
    private OneWayNode<T> getLastNode() {
        OneWayNode<T> current = head;

        while (null != current && null != current.next) {
            current = current.next;
        }

        return current;
    }

    /**
     * Node class that stores the associated value and has a pointer to the next node in a collection of nodes.
     *
     * @param <T> Generic data type supported by the Node.
     */
    @Data
    @Builder
    @AllArgsConstructor
    @RequiredArgsConstructor
    public static class OneWayNode<T> {
        /*
         * Value stored in the node.
         */
        @NonNull
        private final T value;

        /*
         * Pointer to the next node in a collection of nodes.
         */
        private OneWayNode<T> next;
    }
}
