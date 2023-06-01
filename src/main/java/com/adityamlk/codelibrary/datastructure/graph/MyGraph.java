package com.adityamlk.codelibrary.datastructure.graph;

import com.google.common.collect.ImmutableSet;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

import static java.util.Map.Entry;

/**
 * Represents a Graph implementation through the use of an Adjacency List to manage nodes and edges. This supports a
 * directed graph that can be either cyclic or acyclic. Tracks all nodes and edges using a map of sets; each key in the
 * map is a node and each set mapped to the node contains its neighbors. Removing a node will ensure all edges, both
 * incoming and outgoing, will be removed as well. Inserting an edge will ensure both nodes are in the graph if they are
 * not already. Provides access to adjacent neighbors that are successors to a node, but not the predecessors; this way,
 * retrieval is straightforward and consistently fast.
 * <p>
 * Insertion is O(1) for both nodes and edges; either insertion updates map or map and set, respectively.
 * Deletion is O(N) for nodes and O(1) for edges; every other node could be adjacent, so need to check each of them.
 * Search is O(1) for both nodes and edges; either check map or map and set, respectively.
 *
 * @param <T> Generic data type supported by the graph.
 */
@Log4j2
@EqualsAndHashCode
public class MyGraph<T extends Comparable<T>> {

    static final String GRAPH_TO_STRING_FORMAT = "nodes: {0}, edges: {1}";

    private static final String EDGE_TO_STRING_FORMAT = "<{0} -> {1}>";

    /*
     * Collection of all nodes in the graph and their adjacent neighbors.
     */
    private final Map<T, Set<T>> nodeCollection;

    /*
     * Tracks the number of nodes in the graph.
     */
    @NonNull
    private Integer nodes;

    /*
     * Tracks the number of edges in the graph.
     */
    @NonNull
    private Integer edges;

    /**
     * Default Constructor.
     */
    public MyGraph() {
        nodeCollection = new LinkedHashMap<>();
        nodes = 0;
        edges = 0;
    }

    /**
     * Inserts the given value as a node.
     *
     * @param valueToInsert {@link T}
     * @return True if node added successfully, false if node already existed in the graph.
     */
    public boolean insertNode(@NonNull final T valueToInsert) {
        if (nodeCollection.containsKey(valueToInsert)) {
            return false;
        }

        nodeCollection.put(valueToInsert, new LinkedHashSet<>());
        nodes += 1;

        return true;
    }

    /**
     * Inserts the given pair as an edge (from -> to). Will insert both nodes if they do not exist already before
     * inserting edge.
     *
     * @param fromValue {@link T}
     * @param toValue   {@link T}
     * @return True if edge added successfully, false if edge already existed in the graph.
     */
    public boolean insertEdge(@NonNull final T fromValue, @NonNull final T toValue) {
        // Short-circuit if the source node exists and the edge already exists.
        if (nodeCollection.containsKey(fromValue) && nodeCollection.get(fromValue).contains(toValue)) {
            return false;
        }

        // If the source node does not exist, then insert it into the graph.
        if (!nodeCollection.containsKey(fromValue)) {
            insertNode(fromValue);
        }

        // If the destination node does not exist, then insert it into the graph.
        if (!nodeCollection.containsKey(toValue)) {
            insertNode(toValue);
        }

        // Add the destination node to the source node's neighbor set, increment the edge count, and return true.
        final Set<T> adjacentNodes = nodeCollection.get(fromValue);
        adjacentNodes.add(toValue);
        edges += 1;

        return true;
    }

