package org.agh.ics.oop.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import org.agh.ics.oop.IMapElement;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class GuiElementBox {
    Image image;
    ImageView imageView = new ImageView();
    VBox vBox = new VBox();

    public GuiElementBox(IMapElement element) {
        try {
            image = new Image(new FileInputStream(element.getImagePath()));
            imageView.setImage(image);
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            Label label = new Label(element.getPosition().toString());
            vBox.getChildren().addAll(imageView,label);
            vBox.setAlignment(Pos.CENTER);
        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public VBox getGui(){
        return vBox;
    }
}
