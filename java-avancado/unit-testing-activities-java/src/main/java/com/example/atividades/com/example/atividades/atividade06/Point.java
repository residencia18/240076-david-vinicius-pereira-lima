package com.example.atividades.atividade06;

public class Point {
    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Point other) {
        if (other == null) {
            throw new IllegalArgumentException("Argument must be a Point");
        }
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