    /**
     * Removes the given value as a node.
     *
     * @param valueToRemove {@link T}
     * @return True if node and corresponding edges removed successfully, false if node did not exist in the graph.
     */
    public boolean removeNode(@NonNull final T valueToRemove) {
        final Set<T> adjacentNodes = nodeCollection.get(valueToRemove);

        // Short-circuit if the node does not exist already.
        if (null == adjacentNodes) {
            return false;
        }

        // Update the collection with the removed node and its successors, decrement the node count, and decrement the
        // edge count based on the number of successors for the node.
        nodeCollection.remove(valueToRemove);
        nodes -= 1;
        edges -= adjacentNodes.size();

        // Remove incoming edges for this node by iterating through the other nodes in the graph and checking their
        // neighbor sets. Decrement the edge count for each one removed.
        for (final T nodes : nodeCollection.keySet()) {
            final Set<T> neighbors = nodeCollection.get(nodes);

            if (neighbors.remove(valueToRemove)) {
                edges -= 1;
            }
        }

        return true;
    }

    /**
     * Removes the given pair as an edge (from -> to).
     *
     * @param fromValue {@link T}
     * @param toValue   {@link T}
     * @return True if edge removed successfully, false if edge did not exist in the graph.
     */
    public boolean removeEdge(@NonNull final T fromValue, @NonNull final T toValue) {
        final Set<T> adjacentNodes = nodeCollection.get(fromValue);

        // Short-circuit if the source node does not exist or the edge does not exist.
        if (null == adjacentNodes || !adjacentNodes.contains(toValue)) {
            return false;
        }

        // Remove the destination node from the source node's neighbor set, decrement the edge count, and return true.
        adjacentNodes.remove(toValue);
        edges -= 1;

        return true;
    }

    /**
     * Checks for the given value as a node.
     *
     * @param valueToSearch {@link T}
     * @return True if the graph contains this value as a node, false otherwise.
     */
    public boolean containsNode(@NonNull final T valueToSearch) {
        return nodeCollection.containsKey(valueToSearch);
    }

    /**
     * Checks for the given pair as an edge (from -> to).
     *
     * @param fromValue {@link T}
     * @param toValue   {@link T}
     * @return True if the graph contains this pair as an edge, false otherwise.
     */
    public boolean containsEdge(@NonNull final T fromValue, @NonNull final T toValue) {
        final Set<T> adjacentNodes = nodeCollection.get(fromValue);

        return null != adjacentNodes && adjacentNodes.contains(toValue);
    }

    /**
     * Retrieves successors for the value as a node.
     *
     * @param valueForRetrieval {@link T}
     * @return Immutable set of neighbors that are successors of the specified node. Empty if there are no successors.
     */
    public ImmutableSet<T> getSuccessors(@NonNull final T valueForRetrieval) {
        final Set<T> neighbors = nodeCollection.get(valueForRetrieval);

        return null == neighbors ? ImmutableSet.of() : ImmutableSet.copyOf(neighbors);
    }

    /**
     * @return Number of nodes in the graph.
     */
    public int getNodeCount() {
        return nodes;
    }

    /**
     * @return Number of edges in the graph.
     */
    public int getEdgeCount() {
        return edges;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns string version of the graph. Uses brackets to identify start and end of collection. Separates the values
     * using comma and space. Provides both nodes and edges as separate collections in the string.
     */
    @Override
    public String toString() {
        return MessageFormat.format(GRAPH_TO_STRING_FORMAT, getNodesAsString(), getEdgesAsString());
    }

    /*
     * Returns the nodes in the graph as a string. Maintains stable ordering in the string based on nodes inserted.
     */
    private String getNodesAsString() {
        return nodeCollection.keySet().toString();
    }

    /*
     * Returns the edges in the graph as a string. For each edge, uses specific format to display the nodes in the edge.
     * Maintains stable ordering in the string based on nodes inserted first and edges inserted second.
     */
    private String getEdgesAsString() {
        final List<String> edgesAsStringsList = new ArrayList<>(edges);

        for (final Entry<T, Set<T>> entry : nodeCollection.entrySet()) {
            final T fromNode = entry.getKey();
            final Set<T> neighboringNodes = entry.getValue();

            neighboringNodes.forEach(
                    neighbor -> {
                        final String edgeAsString = MessageFormat.format(EDGE_TO_STRING_FORMAT, fromNode, neighbor);
                        edgesAsStringsList.add(edgeAsString);
                    });
        }

        return edgesAsStringsList.toString();
    }
}
