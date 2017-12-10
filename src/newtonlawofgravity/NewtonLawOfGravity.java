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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Popup;


public class NewtonLawOfGravity extends Application {
    
    public static final double BIG_G = 6.674 * Math.pow(10, -11);
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
             
        /******************************** TEXT PANE/ABOUT ****************************/
        ScrollPane scrollText = new ScrollPane();
        TextFlow textPane = new TextFlow();
        textPane.setId("textPane");
        textPane.setPrefWidth(220);
        
        Text title = new Text("PALE BLUE DOT\n\n\n");
        title.setStyle("-fx-font-weight: bold;");
        title.setFont(Font.font ("Verdana", 18));
        Text editBody = new Text("TO EDIT BODY: \n");
        //editBody.setStyle("-fx-font-weight: bold;");
        
        Text instruction = new Text();
        instruction.setText(
                "1. Click the body.\n"
                + "2. Enter desirable values (MUST BE A POSTIVE RATIONAL NUMBER).\n"
                + "3. Save and close the window.\n\n"
                
                + "*Force will be calculated automatically. If not, press 'GET FORCE' button.\n\n "
                
                + "**Use 'SPEED SLIDER' to fast forward or slow down the animation.\n "
                + "**At 0 adjustment, the animation is playing the ACTUAL PERIOD.\n\n"
                
                + "***Use 'DISTANCE SLIDER' to adjust the distance between 2 bodies.\n"
                + "***The smallest distance is the SUM of their radiuses, max distance "
                + "will be limited by the width of the animation pane (1000)");
        textPane.getChildren().addAll(title, editBody, instruction);
        scrollText.setContent(textPane);
      
      /********************************BIG GENERAL PANE****************************/ 
        BorderPane bigPane = new BorderPane();
        bigPane.setPrefSize(1220, 650);
        bigPane.setMinSize(1220, 650);
        bigPane.setMinSize(1220, 650);
        bigPane.setCenter(graphicPane);
        bigPane.setRight(scrollText);
        //bigPane.setBottom(bottomBar);

        Scene scene = new Scene(bigPane, 1220, 650);
        scene.getStylesheets().add
                     (NewtonLawOfGravity.class.getResource("newtonsLaw.css").toExternalForm());
        primaryStage.setTitle("Pale Blue Dot");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 

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