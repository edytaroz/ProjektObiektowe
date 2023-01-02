package org.agh.ics.oop;
import org.agh.ics.oop.gui.App;
import javafx.application.Application;


public class World {
    public static void main(String[] args) {
        System.out.println("Hello world");

        try {
            Application.launch(App.class,args);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
