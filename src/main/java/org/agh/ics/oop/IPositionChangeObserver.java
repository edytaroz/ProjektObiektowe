package org.agh.ics.oop;

public interface IPositionChangeObserver {
    default void change(SimulationEngine engine, AbstractWorldMap map) {

    }
}
