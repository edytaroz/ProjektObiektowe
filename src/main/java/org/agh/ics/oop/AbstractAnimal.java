package org.agh.ics.oop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public abstract class AbstractAnimal implements IAnimal, IMapElement {
    IWorldMap map;
    Vector2d vector;
    int energy;
    Genome genes;
    int age;
    int childCount;
    int activeGene;
    int currentDay;
    int plantsEaten;
    MapDirection direction;
    int dayOfDeath = -1;
    int childEnergy; // this is a parameter
    int lenOfGenome; // this is a parameter
    int plantEnergy; // this is a parameter
    int satietyLevel; // this is a parameter
    int energyLoss; // something like a parameter
    int minMutation; // parameter
    int maxMutation; // parameter

    public AbstractAnimal(int energyLoss,int energy,int childEnergy,int lenOfGenome,int plantEnergy,int satietyLevel,
                          Vector2d position,int minMutation,int maxMutation) {
        //parameters section
        this.energy = energy;
        this.childEnergy = childEnergy;
        this.plantEnergy = plantEnergy;
        this.lenOfGenome = lenOfGenome;
        this.satietyLevel = satietyLevel;
        this.energyLoss = energyLoss;
        this.maxMutation = maxMutation;
        this.minMutation = minMutation;

        //individual section
        this.plantsEaten = 0;
        this.activeGene = (int) (Math.random() * lenOfGenome);
        this.vector = position;
        this.childCount = 0;
        this.age = 0;
        this.currentDay = 0;
        Genome genome = new Genome(lenOfGenome);
        this.genes = genome.getGenes();
        int n = (int) (Math.random() * 8);
        this.direction = MapDirection.EAST.intToMapDirection(n);
    }

    //this is a new animal
    public AbstractAnimal(Animal parent1, Animal parent2){
        this.map = parent1.map;

        //parameters section
        this.energy = parent1.childEnergy;
        this.childEnergy = parent1.childEnergy;
        this.plantEnergy = parent1.plantEnergy;
        this.lenOfGenome = parent1.lenOfGenome;
        this.satietyLevel = parent1.satietyLevel;
        this.maxMutation = parent1.maxMutation;
        this.minMutation = parent1.minMutation;
        this.energyLoss = parent1.energyLoss;

        //individual section
        this.plantsEaten = 0;
        this.age = parent1.currentDay;
        this.vector = parent1.vector;
        this.activeGene = (int) (Math.random() * lenOfGenome);
        this.childCount = 0;
        Genome genome = new Genome(parent1, parent2);
        this.genes = genome.getGenes();
        int n = (int) (Math.random() * 8);
        this.direction = MapDirection.EAST.intToMapDirection(n);

        //updating the parents
        int pEnergy = ((parent1.childEnergy*parent1.energy)/(parent2.energy+ parent1.energy));
        parent1.energy -= pEnergy;
        parent2.energy -= (this.childEnergy - pEnergy);
        parent2.childCount += 1;
        parent1.childCount += 1;
    }

    public void eat() {
        this.energy += plantEnergy;
        this.plantsEaten += 1;
    }

    public void conflictFood(List<Animal> animals) {
        animals.sort(new Animal.ReproductionComparator());
        animals.get(0).eat();
    }

    public boolean canReproduce(Animal parent1, Animal parent2) {
        return parent1.energy >= parent1.satietyLevel && parent2.energy >= parent2.satietyLevel;
    }

    public int getEnergy() {
        return this.energy;
    }

    public int getAge() {
        return this.age;
    }

    public int getChildCount() {
        return this.childCount;
    }

    static class ReproductionComparator implements Comparator<Animal> {
        public int compare(Animal a1, Animal a2) {
            int energyCompare = a2.getEnergy() - a1.getEnergy();
            int ageCompare = a2.getAge() - a1.getAge();
            int childCompare = a2.getChildCount() - a1.getChildCount();

            if (energyCompare == 0) {
                if (ageCompare == 0) {
                    return childCompare;
                } else {
                    return ageCompare;
                }
            } else {
                return energyCompare;
            }
        }
    }

    public List<Animal> conflictReproduction(List<Animal> animals) {
        // assumes there are at least 2 animals
        List<Animal> possibleParents = new ArrayList<>();
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).energy >= animals.get(i).satietyLevel){
                possibleParents.add(animals.get(i));
            }
        }

        possibleParents.sort(new Animal.ReproductionComparator());
        List<Animal> children = new ArrayList<>();

        for (int i = 0; i < possibleParents.size()/2; i++) {
            Animal child = new Animal(possibleParents.get(2*i),possibleParents.get(2*i+1));
            children.add(child);
        }

        return children;
    }

    public Vector2d getPosition() {
        return this.vector;
    }

    public boolean isAt(Vector2d position) {
        return this.vector.equals(position);
    }

    public void move(){
        // not assuming we can move
        MapDirection newDirection = this.direction;

        try {
            for (int i = 0; i < this.genes.genes.get(activeGene % lenOfGenome); i++) {
                newDirection = newDirection.next();
            }

            int x = this.vector.x + newDirection.toUnitVector().x;
            int y = this.vector.y + newDirection.toUnitVector().y;
            Vector2d v = new Vector2d(x,y);

            if (map.canMoveTo(v)) {
                this.vector = new Vector2d(x,y);
                this.direction = newDirection;
            } else {
                map.Variant((Animal) this);
            }

        } catch(NullPointerException e) {
            System.out.println("NullPointerException at move");
        }
    }
    public boolean checkDeath() {
        if (this.energy <= 0){
            if (this.dayOfDeath == -1) {
                this.dayOfDeath = this.currentDay;
            }

            return true;
        }

        return false;
    }
    public void dailyUpdate() {
        this.currentDay += 1;
        this.activeGene += 1;
        this.energy -= this.energyLoss;
        checkDeath();
    }

    public String toString() {
        return this.direction.toString();
    }

    public String getImagePath() {
        return "src/main/resources/circle.png";
    }
}
