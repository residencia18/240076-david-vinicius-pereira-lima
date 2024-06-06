package com.example.atividades.atividade11;

import java.util.List;
import java.util.Collections;

public class CustomSort {
    public List<Integer> customSort(List<Integer> numbers) {
        Collections.sort(numbers, Collections.reverseOrder());
        return numbers;
    }
}
