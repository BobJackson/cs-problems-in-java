package csproblem.injava.chapter9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TSP {
    private static final String RUTLAND = "Rutland";
    private static final String BURLINGTON = "Burlington";
    private static final String WHITE_RIVER_JUNCTION = "White River Junction";
    private static final String BENNINGTON = "Bennington";
    private static final String BRATTLEBORO = "Brattleboro";

    private final Map<String, Map<String, Integer>> distances;

    public TSP(Map<String, Map<String, Integer>> distances) {
        this.distances = distances;
    }

    public static void main(String[] args) {
        Map<String, Map<String, Integer>> vtDistances = Map.of(
                RUTLAND, Map.of(
                        BURLINGTON, 67,
                        WHITE_RIVER_JUNCTION, 46,
                        BENNINGTON, 55,
                        BRATTLEBORO, 75),
                BURLINGTON, Map.of(
                        RUTLAND, 67,
                        WHITE_RIVER_JUNCTION, 91,
                        BENNINGTON, 122,
                        BRATTLEBORO, 153),
                WHITE_RIVER_JUNCTION, Map.of(
                        RUTLAND, 46,
                        BURLINGTON, 91,
                        BENNINGTON, 98,
                        BRATTLEBORO, 65),
                BENNINGTON, Map.of(
                        RUTLAND, 55,
                        BURLINGTON, 122,
                        WHITE_RIVER_JUNCTION, 98,
                        BRATTLEBORO, 40),
                BRATTLEBORO, Map.of(
                        RUTLAND, 75,
                        BURLINGTON, 153,
                        WHITE_RIVER_JUNCTION, 65,
                        BENNINGTON, 40)
        );
        TSP tsp = new TSP(vtDistances);
        String[] shortestPath = tsp.findShortestPath();
        int distance = tsp.pathDistance(shortestPath);
        System.out.println("The shortest path is " + Arrays.toString(shortestPath) + " in " + distance + " miles.");
    }

    public String[] findShortestPath() {
        String[] cities = distances.keySet().toArray(String[]::new);
        List<String[]> paths = permutations(cities);
        String[] shortestPath = null;
        int minDistance = Integer.MAX_VALUE;
        for (String[] path : paths) {
            int distance = pathDistance(path);
            distance += distances.get(path[path.length - 1]).get(path[0]);
            if (distance < minDistance) {
                minDistance = distance;
                shortestPath = path;
            }
        }
        assert shortestPath != null;
        shortestPath = Arrays.copyOf(shortestPath, shortestPath.length + 1);
        shortestPath[shortestPath.length - 1] = shortestPath[0];
        return shortestPath;
    }

    public int pathDistance(String[] path) {
        String last = path[0];
        int distance = 0;
        for (String next : Arrays.copyOfRange(path, 1, path.length)) {
            distance += distances.get(last).get(next);
            last = next;
        }
        return distance;
    }

    private static <T> List<T[]> permutations(T[] original) {
        List<T[]> permutations = new ArrayList<>();
        allPermutationsHelper(original, permutations, original.length);
        return permutations;
    }

    private static <T> void allPermutationsHelper(T[] permutation, List<T[]> permutations, int n) {
        if (n <= 0) {
            permutations.add(permutation);
            return;
        }
        T[] tempPermutation = Arrays.copyOf(permutation, permutation.length);
        for (int i = 0; i < n; i++) {
            swap(tempPermutation, i, n - 1);
            allPermutationsHelper(tempPermutation, permutations, n - 1);
            swap(tempPermutation, i, n - 1);
        }
    }

    public static <T> void swap(T[] array, int first, int second) {
        T temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }
}
