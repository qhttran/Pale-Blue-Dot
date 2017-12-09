/*
 * This is the pane that contains the animation with the bodies, play/pause buttons,
 * and the deltaTimeSlider.

NOTE: FOR SOME REASONS THE MASS UPDATED BUT THE DISTANCE DOESN'T
 */
package newtonlawofgravity;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 *
 * @author Kasin
 */
public class GraphicPane extends BorderPane{
    //++ add the slider function
    private static final double BIG_G = 6.674 * Math.pow(10, -11);
    private double force, period;
    
    public HBox bottomToolbar, HboxController, forceHbox;
    private Slider sliderDeltaTime, sliderDistance;
    private Button btnPlay, btnPause, btnForce;
    
    private Body b1, b2;
    private OrbitPath op;
    
    public Label timeSliderLabel, distanceSliderLabel;
    private Label forceAnswer;
    private StackPane testPane;
    
    public GraphicPane(){
        this(new Body(12225, 20, "Body1", Color.GREY), new Body(1455874, 40, "Body2", Color.ORANGE));
    }
    
    public GraphicPane(Body b1, Body b2){
        super();
        testPane = new StackPane();
        testPane.setId("testPane");
        this.setCenter(testPane);
        
        this.b1 = b1;
        this.b2 = b2;
        op = new OrbitPath(b2, b1);
        op.setRadius(100);
        //b1.setOnMouseReleased(e -> mouseReleased(e));
        //b2.setOnMouseReleased(e -> mouseReleased(e));
        testPane.getChildren().add(b1);
        testPane.getChildren().add(b2);
        this.setMinWidth(300);
        //this.setAlignment(Pos.CENTER);
        this.setId("graphicPane"); // for css
        
        btnPlay = new Button("PLAY");
        btnPlay.setOnAction(e -> actionPerformed(e));
        btnPause = new Button("PAUSE");
        btnPause.setOnAction(e -> actionPerformed(e));
        
        timeSliderLabel = new Label("SPEED SLIDER");
        sliderDeltaTime = new Slider(0.0, 2.0, 0.1);
        sliderDeltaTime.setOnMouseReleased(e -> mouseReleased(e));
        
        distanceSliderLabel = new Label("DISTANCE(m)");
        sliderDistance = new Slider(b1.getRadius() + b2.getRadius(), 1200, b1.getRadius() + b2.getRadius());
        sliderDistance.setOnMouseReleased(e -> mouseReleased(e));
        HboxController = new HBox();
        HboxController.getChildren().addAll(btnPlay, btnPause, 
                                            timeSliderLabel, sliderDeltaTime,
                                            distanceSliderLabel, sliderDistance);
        HboxController.setSpacing(10);
        
        btnForce = new Button("FORCE(N)= ");
        btnForce.setOnAction(e -> actionPerformed(e));
        forceAnswer = new Label("");
        forceAnswer.setStyle("-fx-border-color: gray");
        forceAnswer.setPrefWidth(150);
        forceHbox = new HBox();
        forceHbox.getChildren().addAll(btnForce, forceAnswer);
        forceHbox.setSpacing(5);
             
        bottomToolbar = new HBox();
        bottomToolbar.setAlignment(Pos.CENTER);
        bottomToolbar.setId("bottomToolbar");
        bottomToolbar.setSpacing(30);
        bottomToolbar.setPadding(new Insets(10, 12, 10, 12));
        bottomToolbar.getChildren().addAll(HboxController, forceHbox);
        this.setBottom(bottomToolbar);
        b1.setOnMouseReleased(e -> mouseReleased(e));
        b2.setOnMouseReleased(e -> mouseReleased(e));
    }
    
