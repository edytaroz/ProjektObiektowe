package org.agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractMap implements IPositionChangeObserver {
    protected Map<Vector2d, Animal> animals = new HashMap<>();
    public int width;
    public int height;
    public int day = 0;
    public ArrayList<Vector2d> deadAnimals = new ArrayList<>();
    public ArrayList<Grass> grasses = new ArrayList<>();

    public Vector2d getUpperRight() {
        return new Vector2d(width - 1, height - 1);
    }

    public void change(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = (Animal) animals.remove(oldPosition);
        animals.put(newPosition, animal);
    }

    public void addPlants(int numOfPlants) {
        for (int i = 0; i < numOfPlants; i++) {
            Vector2d position = new Vector2d((int) (Math.random() * width), (int) (Math.random() * height));
            if (!animals.containsKey(position)) {
                grasses.add(new Grass(position));
            }
        }
    }

    public boolean place(Animal animal) {
        if (!animals.containsKey(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
            animal.addObserver(this);
            return true;
        }
        return false;
    }

    public int day() {
        int day = 0;
        while (animals.size() > 0) {
            day++;

            for (Animal animal : animals.values()) {
                animal.move();
            }
            for (Animal animal : animals.values()) {
                animal.eat();
            }
            for (Animal animal : animals.values()) {
                animal.reproduce();
            }
            for (Animal animal : animals.values()) {
                animal.death();
            }
            for (Vector2d deadAnimal : deadAnimals) {
                animals.remove(deadAnimal);
            }
            deadAnimals.clear();
            for (Grass grass : grasses) {
                grass.grow();
            }
        }

        return day;
    }





}
