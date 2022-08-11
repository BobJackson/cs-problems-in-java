package csproblem.injava.chapter2.exercise;

import csproblem.injava.chapter2.GenericSearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class PerformanceVisioned {

    private static final List<Integer> nums;

    public static final int INITIAL_CAPACITY = 9000_0000;
    private static final int TARGET;

    static {
        nums = new ArrayList<>(INITIAL_CAPACITY);
        IntStream.range(0, INITIAL_CAPACITY).forEach(nums::add);

        TARGET = new Random().nextInt(INITIAL_CAPACITY);
    }

    public static void main(String[] args) {
        System.out.println("target = " + TARGET);

        long start = System.currentTimeMillis();
        GenericSearch.binaryContains(nums, TARGET);
        printCost(start);

        start = System.currentTimeMillis();
        GenericSearch.linearContains(nums, TARGET);
        printCost(start);

        start = System.currentTimeMillis();
        Collections.binarySearch(nums, TARGET);
        printCost(start);
    }

    private static void printCost(long start) {
        System.out.println("it costs " + (System.currentTimeMillis() - start) + "ms");
    }

}
