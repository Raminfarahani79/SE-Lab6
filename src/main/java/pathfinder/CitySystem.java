package pathfinder;

import graph.Graph;
import graph.Node;

import java.util.Scanner;

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

    public String getFastestTransport (Node startCity, Node endCity) {
        int trainTime = trainStrategy.calculateTime(graph, startCity, endCity);
        int busTime = busStrategy.calculateTime(graph, startCity, endCity);
        if (trainTime < busTime) return "train";
        if (trainTime > busTime) return "bus";
        return "equal";
    }

    public boolean canAvoidCity (Node startCity, Node endCity, Node avoidCity) {
        avoidCity.setAvoid(true);
        graph.bfs(startCity);
        avoidCity.setAvoid(false);
        return endCity.isVisited();
    }

    public void processRequests() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String request = scanner.nextLine();
            switch (request) {
                case "one-way":
                    makeRoutesOneWay();
                    break;
                case "two-way":
                    makeRoutesTwoWay();
                    break;
                case "change-train-time":
                    int newTime = scanner.nextInt();
                    changeTrainTime(newTime);
                    break;
                case "train-time":
                    int startIndex = scanner.nextInt();
                    int endIndex = scanner.nextInt();
                    Node startCity = graph.getGraph().get(startIndex - 1);
                    Node endCity = graph.getGraph().get(endIndex - 1);
                    System.out.println("Train time: " + calculateTrainTime(startCity, endCity));
                    break;
                case "bus-time":
                    startIndex = scanner.nextInt();
                    endIndex = scanner.nextInt();
                    startCity = graph.getGraph().get(startIndex - 1);
                    endCity = graph.getGraph().get(endIndex - 1);
                    System.out.println("Bus time: " + calculateBusTime(startCity, endCity));
                    break;
                case "fastest-time":
                    startIndex = scanner.nextInt();
                    endIndex = scanner.nextInt();
                    startCity = graph.getGraph().get(startIndex - 1);
                    endCity = graph.getGraph().get(endIndex - 1);
                    System.out.println("Fastest transport is : " + getFastestTransport(startCity, endCity));
                    break;
                case "avoid-city":
                    startIndex = scanner.nextInt();
                    endIndex = scanner.nextInt();
                    int avoidIndex = scanner.nextInt();
                    startCity = graph.getGraph().get(startIndex - 1);
                    endCity = graph.getGraph().get(endIndex - 1);
                    Node avoidCity = graph.getGraph().get(avoidIndex - 1);
                    System.out.println("Can travel without visiting city " + avoidIndex + ": " + canAvoidCity(startCity, endCity, avoidCity));
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Invalid request.");
            }
        }
    }
}
