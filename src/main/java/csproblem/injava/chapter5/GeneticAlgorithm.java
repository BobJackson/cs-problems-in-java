package csproblem.injava.chapter5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm<C extends Chromosome<C>> {

    public enum SelectionType {
        ROULETTE, TOURNAMENT
    }

    private List<C> population;
    private final double mutationChance;
    private final double crossoverChance;
    private final SelectionType selectionType;
    private final Random random;

    public GeneticAlgorithm(List<C> population,
                            double mutationChance,
                            double crossoverChance,
                            SelectionType selectionType) {
        this.population = population;
        this.mutationChance = mutationChance;
        this.crossoverChance = crossoverChance;
        this.selectionType = selectionType;
        this.random = new Random();
    }

    public C run(int maxGenerations, double threshold) {
        C best = Collections.max(population).copy();
        for (int generation = 0; generation < maxGenerations; generation++) {
            if (best.fitness() >= threshold) {
                return best;
            }
            System.out.printf("Generation %d  Best %.2f Avg %.2f",
                    generation,
                    best.fitness(),
                    population.stream().mapToDouble(C::fitness).average().orElse(0.0));

            reproduceAndReplace();
            mutate();
            C highest = Collections.max(population);
            if (highest.fitness() > best.fitness()) {
                best = highest;
            }
        }
        return best;
    }

    private void reproduceAndReplace() {
        List<C> nextPopulation = new ArrayList<>();
        while (nextPopulation.size() < population.size()) {
            List<C> parents;
            if (selectionType == SelectionType.ROULETTE) {
                double totalFitness = population.stream().mapToDouble(C::fitness).sum();
                double[] wheel = population.stream().mapToDouble(it -> it.fitness() / totalFitness).toArray();
                parents = pickRoulette(wheel, 2);
            } else {
                parents = pickTournament(population.size() / 2, 2);
            }
            if (random.nextDouble() < crossoverChance) {
                C parent1 = parents.get(0);
                C parent2 = parents.get(1);
                nextPopulation.addAll(parent1.crossover(parent2));
            } else {
                nextPopulation.addAll(parents);
            }
            population = nextPopulation;
        }
    }

    private void mutate() {
        for (C individual : population) {
            if (random.nextDouble() < mutationChance) {
                individual.mutate();
            }
        }
    }

    private List<C> pickRoulette(double[] wheel, int numPicks) {
        List<C> picks = new ArrayList<>();
        for (int i = 0; i < numPicks; i++) {
            double pick = random.nextDouble();
            for (int j = 0; j < wheel.length; j++) {
                pick -= wheel[j];
                if (pick <= 0) {
                    picks.add(population.get(j));
                    break;
                }
            }
        }
        return picks;
    }

    private List<C> pickTournament(int numParticipants, int numPicks) {
        Collections.shuffle(population);
        List<C> tournament = population.subList(0, numParticipants);
        tournament.sort(Collections.reverseOrder());
        return tournament.subList(0, numPicks);
    }

}
