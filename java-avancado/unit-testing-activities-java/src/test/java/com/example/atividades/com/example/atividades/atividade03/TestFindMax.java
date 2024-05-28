package com.example.atividades.atividade03;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

import com.example.atividades.atividade03.FindMax;

import java.util.Arrays;
import java.util.List;

public class TestFindMax {
    private final FindMax atividade = new FindMax();

    @Test
    public void testFindMaxWithPositiveNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(5, atividade.findMax(numbers));
    }

    @Test
    public void testFindMaxWithNegativeNumbers() {
        List<Integer> numbers = Arrays.asList(-1, -2, -3, -4, -5);
        assertEquals(-1, atividade.findMax(numbers));
    }

    @Test
    public void testFindMaxWithEmptyList() {
        List<Integer> numbers = Arrays.asList();
        assertNull(atividade.findMax(numbers));
    }
}
