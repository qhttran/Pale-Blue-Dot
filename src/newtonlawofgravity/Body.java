/*
 * This is the class for the "bodies" in space. Includes a BodyEditScene for 
 * the popup window to adjust the body's values.
 */
package newtonlawofgravity;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Kasin
 */
public class Body extends Circle{
    private double mass;
    private String name;
    //++ TODO: get the image working
    // private String imageURL = "file:Default.png";
    private Image image;
    
    // this is for the popup window
    private Stage testStage;
    private BodyEditScene scenePop;
    
    public Body(){
        // default values for the Body. Can change.
        this(50000, 30, "Default", Color.RED);
    }
    
    public Body(Body b){
        this(b.getMass(), (int) b.getRadius(), b.getName(), b.getFill());
    }
    
    public Body(double mass, int radius, String name, Paint c){
        super(0, 0, radius, c);
        this.mass = mass;
        this.name = name;
        
        // adds a mouseListener to triger the popup window
       //-- this.setOnMouseReleased(e -> mouseReleased(e));
        
        // all four lines below are creating the popup window
        testStage = new Stage();
        scenePop = new BodyEditScene(this);
        testStage.setScene(scenePop);
        testStage.initModality(Modality.APPLICATION_MODAL);
    }
    
    /*
    private void mouseReleased(MouseEvent e){
        
        // this is what makes the popup window display on user's screen
        scenePop.updateData();
        testStage.setOnCloseRequest(we -> windowClosed(we));
        testStage.showAndWait();
        
        // get the values the user entered
        //this.mass = scenePop.getMass();
        //this.setRadius(scenePop.getRadius());
    }
*/
    public void popUp(){
        scenePop.updateData();
        testStage.setOnCloseRequest(we -> windowClosed(we));
        testStage.showAndWait();
    }
    
    private void windowClosed(WindowEvent e){
        System.out.println("Window Closed");
    }

    public double getMass() {
        return mass;
    }
    public void setMass(double mass) {
        this.mass = mass;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
     public Body compareTo(Body b){
         // returns the smaller body
        
        if(this.getMass() > b.getMass()){
            return b;
        }
        else{
            return this;
        }
     }
     
     public void move(double x, double y){
         this.setCenterX(x);
         this.setCenterY(y);
     }
}
