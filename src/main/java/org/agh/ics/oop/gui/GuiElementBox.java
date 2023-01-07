package org.agh.ics.oop.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.agh.ics.oop.IMapElement;
import org.agh.ics.oop.Plant;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class GuiElementBox {
    Image image;
    ImageView imageView = new ImageView();
    VBox vBox = new VBox();

    public GuiElementBox(IMapElement element) {
        BorderPane border = new BorderPane();
        border.setCenter(imageView);
        if (element instanceof Plant){
            image = App.getPlant();
        }else {
            image = App.getAnimal();
        }
        imageView.setImage(image);
        imageView.setCache(true);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        Label label = new Label(element.toString());
        vBox.getChildren().addAll(border,label);
        vBox.setAlignment(Pos.CENTER);
        if (element.getDominant()){
            changeToDominant();
        }

    }

    public VBox getGui(){
        return vBox;
    }
    public void changeToDominant(){
        imageView.setStyle("-fx-border-color:darkblue ; \n" // #090a0c
                + "-fx-border-insets:3;\n" + "-fx-border-radius:7;\n" + "-fx-border-width:1.0");
        vBox.setStyle("-fx-border-color:darkblue ; \n" // #090a0c
                + "-fx-border-insets:3;\n" + "-fx-border-radius:7;\n" + "-fx-border-width:1.0");
    }
}
