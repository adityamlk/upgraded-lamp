package com.adityamlk.codelibrary.datastructure.graph;

import com.google.common.graph.ElementOrder;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import java.text.MessageFormat;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static com.adityamlk.codelibrary.datastructure.graph.MyGraph.GRAPH_TO_STRING_FORMAT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Log4j2
public class MyGraphTest {

    private MyGraph<String> myGraph;

    private MutableGraph<String> defaultGraph;

    @AfterAll
    public static void cleanup() {
        System.gc();
    }

    @Test
    public void insertTest() {
        myGraph = new MyGraph<>();
        defaultGraph = getNewGraph();

        insertDefaultValues();

        assertThat("Node count is incorrect.", myGraph.getNodeCount(), is(defaultGraph.nodes().size()));
        assertThat("Edge count is incorrect.", myGraph.getEdgeCount(), is(defaultGraph.edges().size()));
        assertThat("Graph is incorrect.", myGraph.toString(), is(getDefaultGraphAsString()));

        assertThat("Result is incorrect.", myGraph.insertNode("burgundy"), is(defaultGraph.addNode("burgundy")));
        assertThat("Result is incorrect.", myGraph.insertNode("zoology"), is(defaultGraph.addNode("zoology")));
        assertThat("Result is incorrect.", myGraph.insertNode("burgundy"), is(defaultGraph.addNode("burgundy")));
        assertThat("Result is incorrect.", myGraph.insertEdge("burgundy", "blah"), is(defaultGraph.putEdge("burgundy", "blah")));
        assertThat("Result is incorrect.", myGraph.insertEdge("blah", "blah"), is(defaultGraph.putEdge("blah", "blah")));
        assertThat("Result is incorrect.", myGraph.insertEdge("blah", "crazy"), is(defaultGraph.putEdge("blah", "crazy")));
        assertThat("Result is incorrect.", myGraph.insertEdge("boom", "energy"), is(defaultGraph.putEdge("boom", "energy")));

        assertThat("Node count is incorrect.", myGraph.getNodeCount(), is(defaultGraph.nodes().size()));
        assertThat("Edge count is incorrect.", myGraph.getEdgeCount(), is(defaultGraph.edges().size()));
        assertThat("Graph is incorrect.", myGraph.toString(), is(getDefaultGraphAsString()));
    }

    @Test
    public void removeTest() {
        myGraph = new MyGraph<>();
        defaultGraph = getNewGraph();

        insertDefaultValues();

        assertThat("Node count is incorrect.", myGraph.getNodeCount(), is(defaultGraph.nodes().size()));
        assertThat("Edge count is incorrect.", myGraph.getEdgeCount(), is(defaultGraph.edges().size()));
        assertThat("Graph is incorrect.", myGraph.toString(), is(getDefaultGraphAsString()));

        assertThat("Result is incorrect.", myGraph.removeNode("burgundy"), is(defaultGraph.removeNode("burgundy")));
        assertThat("Result is incorrect.", myGraph.removeNode("group"), is(defaultGraph.removeNode("group")));
        assertThat("Result is incorrect.", myGraph.removeEdge("blah", "group"), is(defaultGraph.removeEdge("blah", "group")));
        assertThat("Result is incorrect.", myGraph.removeEdge("group", "hurray"), is(defaultGraph.removeEdge("group", "hurray")));
        assertThat("Result is incorrect.", myGraph.removeEdge("blah", "blah"), is(defaultGraph.removeEdge("blah", "blah")));
        assertThat("Result is incorrect.", myGraph.removeEdge("blah", "blah"), is(defaultGraph.removeEdge("blah", "blah")));

        assertThat("Node count is incorrect.", myGraph.getNodeCount(), is(defaultGraph.nodes().size()));
        assertThat("Edge count is incorrect.", myGraph.getEdgeCount(), is(defaultGraph.edges().size()));
        assertThat("Graph is incorrect.", myGraph.toString(), is(getDefaultGraphAsString()));
    }

