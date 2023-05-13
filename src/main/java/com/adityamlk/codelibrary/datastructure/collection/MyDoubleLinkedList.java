package com.adityamlk.codelibrary.datastructure.collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Represents a doubly LinkedList implementation. Maintains both head and tail of the list to track both starting point
 * and ending point, respectively.
 * <p>
 * Insertion is O(1) when inserting to either end of the location.
 * Deletion is O(N) when using the provided index and O(1) when removing from either the head or the tail.
 * Search is O(N) when using the provided value and O(1) when retrieving from either the head or the tail.
 *
 * @param <T> Generic data type supported by the list.
 */
@Log4j2
@EqualsAndHashCode
public class MyDoubleLinkedList<T> {

    /*
     * Pointer to the head of the structure.
     */
    private TwoWayNode<T> head;

    /*
     * Pointer to the tail of the structure.
     */
    private TwoWayNode<T> tail;

    /*
     * Tracks how many values have been written to the internal collection.
     */
    @NonNull
    private Integer size;

    /**
     * Default constructor.
     */
    public MyDoubleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Inserts the given value to the head of the data structure.
     *
     * @param valueToInsert {@link T}
     */
    public void insertToHead(@NonNull final T valueToInsert) {
        final TwoWayNode<T> nodeToInsert = new TwoWayNode<>(valueToInsert);

        // Either this is the first element in the data structure, which means setting the tail here as well. Or, the
        // new node needs to be added ahead of the current head.
        if (null == head) {
            tail = nodeToInsert;
        } else {
            nodeToInsert.next = head;
            head.previous = nodeToInsert;
        }

        head = nodeToInsert;
        size += 1;
    }

    /**
     * Inserts the given value to the tail of the data structure.
     *
     * @param valueToInsert {@link T}
     */
    public void insertToTail(@NonNull final T valueToInsert) {
        // Either this is the first element in the data structure, which means calling the other method. Or, the new
        // node needs to be added behind the current tail.
        if (0 == getSize()) {
            insertToHead(valueToInsert);
        } else {
            final TwoWayNode<T> nodeToInsert = new TwoWayNode<>(valueToInsert);
            nodeToInsert.previous = tail;
            tail.next = nodeToInsert;
            tail = nodeToInsert;
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

        // Short-circuit if the tail node needs to be removed.
        if (valueToRemove.equals(tail.value)) {
            return removeFromTail();
        }

        TwoWayNode<T> current = head;

        // Loop through the nodes and track the current node. If found, then update the previous node to point to the
        // next node, update the next node to point to the previous node, and sever the current node. Otherwise, keep
        // moving the node.
        while (null != current) {
            if (valueToRemove.equals(current.value)) {
                final TwoWayNode<T> previousNode = current.previous;
                final TwoWayNode<T> nextNode = current.next;

                previousNode.next = nextNode;
                nextNode.previous = previousNode;

                current.previous = null;
                current.next = null;
                size -= 1;

                break;
            } else {
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

        TwoWayNode<T> current = head;

        // If there is one element, then set both pointers to null. Otherwise, move the head node and sever the previous
        // head.
        if (1 == getSize()) {
            head = null;
            tail = null;
        } else {
            head = current.next;
            head.previous = null;
            current.next = null;
        }

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

        TwoWayNode<T> current = tail;

        // If there is one element, then set both pointers to null. Otherwise, move the tail node and sever the previous
        // tail.
        if (1 == getSize()) {
            head = null;
            tail = null;
        } else {
            tail = current.previous;
            tail.next = null;
            current.previous = null;
        }

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

        TwoWayNode<T> current = head;
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

        TwoWayNode<T> current = head;

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

            TwoWayNode<T> current = head;

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

    /**
     * {@inheritDoc}
     * <p>
     * Returns string version of the data structure in reverse. Uses brackets to identify start and end of collection.
     * Separates the values using comma and space.
     */
    public String toStringReverse() {
        final StringBuilder stringBuilder = new StringBuilder();

        if (0 == size) {
            stringBuilder.append("[]");
        } else {
            stringBuilder.append("[");

            TwoWayNode<T> current = tail;

            while (null != current) {
                stringBuilder.append(current.value);

                if (null != current.previous) {
                    stringBuilder.append(",").append(" ");
                }

                current = current.previous;
            }

            stringBuilder.append("]");
        }

        return stringBuilder.toString();
    }

    /**
     * Node class that stores the associated value and has a pointer to both the next and the previous nodes in a
     * collection of nodes.
     *
     * @param <T> Generic data type supported by the Node.
     */
    @Data
    @Builder
    @AllArgsConstructor
    @RequiredArgsConstructor
    public static class TwoWayNode<T> {
        /*
         * Value stored in the node.
         */
        @NonNull
        private final T value;

        /*
         * Pointer to the previous node in a collection of nodes.
         */
        private TwoWayNode<T> previous;

        /*
         * Pointer to the next node in a collection of nodes.
         */
        private TwoWayNode<T> next;
    }
}
