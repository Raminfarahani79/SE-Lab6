package pathfinder;

import graph.Graph;
import graph.Node;

public class TrainStrategy implements TravelStrategy {
    private int unitTime;

    public TrainStrategy(int unitTime) {
        this.unitTime = unitTime;
    }

    @Override
    public int calculateTime(Graph graph, Node start, Node end) {
        graph.bfs(start);
        return end.getDistance() * unitTime;
    }

    public void setUnitTime(int unitTime) {
        this.unitTime = unitTime;
    }
}
