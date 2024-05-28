package com.example.atividades.atividade06;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PointTest {
    private Point point1;
    private Point point2;

    @BeforeEach
    void setUp(){
        Faker faker = new Faker(Locale.ENGLISH);
        point1 = new Point(faker.random().nextDouble(), faker.random().nextDouble());
        point2 = new Point(faker.random().nextDouble(), faker.random().nextDouble());
    }

    @Test
    public void testDistanceToDifferentPoint(){
        double expectedDistance = Math.sqrt(Math.pow(point1.getX() - point2.getX(), 2) + Math.pow(point1.getY() - point2.getY(), 2));
        System.out.println(point1.getX() +"\t"+ point2.getX()+"\nExpected: "+expectedDistance+"\nDistance to: "+ point1.distanceTo(point2));
        assertEquals(expectedDistance, point1.distanceTo(point2), 0.001);
    }

    @Test
    public void testDistanceToSamePoint(){
        assertEquals(0.0, point1.distanceTo(point1));
    }

    @Test
    public void testDistanceToNull(){
        assertThrows(IllegalArgumentException.class, ()->point1.distanceTo(null));
    }
}
