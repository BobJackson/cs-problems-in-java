package csproblem.injava.chapter4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Graph<V, E extends Edge> {

    protected final List<V> vertices = new ArrayList<>();
    protected final List<List<E>> edges = new ArrayList<>();

    protected Graph() {
    }

    protected Graph(List<V> vertices) {
        this.vertices.addAll(vertices);
        vertices.stream().<List<E>>map(vertex -> new ArrayList<>()).forEach(edges::add);
    }

    public int getVertexCount() {
        return vertices.size();
    }

    public int getEdgeCount() {
        return edges.stream().mapToInt(List::size).sum();
    }

    public int addVertex(V vertex) {
        vertices.add(vertex);
        edges.add(new ArrayList<>());
        return getVertexCount() - 1;
    }

    public V vertexAt(int index) {
        return vertices.get(index);
    }

    public int indexOf(V vertex) {
        return vertices.indexOf(vertex);
    }

    public List<V> neighborsOf(int index) {
        return edges.get(index)
                .stream()
                .map(edge -> vertexAt(edge.v))
                .toList();
    }

    public List<E> edgeOf(int index) {
        return edges.get(index);
    }

    public List<E> edgeOf(V vertex) {
        return edgeOf(indexOf(vertex));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getVertexCount(); i++) {
            sb.append(vertexAt(i));
            sb.append(" -> ");
            sb.append(Arrays.toString(neighborsOf(i).toArray()));
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
