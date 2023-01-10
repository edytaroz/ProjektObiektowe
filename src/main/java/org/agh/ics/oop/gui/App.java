package org.agh.ics.oop.gui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import org.agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class App extends Application {
    public GridPane grid;
    boolean ready = false;
    boolean canStart = false;

    boolean isPaused = false;
    static Image plant;
    static Image animal;

    public static Image getPlant() {
        return plant;
    }

    public static Image getAnimal() {
        return animal;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            plant = new Image(new FileInputStream("src/main/resources/grass.jpg"));
            animal = new Image(new FileInputStream("src/main/resources/hedgehog.jpg"));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        createParametersGetter(primaryStage);
        primaryStage.setTitle("Start");
    }

    public void mapScene(Stage primaryStage, int energyLoss, int energy, int childEnergy, int lenOfGenome, int plantEnergy, int satietyLevel,
                         int minMutation, int maxMutation, int width, int height,
                         int numAnimals, int numPlants, String genVariant, String animalVariant, String mapVariant, String plantVariant,
                         boolean saveStats) throws InterruptedException {
        synchronized (this) {
            if (!canStart) {
                wait();
            }
        }

        Platform.runLater(() -> {
            GridPane gridPane = new GridPane();
            GridPane stat = new GridPane();
            AbstractMap map;
            SimulationEngine engine;
            engine = new SimulationEngine(energyLoss, energy, childEnergy, lenOfGenome, plantEnergy, satietyLevel,
                    minMutation, maxMutation, width, height,
                    numAnimals, numPlants, genVariant, animalVariant, mapVariant, plantVariant,
                    saveStats, this, gridPane, stat);
            Thread startEngine = new Thread(() -> {
                synchronized (this) {
                    try {
                        while (!ready) {
                            wait();
                        }
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }

                Thread engineThread = new Thread(() -> {
                    try {
                        engine.run();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });

                engineThread.start();
            });
            startEngine.start();
            map = engine.getMap();
            Label r = new Label("           ");
            draw(gridPane, map);
            gridPane.setGridLinesVisible(true);
            Button b = pause(engine);
            Button getDominant = new Button("Show dominant");
            getDominant.setOnAction(action -> {
                if (engine.getState()) {
                    map.setDominant();
                    showDominant(map);
                }
            });

            HBox h = new HBox(b, r, getDominant);
            h.setAlignment(Pos.CENTER);
            VBox vBox = new VBox(h, stat, gridPane);

            Scene scene = new Scene(vBox);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Animals Simulator"); // title
            stage.getIcons().add(new Image(
                    Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(
                            "icon.jpg")))); // try catch // icon
            //stage.setFullScreen(true); // full screen
            stage.show();

            synchronized (this) {
                ready = true;
                notifyAll();
            }
        });
    }

    public void createParametersGetter(Stage primaryStage) {
        grid = new GridPane();
        Button button = new Button("Start");
        HBox hBox = new HBox(button);
        hBox.setAlignment(Pos.CENTER);
        Slider s1 = new Slider(5, 20, 0.5);
        Label l1 = new Label("Map height");
        s1.setMajorTickUnit(5);
        s1.setMinorTickCount(4);
        s1.setShowTickMarks(true);
        s1.setSnapToTicks(true);
        s1.setShowTickLabels(true);
        VBox h1 = new VBox(l1, s1);
        Slider s2 = new Slider(5, 20, 1);
        Label l2 = new Label("Map width");
        s2.setMajorTickUnit(5);
        s2.setMinorTickCount(4);
        s2.setShowTickMarks(true);
        s2.setSnapToTicks(true);
        s2.setShowTickLabels(true);
        VBox h2 = new VBox(l2, s2);
        Slider s3 = new Slider(5, 10, 1);
        Label l3 = new Label("Number of animals");
        s3.setMajorTickUnit(5);
        s3.setMinorTickCount(4);
        s3.setShowTickMarks(true);
        s3.setSnapToTicks(true);
        s3.setShowTickLabels(true);
        VBox h3 = new VBox(l3, s3);
        Slider s4 = new Slider(0, 15, 1);
        Label l4 = new Label("Number of plants");
        s4.setMajorTickUnit(5);
        s4.setMinorTickCount(4);
        s4.setShowTickMarks(true);
        s4.setSnapToTicks(true);
        s4.setShowTickLabels(true);
        VBox h4 = new VBox(l4, s4);
        Slider s5 = new Slider(10, 50, 1);
        Label l5 = new Label("Plant energy");
        s5.setMajorTickUnit(5);
        s5.setMinorTickCount(4);
        s5.setShowTickMarks(true);
        s5.setSnapToTicks(true);
        s5.setShowTickLabels(true);
        VBox h5 = new VBox(l5, s5);
        Slider s6 = new Slider(50, 300, 1);
        Label l6 = new Label("Animal energy");
        s6.setMajorTickUnit(10);
        s6.setMinorTickCount(9);
        s6.setShowTickMarks(true);
        s6.setSnapToTicks(true);
        s6.setShowTickLabels(true);
        VBox h6 = new VBox(l6, s6);
        Slider s7 = new Slider(50, 100, 1);
        Label l7 = new Label("Child animal energy");
        s7.setMajorTickUnit(10);
        s7.setMinorTickCount(9);
        s7.setShowTickMarks(true);
        s7.setSnapToTicks(true);
        s7.setShowTickLabels(true);
        VBox h7 = new VBox(l7, s7);
        Slider s8 = new Slider(20, 50, 1);
        Label l8 = new Label("Satiety level");
        s8.setMajorTickUnit(10);
        s8.setMinorTickCount(9);
        s8.setShowTickMarks(true);
        s8.setSnapToTicks(true);
        s8.setShowTickLabels(true);
        VBox h8 = new VBox(l8, s8);
        Slider s9 = new Slider(5, 20, 1);
        Label l9 = new Label("Energy loss");
        s9.setMajorTickUnit(5);
        s9.setMinorTickCount(4);
        s9.setShowTickMarks(true);
        s9.setSnapToTicks(true);
        s9.setShowTickLabels(true);
        VBox h9 = new VBox(l9, s9);
        Slider s10 = new Slider(2, 20, 1);
        Label l10 = new Label("Length of genome");
        s10.setMajorTickUnit(5);
        s10.setMinorTickCount(4);
        s10.setShowTickMarks(true);
        s10.setSnapToTicks(true);
        s10.setShowTickLabels(true);
        VBox h10 = new VBox(l10, s10);
        Slider s11 = new Slider(0, 5, 1);
        Label l11 = new Label("Minimal amount of mutations");
        s11.setMajorTickUnit(5);
        s11.setMinorTickCount(4);
        s11.setShowTickMarks(true);
        s11.setSnapToTicks(true);
        s11.setShowTickLabels(true);
        VBox h11 = new VBox(l11, s11);
        Slider s12 = new Slider(0, 15, 1);
        Label l12 = new Label("Maximal amount of mutations");
        s12.setMajorTickUnit(5);
        s12.setMinorTickCount(4);
        s12.setShowTickMarks(true);
        s12.setSnapToTicks(true);
        s12.setShowTickLabels(true);
        VBox h12 = new VBox(l12, s12);

        ChoiceBox c13 = new ChoiceBox();
        c13.getItems().addAll("Random genome", "Correction genome");
        Label l13 = new Label("Genome variant");
        //VBox v13 = new VBox(l13,c13);
        ChoiceBox c14 = new ChoiceBox();
        c14.getItems().addAll("Random moves", "Correct moves");
        Label l14 = new Label("Animal variant");
        //VBox v14 = new VBox(l14,c14);
        ChoiceBox c15 = new ChoiceBox();
        c15.getItems().addAll("Globe", "Hell's gate");
        Label l15 = new Label("Map variant");
        //VBox v15 = new VBox(l15,c15);
        ChoiceBox c16 = new ChoiceBox();
        c16.getItems().addAll("Equator", "Toxic corpses");
        Label l16 = new Label("Plant variant");
        //VBox v16 = new VBox(l16,c16);


        CheckBox s13 = new CheckBox("Random genome variant");
        s13.setIndeterminate(false);
        VBox h13 = new VBox(s13);
        CheckBox s14 = new CheckBox("Crazy moves variant");
        s14.setIndeterminate(false);
        VBox h14 = new VBox(s14);
        CheckBox s15 = new CheckBox("Hell's gate variant");
        s15.setIndeterminate(false);
        VBox h15 = new VBox(s15);
        CheckBox s16 = new CheckBox("Toxic corpses variant");
        s15.setIndeterminate(false);
        VBox h16 = new VBox(s16);
        CheckBox s17 = new CheckBox("Save statistics");
        s17.setSelected(true);
        VBox h17 = new VBox(s17);
        Label label = new Label();
        VBox v = new VBox(h1, h2, h3, h4, h5, h6, h7, h8);
        VBox v2 = new VBox(h9, h10, h11, h12, l13, c13, l14, c14, l15, c15, l16, c16, h17, hBox);
        HBox vbox = new HBox(v, v2, grid);
        //VBox vbox = new VBox(h1, h2, h3, h4, h5, h6, h7, h8, h9, h10, h11, h12, l13, c13, l14, c14, l15, c15, l16, c16, h17, grid, hBox);
        GridPane.setHalignment(label, HPos.CENTER);
        Scene scene = new Scene(vbox);
        primaryStage.setTitle("Getting parameters");
        primaryStage.setScene(scene);
        primaryStage.show();

        button.setOnAction(action -> {
            Thread mapCreation = new Thread(() -> {
                try {
                    mapScene(primaryStage, (int) s9.getValue(), (int) s6.getValue(), (int) s7.getValue(), (int) s10.getValue(), (int) s5.getValue(), (int) s8.getValue(), (int) s11.getValue(), (int) s12.getValue(), (int) s2.getValue(), (int) s1.getValue(), (int) s3.getValue(), (int) s4.getValue(), (String) c13.getValue(), (String) c14.getValue(), (String) c15.getValue(), (String) c16.getValue(), (boolean) s17.isSelected());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });
            mapCreation.start();
            synchronized (this) {
                canStart = true;
                notifyAll();
            }
        });
    }


    public void draw(GridPane gridPane, AbstractMap map) {
        Label l = new Label("y\\x");
        GridPane.setHalignment(l, HPos.CENTER);
        gridPane.add(l, 0, 0);

        // background
        /*
        RadialGradient gradient = new RadialGradient(
                0, 0, 0, 0, 1, true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#81c483")),
                new Stop(1, Color.web("#fcc200")));

        gridPane.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));
        */

        gridPane.setBackground(new Background(new BackgroundFill(Color.web(
                "#f2f0c4"), CornerRadii.EMPTY, Insets.EMPTY)));

        for (int i = 1; i < map.getUpperRight().x + 2; i++) {
            Label label = new Label(String.format("%d", i - 1));
            GridPane.setHalignment(label, HPos.CENTER);
            gridPane.add(label, i, 0);
        }

        for (int i = 1; i < map.getUpperRight().y + 2; i++) {
            Label label = new Label(String.format("%d", i - 1));
            GridPane.setHalignment(label, HPos.CENTER);
            gridPane.add(label, 0, i);
        }
        for (int i = 0; i < map.getUpperRight().x + 2; i++) {
            for (int j = 0; j < map.getUpperRight().y + 2; j++) {
                if (map.isOccupied(new Vector2d(i, j))) {
                    List<IMapElement> list = map.objectAt(new Vector2d(i, j));
                    IMapElement element;
                    if (list.size() == 1) {
                        element = list.get(0);
                    } else {
                        if (!(list.get(0) instanceof Plant)) {
                            element = list.get(0);
                        } else {
                            element = list.get(1);
                        }
                    }

                    GuiElementBox g = new GuiElementBox(element);
                    GridPane.setHalignment(g.getGui(), HPos.CENTER);
                    gridPane.add(g.getGui(), i + 1, j + 1);
                }
            }
        }

        for (int i = 0; i < map.getUpperRight().x + 2; i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(50));
        }

        for (int i = 0; i < map.getUpperRight().y + 2; i++) {
            gridPane.getRowConstraints().add(new RowConstraints(50));
        }
    }

    public void update(GridPane gridPane, AbstractMap map, GridPane stat) {
        stat.getChildren().clear();
        Label l1 = new Label("Number of animals: " + map.getNumAnimals());
        Label l2 = new Label("Number of plants: " + map.getNumOfGrass());
        Label l3 = new Label("Number of empty fields: " + map.getNumEmpty());
        Label l4 = new Label("Most popular genotype: " + map.getMostPopularGenome());
        Label l5 = new Label("Average animal energy: " + map.getAvgEnergy());
        Label l6 = new Label("Average lifespan: " + map.getAvgLifespan());
        VBox v = new VBox(l1, l2, l3, l4, l5, l6);

        boolean isTracked = false;
        VBox trackedVBox = new VBox();
        Animal animal1;
        for (int i = 0; i < map.getUpperRight().x + 2; i++) {
            for (int j = 0; j < map.getUpperRight().y + 2; j++) {
                if (map.isOccupied(new Vector2d(i, j))) {
                    List<IMapElement> list = map.objectAt(new Vector2d(i, j));
                    IMapElement element;
                    if (list.size() == 1) {
                        element = list.get(0);
                    } else {
                        if (!(list.get(0) instanceof Plant)) {
                            element = list.get(0);
                        } else {
                            element = list.get(1);
                        }
                    }
                    if (element.getTracked() && element instanceof Animal) {
                        animal1 = (Animal) element;
                        isTracked = true;
                        Label l7 = new Label("Genome: " + animal1.getGenes());
                        Label l8 = new Label("Active part: " + animal1.getActive());
                        Label l9 = new Label("Energy: " + animal1.getEnergy());
                        Label l10 = new Label("Plants eaten: " + animal1.getPlantsEaten());
                        Label l11 = new Label("Number of children: " + animal1.getChildCount());
                        Label l12;
                        if (animal1.checkDeath()) {
                            l12 = new Label("Day of death: " + animal1.getDayOfDeath());
                        } else {
                            l12 = new Label("Lifespan: " + animal1.getAge());
                        }
                        trackedVBox = new VBox(l7, l8, l9, l10, l11, l12);
                    }
                }
            }
        }
        /*
        if (isTracked){
            Label l7 = new Label("Genome: "+ animal1.getNumAnimals());
            Label l8 = new Label("Active part: " + map.getNumOfGrass());
            Label l9 = new Label("Energy: " + map.getNumEmpty());
            Label l10 = new Label("Plants eaten: " + map.getMostPopularGenome());
            Label l11 = new Label("Number of children: " + map.getAvgEnergy());
            Label l12 = new Label("Lifespan: " + map.getAvgLifespan());
            Label l13 = new Label("Day of death: " + map.getAvgLifespan());
        }


         */
        stat.getChildren().addAll(new HBox(v, trackedVBox));

        gridPane.setGridLinesVisible(false);
        gridPane.getChildren().clear();
        draw(gridPane, map);
        gridPane.setGridLinesVisible(true);
        if (map.Dominant()) {
            showDominant(map);
        }
    }

    public Button pause(SimulationEngine engine) {
        Button button = new Button("Pause");
        button.setOnAction(e -> {
            engine.changeState();

            if (Objects.equals(button.getText(), "Play"))
                button.setText("Pause");
            else
                button.setText("Play");
        });

        return button;
    }

    public void showDominant(AbstractMap map) {
        String popularGenome = map.getMostPopularGenome();
        Map<Vector2d, List<Animal>> hash = map.getAnimals();
        for (List<Animal> animalList : hash.values()) {
            for (Animal animal : animalList) {
                if (Objects.equals(popularGenome, animal.getGenes())) {
                    animal.setDominant(true);
                } else {
                    animal.setDominant(false);
                }
            }
        }
    }
}
