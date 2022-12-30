package org.agh.ics.oop;
//wariant Korekta i Szalony
public class AnimalKS extends AnimalSzalony{
    public AnimalKS(int energyLoss, int energy, int childEnergy, int lenOfGenome, int plantEnergy, int satietyLevel, Vector2d position) {
        super(energyLoss, energy, childEnergy, lenOfGenome, plantEnergy, satietyLevel, position);
        GenomeKorekta genome = new GenomeKorekta(lenOfGenome);
        this.genes = genome.getGenes();
    }
    public AnimalKS(Animal parent1, Animal parent2){
        super(parent1, parent2);
        GenomeKorekta genome = new GenomeKorekta(parent1, parent2);
        this.genes = genome.getGenes();
    }
}
