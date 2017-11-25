/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newtonlawofgravity;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

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
    public void start(Stage primaryStage){
        
        
        /*************************Graphic Simulation Pane****************************/
        Circle circle = new Circle(0, 0, 100);
        
        Circle circle2 = new Circle(0, 0, 10);
        circle2.setFill(Color.BLUE);
        
        Circle circle3 = new Circle(0,0, 50);
        circle3.setFill(Color.ORANGE);
        
        PathTransition pathTransition = new PathTransition();
        pathTransition.setPath(circle);
        pathTransition.setRate(2);
        pathTransition.setInterpolator(Interpolator.LINEAR);
        pathTransition.setDuration(Duration.millis(40000)); //period
        pathTransition.setNode(circle2);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.play();
        
        StackPane graphicPane = new StackPane();
        graphicPane.getChildren().add(circle2);
        graphicPane.getChildren().add(circle3);
        graphicPane.setMinHeight(240);
        graphicPane.setMinWidth(240);
        graphicPane.setId("graphicPane"); 
        
        
        /***************************Force Calculator Pane****************************/
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
        
        GridPane calcPane = new GridPane();
        calcPane.setId("calcPane"); // for css
        //put container in middle of scene
        calcPane.setAlignment(Pos.CENTER);
        //set spacing between controls in grid
        calcPane.setHgap(10);
        calcPane.setVgap(35);
        //add to grid, cell by cell
        calcPane.add(lbBigG,0,0,4,1); //1st col, 1st row, spans 4 cols
        calcPane.add(lbM1,0,1); // 1st col, 2nd row
        calcPane.add(m1Txt,1,1,2,1); // 2nd col, 2nd row, spans 2 cols
        calcPane.add(m1Unit,3,1);// 3rd col, 2nd row
        calcPane.add(lbM2,0,2); // 1st col, 3rd row
        calcPane.add(m2Txt,1,2,2,1); // 2nd col, 3rd row, spans 2 cols
        calcPane.add(m2Unit,3,2);// 3rd col, 3rd row
        calcPane.add(lbR,0,3); // 1st col, 4th row
        calcPane.add(sliderR,1,3,2,1); // 2nd col, 4th row, spans 2 cols
        calcPane.add(rUnit,3,3); // 3rd col, 4th row
        calcPane.add(lbForce,0,4); // 1st col, 5th row
        calcPane.add(lbAnswer,1,4,2,1); // 2nd col, 5th row, spans 2 cols
        calcPane.add(fUnit,3,4); // 3rd col, 5th row
        calcPane.add(btnCalc,2,5); // 6th row
        calcPane.add(lbError,0,6,4,1); //1st col, 7th row, spans 4 cols
        
        //set widths of all controls in separate method
        setWidths();
        
        
        /********************************Big General Pane****************************/
        GridPane bigPane = new GridPane();
        bigPane.setAlignment(Pos.CENTER);
        bigPane.add(graphicPane,0,0);
        bigPane.add(calcPane,1,0);
        bigPane.setHgap(10);
        Scene scene = new Scene(bigPane, 1000, 750);
        scene.getStylesheets().add
                     (NewtonLawOfGravity.class.getResource("newtonsLaw.css").toExternalForm());
        
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
        btnCalc.setPrefWidth(150);
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
                ex.toString();
                lbError.setText("Invalid Value! Masses can't be zero or negative!");
            }
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
    public class InvalidValueException extends IllegalArgumentException{
    
        private String msg;
        private static final String DEFAULT_EXCEPTION_MSG = "INVALID VALUE!";

        public InvalidValueException(){
            super();
            msg = new String(DEFAULT_EXCEPTION_MSG );
        }
        public InvalidValueException(String msg){
                    super(msg);
                    this.msg = new String(msg);
            }

        @Override
        public String getMessage(){
            return new String(msg);
        }
        @Override
        public String toString(){
            return new String(getClass().getName() + ": " + msg);
        }
    }
    
}
