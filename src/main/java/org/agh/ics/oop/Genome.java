package org.agh.ics.oop;

import java.util.ArrayList;
import java.util.List;


public class Genome extends AbstractGenome {
    //constructor for an animal with parents
    public Genome(Animal animal1, Animal animal2) {
        this.maxMutation = Math.min(animal1.lenOfGenome, maxMutation);
        this.minMutation = Math.max(0, animal1.minMutation);
        double side = Math.random(); // left - parent1 < 0.5
        Animal parent1;
        Animal parent2;

        if (animal1.energy >= animal2.energy) {
            parent1 = animal1;
            parent2 = animal2;
        } else {
            parent1 = animal2;
            parent2 = animal2;
        }

        int size = parent1.genes.genes.size();
        int number1 = (size * parent1.energy) / (parent1.energy + parent2.energy);
        int number2 = (size * parent2.energy) / (parent1.energy + parent2.energy);
        genes = new ArrayList<>();
        Animal animal = parent1;
        int number = number1;
        int ids = 1;

        if (side > 0.5) {
            animal = parent2;
            number = number2;
            ids = 2;
        }

        for (int i = 0; i < number; i++) {
            genes.add(animal.genes.genes.get(i));
        }

        if (ids == 1) {
            animal = parent2;
        } else {
            animal = parent1;
        }

        for (int i = number; i < size; i++) {
            genes.add(animal.genes.genes.get(i));
        }
    }

    //constructor for a new animal
    public Genome(int n) {
        List<Integer> g = new ArrayList<>(n);
        int rand;

        for (int i = 0; i < n; i++) {
            rand = (int) (Math.random() * 8);
            g.add(rand);
        }

        this.genes = g;
    }

    public Genome getGenes() {
        return this;
    }
}
