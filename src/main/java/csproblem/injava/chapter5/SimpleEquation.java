package csproblem.injava.chapter5;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class SimpleEquation extends Chromosome<SimpleEquation> {

    private static final double A_HALF = 0.5;
    private static final int MAX_START = 100;

    private int x;
    private int y;
    private static final Random random = new Random();

    public SimpleEquation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static void main(String[] args) {
        List<SimpleEquation> initialPopulation = new ArrayList<>();
        final int POPULATION_SIZE = 20;
        final int GENERATIONS = 100;
        final double THRESHOLD = 13.0;
        for (int i = 0; i < POPULATION_SIZE; i++) {
            initialPopulation.add(randomInstance());
        }
        GeneticAlgorithm<SimpleEquation> ga = new GeneticAlgorithm<>(
                initialPopulation,
                0.1,
                0.7,
                GeneticAlgorithm.SelectionType.TOURNAMENT
        );
        SimpleEquation result = ga.run(GENERATIONS, THRESHOLD);
        System.out.println(result);
    }

    public static SimpleEquation randomInstance() {
        return new SimpleEquation(random.nextInt(MAX_START), random.nextInt(MAX_START));
    }

    @Override
    public double fitness() {
        return 6.0 * x - x * x + 4 * y - y * y;
    }

    @Override
    public List<SimpleEquation> crossover(SimpleEquation other) {
        SimpleEquation child1 = new SimpleEquation(other.x, y);
        SimpleEquation child2 = new SimpleEquation(x, other.y);
        return List.of(child1, child2);
    }

    @Override
    public void mutate() {
        if (random.nextDouble() > A_HALF) {
            if (random.nextDouble() > A_HALF) {
                x += 1;
            } else {
                x -= 1;
            }
        } else {
            if (random.nextDouble() > A_HALF) {
                y += 1;
            } else {
                y -= 1;
            }
        }
    }

    @Override
    public SimpleEquation copy() {
        return new SimpleEquation(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SimpleEquation that = (SimpleEquation) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), x, y);
    }

    @Override
    public String toString() {
        return "X: " + x + " Y:" + y + " Fitness:" + fitness();
    }
}
