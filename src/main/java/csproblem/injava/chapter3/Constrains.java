package csproblem.injava.chapter3;

import java.util.List;
import java.util.Map;

public abstract class Constrains<V, D> {

    protected List<V> variables;

    protected Constrains(List<V> variables) {
        this.variables = variables;
    }

    public abstract boolean satisfied(Map<V, D> assignment);
}
