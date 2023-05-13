package com.adityamlk.codelibrary.problem.cracking.easy;

import com.adityamlk.codelibrary.problem.cracking.easy.Chapter2Solutions.LocalTestNode;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import static com.adityamlk.codelibrary.problem.cracking.easy.Chapter2Solutions.getKthToLast;
import static com.adityamlk.codelibrary.problem.cracking.easy.Chapter2Solutions.removeDuplicates;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Chapter2SolutionsTest {

    /*
     * Problem 1: Remove duplicates from an unsorted linked list.
     */

    @Test
    public void isUniqueFirstTest() {
        final LocalTestNode<Integer> linkedListToModify = new LocalTestNode<>(1);
        linkedListToModify.addElements(Lists.newArrayList(2, 3, 2, 4, 3));
        final LocalTestNode<Integer> linkedListExpected = new LocalTestNode<>(1);
        linkedListExpected.addElements(Lists.newArrayList(2, 3, 4));

        final LocalTestNode<Integer> linkedListReturned = removeDuplicates(linkedListToModify);

        assertThat("Result is incorrect.", linkedListReturned, is(linkedListExpected));
    }

    @Test
    public void isUniqueSecondTest() {
        final LocalTestNode<Integer> linkedListToModify = new LocalTestNode<>(1);
        linkedListToModify.addElements(Lists.newArrayList(1, 1, 1, 1, 1, 1));
        final LocalTestNode<Integer> linkedListExpected = new LocalTestNode<>(1);

        final LocalTestNode<Integer> linkedListReturned = removeDuplicates(linkedListToModify);

        assertThat("Result is incorrect.", linkedListReturned, is(linkedListExpected));
    }

    @Test
    public void isUniqueThirdTest() {
        final LocalTestNode<String> linkedListToModify = new LocalTestNode<>("blah");
        linkedListToModify.addElements(Lists.newArrayList("hello", "blah", "goodbye", "hello", "goodbye"));
        final LocalTestNode<String> linkedListExpected = new LocalTestNode<>("blah");
        linkedListExpected.addElements(Lists.newArrayList("hello", "goodbye"));

        final LocalTestNode<String> linkedListReturned = removeDuplicates(linkedListToModify);

        assertThat("Result is incorrect.", linkedListReturned, is(linkedListExpected));
    }

    /*
     * Problem 2: Retrieve the kth to last element from the singly linked list.
     */

    @Test
    public void getKthToLastFirstTest() {
        final LocalTestNode<Integer> linkedList = new LocalTestNode<>(12);
        linkedList.addElements(Lists.newArrayList(44, 23, 77, 999, 56));

        final int elementReturned = getKthToLast(linkedList, 2);

        assertThat("Result is incorrect.", elementReturned, is(999));
    }

    @Test
    public void getKthToLastSecondTest() {
        final LocalTestNode<Integer> linkedList = new LocalTestNode<>(12);
        linkedList.addElements(Lists.newArrayList(44, 23, 77, 999, 56));

        final int elementReturned = getKthToLast(linkedList, 1);

        assertThat("Result is incorrect.", elementReturned, is(56));
    }

    @Test
    public void getKthToLastThirdTest() {
        final LocalTestNode<Integer> linkedList = new LocalTestNode<>(12);
        linkedList.addElements(Lists.newArrayList(44, 23, 77, 999, 56));

        final int elementReturned = getKthToLast(linkedList, 6);

        assertThat("Result is incorrect.", elementReturned, is(12));
    }

    @Test
    public void getKthToLastFourthTest() {
        final LocalTestNode<Integer> linkedList = new LocalTestNode<>(12);
        linkedList.addElements(Lists.newArrayList(44, 23, 77, 999, 56));

        final int elementReturned = getKthToLast(linkedList, 0);

        assertThat("Result is incorrect.", elementReturned, is(56));
    }

    @Test
    public void getKthToLastFifthTest() {
        final LocalTestNode<Integer> linkedList = new LocalTestNode<>(12);
        linkedList.addElements(Lists.newArrayList(44, 23, 77, 999, 56));

        final IllegalArgumentException exceptionThrown =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> getKthToLast(linkedList, 7),
                        "Incorrect exception thrown.");

        assertThat(
                "Exception message is incorrect.",
                exceptionThrown.getMessage(),
                containsString("Position to use is too large compared to the nodes."));
    }
}
