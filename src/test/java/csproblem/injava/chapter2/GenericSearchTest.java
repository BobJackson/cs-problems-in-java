package csproblem.injava.chapter2;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GenericSearchTest {

    @Test
    void should_return_true_when_linear_contains() {
        boolean linearContains = GenericSearch.linearContains(List.of(1, 4, 5, 6, 7, 8), 5);
        assertTrue(linearContains);
    }

    @Test
    void should_return_false_when_linear_contains_not() {
        boolean linearContains = GenericSearch.linearContains(List.of(1, 4, 5, 6, 7, 8), 9);
        assertFalse(linearContains);
    }

    @Test
    void should_return_true_when_binary_contains() {
        boolean binaryContains = GenericSearch.binaryContains(List.of("a", "b", "c", "d", "e"), "c");
        assertTrue(binaryContains);
    }
}