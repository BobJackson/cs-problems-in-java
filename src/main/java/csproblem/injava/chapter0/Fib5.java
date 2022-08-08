package csproblem.injava.chapter0;

import java.util.stream.IntStream;

public class Fib5 {

    int last = 0;
    int next = 1;

    public IntStream stream() {
        return IntStream.generate(() -> {
            int oldLast = last;
            last = next;
            next = oldLast + last;
            return oldLast;
        });
    }

    public static void main(String[] args) {
        Fib5 fib = new Fib5();
        fib.stream().limit(41).forEachOrdered(System.out::println);
    }
}
