package org.agh.ics.oop;


public class Plant implements IMapElement {
    private Vector2d vector; // position on map
    private double growingSpeed; // range <0;1>
    private int lifespan; // lifespan in days
    public boolean isAlive = true;

    // constructor with position assignment
    public Plant(Vector2d vector) {
        this.vector = vector;
        this.growingSpeed = 1; // default growingSpeed
        this.lifespan = 10; // default lifespan
    }

    // constructor with custom growingSpeed and lifespan values
    public Plant(Vector2d vector, double growingSpeed, int lifespan) {
        this.vector = vector;
        this.growingSpeed = growingSpeed;
        this.lifespan = lifespan;
    }

    @Override
    public String toString() {
        return vector.toString();
    }

    // setters
    public void setVector(Vector2d vector) {
        this.vector = vector;
    }

    // getters
    public Vector2d getPosition() {
        return vector;
    }

    @Override
    public boolean getDominant() {
        return false;
    }

    public double getGrowingSpeed() {
        return growingSpeed;
    }

    public String getImagePath() {
        return "src/main/resources/grass.jpg";
    }

    @Override
    public void setTracked(boolean b) {

    }

    @Override
    public boolean getTracked() {
        return false;
    }
}
