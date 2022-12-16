package org.agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Genome {
    List<Integer> genes;
    //constructor for an animal with parents
    public Genome(Animal parent1,Animal parent2) {
        double side = Math.random(); //left < 0.5
        int size = parent1.genes.genes.size();
        int number1 = size*parent1.energy/(parent1.energy + parent2.energy);
        genes = new ArrayList<>(size);
        int start = 0;
        if (side > 0.5) { start = size - number1;}
        for (int i = start; i < number1; i++){
            genes.set(i, parent1.genes.genes.get(i));
        }
        start = size - start;
        for (int i = start; i < size - number1; i++){
            genes.set(i, parent1.genes.genes.get(i));
        }
        //child.genes.genes = genes;
    }
    //constructor for a new animal
    public Genome(int n){
        List<Integer> g = new ArrayList<>(n);
        int rand;
        for (int i = 0; i < n; i++){
            rand = (int) (Math.random() * n);
            g.add(rand);
        }
        this.genes = g;
    }
    public Genome getGenes() {
        return (Genome) genes;
    }
}
