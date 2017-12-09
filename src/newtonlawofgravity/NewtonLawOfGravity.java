/*********************BIG SECTION LABEL*******************/
/*********************************************************/

/*********************SMALL SECTION LABEL*******************/

/*--- For debugging purposes, to be deleted later ---*/

//++ TODO

/*<< Explaining the method>>*/

// explaining detail

package newtonlawofgravity;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Popup;
import javafx.util.Duration;


public class NewtonLawOfGravity extends Application {
    
    private static final double BIG_G = 6.674 * Math.pow(10, -11);
    public final double minDeltaTime = 0.0;
    public final double maxDeltaTime = 2.0;
    public static final double DEFAULT_MASS_1 = 1000;
    public static final double DEFAULT_MASS_2 = 10000;
    
    static double mass1, mass2, distance, force, bigM, period, 
            deltaTime = 1.0; // default "speed"
    static TextField m1Txt, m2Txt, periodTxt, adjPeriod_Txt;
    static Label lbForce, lbBigG, lbM1, lbM2, lbR, lbAnswer, 
            m1Unit, m2Unit, fUnit, rUnit, lbError;
    static Slider sliderR, sliderDeltaTime;
    //static Button btnCalc, btnPlay, btnPause;
    static PathTransition pathTransition;
    static Body b1, b2;
    static Popup popup;
    static BorderPane graphicPane;
    static Stage testStage;
    static GridPane bodyValuesPane, bodyEditPane;
    static FlowPane bodyButtonPane;
    static Button saveChangesBtn;
    
    static Label massLabel;
    static TextField massValue;
    
    @Override
    public void start(Stage primaryStage){
        
        
        graphicPane = new GraphicPane();
        graphicPane.setPrefWidth(700);
        
        
      /***************************FORCE CALCULATOR PANE****************************/
      /****************************************************************************/
        /**********CREATE COMPONENTS**********/
          // Big G
        lbBigG = new Label("Universal Gravitational Constant (G) "
                + "= 6.6740 x 10^-11 m^3 kg^-1 s^-2");
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
          // other
        lbError = new Label("");
        //btnCalc = new Button("Calculate");
        //btnCalc.setOnAction(e -> actionPerformed(e));
        
        /**********CENTER TEXT IN LABEL**********/
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
        
        /**********APPLY SIMPLE BORDER BY CSS**********/
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
        
        /**********STYLING FOR DISTANCE SLIDER**********/
        sliderR.setShowTickMarks(true);
        sliderR.setShowTickLabels(true);
        sliderR.setValueChanging(true);
        sliderR.setMajorTickUnit(10);
        
        /********************************BOTTOM HBOX****************************/
             
        /******************************** TEXT PANE/ABOUT ****************************/ 
        TextFlow textPane = new TextFlow();
        textPane.setId("textPane");
        textPane.setPrefWidth(200);
        
        Text aboutTitle = new Text();
        aboutTitle.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
                + "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris"
                + " nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in"
                + " reprehenderit in voluptate velit esse cillum dolore eu fugiat "
                + "nulla pariatur. Excepteur sint occaecat cupidatat non proident, "
                + "sunt in culpa qui officia deserunt mollit anim id est laborum.");
        textPane.getChildren().add(aboutTitle);
      
      /********************************BIG GENERAL PANE****************************/ 
        BorderPane bigPane = new BorderPane();
        bigPane.setPrefSize(1200, 650);
        bigPane.setMinSize(1200, 650);
        bigPane.setMinSize(1200, 650);
        bigPane.setCenter(graphicPane);
        bigPane.setRight(textPane);
        //bigPane.setBottom(bottomBar);

        Scene scene = new Scene(bigPane, 1200, 650);
        scene.getStylesheets().add
                     (NewtonLawOfGravity.class.getResource("newtonsLaw.css").toExternalForm());
        primaryStage.setTitle("Pale Blue Dot");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 
    
