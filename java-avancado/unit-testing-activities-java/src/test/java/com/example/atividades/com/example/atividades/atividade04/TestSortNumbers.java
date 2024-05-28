package com.example.atividades.atividade04;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.example.atividades.atividade04.SortNumbers;

import java.util.Arrays;
import java.util.List;

public class TestSortNumbers {
    private final SortNumbers atividade = new SortNumbers();

    @Test
    public void testSortNumbersWithUnsortedList() {
        List<Integer> numbers = Arrays.asList(3, 1, 2);
        assertEquals(Arrays.asList(1, 2, 3), atividade.sortNumbers(numbers));
    }

    @Test
    public void testSortNumbersWithMixedList() {
        List<Integer> numbers = Arrays.asList(5, 3, 1, 4, 2);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), atividade.sortNumbers(numbers));
    }

    @Test
    public void testSortNumbersWithEmptyList() {
        List<Integer> numbers = Arrays.asList();
        assertEquals(Arrays.asList(), atividade.sortNumbers(numbers));
    }
}
