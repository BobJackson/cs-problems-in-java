package csproblem.injava.chapter4;

import java.util.*;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static csproblem.injava.chapter4.City.*;

public class WeightedGraph<V> extends Graph<V, WeightedEdge> {

    public WeightedGraph(List<V> vertices) {
        super(vertices);
    }

    public void addEdge(WeightedEdge edge) {
        edges.get(edge.u).add(edge);
        edges.get(edge.v).add(edge.reverse());
    }

    public void addEdge(int u, int v, double weight) {
        addEdge(new WeightedEdge(u, v, weight));
    }

    public void addEdge(V first, V second, double weight) {
        addEdge(indexOf(first), indexOf(second), weight);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getVertexCount(); i++) {
            sb.append(vertexAt(i));
            sb.append(" -> ");
            sb.append(Arrays.toString(
                    edgeOf(i).stream()
                            .map(we -> "(" + vertexAt(we.v) + ", " + we.getWeight() + ")")
                            .toArray()));
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public List<WeightedEdge> mst(int start) {
        List<WeightedEdge> result = new LinkedList<>();
        if (start < 0 || start > (getVertexCount() - 1)) {
            return result;
        }
        Queue<WeightedEdge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[getVertexCount()];

        IntConsumer visit = index -> {
            visited[index] = true;
            for (WeightedEdge edge : edgeOf(index)) {
                if (!visited[edge.v]) {
                    pq.offer(edge);
                }
            }
        };

        visit.accept(start);
        while (!pq.isEmpty()) {
            WeightedEdge edge = pq.poll();
            if (visited[edge.v]) {
                continue;
            }
            result.add(edge);
            visit.accept(edge.v);
        }
        return result;
    }

    public void printWeightedPath(List<WeightedEdge> paths) {
        for (WeightedEdge edge : paths) {
            System.out.println(vertexAt(edge.u) + " " + edge.getWeight() + "> " + vertexAt(edge.v));
        }
        System.out.println("Total Weight: " + totalWeight(paths));
    }

    private static double totalWeight(List<WeightedEdge> path) {
        return path.stream().mapToDouble(WeightedEdge::getWeight).sum();
    }

    public record DijkstraNode(int vertex, double distance) implements Comparable<DijkstraNode> {

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DijkstraNode that = (DijkstraNode) o;
            return vertex == that.vertex && Double.compare(that.distance, distance) == 0;
        }

        @Override
        public int compareTo(DijkstraNode other) {
            Double mine = distance;
            Double theirs = other.distance;
            return mine.compareTo(theirs);
        }
    }

    public static final class DijkstraResult {
        public final double[] distances;
        public final Map<Integer, WeightedEdge> pathMap;

        public DijkstraResult(double[] distances, Map<Integer, WeightedEdge> pathMap) {
            this.distances = distances;
            this.pathMap = pathMap;
        }
    }

    public DijkstraResult dijkstra(V root) {
        int first = indexOf(root);
        double[] distances = new double[getVertexCount()];
        distances[first] = 0;
        boolean[] visited = new boolean[getVertexCount()];
        visited[first] = true;

        Map<Integer, WeightedEdge> pathMap = new HashMap<>();
        Queue<DijkstraNode> pq = new PriorityQueue<>();
        pq.offer(new DijkstraNode(first, 0));

        while (!pq.isEmpty()) {
            int u = pq.poll().vertex;
            double distU = distances[u];
            for (WeightedEdge we : edgeOf(u)) {
                double distV = distances[we.v];
                double pathWeight = we.getWeight() + distU;
                if (!visited[we.v] || distV > pathWeight) {
                    visited[we.v] = true;
                    distances[we.v] = pathWeight;
                    pathMap.put(we.v, we);
                    pq.offer(new DijkstraNode(we.v, pathWeight));
                }
            }
        }

        return new DijkstraResult(distances, pathMap);
    }

