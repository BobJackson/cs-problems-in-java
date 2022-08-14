package csproblem.injava.chapter5;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.zip.GZIPOutputStream;

public class ListCompression extends Chromosome<ListCompression> {
    private static final List<String> ORIGINAL_LIST = List.of("Michael", "Sarah", "Joshua", "Narine",
            "David", "Sajid", "Melanie", "Daniel",
            "Wei", "Dean", "Brian", "Murat", "Lisa");

    private List<String> myList;
    private final Random random;

    public ListCompression(List<String> myList) {
        this.myList = myList;
        this.random = new Random();
    }

    public static ListCompression randomInstance() {
        List<String> tempList = new ArrayList<>(ORIGINAL_LIST);
        Collections.shuffle(tempList);
        return new ListCompression(tempList);
    }

    private int byteCompressed() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            GZIPOutputStream gos = new GZIPOutputStream(baos);
            ObjectOutputStream oos = new ObjectOutputStream(gos);
            oos.writeObject(myList);
            oos.close();
            return baos.size();
        } catch (IOException ioe) {
            throw new IllegalStateException(ioe.getMessage());
        }
    }

    @Override
    public double fitness() {
        return 1.0 / byteCompressed();
    }

    @Override
    public List<ListCompression> crossover(ListCompression other) {
        ListCompression child1 = new ListCompression(new ArrayList<>(myList));
        ListCompression child2 = new ListCompression(new ArrayList<>(myList));
        int idx1 = random.nextInt(myList.size());
        int idx2 = random.nextInt(other.myList.size());
        String s1 = myList.get(idx1);
        String s2 = other.myList.get(idx2);
        int idx3 = myList.indexOf(s2);
        int idx4 = myList.indexOf(s1);
        Collections.swap(child1.myList, idx1, idx3);
        Collections.swap(child2.myList, idx2, idx4);
        return List.of(child1, child2);
    }

    @Override
    public void mutate() {
        int idx1 = random.nextInt(myList.size());
        int idx2 = random.nextInt(myList.size());
        Collections.swap(myList, idx1, idx2);
    }

    @Override
    public ListCompression copy() {
        return new ListCompression(new ArrayList<>(myList));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ListCompression that = (ListCompression) o;
        return Objects.equals(myList, that.myList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), myList);
    }

    @Override
    public String toString() {
        return "Order: " + myList + " Bytes: " + byteCompressed();
    }

    public static void main(String[] args) {
        ListCompression originalOrder = new ListCompression(ORIGINAL_LIST);
        System.out.println(originalOrder);
        List<ListCompression> initialPopulation = new ArrayList<>();
        final int POPULATION_SIZE = 100;
        final int GENERATIONS = 100;
        final double THRESHOLD = 1.0;
        for (int i = 0; i < POPULATION_SIZE; i++) {
            initialPopulation.add(randomInstance());
        }
        GeneticAlgorithm<ListCompression> ga = new GeneticAlgorithm<>(
                initialPopulation,
                0.2,
                0.7,
                GeneticAlgorithm.SelectionType.TOURNAMENT
        );
        ListCompression result = ga.run(GENERATIONS, THRESHOLD);
        System.out.println(result);
    }
}
