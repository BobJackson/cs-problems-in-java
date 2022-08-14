package csproblem.injava.chapter6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class KMeans<Point extends DataPoint> {

    private List<Point> points;
    private List<Cluster> clusters;
    private final Random random = new Random();

    public KMeans(int k, List<Point> points) {
        if (k < 1) {
            throw new IllegalArgumentException("k must be >= 1");
        }
        this.points = points;
        zScoreNormalize();
        this.clusters = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            DataPoint randPoint = randomPoint();
            Cluster cluster = new Cluster(new ArrayList<>(), randPoint);
            clusters.add(cluster);
        }
    }

    public static void main(String[] args) {
        DataPoint point1 = new DataPoint(List.of(2.0, 1.0, 1.0));
        DataPoint point2 = new DataPoint(List.of(2.0, 2.0, 5.0));
        DataPoint point3 = new DataPoint(List.of(3.0, 1.5, 2.5));
        KMeans<DataPoint> kMeansTest = new KMeans<>(2, List.of(point1, point2, point3));
        List<KMeans<DataPoint>.Cluster> testClusters = kMeansTest.run(100);
        for (int clusterIndex = 0; clusterIndex < testClusters.size(); clusterIndex++) {
            System.out.println("Cluster " + clusterIndex + ":" + testClusters.get(clusterIndex).points);
        }
    }

    public List<Cluster> run(int maxIteration) {
        for (int iteration = 0; iteration < maxIteration; iteration++) {
            for (Cluster cluster : clusters) {
                cluster.points.clear();
            }
            assignClusters();
            List<DataPoint> oldCentroids = new ArrayList<>(centroids());
            generateCentroids();
            if (listsEqual(oldCentroids, centroids())) {
                System.out.println("Converged after " + iteration + " iterations.");
                return clusters;
            }
        }
        return clusters;
    }

    private void zScoreNormalize() {
        List<List<Double>> zscored = points.stream().<List<Double>>map(point -> new ArrayList<>()).toList();
        for (int dimension = 0; dimension < points.get(0).numDimensions; dimension++) {
            List<Double> dimensionSlice = dimensionSlice(dimension);
            Statistics stats = new Statistics(dimensionSlice);
            List<Double> zscores = stats.zscored();
            for (int index = 0; index < zscores.size(); index++) {
                zscored.get(index).add(zscores.get(index));
            }
        }
        for (int i = 0; i < points.size(); i++) {
            points.get(i).dimensions = zscored.get(i);
        }
    }

    private DataPoint randomPoint() {
        List<Double> randDimensions = new ArrayList<>();
        for (int dimension = 0; dimension < points.get(0).numDimensions; dimension++) {
            List<Double> values = dimensionSlice(dimension);
            Statistics stats = new Statistics(values);
            Double randValue = random.doubles(stats.min(), stats.max()).findFirst().orElseThrow();
            randDimensions.add(randValue);
        }
        return new DataPoint(randDimensions);
    }

    private void assignClusters() {
        for (Point point : points) {
            double lowestDistance = Double.MAX_VALUE;
            Cluster closestCluster = clusters.get(0);
            for (Cluster cluster : clusters) {
                double centroidDistance = point.distance(cluster.centroid);
                if (centroidDistance < lowestDistance) {
                    lowestDistance = centroidDistance;
                    closestCluster = cluster;
                }
            }
            closestCluster.points.add(point);
        }
    }

    private void generateCentroids() {
        for (Cluster cluster : clusters) {
            if (cluster.points.isEmpty()) {
                continue;
            }
            List<Double> means = new ArrayList<>();
            for (int i = 0; i < cluster.points.get(0).numDimensions; i++) {
                int dimension = i;
                double dimensionMean = cluster.points.stream()
                        .mapToDouble(x -> x.dimensions.get(dimension))
                        .average()
                        .orElseThrow();
                means.add(dimensionMean);
            }
            cluster.centroid = new DataPoint(means);
        }
    }

    private boolean listsEqual(List<DataPoint> first, List<DataPoint> second) {
        if (first.size() != second.size()) {
            return false;
        }
        for (int i = 0; i < first.size(); i++) {
            for (int j = 0; j < first.get(0).numDimensions; j++) {
                if (first.get(i).dimensions.get(j).doubleValue() != second.get(i).dimensions.get(j).doubleValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    private List<DataPoint> centroids() {
        return clusters.stream().map(cluster -> cluster.centroid).collect(Collectors.toList());
    }

    private List<Double> dimensionSlice(int dimension) {
        return points.stream().map(x -> x.dimensions.get(dimension)).collect(Collectors.toList());
    }

    public class Cluster {
        public List<Point> points;
        public DataPoint centroid;

        public Cluster(List<Point> points, DataPoint centroid) {
            this.points = points;
            this.centroid = centroid;
        }
    }
}
