package org.agh.ics.oop;

public interface IMapElement {
    Vector2d getPosition();

    boolean getDominant();

    String toString();

    String getImagePath();

    void setTracked(boolean b);

    boolean getTracked();
}
