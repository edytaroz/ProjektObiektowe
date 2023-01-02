package org.agh.ics.oop;


public class KulaZiemska extends AbstractMap {
    public KulaZiemska(int width, int height) {
        this.width = width;
        this.height = height;
    }


    @Override
    public void change(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = (Animal) animals.remove(oldPosition);
        animals.put(newPosition, animal);
    }
}
