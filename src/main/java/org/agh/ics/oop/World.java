package org.agh.ics.oop;
import org.agh.ics.oop.gui.App;
import javafx.application.Application;

public class World {
    public static void main(String[] args) {
        System.out.println("Hello world");
        Animal parent1 = new AnimalLS(10,150,50,8,10,20,new Vector2d(2,2),2,4);
        Animal parent2 = new AnimalLS(10,50,50,8,10,20,new Vector2d(2,2),2,4);
        System.out.println(parent1.genes.genes);
        System.out.println(parent2.genes.genes);
        Animal child = new AnimalLS(parent1,parent2);
        System.out.println(child.genes.genes);
        try {
            Application.launch(App.class,args);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
