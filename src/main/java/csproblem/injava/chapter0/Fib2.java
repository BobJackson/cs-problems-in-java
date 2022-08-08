package csproblem.injava.chapter0;

public class Fib2 {

    // 0 1 1 2 3 5 8 13 21 34 55 89
    public int fib(int n) {
        if (n < 2) return n;
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int result = new Fib2().fib(40);
        System.out.println("result = " + result);
        System.out.println("it costs " + (System.currentTimeMillis() - start) + "ms");
    }
}
