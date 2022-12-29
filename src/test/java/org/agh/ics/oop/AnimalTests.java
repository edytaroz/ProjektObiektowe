package org.agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AnimalTests {
    @Test
    void childCreationTest(){
        Animal parent1 = new Animal(10,100,50,8,10,20,new Vector2d(2,2));
        Animal parent2 = new Animal(10,100,50,8,10,20,new Vector2d(2,2));
        Animal parent3 = new Animal(10,150,50,8,10,20,new Vector2d(2,2));
        Animal parent4 = new Animal(10,50,50,8,10,20,new Vector2d(2,2));
        Animal child1 = new Animal(parent1,parent2);
        Animal child2 = new Animal(parent3,parent4);
        Assertions.assertEquals(200,parent3.energy+parent4.energy+child2.energy);
        Assertions.assertEquals(parent1.vector, child1.vector);
        Assertions.assertEquals(parent1.childEnergy, child1.energy);
        Assertions.assertEquals(parent1.lenOfGenome, child1.lenOfGenome);
        Assertions.assertEquals(parent1.plantEnergy, child1.plantEnergy);
        Assertions.assertEquals(parent1.satietyLevel, child1.satietyLevel);
        Assertions.assertEquals(1, parent1.childCount);
        Assertions.assertEquals(0, child1.childCount);
    }
    @Test
    void eatTest(){
        Animal animal = new Animal(10,100,50,8,10,20,new Vector2d(2,2));
        animal.eat();
        Assertions.assertEquals(animal.energy,animal.plantEnergy+100);
    }
    @Test
    void foodConflict(){
        Animal animal1 = new Animal(10,150,50,8,10,20,new Vector2d(2,2));
        Animal animal2 = new Animal(10,113,50,8,10,20,new Vector2d(2,2));
        Animal animal3 = new Animal(10,113,50,8,10,20,new Vector2d(2,2));
        Animal animal4 = new Animal(10,50,50,8,10,20,new Vector2d(2,2));
        Animal child = new Animal(animal1,animal4);
        List<Animal> list = new ArrayList<>();
        list.add(animal1);
        list.add(animal2);
        list.add(animal3);
        list.add(animal4);
        child.conflictFood(list);
        Assertions.assertEquals(123, animal1.energy);
        list.remove(0);
        child.conflictFood(list);
        Assertions.assertTrue(animal2.energy == 123 || animal3.energy == 123);
    }
    @Test
    void canReproduceTest(){
        Animal parent1 = new Animal(10,100,50,8,10,20,new Vector2d(2,2));
        Animal parent2 = new Animal(10,50,50,8,10,20,new Vector2d(2,2));
        Animal parent3 = new Animal(10,100,50,8,10,20,new Vector2d(2,2));
        Animal parent4 = new Animal(10,10,50,8,10,20,new Vector2d(2,2));
        Assertions.assertTrue(parent1.canReproduce(parent1,parent2));
        Assertions.assertFalse(parent3.canReproduce(parent3,parent4));
    }
    @Test
    void deathTest(){
        Animal animal = new Animal(10,90,20,6,20,20,new Vector2d(2,2));
        animal.move();
        animal.dailyUpdate();
        animal.move();
        animal.dailyUpdate();
        animal.move();
        animal.dailyUpdate();
        animal.move();
        animal.dailyUpdate();
        animal.move();
        animal.dailyUpdate();
        animal.move();
        animal.dailyUpdate();
        animal.move();
        animal.dailyUpdate();
        animal.move();
        animal.dailyUpdate();
        animal.move();
        animal.dailyUpdate();
        Assertions.assertTrue(animal.checkDeath());
    }
    @Test
    void conflictReproductionTest(){
        Animal animal1 = new Animal(10,100,20,6,20,20,new Vector2d(2,2));
        Animal animal2 = new Animal(10,110,20,6,20,20,new Vector2d(2,2));
        Animal animal3 = new Animal(10,90,20,6,20,20,new Vector2d(2,2));
        Animal animal4 = new Animal(10,10,20,6,20,20,new Vector2d(2,2));
        List<Animal> animals = new ArrayList<>();
        animals.add(animal1);
        animals.add(animal2);
        animals.add(animal3);
        animals.add(animal4);
        List<Animal> animals1 = animal1.conflictReproduction(animals);
        Assertions.assertEquals(90,animal3.energy);
        Assertions.assertEquals(1,animals1.size());
    }
}
