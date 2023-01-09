package org.agh.ics.oop.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.agh.ics.oop.IMapElement;
import org.agh.ics.oop.Plant;


public class GuiElementBox {
    Image image;
    ImageView imageView = new ImageView();
    VBox vBox = new VBox();

    public GuiElementBox(IMapElement element) {

        BorderPane border = new BorderPane();
        if (element instanceof Plant){
            image = App.getPlant();
        }else {
            image = App.getAnimal();
        }
        imageView.setImage(image);
        imageView.setCache(true);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        border.setCenter(imageView);
        Button b = new Button();
        b.setOnAction(action -> {
            //System.out.println("Button");
            element.setTracked(true);
        });
        b.setGraphic(border);
        Label label = new Label(element.toString());
        if (element instanceof Plant){
            vBox.getChildren().addAll(border,label);
        }
        else{
            vBox.getChildren().addAll(b,label);
        }
        vBox.setAlignment(Pos.CENTER);
        if (element.getDominant()){
            changeToDominant();
        }

    }

    public VBox getGui(){
        return vBox;
    }
    public void changeToDominant(){
        // #090a0c
        imageView.setStyle("""
                -fx-border-color:orange ;\s
                -fx-border-insets:3;
                -fx-border-radius:7;
                -fx-border-width:1.0""");
        // #090a0c
        vBox.setStyle("""
                -fx-border-color:orange ;\s
                -fx-border-insets:3;
                -fx-border-radius:7;
                -fx-border-width:1.0""");
    }
}
