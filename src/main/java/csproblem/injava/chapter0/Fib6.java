package csproblem.injava.chapter0;

/*
 * We have only defined the nth Fibonacci number in terms of the two before it.
 * Now, we will look at Binet's formula to calculate the nth Fibonacci number in constant time.

 * The Fibonacci terms maintain a ratio called golden ratio denoted by Φ, the Greek character pronounced ‘phi'.
 * First, let's look at how the golden ratio is calculated:
 * Φ = ( 1 + √5 )/2 = 1.6180339887...
 * Now, let's look at Binet's formula:
 * Sn = Φⁿ–(– Φ⁻ⁿ)/√5
 * Actually, this means that we should be able to get the nth Fibonacci number with just some arithmetic.
 */
public class Fib6 {

    public static int nthFibonacciTerm(int n) {
        double squareRootOf5 = Math.sqrt(5);
        double phi = (1 + squareRootOf5) / 2;
        return (int) ((Math.pow(phi, n) - Math.pow(-phi, -n)) / squareRootOf5);
    }

    public static int fib(int n){
        long start = System.currentTimeMillis();
        int result = nthFibonacciTerm(n);
        System.out.println("it cost " + (System.currentTimeMillis() - start) + " ms.");
        return result;
    }
}
