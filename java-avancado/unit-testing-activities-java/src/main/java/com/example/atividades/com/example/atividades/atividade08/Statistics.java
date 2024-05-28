package com.example.atividades.atividade08;

import java.util.List;

public class Statistics {
	 public double calculateAverage(List<Integer> numbers) {
	        if (numbers == null || numbers.isEmpty()) {
	            throw new IllegalArgumentException("The list of numbers cannot be empty");
	        }
	        int sum = 0;
	        for (int number : numbers) {
	            sum += number;
	        }
	        return (double) sum / numbers.size();
	    }
}
