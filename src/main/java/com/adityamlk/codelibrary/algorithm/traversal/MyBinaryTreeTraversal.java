package com.adityamlk.codelibrary.algorithm.traversal;

import com.google.common.collect.ImmutableList;
import java.util.LinkedList;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

import static com.adityamlk.codelibrary.datastructure.tree.MyBinarySearchTree.BSTNode;

/**
 * Traversal class that provides methods that perform Binary Tree traversal. Supports in-order, pre-order, and
 * post-order traversal. For each traversal method, this accepts the root node of a BST and returns a list with the
 * values in requested order.
 * <p>
 * Traversal is O(N) for all methods since each node requires one visit.
 *
 * @param <T> Generic data type supported by the tree.
 */
@Log4j2
@NoArgsConstructor
public class MyBinaryTreeTraversal<T extends Comparable<T>> {

    /**
     * Traverses the BST backed by the provided root node in-order, which means this will process the left child, add
     * the current value, and process the right child at each node.
     *
     * @param treeRootNode {@link BSTNode<T>}
     * @return List of elements as a result of in-order traversal of BST using provided root node.
     */
    public List<T> convertTreeToListInOrder(@NonNull final BSTNode<T> treeRootNode) {
        final List<T> trackingList = new LinkedList<>();
        traverseInOrder(treeRootNode, trackingList);

        return ImmutableList.copyOf(trackingList);
    }

    /*
     * Traverses sequence of nodes starting with the current node in-order. Updates the tracking list with value of
     * middle node while traversing the child nodes.
     */
    private void traverseInOrder(final BSTNode<T> currentNode, final List<T> trackingList) {
        if (null == currentNode) {
            return;
        }

        traverseInOrder(currentNode.getLeftChild(), trackingList);
        trackingList.add(currentNode.getValue());
        traverseInOrder(currentNode.getRightChild(), trackingList);
    }

    /**
     * Traverses the BST backed by the provided root node pre-order, which means this will add the current value,
     * process the left child, and process the right child at each node.
     *
     * @param treeRootNode {@link BSTNode<T>}
     * @return List of elements as a result of pre-order traversal of BST using provided root node.
     */
    public List<T> convertTreeToListPreOrder(@NonNull final BSTNode<T> treeRootNode) {
        final List<T> trackingList = new LinkedList<>();
        traversePreOrder(treeRootNode, trackingList);

        return ImmutableList.copyOf(trackingList);
    }

    /*
     * Traverses sequence of nodes starting with the current node pre-order. Updates the tracking list with value of
     * middle node while traversing the child nodes.
     */
    private void traversePreOrder(final BSTNode<T> currentNode, final List<T> trackingList) {
        if (null == currentNode) {
            return;
        }

        trackingList.add(currentNode.getValue());
        traversePreOrder(currentNode.getLeftChild(), trackingList);
        traversePreOrder(currentNode.getRightChild(), trackingList);
    }

    /**
     * Traverses the BST backed by the provided root node post-order, which means this will process the left child,
     * process the right child, add the current value at each node.
     *
     * @param treeRootNode {@link BSTNode<T>}
     * @return List of elements as a result of post-order traversal of BST using provided root node.
     */
    public List<T> convertTreeToListPostOrder(@NonNull final BSTNode<T> treeRootNode) {
        final List<T> trackingList = new LinkedList<>();
        traversePostOrder(treeRootNode, trackingList);

        return ImmutableList.copyOf(trackingList);
    }

    /*
     * Traverses sequence of nodes starting with the current node post-order. Updates the tracking list with value of
     * middle node while traversing the child nodes.
     */
    private void traversePostOrder(final BSTNode<T> currentNode, final List<T> trackingList) {
        if (null == currentNode) {
            return;
        }

        traversePostOrder(currentNode.getLeftChild(), trackingList);
        traversePostOrder(currentNode.getRightChild(), trackingList);
        trackingList.add(currentNode.getValue());
    }
}
