package csproblem.injava.chapter0;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Fib4Test {

    private Fib4 fib;

    @BeforeEach
    void setUp() {
        fib = new Fib4();
    }

    @Test
    void should_return_0_when_arg_is_0() {
        int result = fib.fib(0);
        Assertions.assertEquals(0, result);
    }

    @Test
    void should_return_1_when_arg_is_1() {
        int result = fib.fib(1);
        Assertions.assertEquals(1, result);
    }

    @Test
    void should_return_1_when_arg_is_2() {
        int result = fib.fib(1);
        Assertions.assertEquals(1, result);
    }

    @Test
    void should_return_2_when_arg_is_3() {
        int result = fib.fib(3);
        Assertions.assertEquals(2, result);
    }

    @Test
    void should_return_3_when_arg_is_4() {
        int result = fib.fib(4);
        Assertions.assertEquals(3, result);
    }

    @Test
    void should_return_5_when_arg_is_5() {
        int result = fib.fib(5);
        Assertions.assertEquals(5, result);
    }

    @Test
    void should_return_8_when_arg_is_6() {
        int result = fib.fib(6);
        Assertions.assertEquals(8, result);
    }

    @Test
    void should_return_13_when_arg_is_7() {
        int result = fib.fib(7);
        Assertions.assertEquals(13, result);
    }

    @Test
    void should_return_21_when_arg_is_8() {
        int result = fib.fib(8);
        Assertions.assertEquals(21, result);
    }

}