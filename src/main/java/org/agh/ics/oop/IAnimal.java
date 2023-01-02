package org.agh.ics.oop;

import java.util.List;


public interface IAnimal {
    void eat();
    void conflictFood(List<Animal> animals);
    boolean canReproduce(Animal parent1, Animal parent2);
    int getEnergy();
    int getAge();
    int getChildCount();
    Vector2d getPosition();
    List<Animal> conflictReproduction(List<Animal> animals);
    boolean checkDeath();
    boolean isAt(Vector2d position);
    void move();
    void dailyUpdate();
    String toString();
    String getImagePath();
}
