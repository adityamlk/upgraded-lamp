package com.adityamlk.codelibrary.problem.cracking.easy;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

/**
 * Covers problems from Chapter 4: Trees and Graphs.
 */
public class Chapter4Solutions {

    /*
     * Problem 1: Given a directed graph, determine whether there is a route between two nodes.
     */

    /**
     * Determines whether the start node and the end node have a path between them. Uses breadth-first search to find
     * the path. Uses a queue to track the nodes to visit. Visits nodes at most once.
     * <p>
     * Time Complexity: O(N); need to process at most all nodes to determine the path.
     * <p>
     * Space Complexity: O(N); need to track at most all nodes in a queue.
     *
     * @param graph Mark all nodes as unvisited.
     * @param start Starting point of the search.
     * @param end   Ending point of the search.
     * @return True if the route between the two nodes exists, false otherwise.
     */
    public static <T> boolean isRouteBetweenTwoNodes(
            @NonNull final LocalTestGraph<T> graph,
            @NonNull final LocalTestNode<T> start,
            @NonNull final LocalTestNode<T> end) {
        for (final LocalTestNode<T> node : graph.nodes) {
            node.visited = false;
        }

        final Queue<LocalTestNode<T>> nodesToVisitQueue = Lists.newLinkedList();
        nodesToVisitQueue.offer(start);

        while (!nodesToVisitQueue.isEmpty()) {
            final LocalTestNode<T> nodeToVisit = nodesToVisitQueue.poll();

            if (end.equals(nodeToVisit)) {
                return true;
            }

            nodesToVisitQueue.addAll(getNeighborsToVisit(nodeToVisit));
        }

        return false;
    }

    private static <T> Set<LocalTestNode<T>> getNeighborsToVisit(final LocalTestNode<T> nodeToVisit) {
        final Set<LocalTestNode<T>> neighborsToVisit = Sets.newHashSet();
        nodeToVisit.visited = true;

        for (final LocalTestNode<T> neighbor : nodeToVisit.adjacentNodes) {
            if (!neighbor.visited) {
                neighborsToVisit.add(neighbor);
            }
        }

        return neighborsToVisit;
    }

    /*
     * Problem 2: Given a sorted array, construct a binary search tree with minimal height.
     */

    /**
     * Converts provided sorted array into a binary search tree with minimal height (i.e. complete). Uses depth-first
     * search to find the path. Uses recursion to construct the tree.
     * <p>
     * Time Complexity: O(N); need to process all nodes to construct the full tree.
     * <p>
     * Space Complexity: O(N); need to track all nodes in recursion stack.
     *
     * @param sortedArray Array to use for tree construction.
     * @return Root node of the binary search tree.
     */
    public static <T> LocalTestNode<T> getBinarySearchTreeWithMinHeight(@NonNull final T[] sortedArray) {
        return getBinarySearchTree(sortedArray, 0, sortedArray.length - 1);
    }

    private static <T> LocalTestNode<T> getBinarySearchTree(final T[] array, final int minIndex, final int maxIndex) {
        if (maxIndex < minIndex) {
            return null;
        }

        final int medianIndex = (int) Math.ceil((maxIndex - minIndex) / (double) 2);
        final int valueIndex = minIndex + medianIndex;
        final LocalTestNode<T> medianNode =
                LocalTestNode.<T>builder()
                        .value(array[valueIndex])
                        .build();
        medianNode.adjacentNodes.add(getBinarySearchTree(array, minIndex, valueIndex - 1));
        medianNode.adjacentNodes.add(getBinarySearchTree(array, valueIndex + 1, maxIndex));
        return medianNode;
    }

    @Getter
    @Builder
    public static class LocalTestGraph<T> {
        @NonNull
        private final List<LocalTestNode<T>> nodes;
    }

    @Getter
    @Builder
    public static class LocalTestNode<T> {
        @NonNull
        private final T value;

        @NonNull
        private final List<LocalTestNode<T>> adjacentNodes = Lists.newArrayList();

        private boolean visited;
    }
}
