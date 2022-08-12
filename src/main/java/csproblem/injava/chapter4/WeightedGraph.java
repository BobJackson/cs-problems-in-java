package csproblem.injava.chapter4;

import java.util.*;
import java.util.function.IntConsumer;

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

    public static void main(String[] args) {
        WeightedGraph<City> cityGraph2 = new WeightedGraph<>(
                Arrays.stream(City.values()).toList()
        );

        initCityGraph(cityGraph2);
        System.out.println(cityGraph2);

        List<WeightedEdge> mst = cityGraph2.mst(0);
        cityGraph2.printWeightedPath(mst);
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

