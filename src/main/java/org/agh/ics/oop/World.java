package org.agh.ics.oop;

public class World {
    public static void main(String[] args) {
        System.out.println("Hello world");
        Animal parent1 = new Animal(150,50,8,10,20,new Vector2d(2,2));
        Animal parent2 = new Animal(50,50,8,10,20,new Vector2d(2,2));
        System.out.println(parent1.genes.genes);
        System.out.println(parent2.genes.genes);
        Animal child = new Animal(parent1,parent2);
        System.out.println(child.genes.genes);
        System.out.println(parent1.direction);
        System.out.println(parent1.activeGene);
        parent1.move();
        System.out.println(parent1.direction);
        System.out.println(parent1.vector);
    }
}
