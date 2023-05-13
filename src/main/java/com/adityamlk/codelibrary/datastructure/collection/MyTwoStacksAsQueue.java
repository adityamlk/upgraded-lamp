package com.adityamlk.codelibrary.datastructure.collection;

import java.util.Stack;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Represents a Queue as two Stacks implementation. Tracks two stacks to represent a queue. The idea is to maintain a
 * stack that will hold values when enqueue occurs and one that will hold values when dequeue occurs. The dedicate stacks
 * mean that objects will be added or removed correctly in relation to the other elements in the data store. This means
 * any and all elements are moved out of retrieval stack into insertion stack for enqueues and vice a versa for dequeues.
 * <p>
 * Insertion is O(1) despite resizing due to amortized insertion. Inserts to the top of insertion stack.
 * Deletion is O(1) despite resizing due to amortized deletion. Removes from the top of retrieval stack.
 * Search is O(N).
 *
 * @param <T> Generic data type supported by the list.
 */
@NoArgsConstructor
public class MyTwoStacksAsQueue<T> {

    /*
     * Used for tracking values when an insertion occurs. Will track these values in reverse order so the latest
     * insertion keeps the overall order of values intact.
     */
    @NonNull
    private final Stack<T> insertionStack = new Stack<>();

    /*
     * Used for tracking values when a retrieval occurs. Will track these values in order of insertion so the latest
     * retrieval keeps the overall order of values intact.
     */
    @NonNull
    private final Stack<T> retrievalStack = new Stack<>();

    /**
     * Inserts the given value to the tail of the queue.
     *
     * @param valueToEnqueue {@link T}
     */
    public void enqueue(@NonNull final T valueToEnqueue) {
        // If the retrieval stack is not empty, then move the elements to the insertion stack.
        if (!retrievalStack.isEmpty()) {
            moveElementsFromStack(retrievalStack, insertionStack);
        }

        insertionStack.push(valueToEnqueue);
    }

    /**
     * Retrieves the value at the head of the queue.
     *
     * @return Value at the head of the queue.
     */
    public T dequeue() {
        // If the insertion stack is not empty, then move the elements to the retrieval stack.
        if (!insertionStack.isEmpty()) {
            moveElementsFromStack(insertionStack, retrievalStack);
        }

        return retrievalStack.pop();
    }

    /**
     * Checks for the given element.
     *
     * @param elementToSearch {@link T}
     * @return True if the data structure contains this element, false otherwise.
     */
    public boolean contains(@NonNull final T elementToSearch) {
        boolean isElementInStack = false;
        final Stack<T> stackToSearch = insertionStack.isEmpty() ? retrievalStack : insertionStack;

        // Depending on which stack is in use, loop through each element looking for a match.
        for (final T element : stackToSearch) {
            if (elementToSearch.equals(element)) {
                isElementInStack = true;
                break;
            }
        }

        return isElementInStack;
    }

    /**
     * @return Number of values in the data structure.
     */
    public int getSize() {
        return insertionStack.isEmpty() ? retrievalStack.size() : insertionStack.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        // If the retrieval stack is not empty, then move the elements to the insertion stack.
        if (!retrievalStack.isEmpty()) {
            moveElementsFromStack(retrievalStack, insertionStack);
        }

        return insertionStack.toString();
    }

    /*
     * Moves elements from one stack to another. This will pop off from the source stack and push into the destination
     * stack until the source stack is empty.
     */
    private void moveElementsFromStack(final Stack<T> stackSource, final Stack<T> stackDestination) {
        while (!stackSource.isEmpty()) {
            final T elementToMove = stackSource.pop();
            stackDestination.push(elementToMove);
        }
    }
}
