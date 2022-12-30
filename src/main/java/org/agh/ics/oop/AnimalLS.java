package org.agh.ics.oop;
//wariant Losowy i Szalony
public class AnimalLS extends AnimalSzalony {

    public AnimalLS(int energyLoss, int energy, int childEnergy, int lenOfGenome, int plantEnergy, int satietyLevel, Vector2d position) {
        super(energyLoss, energy, childEnergy, lenOfGenome, plantEnergy, satietyLevel, position);
        GenomeLosowe genome = new GenomeLosowe(lenOfGenome);
        this.genes = genome.getGenes();
    }
    public AnimalLS(Animal parent1, Animal parent2){
        super(parent1, parent2);
        GenomeLosowe genome = new GenomeLosowe(parent1, parent2);
        this.genes = genome.getGenes();
    }
}
