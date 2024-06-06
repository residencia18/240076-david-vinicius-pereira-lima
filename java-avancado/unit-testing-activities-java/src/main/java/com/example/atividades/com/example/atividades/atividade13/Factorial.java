package com.example.atividades.atividade13;

public class Factorial {
    public int calculate(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Number must be non-negative");
        }
        if (n == 0) {
            return 1;
        }
        return n * calculate(n - 1);
    }
}