package com.example.atividades.atividade03;

import java.util.List;
import java.util.Collections;

public class FindMax {
    public Integer findMax(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return null;
        }
        return Collections.max(numbers);
    }
}
