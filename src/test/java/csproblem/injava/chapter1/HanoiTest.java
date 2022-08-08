package csproblem.injava.chapter1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HanoiTest {

    private Hanoi hanoi;

    @BeforeEach
    void setUp() {
        hanoi = new Hanoi(3);
    }

    @Test
    void should_move_all_disc_from_a_to_c() {
        hanoi.solve();

        Assertions.assertTrue(hanoi.getTowerA().isEmpty());
        Assertions.assertTrue(hanoi.getTowerB().isEmpty());
        Assertions.assertEquals(3, hanoi.getTowerC().size());
        Assertions.assertEquals(3, hanoi.getTowerC().pop());
        Assertions.assertEquals(2, hanoi.getTowerC().pop());
        Assertions.assertEquals(1, hanoi.getTowerC().pop());
    }
}