package pathfinder;

import graph.Graph;
import graph.Node;

public class BusStrategy implements TravelStrategy {
    @Override
    public int calculateTime(Graph graph, Node start, Node end) {
        graph.dijkstra(start);
        return end.getDistance();
    }
}
