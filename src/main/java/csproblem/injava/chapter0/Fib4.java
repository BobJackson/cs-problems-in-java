package csproblem.injava.chapter0;

public class Fib4 {

    // 0 1 1 2 3 5 8 13 21 34 55 89
    public int fib(int n) {
        int last = 0;
        int next = 1;
        for (int i = 0; i < n; i++) {
            int oldLast = last;
            last = next;
            next = oldLast + last;
        }
        return last;
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int result = new Fib4().fib(40);
        System.out.println("result = " + result);
        System.out.println("it costs " + (System.currentTimeMillis() - start) + "ms");
    }
}