    @Test
    public void containsTest() {
        myGraph = new MyGraph<>();
        defaultGraph = getNewGraph();

        insertDefaultValues();

        assertThat("Node count is incorrect.", myGraph.getNodeCount(), is(defaultGraph.nodes().size()));
        assertThat("Edge count is incorrect.", myGraph.getEdgeCount(), is(defaultGraph.edges().size()));
        assertThat("Graph is incorrect.", myGraph.toString(), is(getDefaultGraphAsString()));

        assertThat("Result is incorrect.", myGraph.containsNode("burgundy"), is(defaultGraph.nodes().contains("burgundy")));
        assertThat("Result is incorrect.", myGraph.containsNode("group"), is(defaultGraph.nodes().contains("group")));
        assertThat("Result is incorrect.", myGraph.containsEdge("blah", "group"), is(defaultGraph.hasEdgeConnecting("blah", "group")));
        assertThat("Result is incorrect.", myGraph.containsEdge("group", "hurray"), is(defaultGraph.hasEdgeConnecting("group", "hurray")));
        assertThat("Result is incorrect.", myGraph.containsEdge("blah", "blah"), is(defaultGraph.hasEdgeConnecting("blah", "blah")));
        assertThat("Result is incorrect.", myGraph.containsEdge("burgundy", "chronos"), is(defaultGraph.hasEdgeConnecting("burgundy", "chronos")));
    }

    @Test
    public void getNeighborsTest() {
        myGraph = new MyGraph<>();
        defaultGraph = getNewGraph();

        insertDefaultValues();

        assertThat("Node count is incorrect.", myGraph.getNodeCount(), is(defaultGraph.nodes().size()));
        assertThat("Edge count is incorrect.", myGraph.getEdgeCount(), is(defaultGraph.edges().size()));
        assertThat("Graph is incorrect.", myGraph.toString(), is(getDefaultGraphAsString()));

        assertThat("Result is incorrect.", myGraph.getSuccessors("blah"), is(defaultGraph.successors("blah")));
        assertThat("Result is incorrect.", myGraph.getSuccessors("group"), is(defaultGraph.successors("group")));
        assertThat("Result is incorrect.", myGraph.getSuccessors("hurray"), is(defaultGraph.successors("hurray")));
        assertThat("Result is incorrect.", myGraph.getSuccessors("chronos"), is(defaultGraph.successors("chronos")));
    }

    private MutableGraph<String> getNewGraph() {
        return GraphBuilder.directed()
                .allowsSelfLoops(true)
                .incidentEdgeOrder(ElementOrder.stable())
                .nodeOrder(ElementOrder.stable())
                .build();
    }

    private void insertDefaultValues() {
        defaultGraph.addNode("group");
        defaultGraph.addNode("hurray");
        defaultGraph.addNode("blah");
        defaultGraph.addNode("chronos");
        defaultGraph.putEdge("blah", "group");
        defaultGraph.putEdge("blah", "hurray");
        defaultGraph.putEdge("hurray", "blah");
        defaultGraph.putEdge("blah", "blah");
        defaultGraph.putEdge("group", "hurray");
        defaultGraph.putEdge("hurray", "group");

        myGraph.insertNode("group");
        myGraph.insertNode("hurray");
        myGraph.insertNode("blah");
        myGraph.insertNode("chronos");
        myGraph.insertEdge("blah", "group");
        myGraph.insertEdge("blah", "hurray");
        myGraph.insertEdge("hurray", "blah");
        myGraph.insertEdge("blah", "blah");
        myGraph.insertEdge("group", "hurray");
        myGraph.insertEdge("hurray", "group");
    }

    private String getDefaultGraphAsString() {
        return MessageFormat.format(GRAPH_TO_STRING_FORMAT, defaultGraph.nodes(), defaultGraph.edges());
    }
}
