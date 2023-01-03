package org.agh.ics.oop;

import java.util.List;
import java.util.ArrayList;


public class KulaZiemska extends AbstractMap {
    public KulaZiemska(int width, int height) {
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width, height);
    }

    public Vector2d getUpperRight() {
        return upperRight;
    }

    public Vector2d getLowerLeft() {
        return lowerLeft;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.x < upperRight.x &&
                position.x > lowerLeft.x &&
                position.y < upperRight.y &&
                position.y > lowerLeft.y;
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.vector)){
            animalsList.add(animal);
            if (animals.containsKey(animal.vector)){
                animals.get(animal.vector).add(animal);
            }else {
                List<Animal> list = new ArrayList<>();
                list.add(animal);
                animals.put(animal.vector,list);
            }

            animal.map = this;
            return true;
        }
        return false;
    }

    @Override
    public void Variant(Animal animal){
        MapDirection newDirection = animal.direction;
        int x = animal.vector.x + newDirection.toUnitVector().x;
        int y = animal.vector.y + newDirection.toUnitVector().y;

        if (y > getUpperRight().y || y < getLowerLeft().y){
            animal.direction = animal.direction.next();
            animal.direction = animal.direction.next();
            animal.direction = animal.direction.next();
            animal.direction = animal.direction.next();
        } else {
            animal.direction = newDirection;

            if (x > getUpperRight().x) {
                x = 0;
            } else {
                x = getUpperRight().x;
            }

            animal.vector = new Vector2d(x,y);
        }
    }

    @Override
    public List<IMapElement> objectAt(Vector2d position) {
        List<IMapElement> listOfObjectsAtPosition = new ArrayList<>();

        // is animal?
        if (animals.containsKey(position)){
            listOfObjectsAtPosition.addAll(animals.get(position));
        }

        // is plant?
        if (plants.containsKey(position)){
            listOfObjectsAtPosition.add(plants.get(position));
        }

        return listOfObjectsAtPosition;
    }
}
