package com.adityamlk.codelibrary.algorithm.traversal;

import com.adityamlk.codelibrary.datastructure.graph.MyGraph;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

/**
 * Traversal class that can perform Breadth-First Search (BFS) on a provided graph and determine whether a path exists
 * between the starting node and the ending node.
 * <p>
 * Traversal is O(N) since all nodes may require a visit.
 *
 * @param <T> Generic data type supported by the graph.
 */
@Log4j2
@NoArgsConstructor
public class MyBreadthFirstSearch<T extends Comparable<T>> {

    /**
     * Determines whether a path exists between two nodes using the provided graph via Breadth-First Search (DFS)
     * traversal. Will avoid visiting the same node more than once. Will prioritize immediate neighbors over successors
     * when traversing, hence breadth-first approach.
     *
     * @param start {@link T}
     * @param end   {@link T}
     * @param graph {@link MyGraph}
     * @return True if a path exists between the two values, false otherwise.
     */
    public boolean doesPathExist(@NonNull final T start, @NonNull final T end, @NonNull final MyGraph<T> graph) {
        final Queue<T> nodesToVisit = new LinkedList<>();
        final Set<T> nodesVisited = new LinkedHashSet<>();

        // Short-circuit if graph does not contain either value as a node.
        if (!graph.containsNode(start) || !graph.containsNode(end)) {
            return false;
        }

        // Short-circuit if graph does not contain path between start and end nodes even though they are the same. This
        // will keep the logic below simpler since start node gets processed first.
        if (start.equals(end) && !graph.containsEdge(start, end)) {
            return false;
        }

        // Push start to the queue to begin processing.
        nodesToVisit.offer(start);

        // While the queue still has a value, retrieve the top and check if it is end value. If yes, then stop
        // processing. If no, then add the neighbors of this value to the queue if they have not been visited already;
        // also mark this value as visited.
        while (!nodesToVisit.isEmpty()) {
            final T valueToVisit = nodesToVisit.poll();

            // If this node has already been visited due to multiple nodes pointing to it, then ignore it.
            if (nodesVisited.contains(valueToVisit)) {
                log.info("Already visited node: {}, ignoring.", valueToVisit.toString());
                continue;
            }

            log.info("Visiting node: {}.", valueToVisit.toString());

            if (end.equals(valueToVisit)) {
                return true;
            }

            final Set<T> neighbors = graph.getSuccessors(valueToVisit);

            for (final T neighbor : neighbors) {
                if (!nodesVisited.contains(neighbor)) {
                    nodesToVisit.offer(neighbor);
                }
            }

            nodesVisited.add(valueToVisit);
        }

        return false;
    }
}