  /*<< Calculate and return the appropriate period (orbiting speed) >>*/
    public static double getPeriod(){      
      // compare to find the larger mass
        if(mass1 > mass2){
            bigM = mass1;
        }
        else{
            bigM = mass2;
        }
        
      // calc period in seconds
        double p = (2*Math.PI) * Math.sqrt(Math.pow(distance, 3)/(bigM * BIG_G));     
        return p;
    }
    
    
    /*<< Controlling the time slider to speed up/slow down period >>*/
    public void mouseReleased(MouseEvent e){
        System.out.println(e.getSource());
        if(e.getSource().equals(sliderDeltaTime)){
            //++ TODO Clean up      
            deltaTime = sliderDeltaTime.getValue();
            /*---System.out.println("Delta Time mouseReleased: " + deltaTime 
                + " Period mouseReleased: " + period);---*/

          //++ TODO adj. Duration.millis(period * 1000 / deltaTime) 
          //++based on how the slider value is recorded
            if(deltaTime == 0){
                pathTransition.setDuration(Duration.millis(period * 1000));
            }
            else{
                pathTransition.setDuration(Duration.millis((period * 1000) /  (period * deltaTime)));
            }
            System.out.println("Duration after adjusted with slider: " + (period * 1000) /  (period * deltaTime));
            pathTransition.playFromStart();
        }
        else if(e.getSource().getClass().equals(Body.class)){
            if(e.getSource().equals(b1)){
                // popup window to adjust the values of body 1
                System.out.println("Body1");
                
                testStage.setTitle("Body1 Values");
                massValue.setText(Double.toString(b1.getMass()));
                testStage.showAndWait();
                
            }
            else if(e.getSource().equals(b2)){
                // popup window to adjust the values of body 2
                System.out.println("Body2");
                
                testStage.setTitle("Body2 Values");
                massValue.setText(Double.toString(b2.getMass()));
                testStage.showAndWait();
            }
        }
    }
    
    
    /*<< So far: controlling buttons: Calculate, Play, and Pause >>
    public void actionPerformed(ActionEvent e){
      /******************FOR btnCalc/CALCULATING FORCE******************
        if(e.getSource() == btnCalc){
            try{
                
                if(Double.parseDouble(m1Txt.getText()) <= 0 
                    || Double.parseDouble(m2Txt.getText()) <= 0){
                    throw new InvalidValueException();
                }  
                
                else {
                    mass1 = Double.parseDouble(m1Txt.getText());
                    mass2 = Double.parseDouble(m2Txt.getText());
                    distance = sliderR.getValue();
                    force = (BIG_G*mass1*mass2)/Math.pow(distance, 2);
                    lbAnswer.setText("" + force);                  
                }
            }
            catch(InvalidValueException ex){
                ex.toString();
                lbError.setText("Values have to be integer or double, "
                        + "and cannot be zero or negative ");
            }
        }
      /*****************************************************************
        
        else if(e.getSource() == btnPlay){
            playbtnPerformed();
        }
        else if(e.getSource() == btnPause){
            pathTransition.pause();
        }
    }*/
    
    
    /*<< Exclusively used to control Play button >>
    public static void playbtnPerformed(){
        if(m1Txt.getText().equals("") || m2Txt.getText().equals("")){
            mass1 = DEFAULT_MASS_1;
            mass2 = DEFAULT_MASS_2;
            m1Txt.setText("" + DEFAULT_MASS_1);
            m2Txt.setText("" + DEFAULT_MASS_2);
            distance = sliderR.getValue();
            force = (BIG_G*mass1*mass2)/Math.pow(distance, 2);
            lbAnswer.setText("" + force);
            
            period = getPeriod();
            /*---System.out.println("Period: " + period);
            System.out.println("Big mass: " + bigM);
            System.out.println("Distance: " + distance);---
           
        }
        else{
            mass1 = Double.parseDouble(m1Txt.getText());
            mass2 = Double.parseDouble(m2Txt.getText());
            
            period = getPeriod();
        }
        
      //++ TODO adj. Duration.millis(period * 1000 / deltaTime) based on how the slider value is recorded
        pathTransition.setDuration(Duration.millis((period * 1000) /  (period * deltaTime)));
        System.out.println("Duration after hit play button: " + (period * 1000) /  (period * deltaTime));
        pathTransition.play();
    }*/
    
 
    public static void main(String[] args) {
        launch(args);
    }
}


/* ############# QUYEN'S TO-DOs ##############
- Re-arrange the content's layout
- Add 2 labels to display the "real" period vs the "adjusted" period.
- Style the GUI with CSS
*/

/* ############# KASIN'S TO-DOs ##############
- Rewrite the period/duration calculating function if needed..... needs work
- Add pop-up windows for 2 planets............................... in progress
- Add period/duration slider function............................ TODO
*/