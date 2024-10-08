package pathfinder;

import graph.Graph;

public class Governor {
    private RouteState routeState;

    public void setRouteState(RouteState routeState) {
        this.routeState = routeState;
    }

    public void updateRoutes(Graph graph) {
        routeState.setRoutes(graph);
    }

    public void changeTrainUnitTime(TrainStrategy trainStrategy, int unitTime) {
        trainStrategy.setUnitTime(unitTime);
    }
}
