package csproblem.injava.chapter3;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static csproblem.injava.chapter3.Colors.*;
import static csproblem.injava.chapter3.Regions.*;

public class MapColoringConstraint extends Constraint<Regions, Colors> {
    private final Regions place1;
    private final Regions place2;

    protected MapColoringConstraint(Regions place1, Regions place2) {
        super(List.of(place1, place2));
        this.place1 = place1;
        this.place2 = place2;
    }

    @Override
    public boolean satisfied(Map<Regions, Colors> assignment) {
        if (!assignment.containsKey(place1) || !assignment.containsKey(place2)) {
            return true;
        }
        return !assignment.get(place1).equals(assignment.get(place2));
    }

    public static void main(String[] args) {
        List<Regions> variables = List.of(WESTERN_AUSTRALIA, NORTHERN_TERRITORY, SOUTH_AUSTRALIA,
                QUEENSLAND, NEW_SOUTH_WALES, VICTORIA, TASMANIA);
        Map<Regions, List<Colors>> domains = new EnumMap<>(Regions.class);
        for (Regions variable : variables) {
            domains.put(variable, List.of(RED, GREEN, BLUE));
        }
        CSP<Regions, Colors> csp = initRegionsColorsCSP(variables, domains);
        Map<Regions, Colors> solution = csp.backtrackingSearch();
        csp.printSolution(solution);
    }

    private static CSP<Regions, Colors> initRegionsColorsCSP(List<Regions> variables,
                                                             Map<Regions, List<Colors>> domains) {
        CSP<Regions, Colors> csp = new CSP<>(variables, domains);
        addConstraint(csp, WESTERN_AUSTRALIA, NORTHERN_TERRITORY);
        addConstraint(csp, WESTERN_AUSTRALIA, SOUTH_AUSTRALIA);
        addConstraint(csp, SOUTH_AUSTRALIA, NORTHERN_TERRITORY);
        addConstraint(csp, QUEENSLAND, NORTHERN_TERRITORY);
        addConstraint(csp, QUEENSLAND, SOUTH_AUSTRALIA);
        addConstraint(csp, QUEENSLAND, NEW_SOUTH_WALES);
        addConstraint(csp, NEW_SOUTH_WALES, SOUTH_AUSTRALIA);
        addConstraint(csp, VICTORIA, SOUTH_AUSTRALIA);
        addConstraint(csp, VICTORIA, NEW_SOUTH_WALES);
        addConstraint(csp, VICTORIA, TASMANIA);
        return csp;
    }

    private static void addConstraint(CSP<Regions, Colors> csp, Regions one, Regions another) {
        csp.addConstraint(new MapColoringConstraint(one, another));
    }
}
