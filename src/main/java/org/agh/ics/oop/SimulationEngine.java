package org.agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine, Runnable {
    private AbstractWorldMap map;
    private IPositionChangeObserver observer;
    private ArrayList<Animal> animals;

    public SimulationEngine(AbstractWorldMap map, IPositionChangeObserver observer) {
        this.map = map;
        this.observer = observer;
    }

    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }

    @Override
    public void run() {

    }
}
