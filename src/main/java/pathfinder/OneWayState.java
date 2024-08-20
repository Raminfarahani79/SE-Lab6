package pathfinder;

import graph.Edge;
import graph.Graph;
import graph.Node;

public class OneWayState implements RouteState {
    @Override
    public void setRoutes(Graph graph) {
        for (Node node : graph.getGraph()) {
            for (Edge edge : node.getEdges()) {
                edge.setDirected(true);
            }
        }
    }
}
