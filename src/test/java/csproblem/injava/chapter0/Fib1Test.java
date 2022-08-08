package csproblem.injava.chapter0;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Fib1Test {
    private Fib1 fib;

    @BeforeEach
    void setUp() {
        fib = new Fib1();
    }

    @Test
    void should_raise_stack_over_error() {
        Assertions.assertThrows(StackOverflowError.class, () -> fib.fib(5));
    }
}