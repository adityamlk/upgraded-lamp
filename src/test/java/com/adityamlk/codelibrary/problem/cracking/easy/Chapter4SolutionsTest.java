package com.adityamlk.codelibrary.problem.cracking.easy;

import com.adityamlk.codelibrary.problem.cracking.easy.Chapter4Solutions.LocalTestGraph;
import com.adityamlk.codelibrary.problem.cracking.easy.Chapter4Solutions.LocalTestNode;
import com.google.common.collect.Lists;
import java.util.List;
import org.junit.jupiter.api.Test;

import static com.adityamlk.codelibrary.problem.cracking.easy.Chapter4Solutions.getBinarySearchTreeWithMinHeight;
import static com.adityamlk.codelibrary.problem.cracking.easy.Chapter4Solutions.isRouteBetweenTwoNodes;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Chapter4SolutionsTest {

    /*
     * Problem 1: Given a directed graph, determine whether there is a route between two nodes.
     */

    @Test
    public void isRouteBetweenTwoNodesFirstTest() {
        final LocalTestGraph<Integer> graph = getGraphForTest();
        final LocalTestNode<Integer> start = graph.getNodes().get(0);
        final LocalTestNode<Integer> end = graph.getNodes().get(3);

        final boolean resultReturned = isRouteBetweenTwoNodes(graph, start, end);

        assertThat("Result is incorrect.", resultReturned, is(true));
    }

    @Test
    public void isRouteBetweenTwoNodesSecondTest() {
        final LocalTestGraph<Integer> graph = getGraphForTest();
        final LocalTestNode<Integer> start = graph.getNodes().get(2);
        final LocalTestNode<Integer> end = graph.getNodes().get(4);

        final boolean resultReturned = isRouteBetweenTwoNodes(graph, start, end);

        assertThat("Result is incorrect.", resultReturned, is(true));
    }

    @Test
    public void isRouteBetweenTwoNodesThirdTest() {
        final LocalTestGraph<Integer> graph = getGraphForTest();
        final LocalTestNode<Integer> start = graph.getNodes().get(5);
        final LocalTestNode<Integer> end = graph.getNodes().get(0);

        final boolean resultReturned = isRouteBetweenTwoNodes(graph, start, end);

        assertThat("Result is incorrect.", resultReturned, is(false));
    }

    @Test
    public void isRouteBetweenTwoNodesFourthTest() {
        final LocalTestGraph<Integer> graph =
                LocalTestGraph.<Integer>builder()
                        .nodes(Lists.newArrayList())
                        .build();
        final LocalTestNode<Integer> start =
                LocalTestNode.<Integer>builder()
                        .value(1)
                        .visited(false)
                        .build();
        final LocalTestNode<Integer> end =
                LocalTestNode.<Integer>builder()
                        .value(2)
                        .visited(false)
                        .build();

        final boolean resultReturned = isRouteBetweenTwoNodes(graph, start, end);

        assertThat("Result is incorrect.", resultReturned, is(false));
    }

    /*
     * Problem 2: Given a sorted array, construct a binary search tree with minimal height.
     */

    @Test
    public void getBinarySearchTreeFirstTest() {
        final List<Integer> sortedList = Lists.newArrayList(1, 2, 3, 4, 5);

        final LocalTestNode<Integer> nodeReturned = getBinarySearchTreeWithMinHeight(sortedList.toArray(new Integer[]{}));

        final List<Integer> listOfNodeData = Lists.newArrayList();
        convertTreeToList(nodeReturned, listOfNodeData);

        assertThat("Result is incorrect.", listOfNodeData.toString(), is(sortedList.toString()));
    }

    @Test
    public void getBinarySearchTreeSecondTest() {
        final List<Integer> sortedList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        final LocalTestNode<Integer> nodeReturned = getBinarySearchTreeWithMinHeight(sortedList.toArray(new Integer[]{}));

        final List<Integer> listOfNodeData = Lists.newArrayList();
        convertTreeToList(nodeReturned, listOfNodeData);

        assertThat("Result is incorrect.", listOfNodeData.toString(), is(sortedList.toString()));
    }

    @Test
    public void getBinarySearchTreeThirdTest() {
        final List<Integer> sortedList = Lists.newArrayList(1);

        final LocalTestNode<Integer> nodeReturned = getBinarySearchTreeWithMinHeight(sortedList.toArray(new Integer[]{}));

        final List<Integer> listOfNodeData = Lists.newArrayList();
        convertTreeToList(nodeReturned, listOfNodeData);

        assertThat("Result is incorrect.", listOfNodeData.toString(), is(sortedList.toString()));
    }

    @Test
    public void getBinarySearchTreeFourthTest() {
        final LocalTestNode<Integer> nodeReturned = getBinarySearchTreeWithMinHeight(new Integer[]{});

        final List<Integer> listOfNodeData = Lists.newArrayList();
        convertTreeToList(nodeReturned, listOfNodeData);

        assertThat("Result is incorrect.", listOfNodeData.toString(), is(Lists.newArrayList().toString()));
    }

    private LocalTestGraph<Integer> getGraphForTest() {
        final LocalTestNode<Integer> firstNode =
                LocalTestNode.<Integer>builder()
                        .value(0)
                        .visited(false)
                        .build();
        final LocalTestNode<Integer> secondNode =
                LocalTestNode.<Integer>builder()
                        .value(1)
                        .visited(false)
                        .build();
        final LocalTestNode<Integer> thirdNode =
                LocalTestNode.<Integer>builder()
                        .value(2)
                        .visited(false)
                        .build();
        final LocalTestNode<Integer> fourthNode =
                LocalTestNode.<Integer>builder()
                        .value(3)
                        .visited(false)
                        .build();
        final LocalTestNode<Integer> fifthNode =
                LocalTestNode.<Integer>builder()
                        .value(4)
                        .visited(false)
                        .build();
        final LocalTestNode<Integer> sixthNode =
                LocalTestNode.<Integer>builder()
                        .value(5)
                        .visited(false)
                        .build();

        firstNode.getAdjacentNodes().add(secondNode);
        firstNode.getAdjacentNodes().add(fifthNode);
        firstNode.getAdjacentNodes().add(sixthNode);
        secondNode.getAdjacentNodes().add(fourthNode);
        secondNode.getAdjacentNodes().add(fifthNode);
        thirdNode.getAdjacentNodes().add(secondNode);
        fourthNode.getAdjacentNodes().add(thirdNode);
        fourthNode.getAdjacentNodes().add(fifthNode);

        return LocalTestGraph.<Integer>builder()
                .nodes(
                        Lists.newArrayList(
                                firstNode,
                                secondNode,
                                thirdNode,
                                fourthNode,
                                fifthNode,
                                sixthNode))
                .build();
    }

    private void convertTreeToList(final LocalTestNode<Integer> node, final List<Integer> list) {
        if (null != node) {
            final LocalTestNode<Integer> left = node.getAdjacentNodes().get(0);
            final LocalTestNode<Integer> right = node.getAdjacentNodes().get(1);
            convertTreeToList(left, list);
            list.add(node.getValue());
            convertTreeToList(right, list);
        }
    }
}
