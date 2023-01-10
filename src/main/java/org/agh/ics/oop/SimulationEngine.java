package org.agh.ics.oop;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.scene.layout.GridPane;
import org.agh.ics.oop.gui.App;
import javafx.application.Platform;


public class SimulationEngine implements IEngine, Runnable {
    private AbstractMap map;
    private IPositionChangeObserver observer;
    private ArrayList<Animal> animals;
    App app;
    GridPane gridPane;
    GridPane stat;
    int numPlants;
    boolean saveStats;
    boolean isPaused = false;

    public SimulationEngine(int energyLoss, int energy, int childEnergy, int lenOfGenome, int plantEnergy,
                            int satietyLevel, int minMutation, int maxMutation, int width, int height, int numAnimals,
                            int numPlants,
                            String genVariant, String animalVariant, String mapVariant, String plantVariant,
                            boolean saveStats, App app, GridPane gridPane, GridPane stat) {
        if (Objects.equals(mapVariant, "Globe")) {
            this.map = new KulaZiemska(width, height);
        } else {
            this.map = new Portal(width, height);
        }
        this.stat = stat;
        this.gridPane = gridPane;
        this.saveStats = saveStats;
        this.animals = new ArrayList<>();
        this.app = app;
        this.numPlants = numPlants;
        map.addPlants(numPlants);

        if (Objects.equals(animalVariant, "Correct moves") && Objects.equals(genVariant, "Correction genome")) {
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
        } else if (Objects.equals(animalVariant, "Random moves") && Objects.equals(genVariant, "Random genome")) {
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
        } else if (Objects.equals(animalVariant, "Random moves") && Objects.equals(genVariant, "Correction genome")) {
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
        } else if (Objects.equals(animalVariant, "Correct moves") && Objects.equals(genVariant, "Random genome")) {
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
    public boolean getState(){
        return isPaused;
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
            try {
                BufferedWriter output = new BufferedWriter(new FileWriter("stats.txt", false));
                output.write("Day;Animals;Plants;Free_fields;Most_popular_genome;Avg_energy;Avg_lifespan");
                output.close();
                fileCreated = true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }



        while (true) {
            map.day();
            map.addPlants(numPlants);

            if (this.saveStats && fileCreated) {
                try {
                    BufferedWriter output = new BufferedWriter(new FileWriter("stats.txt", true));
                    output.append("\n" + day + ";" + map.animals.size() + ";" + map.getNumOfGrass()+ ";" +
                            map.getFreeFields() + ";" + map.getMostPopularGenome() + ";" +
                            map.getAvgEnergy() + ";" + map.getAvgLifespan());
                    output.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    app.update(gridPane,map,stat);
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
