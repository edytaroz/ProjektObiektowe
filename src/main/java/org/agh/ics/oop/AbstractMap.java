package org.agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMap implements IPositionChangeObserver {
    protected Map<Vector2d, List<Animal>> animals = new HashMap<>();
    protected List<Animal> animalsList = new ArrayList<>();
    protected List<Animal> deadAnimals = new ArrayList<>();
    protected Map<Vector2d, Plant> plants = new HashMap<>();
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;





    public Vector2d getUpperRight() {
        return upperRight;
    }

    public Vector2d getLowerLeft() {
        return lowerLeft;
    }

    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position) || plants.containsKey(position);
    }




    public void addPlants(int numOfPlants) {
        for (int i = 0; i < numOfPlants; i++) {
            Vector2d position = new Vector2d((int) (Math.random() * width), (int) (Math.random() * height));
            if (!animals.containsKey(position)) {
                plants.put(position, new Plant(position));
            }
        }
    }

    public boolean place(Animal animal) {
        if (!animals.containsKey(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }








}
