package org.agh.ics.oop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Animal extends AbstractAnimal {
    //this is a starting animal
    public Animal(int energyLoss,int energy,int childEnergy,int lenOfGenome,int plantEnergy,int satietyLevel,Vector2d position){
        super(energyLoss, energy, childEnergy, lenOfGenome, plantEnergy, satietyLevel, position);
    }
    //this is a new animal
    public Animal(Animal parent1, Animal parent2){
        super(parent1, parent2);
    }
}
