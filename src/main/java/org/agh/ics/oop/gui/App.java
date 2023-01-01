package org.agh.ics.oop.gui;
import org.agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {
    public GridPane grid;

    @Override
    public void start(Stage primaryStage) throws Exception {
        grid = new GridPane();
        Button button = new Button("Start");
        HBox hBox = new HBox(button);
        hBox.setAlignment(Pos.CENTER);

        TextField textField = new TextField();
        Label label = new Label("Map height");
        Label l = new Label("Map height");
        HBox h1 = new HBox(textField,l);

        TextField textField1 = new TextField();
        Label label1 = new Label("Map width");
        HBox h2 = new HBox(textField1,label1);

        TextField textField2 = new TextField();
        Label label2 = new Label("Number of animals");
        HBox h3 = new HBox(textField2,label2);

        TextField textField3 = new TextField();
        Label label3 = new Label("Number of plants");
        HBox h4 = new HBox(textField3,label3);

        TextField textField4 = new TextField();
        Label label4 = new Label("Plant energy");
        HBox h5 = new HBox(textField4,label4);

        TextField textField5 = new TextField();
        Label label5 = new Label("Animal energy");
        HBox h6 = new HBox(textField5,label5);

        TextField textField6 = new TextField();
        Label label6 = new Label("Child animal energy");
        HBox h7 = new HBox(textField6,label6);

        TextField textField7 = new TextField();
        Label label7 = new Label("Satiety level");
        HBox h8 = new HBox(textField7,label7);

        TextField textField8 = new TextField();
        Label label8 = new Label("Energy loss");
        HBox h9 = new HBox(textField8,label8);

        TextField textField9 = new TextField();
        Label label9 = new Label("Length of genome");
        HBox h10 = new HBox(textField9,label9);

        TextField textField10 = new TextField();
        Label label10 = new Label("Minimal amount of mutations");
        HBox h11 = new HBox(textField10,label10);

        TextField textField11 = new TextField();
        Label label11 = new Label("Maximal amount of mutations");
        HBox h12 = new HBox(textField11,label11);

        TextField textField12 = new TextField();
        Label label12 = new Label("Genome variant l or k");
        HBox h13 = new HBox(textField12,label12);

        TextField textField13 = new TextField();
        Label label13 = new Label("Movement variant n or s");
        HBox h14 = new HBox(textField13,label13);

        //VBox vbox = new VBox(label,textField,textField1,textField2,v,grid,hBox);
        VBox vbox = new VBox(h1,h2,h3,h4,h5,h6,h7,h8,h9,h10,h11,h12,h13,h14,grid,hBox);
        GridPane.setHalignment(label, HPos.CENTER);

        //engine = new SimulationEngine(map, positions,this);
        button.setOnAction(action -> {
            String str1 = textField.getText();
            String str2 = textField1.getText();
            String str3 = textField2.getText();
            String str4 = textField3.getText();
            String str5 = textField4.getText();
            String str6 = textField5.getText();
            String str7 = textField6.getText();
            String str8 = textField7.getText();
            String str9 = textField8.getText();
            String str10 = textField9.getText();
            String str11 = textField10.getText();
            String str12 = textField11.getText();
            //engine.setDirections(OptionsParser.parse(str));
            //Thread thread = new Thread();
            //thread.start();
            //IEngine engine = new SimulationEngine(str1,str2,str3,str4,str5,str6,str7,str8,str9,str10,str11);
            //engine.run();
        });
        /*
        drawHeader();
        drawObjects();
        set_column_and_row_constrain();
        grid.setGridLinesVisible(true);
        */

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
