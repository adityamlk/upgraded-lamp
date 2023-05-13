package com.adityamlk.codelibrary.datastructure.tree;

import java.util.LinkedList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Represents a Binary Search Tree implementation. Tracks all nodes using a root node. Each node has a left and a right
 * child. Ordering in the tree is based on the tradition definition where left <= node <= right. For removals, if the
 * node to remove has both children populated, then the traversal finds the left-most node from the right subtree,
 * replaces the value, and then removes that duplicate node by traversing down again.
 * <p>
 * Insertion is O(logN) assuming random inputs, O(N) with sorted inputs.
 * Deletion is O(logN) assuming balanced tree, O(n) with unbalanced/linear tree.
 * Search is O(logN) assuming balanced tree, O(n) with unbalanced/linear tree.
 *
 * @param <T> Generic data type supported by the tree.
 */
@Log4j2
@EqualsAndHashCode
public class MyBinarySearchTree<T extends Comparable<T>> {

    /*
     * Tracks the number of nodes in the tree.
     */
    @NonNull
    private Integer size;

    /*
     * Pointer to the head of the structure.
     */
    private BSTNode<T> root;

    /**
     * Default Constructor.
     */
    public MyBinarySearchTree() {
        root = null;
        size = 0;
    }

    /**
     * Inserts the given value into the data structure. Will maintain left <= node <= right order.
     *
     * @param valueToInsert {@link T}
     */
    public void insert(@NonNull final T valueToInsert) {
        final TraversalTracker insertTracker = new TraversalTracker();
        root = insert(root, valueToInsert, insertTracker);

        if (insertTracker.didInsertNode) {
            size += 1;
        }
    }

    /*
     * Recursive method that inserts the given value to the provided node's subtree. Traverses the tree based on
     * comparison check between the node's value and the value to search.
     *
     * Will create new node with the value if the node to process is null (meaning leaf). Otherwise, will check which
     * direction to go based on the comparison.
     */
    private BSTNode<T> insert(final BSTNode<T> nodeToProcess, final T valueToInsert, final TraversalTracker tracker) {
        if (null == nodeToProcess) {
            tracker.didInsertNode = true;
            return new BSTNode<>(valueToInsert);
        }

        final int compareResult = valueToInsert.compareTo(nodeToProcess.value);

        if (0 >= compareResult) {
            nodeToProcess.leftChild = insert(nodeToProcess.leftChild, valueToInsert, tracker);
        } else {
            nodeToProcess.rightChild = insert(nodeToProcess.rightChild, valueToInsert, tracker);
        }

        return nodeToProcess;
    }

    /**
     * Removes the given value into the data structure. Will maintain left <= node <= right order.
     *
     * @param valueToRemove {@link T}
     */
    public T remove(@NonNull final T valueToRemove) {
        final TraversalTracker removeTracker = new TraversalTracker();
        root = remove(root, valueToRemove, removeTracker);

        if (removeTracker.didRemoveNode) {
            size -= 1;
            return valueToRemove;
        }

        return null;
    }

    /*
     * Recursive method that removes the given value from the provided node's subtree. Traverses the tree based on
     * comparison check between the node's value and the value to search.
     *
     * If the node is null, then reached the end of a subtree without a match and need to go back. Otherwise, will check
     * which direction to go based on the comparison. If the node should be removed, then performs additional logic.
     * Otherwise, removes from one of the children.
     *
     * Removal is trickier than traversal or insertion because a node with one or both children will require another
     * node to take its place. Depending on which children the node has, different movement will take place.
     */
    private BSTNode<T> remove(final BSTNode<T> nodeToProcess, final T valueToRemove, final TraversalTracker tracker) {
        if (null == nodeToProcess) {
            return null;
        }

        final int compareResult = valueToRemove.compareTo(nodeToProcess.value);
        BSTNode<T> nodeToReturn = nodeToProcess;

        if (0 == compareResult) {
            nodeToReturn = removeNode(nodeToProcess, tracker);
        } else if (0 > compareResult) {
            nodeToReturn.leftChild = remove(nodeToProcess.leftChild, valueToRemove, tracker);
        } else {
            nodeToReturn.rightChild = remove(nodeToProcess.rightChild, valueToRemove, tracker);
        }

        return nodeToReturn;
    }

    /*
     * Removes the given node by returning its replacement.
     *
     * If neither child exists, then return null since this is now a leaf node for the parent.
     * If either left or right child exists, then return this child so the parent can point to it.
     * If both children exist, then do the following:
     *   (1) get the left-most node from node-to-remove's right subtree
     *   (2) set node-to-return with this left-most node's value
     *   (3) set node-to-return's left child to node-to-remove's left child
     *   (4) set node-to-return's right child to result of removing the left-most node from node-to-remove's right subtree
     *
     * This logic will ensure the tree's order remains correct while limiting additional traversal.
     */
    private BSTNode<T> removeNode(final BSTNode<T> nodeToRemove, final TraversalTracker tracker) {
        final boolean doesLeftChildExist = null != nodeToRemove.leftChild;
        final boolean doesRightChildExist = null != nodeToRemove.rightChild;
        tracker.didRemoveNode = true;

        if (!doesLeftChildExist && !doesRightChildExist) {
            return null;
        } else if (doesLeftChildExist && !doesRightChildExist) {
            return nodeToRemove.leftChild;
        } else if (!doesLeftChildExist) {
            return nodeToRemove.rightChild;
        } else {
            final BSTNode<T> leftMostNode = getMinNode(nodeToRemove.rightChild);
            final BSTNode<T> nodeToReturn = new BSTNode<>(leftMostNode.value);
            nodeToReturn.leftChild = nodeToRemove.leftChild;
            nodeToReturn.rightChild = remove(nodeToRemove.rightChild, leftMostNode.value, tracker);

            return nodeToReturn;
        }
    }

