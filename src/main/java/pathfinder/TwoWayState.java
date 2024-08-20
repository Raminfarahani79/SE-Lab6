package pathfinder;

import graph.Edge;
import graph.Graph;
import graph.Node;

public class TwoWayState implements RouteState {
    @Override
    public void setRoutes(Graph graph) {
        for (Node node : graph.getGraph()) {
            for (Edge edge : node.getEdges()) {
                edge.setDirected(false);
            }
        }
    }
}
