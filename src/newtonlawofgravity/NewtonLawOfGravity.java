/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newtonlawofgravity;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

/**
 *
 * @author Quyen
 */
public class NewtonLawOfGravity extends Application {
    
    private final double BIG_G = 6.674 * Math.pow(10, -11);
    TextField m1Txt, m2Txt;
    Label lbForce, lbBigG, lbM1, lbM2, lbR, lbAnswer, 
            m1Unit, m2Unit, fUnit, rUnit, lbError;
    Slider sliderR;
    Button btnCalc;
    
    @Override
    public void start(Stage primaryStage) {
      //create components
          // Big G
        lbBigG = new Label("Universal Gravitational Constant (G) = 6.6740 x 10^-11 m^3 kg^-1 s^-2");
          // 1st mass
        lbM1 = new Label("Mass 1 = ");
        m1Txt = new TextField();
        m1Unit = new Label("kg");
          // 2nd mass
        lbM2 = new Label("Mass 2 = ");
        m2Txt = new TextField();
        m2Unit = new Label("kg");
          // distance r
        lbR = new Label("Distance (r) = ");
        sliderR = new Slider(10, 100, 55);
        rUnit = new Label("m");
          // gravitational force
        lbForce = new Label("Force G = ");
        lbAnswer = new Label("?");
        fUnit = new Label("N"); 
        
        btnCalc = new Button("Calculate");
        lbError = new Label("");
      // center text in label
        lbForce.setAlignment(Pos.CENTER_LEFT);
        lbAnswer.setAlignment(Pos.CENTER);
        lbBigG.setAlignment(Pos.CENTER);
        lbM1.setAlignment(Pos.CENTER_LEFT);
        lbM2.setAlignment(Pos.CENTER_LEFT);
        lbR.setAlignment(Pos.CENTER_LEFT);
        m1Unit.setAlignment(Pos.CENTER);
        m2Unit.setAlignment(Pos.CENTER);
        rUnit.setAlignment(Pos.CENTER);
        fUnit.setAlignment(Pos.CENTER);
        lbError.setAlignment(Pos.CENTER);
          // apply css-like style to label
        lbForce.setStyle("-fx-border-color: #C0C0C0; -fx-padding: 10px;");
        lbAnswer.setStyle("-fx-border-color: #C0C0C0; -fx-padding: 10px;");
        lbBigG.setStyle("-fx-border-color: #C0C0C0; -fx-padding: 10px;");
        lbM1.setStyle("-fx-border-color: #C0C0C0; -fx-padding: 10px;");
        lbM2.setStyle("-fx-border-color: #C0C0C0; -fx-padding: 10px;");
        lbR.setStyle("-fx-border-color: #C0C0C0; -fx-padding: 10px;");
        m1Unit.setStyle("-fx-padding: 10px;");
        m2Unit.setStyle("-fx-padding: 10px;");
        rUnit.setStyle("-fx-padding: 10px;");
        fUnit.setStyle("-fx-padding: 10px;");
        lbError.setStyle("-fx-color: red");
          // set slider style
        sliderR.setShowTickMarks(true);
        sliderR.setShowTickLabels(true);
        sliderR.setValueChanging(true);
        sliderR.setMajorTickUnit(10);
          // set action to button
        btnCalc.setOnAction(e -> actionPerformed(e));       
        
        GridPane root = new GridPane();
        //put container in middle of scene
        root.setAlignment(Pos.CENTER);
        //set spacing between controls in grid
        root.setHgap(10);
        root.setVgap(35);
        //add to grid, cell by cell
        root.add(lbBigG,0,0,4,1); //1st col, 1st row, spans 4 cols
        root.add(lbM1,0,1); // 1st col, 2nd row
        root.add(m1Txt,1,1,2,1); // 2nd col, 2nd row, spans 2 cols
        root.add(m1Unit,3,1);// 3rd col, 2nd row
        root.add(lbM2,0,2); // 1st col, 3rd row
        root.add(m2Txt,1,2,2,1); // 2nd col, 3rd row, spans 2 cols
        root.add(m2Unit,3,2);// 3rd col, 3rd row
        root.add(lbR,0,3); // 1st col, 4th row
        root.add(sliderR,1,3,2,1); // 2nd col, 4th row, spans 2 cols
        root.add(rUnit,3,3); // 3rd col, 4th row
        root.add(lbForce,0,4); // 1st col, 5th row
        root.add(lbAnswer,1,4,2,1); // 2nd col, 5th row, spans 2 cols
        root.add(fUnit,3,4); // 3rd col, 5th row
        root.add(btnCalc,1,5); // 6th row
        root.add(lbError,0,6,4,1); //1st col, 7th row, spans 4 cols
        
        //set widths of all controls in separate method
        setWidths();
        
        Scene scene = new Scene(root, 550, 500);
        
        primaryStage.setTitle("Newton's Gravitational Law");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void setWidths(){
          // row 1
        lbBigG.setPrefWidth(430); 
          // row 2
        lbM1.setPrefWidth(100); 
        m1Txt.setPrefWidth(210);
        m1Unit.setPrefWidth(100);
          // row 3
        lbM2.setPrefWidth(100); 
        m2Txt.setPrefWidth(210);
        m2Unit.setPrefWidth(100);
          // row 4
        lbR.setPrefWidth(100); 
        sliderR.setPrefWidth(210);
        rUnit.setPrefWidth(100);
          // row 5
        lbForce.setPrefWidth(100);
        lbAnswer.setPrefWidth(210);
        fUnit.setPrefWidth(100);
          // row 6
        btnCalc.setPrefWidth(100);
          // row 7
        lbError.setPrefWidth(430);
    }
    
    public void actionPerformed(ActionEvent e){
        double mass1, mass2, distance, force;
        if(e.getSource() == btnCalc){
            try{
                
                if(Double.parseDouble(m1Txt.getText()) <= 0 
                    || Double.parseDouble(m2Txt.getText()) <= 0 ){
                    throw new InvalidValueException();
                }         
                else {
                    mass1 = Double.parseDouble(m1Txt.getText());
                    mass2 = Double.parseDouble(m2Txt.getText());
                    distance = sliderR.getValue();
                    force = (BIG_G*mass1*mass2)/Math.pow(distance, 2);
                    //display answer
                    lbAnswer.setText("" + force);
                }
            }
            catch(InvalidValueException ex){
                lbError.setText("Invalid Value! Masses can't be zero or negative!");
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
