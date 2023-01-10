package org.agh.ics.oop;


import java.util.List;

public interface IMap {
    Vector2d getUpperRight();

    Vector2d getLowerLeft();

    boolean canMoveTo(Vector2d position);

    boolean place(Animal animal);

    void Variant(Animal animal);
}
