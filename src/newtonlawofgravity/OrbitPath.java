/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newtonlawofgravity;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Transform;
import javafx.util.Duration;

/**
 *
 * @author Kasin
 */
public class OrbitPath {
    private PathTransition path;
    private Circle orbPath;
    private double period;
    
    public OrbitPath(Body orbBody, Body centerBody){
        this.calcPeriod(orbBody, centerBody, 0);
        orbPath = new Circle();
        path = new PathTransition();
        path.setPath(orbPath);
        path.setInterpolator(Interpolator.LINEAR);
        path.setNode(orbBody);
        path.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        path.setCycleCount(Timeline.INDEFINITE);
    }
    
    public void setRadius(int r){
        orbPath.setRadius((double) r);
    }
    
    public double getRadius(){
        return orbPath.getRadius();
    }
    
    public void setPosX(int posX){
        orbPath.setCenterX(posX);
    }
    
    public double getPosX(){
        return orbPath.getCenterX();
    }
    
    public void setPosY(int posY){
        orbPath.setCenterY(posY);
    }
    
    public double getPosY(){
        return orbPath.getCenterY();
    }
    
    public Circle getOrbit(){
        return orbPath;
    }
    
    public PathTransition getPath(){
        return path;
    }
    
    public void calcDeltaTime(double deltaTimeAmount){
        /*---System.out.println("Delta Time mouseReleased: " + deltaTime 
            + " Period mouseReleased: " + period);---*/

      //++ TODO adj. Duration.millis(period * 1000 / deltaTime) 
      //++based on how the slider value is recorded
        if(deltaTimeAmount == 0){
            path.setDuration(Duration.millis(period * 1000));
            path.playFromStart();
            System.out.println(period * 1000);
        }
        else{
            path.setDuration(Duration.millis((period * 1000) /  (period * deltaTimeAmount)));
            path.playFromStart();
            System.out.println(deltaTimeAmount);
            System.out.println((period) /  (period * deltaTimeAmount));
        }
    }
    
    /*<< Calculate and return the appropriate period (orbiting speed) >>*/
    public double getPeriod(){
        return period;
    }
    
    public void setPeriod(double p){
        period = p;
    }
    
    public void calcPeriod(Body b1, Body b2, double distance){
        double bigM;
      // compare to find the larger mass
        if(b1.getMass() > b2.getMass()){
            bigM = b1.getMass();
        }
        else{
            bigM = b2.getMass();
        }
        
      // calc period in seconds
        double p = (2*Math.PI) * Math.sqrt(Math.pow(distance, 3)/(bigM * NewtonLawOfGravity.BIG_G));     
        period = p;
    }
    
    public void setNode(Body b){
        path.setNode(b);
    }
}
