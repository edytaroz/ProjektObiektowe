package org.agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

//wariant Losowy i Predestynacja(Normalny)
public class AnimalLN extends Animal {
    public AnimalLN(int energyLoss, int energy, int childEnergy, int lenOfGenome, int plantEnergy, int satietyLevel, Vector2d position) {
        super(energyLoss, energy, childEnergy, lenOfGenome, plantEnergy, satietyLevel, position);
        GenomeLosowe genome = new GenomeLosowe(lenOfGenome);
        this.genes = genome.getGenes();
    }
    public AnimalLN(Animal parent1, Animal parent2){
        super(parent1, parent2);
        GenomeLosowe genome = new GenomeLosowe(parent1, parent2);
        this.genes = genome.getGenes();
    }
    @Override
    public List<Animal> conflictReproduction(List<Animal> animals){//assumes there are at least 2 animals
        List<Animal> possibleParents = new ArrayList<>();
        for (int i = 0; i < animals.size(); i++){
            if (animals.get(i).energy >= animals.get(i).satietyLevel){
                possibleParents.add(animals.get(i));
            }
        }
        possibleParents.sort(new Animal.ReproductionComparator());
        List<Animal> children = new ArrayList<>();
        for (int i = 0; i < possibleParents.size()/2; i++){
            Animal child = new AnimalLN(possibleParents.get(2*i),possibleParents.get(2*i+1));
            children.add(child);
        }
        return children;
    }
}
