package csproblem.injava.chapter1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PiCalculatorTest {

    @Test
    void should_calculate_pi() {
        double pi = PiCalculator.calculate(1000_000_000);
        Assertions.assertEquals(3.141592653, pi, 0.000000001);
    }
}