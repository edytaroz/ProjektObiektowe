package org.agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class AbstractMap {
    protected Map<Vector2d, List<Animal>> animals = new HashMap<>();
    protected List<Animal> animalsList = new ArrayList<>();
    protected List<Animal> deadAnimals = new ArrayList<>();
    protected Map<Vector2d, Plant> plants = new HashMap<>();
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;
    protected int numOfGrass = 0;
    protected int freeFields = 0;
    protected int numEmpty; // number of empty cells

    public int getNumEmpty() {
        int n = 0;
        for (Vector2d planPosition : plants.keySet()) {
            if (animals.containsKey(planPosition)) {
                n += 1;
            }
        }

        return (upperRight.x + 1) * (upperRight.y + 1) - (animals.size() + plants.size() - n);
    }

    // abstract classes
    public abstract boolean canMoveTo(Vector2d position);

    public abstract boolean place(Animal animal);

    public abstract void Variant(Animal animal);

    public abstract List<IMapElement> objectAt(Vector2d position);
    // ----------

    public Vector2d getUpperRight() {
        return upperRight;
    }

    public Vector2d getLowerLeft() { // always (0, 0)
        return lowerLeft;
    }

    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position) || plants.containsKey(position);
    }

    public boolean isOccupiedByGrass(Vector2d position) {
        return plants.containsKey(position);
    }

    public void newMap() {
        animals = new HashMap<>();

        for (Animal animalFromList : animalsList) {
            if (animals.containsKey(animalFromList.vector)) {
                animals.get(animalFromList.vector).add(animalFromList);
            } else {
                List<Animal> list = new ArrayList<>();
                list.add(animalFromList);
                animals.put(animalFromList.vector, list);
            }
        }
    }

    public void addPlants(int numPlants) {
        int x;
        int y;

        if (numEmpty > numPlants) {
            for (int i = 0; i < numPlants; i++) {
                boolean flag = true;

                while (flag) {
                    x = (int) (Math.random() * (upperRight.x + 1));
                    y = (int) (Math.random() * (upperRight.y + 1));
                    Vector2d vec = new Vector2d(x, y);

                    if (!isOccupiedByGrass(vec)) {
                        plants.put(vec, new Plant(vec));
                        numOfGrass += 1;
                        flag = false;
                    }
                }
            }
        }
    }

    public int getNumOfGrass() {
        return numOfGrass;
    }

    public int getFreeFields() {
        return freeFields;
    }

    public void day() {
        // deleting dead animals
        List<Animal> toDeleteFromList = new ArrayList<>();
        for (int i = 0; i < animalsList.size(); i++) {
            if (animalsList.get(i).dayOfDeath != -1) {
                toDeleteFromList.add(animalsList.get(i));
                deadAnimals.add(animalsList.get(i));
                freeFields += 1;
            }
        }

        animalsList.removeAll(toDeleteFromList);

        for (int i = 0; i < toDeleteFromList.size(); i++) {
            if (animals.containsKey(toDeleteFromList.get(i).vector)) {
                if (animals.get(toDeleteFromList.get(i).vector).size() == 1) {
                    animals.remove(toDeleteFromList.get(i).vector);
                } else {
                    animals.get(toDeleteFromList.get(i).vector).remove(toDeleteFromList.get(i));
                }
            }

            animalsList.removeAll(toDeleteFromList);

        }

        // moving
        for (Animal animalFromList : animalsList) {
            animalFromList.move();
            //animalFromList.dailyUpdate();
        }

        newMap();

        // eating
        for (List<Animal> list : animals.values()) {
            if (plants.containsKey(list.get(0).vector)) {
                if (list.size() == 1) {
                    list.get(0).eat();
                    plants.remove(list.get(0).vector);
                } else if (list.size() > 1) {
                    list.get(0).conflictFood(list);
                    plants.remove(list.get(0).vector);
                    freeFields += 1;
                }
            }
        }

        // reproduction
        List<Animal> toAdd = new ArrayList<>();
        for (List<Animal> list : animals.values()) {
            if (list.size() > 1) {
                toAdd.addAll(list.get(0).conflictReproduction(list));
                freeFields -= 1;
            }
        }

        animalsList.addAll(toAdd);
        for (Animal animalToAdd : toAdd) {
            if (animals.containsKey(animalToAdd.vector)) {
                animals.get(animalToAdd.vector).add(animalToAdd);
            } else {
                List<Animal> list = new ArrayList<>();
                list.add(animalToAdd);
                animals.put(animalToAdd.vector, list);
            }
        }

        for (Animal animalFromList : animalsList) {
            animalFromList.dailyUpdate();
        }

        this.numEmpty = getNumEmpty();
    }

    public int getAvgEnergy() {
        int sum = 0;
        for (Animal animal : animalsList) {
            sum += animal.energy;
        }

        return sum / animalsList.size();
    }
     /*
    public int getAvgLifespan() {
        int sum = 0;
        for (Animal animal : animalsList) {
            sum += animal.dayOfDeath - animal.dayOfBirth;
        }

        return sum / animalsList.size();
    }
    */


    /*
    public int getMostPopularGenome() {
        Map<String, Integer> genomes = new HashMap<>();
        for (Animal animal : animalsList) {
            if (genomes.containsKey(animal.genome)) {
                genomes.put(animal.genome, genomes.get(animal.genome) + 1);
            } else {
                genomes.put(animal.genome, 1);
            }
        }

        int max = 0;
        for (int i : genomes.values()) {
            if (i > max) {
                max = i;
            }
        }

        return max;
    } */
}
