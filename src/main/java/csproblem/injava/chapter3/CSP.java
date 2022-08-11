package csproblem.injava.chapter3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSP<V, D> {
    private final List<V> variables;
    private final Map<V, List<D>> domains;
    private final Map<V, List<Constraint<V, D>>> constraints = new HashMap<>();

    public CSP(List<V> variables, Map<V, List<D>> domains) {
        this.variables = variables;
        this.domains = domains;
        for (V variable : variables) {
            constraints.put(variable, new ArrayList<>());
            if (!domains.containsKey(variable)) {
                throw new IllegalArgumentException("Every variable should have a domain assigned to it.");
            }
        }
    }

    public void addConstraint(Constraint<V, D> constraint) {
        for (V variable : constraint.variables) {
            if (!variables.contains(variable)) {
                throw new IllegalArgumentException("Variable in constraint not in CSP");
            }
            constraints.get(variable).add(constraint);
        }
    }

    public boolean consistent(V variable, Map<V, D> assignment) {
        for (Constraint<V, D> constraint : constraints.get(variable)) {
            if (!constraint.satisfied(assignment)) {
                return false;
            }
        }
        return true;
    }

    public Map<V, D> backtrackingSearch(Map<V, D> assignment) {
        if (assignment.size() == variables.size()) {
            return assignment;
        }
        V unassigned = variables.stream().filter(v -> !assignment.containsKey(v)).findFirst().orElseThrow();
        for (D value : domains.get(unassigned)){
            HashMap<V, D> localAssignment = new HashMap<>(assignment);
            localAssignment.put(unassigned, value);
            if(consistent(unassigned, localAssignment)){
                Map<V, D> result = backtrackingSearch(localAssignment);
                if(result != null){
                    return  result;
                }
            }
        }
        return null;
    }

    public Map<V, D> backtrackingSearch(){
        return backtrackingSearch(new HashMap<>());
    }
}
