package csproblem.injava.chapter6;

import java.util.ArrayList;
import java.util.List;

public class DataPoint {
    private final List<Double> originals;
    public final int numDimensions;
    public List<Double> dimensions;

    public DataPoint(List<Double> initials) {
        this.originals = initials;
        this.dimensions = new ArrayList<>(initials);
        this.numDimensions = dimensions.size();
    }

    public double distance(DataPoint other) {
        double differences = 0.0;
        for (int i = 0; i < numDimensions; i++) {
            double difference = dimensions.get(i) - other.dimensions.get(i);
            differences += Math.pow(difference, 2);
        }
        return Math.sqrt(differences);
    }

    @Override
    public String toString() {
        return originals.toString();
    }
}
