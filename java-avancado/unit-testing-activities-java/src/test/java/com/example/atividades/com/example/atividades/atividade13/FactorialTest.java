package com.example.atividades.atividade13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FactorialTest {
    private Factorial factorial;

    @BeforeEach
    void setUp() {
        factorial = new Factorial();
    }

    @Test
    void testCalculateZeroFactorial() {
        int result = factorial.calculate(0);
        assertEquals(1, result, "Factorial of 0 should be 1");
    }

    @Test
    void testCalculateFactorialOfOne() {
        int result = factorial.calculate(1);
        assertEquals(1, result, "Factorial of 1 should be 1");
    }

    @Test
    void testCalculatePositiveNumberFactorial() {
        int result = factorial.calculate(5);
        assertEquals(120, result, "Factorial of 5 should be 120");
    }

    @Test
    void testCalculateNegativeNumberFactorial() {
        assertThrows(IllegalArgumentException.class, () -> {
            factorial.calculate(-1);
        }, "Should throw IllegalArgumentException for negative numbers");
    }

    @Test
    void testCalculateLargeNumberFactorial() {
        int result = factorial.calculate(10);
        assertEquals(3628800, result, "Factorial of 10 should be 3628800");
    }
}