    public void mouseReleased(MouseEvent e){
        if(e.getSource().equals(sliderDeltaTime)){
            System.out.println("Slider");
            op.calcPeriod(b1, b2, sliderDistance.getValue());
            System.out.println("Period: " + op.getPeriod());
            op.calcDeltaTime(sliderDeltaTime.getValue());
        }
        else if(e.getSource().equals(sliderDistance)){
            op.setRadius((int) sliderDistance.getValue());
            op.getPath().playFromStart();
            forceAnswer.setText("" + getForce());
        }
        else if(e.getSource().equals(b1)){
            b1.popUp();
            testPane.getChildren().clear();
            
            System.out.println(b1.getCenterX());
            System.out.println(b2.getCenterX());
            
            if(b1.compareTo(b2).equals(b1)){
                op.setNode(b1);
                b2 = new Body(b2);
                b2.setOnMouseReleased(a -> mouseReleased(a));
            }
            else{
                op.setNode(b2);
                b1 = new Body(b1);
                b1.setOnMouseReleased(a -> mouseReleased(a));
            }
            System.out.println(b1.getCenterX());
            System.out.println(b2.getCenterX());
            testPane.getChildren().add(b1);
            testPane.getChildren().add(b2);
            sliderDistance.setMin(b1.getRadius() + b2.getRadius());
            op.getPath().playFromStart();
        }
        else if(e.getSource().equals(b2)){
            b2.popUp();
            testPane.getChildren().clear();
            
            if(b1.compareTo(b2).equals(b1)){
                op.setNode(b1);
                b2 = new Body(b2);
                b2.setOnMouseReleased(a -> mouseReleased(a));
            }
            else{
                op.setNode(b2);
                b1 = new Body(b1);
                b1.setOnMouseReleased(a -> mouseReleased(a));
            }
            //b1.setRadius(500.0);
            System.out.println(b1.getCenterX());
            System.out.println(b2.getCenterX());
            testPane.getChildren().add(b1);
            testPane.getChildren().add(b2);
            sliderDistance.setMin(b1.getRadius() + b2.getRadius());
            op.getPath().playFromStart();
        }
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(btnPlay)){
            op.calcPeriod(b1, b2, sliderDistance.getValue());
            op.calcDeltaTime(sliderDeltaTime.getValue());
            op.setRadius((int) sliderDistance.getValue());
            op.getPath().playFromStart();
            //op.getPath().play();
            System.out.println("Button Play");
        }
        else if(e.getSource().equals(btnPause)){
            op.getPath().pause();
            System.out.println("Button Pause");
        }
        else if(e.getSource().equals(btnForce)){
            System.out.println("Button Get Force Pressed");          
            forceAnswer.setText("" + getForce());
            System.out.println("Mass 1:" + b1.getMass());
            System.out.println("Mass 2:" + b2.getMass());
            System.out.println("Distance:" + sliderDistance.getValue());
            System.out.println("Force Calculated: " + force);
        }
    }


    public Slider getSliderDeltaTime() {
        return sliderDeltaTime;
    }
    public void setSliderDeltaTime(Slider sliderDeltaTime) {
        this.sliderDeltaTime = sliderDeltaTime;
    }

    public Button getBtnPlay() {
        return btnPlay;
    }
    public void setBtnPlay(Button btnPlay) {
        this.btnPlay = btnPlay;
    }

    public Button getBtnPause() {
        return btnPause;
    }
    public void setBtnPause(Button btnPause) {
        this.btnPause = btnPause;
    }
    
    public Slider getSliderDistance() {
        return sliderDistance;
    }
    public void setSliderDistance(Slider sliderDistance) {
        this.sliderDistance = sliderDistance;
    }

    public Button getBtnForce() {
        return btnForce;
    }
    public void setBtnForce(Button btnForce) {
        this.btnForce = btnForce;
    }
    
    public double getForce() {
        force = (BIG_G * b1.getMass() * b2.getMass()) / Math.pow(sliderDistance.getValue(), 2);
        return force;
    }
    public void setForce(double force) {
        this.force = force;
    }
    
    

    public Body getB1() {
        return b1;
    }
    public void setB1(Body b1) {
        this.b1 = b1;
    }
    

    public Body getB2() {
        return b2;
    }
    public void setB2(Body b2) {
        this.b2 = b2;
    }

    
    public OrbitPath getOp() {
        return op;
    }
    public void setOp(OrbitPath op) {
        this.op = op;
    }
}
