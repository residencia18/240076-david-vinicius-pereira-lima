package com.example.atividades.atividade01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.example.atividades.atividade01.Add;

public class TestAdd {
    private final Add atividade = new Add();

    @Test
    public void testAddPositiveNumbers() {
        assertEquals(5, atividade.add(2, 3));
    }

    @Test
    public void testAddNegativeAndPositive() {
        assertEquals(0, atividade.add(-1, 1));
    }

    @Test
    public void testAddNegativeNumbers() {
        assertEquals(-2, atividade.add(-1, -1));
    }
}
