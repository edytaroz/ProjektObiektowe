package org.agh.ics.oop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.agh.ics.oop.gui.App;
import javafx.application.Platform;


public class SimulationEngine implements IEngine, Runnable {
    private AbstractMap map;
    private IPositionChangeObserver observer;
    private ArrayList<Animal> animals;
    App app;
    int numPlants;
    boolean toxic;
    boolean saveStats;
    boolean isPaused = false;

    public SimulationEngine(int energyLoss, int energy, int childEnergy, int lenOfGenome, int plantEnergy,
                            int satietyLevel, int minMutation, int maxMutation, int width, int height, int numAnimals,
                            int numPlants,
                            boolean genVariant, boolean animalVariant, boolean mapVariant, boolean plantVariant,
                            boolean saveStats, App app) {
        if (mapVariant) {
            this.map = new KulaZiemska(width, height);
        } else {
            this.map = new Portal(width, height);
        }

        this.toxic = plantVariant;
        this.saveStats = saveStats;

        this.animals = new ArrayList<>();
        this.app = app;
        this.numPlants = numPlants;
        map.addPlants(numPlants);

        if (!animalVariant && !genVariant) {
            for (int i = 0; i < numAnimals; i++) {
                int x = (int) (Math.random() * width);
                int y = (int) (Math.random() * height);
                Vector2d position = new Vector2d(x, y);
                Animal hedgehog = new AnimalKN(energyLoss, energy, childEnergy, lenOfGenome, plantEnergy, satietyLevel,
                        position, minMutation, maxMutation);

                if (this.map.place(hedgehog)) {
                    this.animals.add(hedgehog);
                }
            }
        } else if (animalVariant && genVariant) {
            for (int i = 0; i < numAnimals; i++) {
                int x = (int) (Math.random() * width);
                int y = (int) (Math.random() * height);
                Vector2d position = new Vector2d(x, y);
                Animal hedgehog = new AnimalLS(energyLoss, energy, childEnergy, lenOfGenome, plantEnergy, satietyLevel,
                        position, minMutation, maxMutation);

                if (this.map.place(hedgehog)) {
                    this.animals.add(hedgehog);
                }
            }
        } else if (animalVariant && !genVariant) {
            for (int i = 0; i < numAnimals; i++) {
                int x = (int) (Math.random() * width);
                int y = (int) (Math.random() * height);
                Vector2d position = new Vector2d(x, y);
                Animal hedgehog = new AnimalKS(energyLoss, energy, childEnergy, lenOfGenome, plantEnergy, satietyLevel,
                        position, minMutation, maxMutation);

                if (this.map.place(hedgehog)) {
                    this.animals.add(hedgehog);
                }
            }
        } else if (!animalVariant && genVariant) {
            for (int i = 0; i < numAnimals; i++) {
                int x = (int) (Math.random() * width);
                int y = (int) (Math.random() * height);
                Vector2d position = new Vector2d(x, y);
                Animal hedgehog = new AnimalLN(energyLoss, energy, childEnergy, lenOfGenome, plantEnergy, satietyLevel,
                        position, minMutation, maxMutation);

                if (this.map.place(hedgehog)) {
                    this.animals.add(hedgehog);
                }
            }
        }
    }

    public void changeState() {
        synchronized (this) {
            this.isPaused = !isPaused;
            notifyAll();
        }
    }

    public AbstractMap getMap() {
        return this.map;
    }

    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }

    @Override
    public void run() {
        int day = 0;

        boolean fileCreated = false;
        if (this.saveStats) {
            File plik = new File("stats.txt");

            try {
                PrintWriter output = new PrintWriter("stats.txt");
                output.print("Day,Animals,Plants,Average_energy,Average_children,Average_age,Average_lifespan");
                fileCreated = true;
                output.close();
            } catch (Exception e) {
                System.out.println("Problem z plikiem!");
            }
        }

        while (day < 100) {
            map.day();
            map.addPlants(numPlants);

            if (this.saveStats && fileCreated) {
                try {
                    BufferedWriter output = new BufferedWriter(new FileWriter("stats.txt", true));
                    output.append("\n" + day);
                    /*output.append("\n" + day + "," + animals.size() + "," + map.getPlants().size() + "," +
                            map.getAverageEnergy() + "," + map.getAverageChildren() + "," + map.getAverageAge() + ","
                            + map.getAverageLifespan()); */
                    output.close();
                } catch (Exception e) {
                    System.out.println("Problem z plikiem!");
                }
            }

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    app.update();
                }
            });

            day += 1;

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }

            synchronized (this) {
                while (isPaused) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted");
                    }
                }
            }
        }
    }
}
