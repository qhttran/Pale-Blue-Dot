/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package testforfinalproject;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Kasin
 */
public class TestForFinalProject extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        
        
        /*
        Path path = new Path();
        MoveTo moveTo = new MoveTo();
        moveTo.setX(50.0);
        moveTo.setY(50.0);
                
        ArcTo arcTo = new ArcTo();
        arcTo.setX(50.0001);
        arcTo.setY(50.0001);
        arcTo.setRadiusX(100.0);
        arcTo.setRadiusY(100.0);
        arcTo.setLargeArcFlag(true);
        arcTo.setSweepFlag(true);
        
        path.getElements().add(moveTo);
        path.getElements().add(arcTo);
        */
        
        //final Circle circle = new Circle(125 , -25, 20.0);
        Circle circle = new Circle(0, 0, 100);
        
        Circle circle2 = new Circle(0, 0, 10);
        circle2.setFill(Color.BLUE);
        
        Circle circle3 = new Circle(0,0, 50);
        circle3.setFill(Color.ORANGE);
        
        PathTransition pathTransition = new PathTransition();
        pathTransition.setPath(circle);
        pathTransition.setRate(2);
        pathTransition.setInterpolator(Interpolator.LINEAR);
        pathTransition.setDuration(Duration.millis(40000));
        //pathTransition.setPath(path);
        pathTransition.setNode(circle2);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
       // pathTransition.setAutoReverse(true);
        pathTransition.play();
        
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        //root.getChildren().add(btn);
        //root.getChildren().add(path);
        root.getChildren().add(circle2);
        root.getChildren().add(circle3);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
