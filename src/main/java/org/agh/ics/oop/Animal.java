package org.agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal {
    Vector2d vector;
    int energy;
    Genome genes;
    int age;
    int childCount;
    int childEnergy;//this is a parameter
    int lenOfGenome;//this is a parameter
    int plantEnergy;//this is a parameter
    int satietyLevel;//this is a parameter
    //this is a starting animal
    public Animal(int energy,int childEnergy,int lenOfGenome,int plantEnergy,int satietyLevel,Vector2d position){
        //parameters section
        this.energy = energy;
        this.childEnergy = childEnergy;
        this.plantEnergy = plantEnergy;
        this.lenOfGenome = lenOfGenome;
        this.satietyLevel = satietyLevel;
        //individual section
        this.vector = position;
        this.childCount = 0;
        this.age = 0;
        Genome genome = new Genome(lenOfGenome);
        this.genes = genome.getGenes();
    }
    //this is a new animal
    public Animal(Animal parent1, Animal parent2){
        //parameters section
        this.energy = parent1.childEnergy;
        this.childEnergy = parent1.childEnergy;
        this.plantEnergy = parent1.plantEnergy;
        this.lenOfGenome = parent1.lenOfGenome;
        this.satietyLevel = parent1.satietyLevel;
        //individual section
        this.age = 10; //!!!!!!!!!!!!!!!!!!!!!!!!! fix this
        this.vector = parent1.vector;
        this.childCount = 0;
        Genome genome = new Genome(parent1,parent2);
        this.genes = genome.getGenes();
        //updating the parents
        parent1.energy -= parent1.childEnergy*(parent1.energy/(parent2.energy+ parent1.energy));
        parent2.energy -= parent2.childEnergy*(parent2.energy/(parent2.energy+ parent1.energy));
        parent2.childCount += 1;
        parent1.childCount += 1;
    }
    public void eat(){
        this.energy += plantEnergy;
    }
    public boolean conflictFood(List<Animal> animals){
        int maxEnergy = 0;
        int countMax = 0;
        int indMax = 0;
        //energy condition
        for (int i = 0; i < animals.size(); i++){
            if (animals.get(i).energy > maxEnergy) {
                maxEnergy = animals.get(i).energy;
                countMax = 1;
                indMax = i;
            } else if (animals.get(i).energy == maxEnergy) {
                countMax += 1;
            }
        }
        if (countMax == 1){
            animals.get(indMax).eat();
            return true;
        } else {
            //age condition
            List<Animal> animals2 = new ArrayList<>();
            for (int i = 0; i < animals.size(); i++){
                if (animals.get(i).energy == maxEnergy) {
                    animals2.add(animals.get(i));
                }
            }
            int maxAge = 0;
            countMax = 0;
            indMax = 0;
            for (int i = 0; i < animals2.size(); i++){
                if (animals2.get(i).age > maxAge) {
                    maxAge = animals2.get(i).age;
                    countMax = 1;
                    indMax = i;
                } else if (animals2.get(i).age == maxAge) {
                    countMax += 1;
                }
            }
            if (countMax == 1){
                animals2.get(indMax).eat();
                return true;
            } else {
                //childCount condition
                List<Animal> animals3 = new ArrayList<>();
                for (int i = 0; i < animals2.size(); i++){
                    if (animals2.get(i).age == maxAge) {
                        animals3.add(animals2.get(i));
                    }
                }
                int maxChildCount = 0;
                countMax = 0;
                indMax = 0;
                for (int i = 0; i < animals3.size(); i++){
                    if (animals3.get(i).childCount > maxChildCount) {
                        maxChildCount = animals3.get(i).childCount;
                        countMax = 1;
                        indMax = i;
                    } else if (animals3.get(i).childCount == maxChildCount) {
                        countMax += 1;
                    }
                }
                animals3.get(indMax).eat();
                return true;
            }
        }
    }
    public boolean canReproduce(Animal parent1, Animal parent2){
        return parent1.energy >= parent1.satietyLevel && parent2.energy >= parent2.satietyLevel;
    }
    public boolean conflictReproduction(List<Animal> animals){//assumes there are at least 3 animals

        return true;
    }
}
