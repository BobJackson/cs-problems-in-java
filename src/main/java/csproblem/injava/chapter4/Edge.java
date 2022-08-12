package csproblem.injava.chapter4;

public class Edge {
    protected final int u;
    protected final int v;

    public Edge(int u, int v) {
        this.u = u;
        this.v = v;
    }

    public Edge reverse() {
        return new Edge(v, u);
    }

    @Override
    public String toString() {
        return u + " -> " + v;
    }
}
