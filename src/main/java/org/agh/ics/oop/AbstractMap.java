package org.agh.ics.oop;

import java.util.ArrayList;

public class AbstractMap implements IPositionChangeObserver {
    public ArrayList<Animal> animals = new ArrayList<>();
    public ArrayList<Vector2d> deadAnimals = new ArrayList<>();
    public ArrayList<Grass> grasses = new ArrayList<>();
}
