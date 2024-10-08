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

    @Test
    @DisplayName("Should calculate the distance with train correctly")
    void calculateTrainDistance(){
        Assertions.assertEquals(2, citySystem.calculateTrainTime(startNode, finishNode));
    }

    @Test
    @DisplayName("Should calculate the distance with bus correctly")
    void calculateBusDistance(){
        Assertions.assertEquals(3, citySystem.calculateBusTime(startNode, finishNode));
    }


    @Test
    @DisplayName("Governor should be able to change trains unit time.")
    void changeTrainTimeUnit(){
        citySystem.changeTrainTime(3);
        Assertions.assertEquals(6, citySystem.calculateTrainTime(startNode, finishNode));

    }

    @Test
    @DisplayName("should find the fastest transport between cities")
    void findFastestTransport() {
        Assertions.assertEquals("train", citySystem.getFastestTransport(startNode, finishNode));
    }

    @Test
    @DisplayName("should find the fastest transport between cities")
    void findFastestTransport2() {
        citySystem.makeRoutesOneWay();
        Assertions.assertEquals("equal", citySystem.getFastestTransport(finishNode, startNode));
    }

    @Test
    @DisplayName("should find the fastest transport between cities")
    void findFastestTransport3() {
        citySystem.changeTrainTime(20);
        Assertions.assertEquals("bus", citySystem.getFastestTransport(finishNode, startNode));
    }

    @Test
    @DisplayName("should be able to avoid city")
    void avoidCity() {
        citySystem.makeRoutesOneWay();
        Assertions.assertEquals(false, citySystem.canAvoidCity(startNode, finishNode, nodeToBeAvoided));
    }

    @Test
    @DisplayName("should be able to avoid city")
    void avoidCity2() {
        citySystem.makeRoutesOneWay();
        Assertions.assertEquals(true, citySystem.canAvoidCity(nodeToBeAvoided, finishNode, startNode));
    }
}