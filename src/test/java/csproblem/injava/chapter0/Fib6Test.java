package csproblem.injava.chapter0;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Fib6Test {

    @Test
    void should_calculate_correct() {
        int expect = new Fib4().fib(40);
        int actual = Fib6.fib(40);
        Assertions.assertEquals(expect, actual);
    }
}