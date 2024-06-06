package com.example.atividades.atividade11;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class CustomSortTest {
    private CustomSort customSort;
    private Faker faker;

    @BeforeEach
    void setUp() {
        customSort = new CustomSort();
        faker = new Faker();
    }

    @Test
    void testCustomSortWithPositiveNumbers() {
        List<Integer> numbers = generateRandomIntegerList(5, 1, 100);
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers, Collections.reverseOrder());

        List<Integer> result = customSort.customSort(numbers);
        assertEquals(sortedNumbers, result, "The list should be sorted in reverse order");
    }

    @Test
    void testCustomSortWithNegativeNumbers() {
        List<Integer> numbers = generateRandomIntegerList(5, -100, -1);
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers, Collections.reverseOrder());

        List<Integer> result = customSort.customSort(numbers);
        assertEquals(sortedNumbers, result, "The list should be sorted in reverse order");
    }

    @Test
    void testCustomSortWithEmptyList() {
        List<Integer> numbers = new ArrayList<>();
        List<Integer> result = customSort.customSort(numbers);
        assertTrue(result.isEmpty(), "The list should be empty");
    }

    @Test
    void testCustomSortWithDuplicateElements() {
        List<Integer> numbers = List.of(5, 3, 5, 1, 3);
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers, Collections.reverseOrder());

        List<Integer> result = customSort.customSort(new ArrayList<>(numbers));
        assertEquals(sortedNumbers, result, "The list should be sorted in reverse order");
    }

    @Test
    void testCustomSortWithSingleElement() {
        List<Integer> numbers = List.of(faker.number().numberBetween(1, 100));
        List<Integer> result = customSort.customSort(new ArrayList<>(numbers));
        assertEquals(numbers, result, "The list should be the same as the input list");
    }

    private List<Integer> generateRandomIntegerList(int size, int min, int max) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            numbers.add(faker.number().numberBetween(min, max));
        }
        return numbers;
    }
}
