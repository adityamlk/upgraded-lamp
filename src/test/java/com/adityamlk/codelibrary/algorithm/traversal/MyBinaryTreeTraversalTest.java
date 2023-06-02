package com.adityamlk.codelibrary.algorithm.traversal;

import com.adityamlk.codelibrary.datastructure.tree.MyBinarySearchTree;
import com.adityamlk.codelibrary.datastructure.tree.MyBinarySearchTree.BSTNode;
import com.google.common.collect.ImmutableList;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Log4j2
public class MyBinaryTreeTraversalTest {

    private final MyBinaryTreeTraversal<Integer> myBinaryTreeTraversal = new MyBinaryTreeTraversal<>();

    @AfterAll
    public static void cleanup() {
        System.gc();
    }

    @Test
    public void inOrderTraversalTest() {
        final BSTNode<Integer> rootNode = getRootOfBST();

        final List<Integer> result = myBinaryTreeTraversal.convertTreeToListInOrder(rootNode);

        assertThat("Result does not match.", result, is(getExpectedInOrderTraversalResult()));
    }

    @Test
    public void preOrderTraversalTest() {
        final BSTNode<Integer> rootNode = getRootOfBST();

        final List<Integer> result = myBinaryTreeTraversal.convertTreeToListPreOrder(rootNode);

        assertThat("Result does not match.", result, is(getExpectedPreOrderTraversalResult()));
    }

    @Test
    public void postOrderTraversalTest() {
        final BSTNode<Integer> rootNode = getRootOfBST();

        final List<Integer> result = myBinaryTreeTraversal.convertTreeToListPostOrder(rootNode);

        assertThat("Result does not match.", result, is(getExpectedPostOrderTraversalResult()));
    }

    private BSTNode<Integer> getRootOfBST() {
        final MyBinarySearchTree<Integer> myBinarySearchTree = new MyBinarySearchTree<>();
        myBinarySearchTree.insert(44);
        myBinarySearchTree.insert(31);
        myBinarySearchTree.insert(23);
        myBinarySearchTree.insert(8);
        myBinarySearchTree.insert(26);
        myBinarySearchTree.insert(38);
        myBinarySearchTree.insert(35);
        myBinarySearchTree.insert(41);
        myBinarySearchTree.insert(88);
        myBinarySearchTree.insert(91);
        myBinarySearchTree.insert(56);
        myBinarySearchTree.insert(48);
        myBinarySearchTree.insert(77);
        myBinarySearchTree.insert(89);
        myBinarySearchTree.insert(99);

        return myBinarySearchTree.getRoot();
    }

    private List<Integer> getExpectedInOrderTraversalResult() {
        return ImmutableList.of(8, 23, 26, 31, 35, 38, 41, 44, 48, 56, 77, 88, 89, 91, 99);
    }

    private List<Integer> getExpectedPreOrderTraversalResult() {
        return ImmutableList.of(44, 31, 23, 8, 26, 38, 35, 41, 88, 56, 48, 77, 91, 89, 99);
    }

    private List<Integer> getExpectedPostOrderTraversalResult() {
        return ImmutableList.of(8, 26, 23, 35, 41, 38, 31, 48, 77, 56, 89, 99, 91, 88, 44);
    }
}