    public Map<V, Double> distanceArrayToDistanceMap(double[] distances) {
        return IntStream.range(0, distances.length)
                .boxed()
                .collect(Collectors.toMap(this::vertexAt, i -> distances[i], (a, b) -> b));
    }

    public static List<WeightedEdge> pathMapToPath(int start, int end, Map<Integer, WeightedEdge> pathMap) {
        if (pathMap.isEmpty()) {
            return List.of();
        }
        List<WeightedEdge> path = new LinkedList<>();
        WeightedEdge edge = pathMap.get(end);
        path.add(edge);
        while (edge.u != start) {
            edge = pathMap.get(edge.u);
            path.add(edge);
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        WeightedGraph<City> cityGraph2 = new WeightedGraph<>(
                Arrays.stream(City.values()).toList()
        );

        initCityGraph(cityGraph2);
        System.out.println(cityGraph2);

        List<WeightedEdge> mst = cityGraph2.mst(0);
        cityGraph2.printWeightedPath(mst);

        System.out.println();

        DijkstraResult dijkstraResult = cityGraph2.dijkstra(LOS_ANGELES);
        Map<City, Double> nameDistance = cityGraph2.distanceArrayToDistanceMap(dijkstraResult.distances);
        System.out.printf("Distances from %s:%n", LOS_ANGELES);
        nameDistance.forEach((name, distance) -> System.out.println(name + " : " + distance));

        System.out.println();

        System.out.printf("Shortest path from %s to %s:%n", LOS_ANGELES, BOSTON);
        List<WeightedEdge> path = pathMapToPath(cityGraph2.indexOf(LOS_ANGELES),
                cityGraph2.indexOf(BOSTON),
                dijkstraResult.pathMap);
        cityGraph2.printWeightedPath(path);
    }

    private static void initCityGraph(WeightedGraph<City> cityGraph) {
        cityGraph.addEdge(SEATTLE, CHICAGO, 1737);
        cityGraph.addEdge(SEATTLE, SAN_FRANCISCO, 678);
        cityGraph.addEdge(SAN_FRANCISCO, RIVERSIDE, 386);
        cityGraph.addEdge(SAN_FRANCISCO, LOS_ANGELES, 348);
        cityGraph.addEdge(LOS_ANGELES, RIVERSIDE, 50);
        cityGraph.addEdge(LOS_ANGELES, PHOENIX, 357);
        cityGraph.addEdge(RIVERSIDE, PHOENIX, 307);
        cityGraph.addEdge(RIVERSIDE, CHICAGO, 1704);
        cityGraph.addEdge(PHOENIX, DALLAS, 887);
        cityGraph.addEdge(PHOENIX, HOUSTON, 1015);
        cityGraph.addEdge(DALLAS, CHICAGO, 805);
        cityGraph.addEdge(DALLAS, ATLANTA, 721);
        cityGraph.addEdge(DALLAS, HOUSTON, 225);
        cityGraph.addEdge(HOUSTON, ATLANTA, 702);
        cityGraph.addEdge(HOUSTON, MIAMI, 968);
        cityGraph.addEdge(ATLANTA, CHICAGO, 588);
        cityGraph.addEdge(ATLANTA, WASHINGTON, 543);
        cityGraph.addEdge(ATLANTA, MIAMI, 604);
        cityGraph.addEdge(MIAMI, WASHINGTON, 923);
        cityGraph.addEdge(CHICAGO, DETROIT, 238);
        cityGraph.addEdge(DETROIT, BOSTON, 613);
        cityGraph.addEdge(DETROIT, WASHINGTON, 396);
        cityGraph.addEdge(DETROIT, NEW_YORK, 482);
        cityGraph.addEdge(BOSTON, NEW_YORK, 190);
        cityGraph.addEdge(NEW_YORK, PHILADELPHIA, 81);
        cityGraph.addEdge(PHILADELPHIA, WASHINGTON, 123);
    }
}

