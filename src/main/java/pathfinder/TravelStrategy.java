package pathfinder;

import graph.Graph;
import graph.Node;

public interface TravelStrategy {
    int calculateTime(Graph graph, Node start, Node end);
}
