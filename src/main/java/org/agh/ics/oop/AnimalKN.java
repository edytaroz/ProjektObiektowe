package org.agh.ics.oop;
//wariant Korekta i Predestynacja(Normalny)
public class AnimalKN extends Animal {
    public AnimalKN(int energyLoss, int energy, int childEnergy, int lenOfGenome, int plantEnergy, int satietyLevel, Vector2d position) {
        super(energyLoss, energy, childEnergy, lenOfGenome, plantEnergy, satietyLevel, position);
        GenomeKorekta genome = new GenomeKorekta(lenOfGenome);
        this.genes = genome.getGenes();
    }
    public AnimalKN(Animal parent1, Animal parent2){
        super(parent1, parent2);
        GenomeKorekta genome = new GenomeKorekta(parent1, parent2);
        this.genes = genome.getGenes();
    }
}
