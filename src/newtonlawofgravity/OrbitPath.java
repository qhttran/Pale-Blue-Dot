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

/**
 *
 * @author Kasin
 */
public class OrbitPath {
    private PathTransition path;
    private Circle orbPath;
    
    public OrbitPath(Body orbBody, Body centerBody){
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
}
