package pathfinder.test;


import graph.Edge;
import graph.Graph;
import graph.Node;
import org.junit.jupiter.api.*;
import pathfinder.*;

import java.util.ArrayList;

class PathfinderTest {

    Graph graph;
    Node startNode;
    Node finishNode;
    Node nodeToBeAvoided;

    ArrayList<Node> nodes;

    CitySystem citySystem;

    @BeforeEach
    void setUp() {
        nodes = new ArrayList<>();
        startNode = new Node();
        finishNode = new Node();
        nodeToBeAvoided = new Node();
        Node n1 = new Node();
        nodes.add(startNode);
        nodes.add(n1);
        nodes.add(finishNode);
        nodes.add(nodeToBeAvoided);
        Edge.createEdge(startNode, n1, false, 1);
        Edge.createEdge(finishNode, n1, false, 2);
        Edge.createEdge(n1, nodeToBeAvoided, false, 4);
        Edge.createEdge(nodeToBeAvoided, finishNode, false, 8);
        graph = new Graph(nodes);
        citySystem = new CitySystem(graph);
    }

    @Test
    @DisplayName("Governor should be able to make roads one-way")
    void makeRoadsOneWay() {
        citySystem.makeRoutesOneWay();
        Assertions.assertEquals(1, finishNode.getAvailableNeighbors().size());
    }

    @Test
    @DisplayName("Governor should be able to make roads two-way")
    void makeRoadsTwoWay() {
        citySystem.makeRoutesOneWay();
        citySystem.makeRoutesTwoWay();
        Assertions.assertEquals(2, finishNode.getAvailableNeighbors().size());
    }
}