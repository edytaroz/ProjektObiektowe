package org.agh.ics.oop;

import java.util.List;
import java.util.ArrayList;


public class Portal extends AbstractMap {
    public Portal(int width, int height) {
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width, height);
        this.freeFields = (width + 1) * (height + 1);
    }

    public Vector2d getUpperRight() {
        return upperRight;
    }

    public Vector2d getLowerLeft() {
        return lowerLeft;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return true;
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.vector)) {
            animalsList.add(animal);

            if (animals.containsKey(animal.vector)) {
                animals.get(animal.vector).add(animal);
            } else {
                List<Animal> list = new ArrayList<>();
                list.add(animal);
                animals.put(animal.vector, list);
            }

            animal.map = this;
            return true;
        }

        return false;
    }

    @Override
    public void Variant(Animal animal) {
        MapDirection newDirection = animal.direction;
        int x = animal.vector.x + newDirection.toUnitVector().x;
        int y = animal.vector.y + newDirection.toUnitVector().y;

        if (y > getUpperRight().y || y < getLowerLeft().y || x > getUpperRight().x || x < getLowerLeft().x) {
            x = (int) (Math.random() * (getUpperRight().x + 1));
            y = (int) (Math.random() * (getUpperRight().y + 1));
        }

        animal.vector = new Vector2d(x, y);
    }

    @Override
    public List<IMapElement> objectAt(Vector2d position) {
        List<IMapElement> listOfObjectsAtPosition = new ArrayList<>();

        // is animal?
        if (animals.containsKey(position)) {
            listOfObjectsAtPosition.addAll(animals.get(position));
        }

        // is plant?
        if (plants.containsKey(position)) {
            listOfObjectsAtPosition.add(plants.get(position));
        }

        return listOfObjectsAtPosition;
    }
}
