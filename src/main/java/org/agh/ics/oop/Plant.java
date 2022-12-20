package org.agh.ics.oop;

public class Plant {
    private Vector2d position; // position on map
    private double growingSpeed; // range <0;1>
    private int lifespan; // lifespan in days
    public boolean isAlive = true;

    // constructor with position assignment
    public Plant(Vector2d position) {
        this.position = position;
        this.growingSpeed = 1; // default growingSpeed
        this.lifespan = 10; // default lifespan
    }

    // constructor with custom growingSpeed and lifespan values
    public Plant(Vector2d position, double growingSpeed, int lifespan) {
        this.position = position;
        this.growingSpeed = growingSpeed;
        this.lifespan = lifespan;
    }

    @Override
    public String toString() { return position.toString(); }

    // setters
    public void setPosition(Vector2d position) { this.position = position; }

    // getters
    public Vector2d getPosition() { return position; }
    public double getGrowingSpeed() { return growingSpeed; }
}
