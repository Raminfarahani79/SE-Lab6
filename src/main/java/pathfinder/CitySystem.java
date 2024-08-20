package pathfinder;

import graph.Graph;
import graph.Node;

public class CitySystem {
    private final Graph graph;
    private final Governor governor;
    private final TrainStrategy trainStrategy;
    private final BusStrategy busStrategy;

    public CitySystem(Graph graph) {
        this.graph = graph;
        this.governor = new Governor();
        this.trainStrategy = new TrainStrategy(1);
        this.busStrategy = new BusStrategy();
    }

    public void makeRoutesOneWay() {
        governor.setRouteState(new OneWayState());
        governor.updateRoutes(graph);
    }

    public void makeRoutesTwoWay() {
        governor.setRouteState(new TwoWayState());
        governor.updateRoutes(graph);
    }

    public void changeTrainTime(int newTime) {
        governor.changeTrainUnitTime(trainStrategy, newTime);
    }

    public int calculateTrainTime(Node startCity, Node endCity) {
        return trainStrategy.calculateTime(graph, startCity, endCity);
    }

    public int calculateBusTime(Node startCity, Node endCity) {
        return busStrategy.calculateTime(graph, startCity, endCity);
    }
}
