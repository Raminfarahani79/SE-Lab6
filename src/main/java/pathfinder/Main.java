package pathfinder;

import graph.Edge;
import graph.Graph;
import graph.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static Graph loadGraphFromFile(String filePath) throws IOException {
        ArrayList<Node> nodes = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            int node1Index = Integer.parseInt(parts[0]);
            int node2Index = Integer.parseInt(parts[1]);
            int weight = Integer.parseInt(parts[2]);
            boolean directed = Boolean.parseBoolean(parts[3]);

            Node node1 = getNode(nodes, node1Index);
            Node node2 = getNode(nodes, node2Index);

            Edge.createEdge(node1, node2, directed, weight);
        }
        reader.close();
        return new Graph(nodes);
    }

    private static Node getNode(ArrayList<Node> nodes, int index) {
        while (nodes.size() < index) {
            nodes.add(new Node());
        }
        return nodes.get(index - 1);
    }

    public static void main(String[] args) {
        try {
            Graph graph = loadGraphFromFile("src/main/java/pathfinder/resources/input.txt");
            CitySystem citySystem = new CitySystem(graph);
            citySystem.processRequests();
        } catch (IOException e) {
            System.err.println("Error reading graph file: " + e.getMessage());
        }
    }
}
