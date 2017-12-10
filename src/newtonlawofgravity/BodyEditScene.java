/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newtonlawofgravity;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
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
    private Label massLabel, radiusLabel, colorLabel, imageLabel, errorLabel;
    private TextField massText, radiusText;
    private Button saveBtn;
    private GridPane gp;
    
    public BodyEditScene(Body b){
        super(new GridPane(), 300, 250);
        this.body = b;
        this.getStylesheets().add
                     (NewtonLawOfGravity.class.getResource("newtonsLaw.css").toExternalForm());
        
        massLabel = new Label("MASS (kg): ");
        radiusLabel = new Label("RADIUS (m): ");
        colorLabel = new Label("Color: ");
        imageLabel = new Label("Image: ");
        errorLabel = new Label("");
        errorLabel.setTextFill(Color.RED);
        saveBtn = new Button("SAVE");
        saveBtn.setPrefWidth(70);
        saveBtn.setOnAction(e -> saveButtonPressed(e));
        gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setVgap(5);
        this.setRoot(gp);
        massText = new TextField(Double.toString(body.getMass()));
        radiusText = new TextField(Double.toString(body.getRadius()));
        
        gp.add(massLabel, 0, 0);
        gp.add(massText, 1, 0);
        gp.add(radiusLabel, 0, 1);
        gp.add(radiusText, 1, 1);
        gp.add(saveBtn, 1, 2);
        gp.add(errorLabel, 0, 4, 2, 1);
        
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
            try{
                if(massText.getText().isEmpty()
                    || radiusText.getText().isEmpty()){
                    System.out.println("Exception caught: empty value");
                    errorLabel.setText("Please enter mass/radius value");
                    throw new InvalidValueException();
                }
                else if(Double.parseDouble(massText.getText()) <= 0 
                    || Double.parseDouble(radiusText.getText()) <= 0){
                    System.out.println("Exception caught: illegal value");
                    errorLabel.setText("Value(s) has to be greater than 0");
                    throw new InvalidValueException();
                }
                else{
                    body.setRadius(Double.parseDouble(radiusText.getText()));
                    body.setMass(Double.parseDouble(massText.getText()));
                    System.out.println("New radius: " + radiusText.getText());
                    System.out.println("New mass: " + massText.getText());
                }
            }
            catch(InvalidValueException ex){
                ex.toString();
            }           
        }
    }

    public Color getC() {
        return c;
    }

    public Image getImage() {
        return image;
    }
}
