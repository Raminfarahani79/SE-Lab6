package pathfinder;

import graph.Graph;

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
}
