package org.agh.ics.oop;


public class GenomeLosowe extends Genome {
    public GenomeLosowe(int n) {
        super(n);
    }

    public GenomeLosowe(Animal parent1, Animal parent2) {
        super(parent1, parent2);
        this.mutate();
    }

    public GenomeLosowe getGenes() {
        return this;
    }
}
