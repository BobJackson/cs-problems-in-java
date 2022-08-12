package csproblem.injava.chapter4;

import csproblem.injava.chapter2.GenericSearch;

import java.util.Arrays;
import java.util.List;

import static csproblem.injava.chapter4.City.*;


public class UnweightedGraph<V> extends Graph<V, Edge> {

    public UnweightedGraph(List<V> vertices) {
        super(vertices);
    }

    public void addEdge(Edge edge) {
        edges.get(edge.u).add(edge);
        edges.get(edge.v).add(edge.reverse());
    }

    public void addEdge(int u, int v) {
        addEdge(new Edge(u, v));
    }

    public void addEdge(V first, V second) {
        addEdge(new Edge(indexOf(first), indexOf(second)));
    }

    public static void main(String[] args) {
        UnweightedGraph<City> cityGraph = new UnweightedGraph<>(
                Arrays.stream(City.values()).toList()
        );
        initCityGraph(cityGraph);

        System.out.println(cityGraph);

        GenericSearch.Node<City> bfsResult = GenericSearch.bfs(BOSTON,
                v -> v.equals(MIAMI),
                v -> cityGraph.neighborsOf(cityGraph.indexOf(v)));

        if (bfsResult == null) {
            System.out.println("No solution found using breadth-first search!");
        } else {
            List<City> path = GenericSearch.nodeToPath(bfsResult);
            System.out.println("Path from Boston to Miami:");
            System.out.println(path);
        }
    }

    private static void initCityGraph(UnweightedGraph<City> cityGraph) {
        cityGraph.addEdge(SEATTLE, CHICAGO);
        cityGraph.addEdge(SEATTLE, SAN_FRANCISCO);
        cityGraph.addEdge(SAN_FRANCISCO, RIVERSIDE);
        cityGraph.addEdge(SAN_FRANCISCO, LOS_ANGELES);
        cityGraph.addEdge(LOS_ANGELES, RIVERSIDE);
        cityGraph.addEdge(LOS_ANGELES, PHOENIX);
        cityGraph.addEdge(RIVERSIDE, PHOENIX);
        cityGraph.addEdge(RIVERSIDE, CHICAGO);
        cityGraph.addEdge(PHOENIX, DALLAS);
        cityGraph.addEdge(PHOENIX, HOUSTON);
        cityGraph.addEdge(DALLAS, CHICAGO);
        cityGraph.addEdge(DALLAS, ATLANTA);
        cityGraph.addEdge(DALLAS, HOUSTON);
        cityGraph.addEdge(HOUSTON, ATLANTA);
        cityGraph.addEdge(HOUSTON, MIAMI);
        cityGraph.addEdge(ATLANTA, CHICAGO);
        cityGraph.addEdge(ATLANTA, WASHINGTON);
        cityGraph.addEdge(ATLANTA, MIAMI);
        cityGraph.addEdge(MIAMI, WASHINGTON);
        cityGraph.addEdge(CHICAGO, DETROIT);
        cityGraph.addEdge(DETROIT, BOSTON);
        cityGraph.addEdge(DETROIT, WASHINGTON);
        cityGraph.addEdge(DETROIT, NEW_YORK);
        cityGraph.addEdge(BOSTON, NEW_YORK);
        cityGraph.addEdge(NEW_YORK, PHILADELPHIA);
        cityGraph.addEdge(PHILADELPHIA, WASHINGTON);
    }

}
