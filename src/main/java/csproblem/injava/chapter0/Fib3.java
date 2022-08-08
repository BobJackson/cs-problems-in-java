package csproblem.injava.chapter0;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Fib3 {

    private final Map<Integer, Integer> memo = new ConcurrentHashMap<>(Map.of(0, 0, 1, 1));

    public int fib(int n) {
        if (!memo.containsKey(n)) {
            memo.put(n, fib(n - 1) + fib(n - 2));
        }
        return memo.get(n);
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int result = new Fib3().fib(40);
        System.out.println("result = " + result);
        System.out.println("it costs " + (System.currentTimeMillis() - start) + "ms");
    }
}
