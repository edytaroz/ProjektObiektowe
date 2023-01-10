package org.agh.ics.oop;

public abstract class AbstractMapElement implements IMapElement {
    public boolean isTracked = false;
    public boolean isDominant = false;

    @Override
    public boolean getDominant() {
        return isDominant;
    }

    public boolean getTracked() {
        return isTracked;
    }

    public void changeDominant() {
        this.isDominant = !isDominant;
    }

    protected Vector2d position = new Vector2d(0, 0);

    public AbstractMapElement(Vector2d position) {
        this.position = position;
    }

    public Vector2d getPosition() {
        return this.position;
    }
}
