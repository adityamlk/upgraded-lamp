package com.adityamlk.codelibrary.datastructure.collection;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

/**
 * Represents a Stack implementation. This is a wrapper around {@link MyDoubleLinkedList} and implements all methods
 * through this class.
 * <p>
 * Insertion is O(1) to the front of the stack.
 * Deletion is O(1) from the front of the stack.
 * Search is O(N) when using the provided value and O(1) when retrieving from the front of the stack.
 *
 * @param <T> Generic data type supported by the stack.
 */
@Log4j2
@EqualsAndHashCode
public class MyStack<T> {

    /*
     * Internal collection that represents a stack. The doubly linked list will be able to add and remove from the head
     * to mimic a stack.
     */
    @NonNull
    private final MyDoubleLinkedList<T> internalCollection;

    /**
     * Default Constructor.
     */
    public MyStack() {
        internalCollection = new MyDoubleLinkedList<>();
    }

    /**
     * Inserts the given value to the front of the data structure.
     *
     * @param valueToInsert {@link T}
     */
    public void push(@NonNull final T valueToInsert) {
        internalCollection.insertToHead(valueToInsert);
    }

    /**
     * Returns the value at the front of the data structure.
     *
     * @return Value that is at the front, or null if the data structure is empty.
     */
    public T peek() {
        return internalCollection.getFromHead();
    }

    /**
     * Removes the value at the front of the data structure.
     *
     * @return Value that was removed from the front, or null if the data structure is empty.
     */
    public T pop() {
        return internalCollection.removeFromHead();
    }

    /**
     * Checks for the given value.
     *
     * @param valueToSearch {@link T}
     * @return True if the data structure contains this value, false otherwise.
     */
    public boolean contains(@NonNull final T valueToSearch) {
        return internalCollection.contains(valueToSearch);
    }

    /**
     * @return Number of values in the data structure.
     */
    public int getSize() {
        return internalCollection.getSize();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns string version of the data structure. Uses brackets to identify start and end of collection. Separates
     * the values using comma and space.
     */
    @Override
    public String toString() {
        return internalCollection.toStringReverse();
    }
}
