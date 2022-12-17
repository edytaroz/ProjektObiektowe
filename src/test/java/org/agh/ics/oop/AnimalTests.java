package org.agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AnimalTests {
    @Test
    void childCreationTest(){
        Animal parent1 = new Animal(100,50,8,10,20,new Vector2d(2,2));
        Animal parent2 = new Animal(100,50,8,10,20,new Vector2d(2,2));
        Animal parent3 = new Animal(150,50,8,10,20,new Vector2d(2,2));
        Animal parent4 = new Animal(50,50,8,10,20,new Vector2d(2,2));
        Animal child1 = new Animal(parent1,parent2);
        Animal child2 = new Animal(parent3,parent4);
        Assertions.assertTrue(parent1.vector.equals(child1.vector));
        Assertions.assertTrue(parent1.childEnergy == child1.energy);
        Assertions.assertTrue(parent1.lenOfGenome == child1.lenOfGenome);
        Assertions.assertTrue(parent1.plantEnergy == child1.plantEnergy);
        Assertions.assertTrue(parent1.satietyLevel == child1.satietyLevel);
        Assertions.assertTrue(parent1.childCount == 1);
        Assertions.assertTrue(child1.childCount == 0);
    }
    @Test
    void eatTest(){
        Animal animal = new Animal(100,50,8,10,20,new Vector2d(2,2));
        animal.eat();
        Assertions.assertEquals(animal.energy,animal.plantEnergy+100);
    }
    @Test
    void foodConflict(){
        Animal animal1 = new Animal(100,50,8,10,20,new Vector2d(2,2));
        Animal animal2 = new Animal(100,50,8,10,20,new Vector2d(2,2));
        Animal animal3 = new Animal(100,50,8,10,20,new Vector2d(2,2));
        Animal animal4 = new Animal(50,50,8,10,20,new Vector2d(2,2));
        Animal child = new Animal(animal1,animal4);
        List<Animal> list = new ArrayList<>();
        list.add(animal1);
        list.add(animal2);
        list.add(animal3);
        list.add(animal4);
        child.conflictFood(list);
        Assertions.assertTrue(animal1.energy == 110);
        list.remove(0);
        child.conflictFood(list);
        Assertions.assertTrue(animal2.energy == 110 || animal3.energy == 110);
    }
    @Test
    void canReproduceTest(){
        Animal parent1 = new Animal(100,50,8,10,20,new Vector2d(2,2));
        Animal parent2 = new Animal(50,50,8,10,20,new Vector2d(2,2));
        Animal parent3 = new Animal(100,50,8,10,20,new Vector2d(2,2));
        Animal parent4 = new Animal(10,50,8,10,20,new Vector2d(2,2));
        Assertions.assertTrue(parent1.canReproduce(parent1,parent2));
        Assertions.assertFalse(parent3.canReproduce(parent3,parent4));
    }
}
