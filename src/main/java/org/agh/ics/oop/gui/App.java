package org.agh.ics.oop.gui;

import javafx.scene.control.*;
import org.agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class App extends Application {
    public GridPane grid;
    public GridPane gridPane;
    IWorldMap map;
    SimulationEngine engine;
    boolean ready = false;
    boolean canStart = false;
    int energy;
    int energyLoss;
    int childEnergy;
    int lenOfGenome;
    int plantEnergy;
    int satietyLevel;
    int minMutation;
    int maxMutation;
    int width;
    int height;
    int numAnimals;
    int numPlants;
    boolean genVariant;
    boolean animalVariant;
    boolean mapVariant;

    @Override
    public void start(Stage primaryStage) throws Exception {
        createParametersGetter(primaryStage);

        Thread mapCreation = new Thread(() -> {
            try {
                mapScene(primaryStage);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        Thread mapThread = new Thread(this::startEngine);
        mapCreation.start();
        mapThread.start();
    }

    public void mapScene(Stage primaryStage) throws InterruptedException {
        synchronized (this) {
            if (!canStart) {
                wait();
            }
        }

        Platform.runLater(() -> {
            primaryStage.hide();
            engine = new SimulationEngine(energyLoss, energy, childEnergy, lenOfGenome, plantEnergy, satietyLevel, minMutation, maxMutation, width, height, numAnimals, numPlants, genVariant, animalVariant, mapVariant, this);
            map = engine.getMap();
            Label r = new Label();
            gridPane = new GridPane();
            draw();
            gridPane.setGridLinesVisible(true);
            VBox vBox = new VBox(gridPane, pause());

            Scene scene = new Scene(vBox);
            Stage stage = new Stage();
            stage.setScene(scene);
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
        VBox h1 = new VBox(s1, l1);
        Slider s2 = new Slider(5, 20, 1);
        Label l2 = new Label("Map width");
        s2.setMajorTickUnit(5);
        s2.setMinorTickCount(4);
        s2.setShowTickMarks(true);
        s2.setSnapToTicks(true);
        s2.setShowTickLabels(true);
        VBox h2 = new VBox(s2, l2);
        Slider s3 = new Slider(5, 10, 1);
        Label l3 = new Label("Number of animals");
        s3.setMajorTickUnit(5);
        s3.setMinorTickCount(4);
        s3.setShowTickMarks(true);
        s3.setSnapToTicks(true);
        s3.setShowTickLabels(true);
        VBox h3 = new VBox(s3, l3);
        Slider s4 = new Slider(0, 15, 1);
        Label l4 = new Label("Number of plants");
        s4.setMajorTickUnit(5);
        s4.setMinorTickCount(4);
        s4.setShowTickMarks(true);
        s4.setSnapToTicks(true);
        s4.setShowTickLabels(true);
        VBox h4 = new VBox(s4, l4);
        Slider s5 = new Slider(10, 50, 1);
        Label l5 = new Label("Plant energy");
        s5.setMajorTickUnit(5);
        s5.setMinorTickCount(4);
        s5.setShowTickMarks(true);
        s5.setSnapToTicks(true);
        s5.setShowTickLabels(true);
        VBox h5 = new VBox(s5, l5);
        Slider s6 = new Slider(50, 300, 1);
        Label l6 = new Label("Animal energy");
        s6.setMajorTickUnit(10);
        s6.setMinorTickCount(9);
        s6.setShowTickMarks(true);
        s6.setSnapToTicks(true);
        s6.setShowTickLabels(true);
        VBox h6 = new VBox(s6, l6);
        Slider s7 = new Slider(50, 100, 1);
        Label l7 = new Label("Child animal energy");
        s7.setMajorTickUnit(10);
        s7.setMinorTickCount(9);
        s7.setShowTickMarks(true);
        s7.setSnapToTicks(true);
        s7.setShowTickLabels(true);
        VBox h7 = new VBox(s7, l7);
        Slider s8 = new Slider(20, 50, 1);
        Label l8 = new Label("Satiety level");
        s8.setMajorTickUnit(10);
        s8.setMinorTickCount(9);
        s8.setShowTickMarks(true);
        s8.setSnapToTicks(true);
        s8.setShowTickLabels(true);
        VBox h8 = new VBox(s8, l8);
        Slider s9 = new Slider(5, 20, 1);
        Label l9 = new Label("Energy loss");
        s9.setMajorTickUnit(5);
        s9.setMinorTickCount(4);
        s9.setShowTickMarks(true);
        s9.setSnapToTicks(true);
        s9.setShowTickLabels(true);
        VBox h9 = new VBox(s9, l9);
        Slider s10 = new Slider(2, 20, 1);
        Label l10 = new Label("Length of genome");
        s10.setMajorTickUnit(5);
        s10.setMinorTickCount(4);
        s10.setShowTickMarks(true);
        s10.setSnapToTicks(true);
        s10.setShowTickLabels(true);
        VBox h10 = new VBox(s10, l10);
        Slider s11 = new Slider(0, 5, 1);
        Label l11 = new Label("Minimal amount of mutations");
        s11.setMajorTickUnit(5);
        s11.setMinorTickCount(4);
        s11.setShowTickMarks(true);
        s11.setSnapToTicks(true);
        s11.setShowTickLabels(true);
        VBox h11 = new VBox(s11, l11);
        Slider s12 = new Slider(0, 15, 1);
        Label l12 = new Label("Maximal amount of mutations");
        s12.setMajorTickUnit(5);
        s12.setMinorTickCount(4);
        s12.setShowTickMarks(true);
        s12.setSnapToTicks(true);
        s12.setShowTickLabels(true);
        VBox h12 = new VBox(s12, l12);
        CheckBox s13 = new CheckBox("Losowy genom");
        s13.setIndeterminate(false);
        VBox h13 = new VBox(s13);
        CheckBox s14 = new CheckBox("Szalone ruchy");
        s14.setIndeterminate(false);
        VBox h14 = new VBox(s14);
        CheckBox s15 = new CheckBox("Piekielny portal");
        s15.setIndeterminate(false);
        VBox h15 = new VBox(s15);
        Label label = new Label();
        VBox vbox = new VBox(h1, h2, h3, h4, h5, h6, h7, h8, h9, h10, h11, h12, h13, h14, h15, grid, hBox);
        GridPane.setHalignment(label, HPos.CENTER);
        Scene scene = new Scene(vbox);
        primaryStage.setTitle("Getting parameters");
        primaryStage.setScene(scene);
        primaryStage.show();

        button.setOnAction(action -> {
            height = (int) s1.getValue();
            width = (int) s2.getValue();
            numAnimals = (int) s3.getValue();
            numPlants = (int) s4.getValue();
            plantEnergy = (int) s5.getValue();
            energy = (int) s6.getValue();
            childEnergy = (int) s7.getValue();
            satietyLevel = (int) s8.getValue();
            energyLoss = (int) s9.getValue();
            lenOfGenome = (int) s10.getValue();
            minMutation = (int) s11.getValue();
            maxMutation = (int) s12.getValue();
            genVariant = s13.isSelected();
            animalVariant = s14.isSelected();
            mapVariant = s15.isSelected();

            synchronized (this) {
                canStart = true;
                notifyAll();
            }
        });
    }

    public void startEngine() {
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
    }

    public void draw() {
        Label l = new Label("y\\x");
        GridPane.setHalignment(l, HPos.CENTER);
        gridPane.add(l, 0, 0);

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

    public void update() {
        gridPane.setGridLinesVisible(false);
        gridPane.getChildren().clear();
        draw();
        gridPane.setGridLinesVisible(true);
    }

    public Button pause() {
        Button button = new Button("Play");
        button.setOnAction(e -> {
            engine.changeState();

            if (Objects.equals(button.getText(), "Play"))
                button.setText("Pause");
            else
                button.setText("Play");
        });

        return button;
    }
}
