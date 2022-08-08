package csproblem.injava.chapter1;

public class PiCalculator {

    public static double calculate(int nTerms) {
        final double numerator = 4.0;
        double denominator = 1.0;
        double operation = 1.0;
        double pi = 0;
        for (int i = 0; i < nTerms; i++) {
            pi += operation * (numerator / denominator);
            denominator += 2;
            operation *= -1.0;
        }
        return pi;
    }

}
