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
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;

/**
 *
 * @author Kasin
 */
public class BodyEditScene extends Scene{
    Body body;
    private Color c;
    private Image image;
    private Label massLabel, radiusLabel, colorLabel, imageLabel;
    private TextField massText, radiusText;
    private Button saveBtn;
    private GridPane gp;
    
    public BodyEditScene(Body b){
        super(new GridPane(), 400, 400);
        this.body = b;
        massLabel = new Label("Mass (kg): ");
        radiusLabel = new Label("Radius (m): ");
        colorLabel = new Label("Color: ");
        imageLabel = new Label("Image: ");
        saveBtn = new Button("Save");
        saveBtn.setOnAction(e -> saveButtonPressed(e));
        gp = new GridPane();
        this.setRoot(gp);
        massText = new TextField(Double.toString(body.getMass()));
        radiusText = new TextField(Double.toString(body.getRadius()));
        
        gp.add(massLabel, 0, 0);
        gp.add(massText, 1, 0);
        gp.add(radiusLabel, 0, 1);
        gp.add(radiusText, 1, 1);
        gp.add(saveBtn, 0, 2);
        
        //this.getRoot().getChildrenUnmodifiable().add(saveBtn);
        //this.getRoot().getChildrenUnmodifiable().add(massLabel);
        //this.getRoot().getChildrenUnmodifiable().add(massText);
        
    }
    
    public void updateData(){
        massText.setText(Double.toString(body.getMass()));
        radiusText.setText(Double.toString(body.getRadius()));
    }
    
    private void saveButtonPressed(ActionEvent e){
        if(e.getSource().equals(saveBtn)){
            body.setRadius(Double.parseDouble(radiusText.getText()));
            body.setMass(Double.parseDouble(massText.getText()));
        }
    }

    public Color getC() {
        return c;
    }

    public Image getImage() {
        return image;
    }
}