    /**
     * Returns the minimum value of the data structure.
     *
     * @return Value that is the minimum, or null if the data structure is empty.
     */
    public T getMinValue() {
        final BSTNode<T> minNode = getMinNode(root);

        if (null == minNode) {
            return null;
        } else {
            return minNode.value;
        }
    }

    /*
     * Iterative method that traverses through the tree to get the node with the smallest value. The left-most node in
     * the BST is the one with the smallest value. Goes down the left-side of the tree until it finds the final node
     * and returns it.
     */
    private BSTNode<T> getMinNode(final BSTNode<T> nodeToProcess) {
        BSTNode<T> leftMostNode = nodeToProcess;

        while (null != leftMostNode && null != leftMostNode.leftChild) {
            leftMostNode = leftMostNode.leftChild;
        }

        return leftMostNode;
    }

    /**
     * Returns the maximum value of the data structure.
     *
     * @return Value that is the maximum, or null if the data structure is empty.
     */
    public T getMaxValue() {
        final BSTNode<T> maxNode = getMaxNode(root);

        if (null == maxNode) {
            return null;
        } else {
            return maxNode.value;
        }
    }

    /*
     * Iterative method that traverses through the tree to get the node with the largest value. The right-most node in
     * the BST is the one with the largest value. Goes down the right-side of the tree until it finds the final node
     * and returns it.
     */
    private BSTNode<T> getMaxNode(final BSTNode<T> nodeToProcess) {
        BSTNode<T> rightMostNode = nodeToProcess;

        while (null != rightMostNode && null != rightMostNode.rightChild) {
            rightMostNode = rightMostNode.rightChild;
        }

        return rightMostNode;
    }

    /**
     * Checks for the given value.
     *
     * @param valueToSearch {@link T}
     * @return True if the data structure contains this value, false otherwise.
     */
    public boolean contains(@NonNull final T valueToSearch) {
        return contains(root, valueToSearch);
    }

    /*
     * Recursive method that checks whether the node to process contains the value to search. Compares the node's value
     * to the one to search and traverses/stops based on that. If the node is null, then return false. If the node's
     * value matches, then returns true. Otherwise, traverses one of the children based on the comparison result.
     */
    private boolean contains(final BSTNode<T> nodeToProcess, final T valueToSearch) {
        if (null == nodeToProcess) {
            return false;
        }

        final int compareResult = valueToSearch.compareTo(nodeToProcess.value);

        if (0 == compareResult) {
            return true;
        } else if (0 > compareResult) {
            return contains(nodeToProcess.leftChild, valueToSearch);
        } else {
            return contains(nodeToProcess.rightChild, valueToSearch);
        }
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
        final LinkedList<T> collectionOfValues = new LinkedList<>();
        toString(root, collectionOfValues);

        return collectionOfValues.toString();
    }

    /*
     * Recursive method that performs in-order traversal to collect values of nodes. This traversal will ensure
     * collection will contain the values in their natural order.
     */
    private void toString(final BSTNode<T> nodeToProcess, final LinkedList<T> collectionOfValues) {
        if (null == nodeToProcess) {
            return;
        }

        toString(nodeToProcess.leftChild, collectionOfValues);
        collectionOfValues.add(nodeToProcess.value);
        toString(nodeToProcess.rightChild, collectionOfValues);
    }

    /**
     * Node class that stores the associated value and has a pointer to both the left and right child nodes in a
     * tree of nodes. The node supports a BST with its left child being less than or equal and its right child being
     * greater than.
     *
     * @param <T> Generic data type supported by the Node.
     */
    @Data
    @Builder
    @AllArgsConstructor
    @RequiredArgsConstructor
    public static class BSTNode<T extends Comparable<T>> {
        /*
         * Value stored in the node.
         */
        @NonNull
        private final T value;

        /*
         * Left child of this node. Its value is less than or equal to this node's value.
         */
        private BSTNode<T> leftChild;

        /*
         * Right child of this node. Its value is greater than this node's value.
         */
        private BSTNode<T> rightChild;
    }

    /**
     * Inner class that tracks whether the tree has changed from an operation that updates the tree (e.g. insert or
     * remove). This class is useful for flagging that the subtree changed and persisting this state when navigating
     * through the tree recursively. This can be useful during insert and remove operations, where the core change to
     * the tree can update this tracker, and the original caller can act on the changed flag, like updating tree size.
     */
    @Data
    @Builder
    @AllArgsConstructor
    @RequiredArgsConstructor
    public static class TraversalTracker {
        /*
         * Flag that indicates whether tree traversal successfully inserted a node.
         */
        private boolean didInsertNode;

        /*
         * Flag that indicates whether tree traversal successfully removed a node.
         */
        private boolean didRemoveNode;
    }
}
