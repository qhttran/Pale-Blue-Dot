/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newtonlawofgravity;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Kasin
 */
public class Body extends Circle{
    private double mass;
    private String name;
    // private String imageURL = "file:Default.png";
    private Image image;
    private Stage testStage;
    private BodyEditScene scenePop;
    
    public Body(){
        this(50000, 30, "Default", Color.RED);
    }
    
    public Body(double mass, int radius, String name, Color c){
        super(0, 0, radius, c);
        this.mass = mass;
        this.name = name;
        System.out.println("Yo");
        this.setOnMouseReleased(e -> mouseReleased(e));
        testStage = new Stage();
        scenePop = new BodyEditScene(mass, radius);
        testStage.setScene(scenePop);
        testStage.initModality(Modality.APPLICATION_MODAL);
    }
    
    private void mouseReleased(MouseEvent e){
        System.out.println(this.name);
        
        testStage.showAndWait();
        
        this.mass = scenePop.getMass();
        this.setRadius(scenePop.getRadius());
    }

    /**
     * @return the mass
     */
    public double getMass() {
        return mass;
    }

    /**
     * @param mass the mass to set
     */
    public void setMass(double mass) {
        this.mass = mass;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
