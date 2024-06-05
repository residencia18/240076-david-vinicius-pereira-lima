package com.example.atividades.atividade08;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StatisticsTest {

    private final Statistics statistics = new Statistics();

    @Test
    void testCalculatePositiveNumbersAverage() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        double expectedAverage = 3.0;
        double actualAverage = statistics.calculateAverage(numbers);
        assertEquals(expectedAverage, actualAverage, "The average should be 3.0");
    }

    @Test
    void testCalculateNegativeNumbersAverage() {
        List<Integer> numbers = Arrays.asList(-1, -2, -3, -4, -5);
        double expectedAverage = -3.0;
        double actualAverage = statistics.calculateAverage(numbers);
        assertEquals(expectedAverage, actualAverage, "The average should be -3.0");
    }

    @Test
    void testCalculateAverageSingleElement() {
        List<Integer> numbers = Collections.singletonList(42);
        double expectedAverage = 42.0;
        double actualAverage = statistics.calculateAverage(numbers);
        assertEquals(expectedAverage, actualAverage, "The average should be 42.0");
    }

    @Test
    void testCalculateAverageWithNullList() {
        assertThrows(IllegalArgumentException.class, () -> {
            statistics.calculateAverage(null);
        }, "Should throw IllegalArgumentException when the list is null");
    }

    @Test
    void testCalculateAverageWithEmptyList() {
        List<Integer> numbers = Collections.emptyList();
        assertThrows(IllegalArgumentException.class, () -> {
            statistics.calculateAverage(numbers);
        }, "Should throw IllegalArgumentException when the list is empty");
    }
}
