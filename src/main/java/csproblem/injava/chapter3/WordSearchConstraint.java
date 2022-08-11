package csproblem.injava.chapter3;

import java.util.*;

import static csproblem.injava.chapter3.WordGrid.RANDOM;

public class WordSearchConstraint extends Constraint<String, List<WordGrid.GridLocation>> {

    protected WordSearchConstraint(List<String> words) {
        super(words);
    }

    @Override
    public boolean satisfied(Map<String, List<WordGrid.GridLocation>> assignment) {
        List<WordGrid.GridLocation> allLocations = assignment.values().stream().flatMap(Collection::stream).toList();
        Set<WordGrid.GridLocation> allLocationsSet = new HashSet<>(allLocations);
        return allLocations.size() == allLocationsSet.size();
    }

    public static void main(String[] args) {
        WordGrid grid = new WordGrid(9, 9);
        List<String> words = List.of("MATTHEW", "JOE", "MARY", "SARAH", "SALLY");
        Map<String, List<List<WordGrid.GridLocation>>> domains = new HashMap<>();
        for (String word : words) {
            domains.put(word, grid.generateDomain(word));
        }
        CSP<String, List<WordGrid.GridLocation>> csp = new CSP<>(words, domains);
        csp.addConstraint(new WordSearchConstraint(words));
        Map<String, List<WordGrid.GridLocation>> solution = csp.backtrackingSearch();
        if (solution.isEmpty()) {
            System.out.println("No solution found!");
        } else {
            for (Map.Entry<String, List<WordGrid.GridLocation>> item : solution.entrySet()) {
                String word = item.getKey();
                List<WordGrid.GridLocation> locations = item.getValue();
                if (RANDOM.nextBoolean()) {
                    Collections.reverse(locations);
                }
                grid.mark(word, locations);
            }
            System.out.println(grid);
        }
    }
}
