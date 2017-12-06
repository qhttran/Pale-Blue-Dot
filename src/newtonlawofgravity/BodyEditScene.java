/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newtonlawofgravity;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 *
 * @author Kasin
 */
public class BodyEditScene extends Scene{
    private double mass;
    private int radius;
    private Color c;
    private Image image;
    private Label massLabel, radiusLabel, colorLabel, imageLabel;
    private TextField massText, radiusText;
    private Button saveBtn;
    private GridPane gp;
    
    public BodyEditScene(double mass, int radius){
        super(new GridPane(), 400, 400);
        massLabel = new Label("Mass (kg): ");
        radiusLabel = new Label("Radius (m): ");
        colorLabel = new Label("Color: ");
        imageLabel = new Label("Image: ");
        saveBtn = new Button("Save");
        saveBtn.setOnAction(e -> saveButtonPressed(e));
        gp = new GridPane();
        this.setRoot(gp);
        massText = new TextField(Double.toString(mass));
        radiusText = new TextField(Integer.toString(radius));
        
        gp.add(massLabel, 0, 0);
        gp.add(massText, 1, 0);
        gp.add(radiusLabel, 0, 1);
        gp.add(radiusText, 1, 1);
        gp.add(saveBtn, 0, 2);
        
        //this.getRoot().getChildrenUnmodifiable().add(saveBtn);
        //this.getRoot().getChildrenUnmodifiable().add(massLabel);
        //this.getRoot().getChildrenUnmodifiable().add(massText);
        
    }
    
    private void saveButtonPressed(ActionEvent e){
        if(e.getSource().equals(saveBtn)){
            mass = Double.parseDouble(massText.getText());
            radius = Integer.parseInt(radiusText.getText());
        }
    }

    /**
     * @return the mass
     */
    public double getMass() {
        return mass;
    }

    /**
     * @return the radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * @return the c
     */
    public Color getC() {
        return c;
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }
}
