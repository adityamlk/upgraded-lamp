package com.adityamlk.codelibrary.algorithm.traversal;

import com.adityamlk.codelibrary.datastructure.graph.MyGraph;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Log4j2
public class MyBreadthFirstSearchTest {

    private final MyBreadthFirstSearch<String> myBreadthFirstSearch = new MyBreadthFirstSearch<>();

    @AfterAll
    public static void cleanup() {
        System.gc();
    }

    @Test
    public void doesPathExistWithInvalidStartNodeTest() {
        final MyGraph<String> myGraph = getPopulatedGraph();

        final boolean result = myBreadthFirstSearch.doesPathExist("blah", "brown", myGraph);

        assertThat("Result does not match.", result, is(false));
    }

    @Test
    public void doesPathExistWithInvalidEndNodeTest() {
        final MyGraph<String> myGraph = getPopulatedGraph();

        final boolean result = myBreadthFirstSearch.doesPathExist("red", "blah", myGraph);

        assertThat("Result does not match.", result, is(false));
    }

    @Test
    public void doesPathExistBetweenIsolatedSubGraphsTest() {
        final MyGraph<String> myGraph = getPopulatedGraph();

        final boolean result = myBreadthFirstSearch.doesPathExist("red", "brown", myGraph);

        assertThat("Result does not match.", result, is(false));
    }

    @Test
    public void doesPathExistWithSameStartAndEndTest() {
        final MyGraph<String> myGraph = getPopulatedGraph();

        final boolean result = myBreadthFirstSearch.doesPathExist("orange", "orange", myGraph);

        assertThat("Result does not match.", result, is(false));
    }

    @Test
    public void doesPathExistWithShortPathTest() {
        final MyGraph<String> myGraph = getPopulatedGraph();

        final boolean result = myBreadthFirstSearch.doesPathExist("orange", "black", myGraph);

        assertThat("Result does not match.", result, is(true));
    }

    @Test
    public void doesPathExistWithShortPathBecauseBFSTest() {
        final MyGraph<String> myGraph = getPopulatedGraph();

        final boolean result = myBreadthFirstSearch.doesPathExist("orange", "yellow", myGraph);

        assertThat("Result does not match.", result, is(true));
    }


    @Test
    public void doesPathExistWithLongPathTest() {
        final MyGraph<String> myGraph = getPopulatedGraph();

        final boolean result = myBreadthFirstSearch.doesPathExist("orange", "grey", myGraph);

        assertThat("Result does not match.", result, is(true));
    }

    private MyGraph<String> getPopulatedGraph() {
        final MyGraph<String> myGraph = new MyGraph<>();
        myGraph.insertNode("red");
        myGraph.insertNode("green");
        myGraph.insertNode("blue");

        myGraph.insertEdge("red", "blue");
        myGraph.insertEdge("blue", "green");
        myGraph.insertEdge("green", "red");

        myGraph.insertNode("orange");
        myGraph.insertNode("purple");
        myGraph.insertNode("yellow");
        myGraph.insertNode("black");
        myGraph.insertNode("magenta");
        myGraph.insertNode("cyan");
        myGraph.insertNode("turquoise");
        myGraph.insertNode("pink");
        myGraph.insertNode("brown");
        myGraph.insertNode("grey");

        myGraph.insertEdge("orange", "yellow");
        myGraph.insertEdge("orange", "purple");
        myGraph.insertEdge("purple", "black");
        myGraph.insertEdge("black", "purple");
        myGraph.insertEdge("black", "yellow");
        myGraph.insertEdge("black", "cyan");
        myGraph.insertEdge("cyan", "black");
        myGraph.insertEdge("yellow", "turquoise");
        myGraph.insertEdge("yellow", "magenta");
        myGraph.insertEdge("magenta", "turquoise");
        myGraph.insertEdge("magenta", "cyan");
        myGraph.insertEdge("cyan", "magenta");
        myGraph.insertEdge("turquoise", "pink");
        myGraph.insertEdge("turquoise", "brown");
        myGraph.insertEdge("brown", "turquoise");
        myGraph.insertEdge("cyan", "brown");
        myGraph.insertEdge("brown", "pink");
        myGraph.insertEdge("pink", "grey");
        myGraph.insertEdge("cyan", "grey");
        myGraph.insertEdge("grey", "cyan");

        return myGraph;
    }
}
