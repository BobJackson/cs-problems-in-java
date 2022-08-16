package csproblem.injava.chapter7;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public final class Util {

    public static double dotProduct(double[] xs, double[] ys) {
        double sum = 0.0;
        for (int i = 0; i < xs.length; i++) {
            sum += (xs[i] * ys[i]);
        }
        return sum;
    }

    public static double sigmoid(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }

    public static double derivativeSigmoid(double x) {
        double sig = sigmoid(x);
        return sig * (1.0 - sig);
    }

    public static void normalizeByFeatureScaling(List<double[]> dataSet) {
        for (int colNum = 0; colNum < dataSet.get(0).length; colNum++) {
            List<Double> column = new ArrayList<>();
            for (double[] row : dataSet) {
                column.add(row[colNum]);
            }
            double maximum = Collections.max(column);
            double minimum = Collections.min(column);
            double difference = maximum - minimum;
            for (double[] row : dataSet) {
                row[colNum] = (row[colNum] - minimum) / difference;
            }
        }
    }

    public static List<String[]> loadCSV(String filename) {
        try {
            return FileUtils.readLines(new File(filename), UTF_8)
                    .stream()
                    .map(line -> line.split(","))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static double max(double[] numbers) {
        return Arrays.stream(numbers).max().orElse(Double.MIN_NORMAL);
    }
}
