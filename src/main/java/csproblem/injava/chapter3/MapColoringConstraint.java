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
        CSP<Regions, Colors> csp = new CSP<>(variables, domains);
        csp.addConstraint(new MapColoringConstraint(WESTERN_AUSTRALIA, NORTHERN_TERRITORY));
        csp.addConstraint(new MapColoringConstraint(WESTERN_AUSTRALIA, SOUTH_AUSTRALIA));
        csp.addConstraint(new MapColoringConstraint(SOUTH_AUSTRALIA, NORTHERN_TERRITORY));
        csp.addConstraint(new MapColoringConstraint(QUEENSLAND, NORTHERN_TERRITORY));
        csp.addConstraint(new MapColoringConstraint(QUEENSLAND, SOUTH_AUSTRALIA));
        csp.addConstraint(new MapColoringConstraint(QUEENSLAND, NEW_SOUTH_WALES));
        csp.addConstraint(new MapColoringConstraint(NEW_SOUTH_WALES, SOUTH_AUSTRALIA));
        csp.addConstraint(new MapColoringConstraint(VICTORIA, SOUTH_AUSTRALIA));
        csp.addConstraint(new MapColoringConstraint(VICTORIA, NEW_SOUTH_WALES));
        csp.addConstraint(new MapColoringConstraint(VICTORIA, TASMANIA));

        Map<Regions, Colors> solution = csp.backtrackingSearch();
        if (solution == null) {
            System.out.println("No solution found!");
        } else {
            System.out.println(solution);
        }
    }
}
