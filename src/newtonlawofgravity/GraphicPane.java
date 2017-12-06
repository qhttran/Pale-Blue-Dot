/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newtonlawofgravity;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author Kasin
 */
public class GraphicPane extends GridPane{
    private Slider sliderDeltaTime;
    private Button btnPlay, btnPause;
    private Body b1, b2;
    private OrbitPath op;
    
    public GraphicPane(){
        this(new Body(12225, 20, "Body1", Color.GREY), new Body(1455874, 40, "Body2", Color.ORANGE));
    }
    
    public GraphicPane(Body b1, Body b2){
        super();
        StackPane testPane = new StackPane();
        this.add(testPane, 0, 0);
        sliderDeltaTime = new Slider();
        sliderDeltaTime.setOnMouseReleased(e -> mouseReleased(e));
        btnPlay = new Button("Play");
        btnPlay.setOnAction(e -> actionPerformed(e));
        btnPause = new Button("Pause");
        btnPause.setOnAction(e -> actionPerformed(e));
        this.add(sliderDeltaTime, 0, 1);
        this.add(btnPlay, 0, 2);
        this.add(btnPause, 0, 3);
        this.b1 = b1;
        this.b2 = b2;
        op = new OrbitPath(b2, b1);
        op.setRadius(100);
        //b1.setOnMouseReleased(e -> mouseReleased(e));
        //b2.setOnMouseReleased(e -> mouseReleased(e));
        testPane.getChildren().add(b1);
        testPane.getChildren().add(b2);
        this.setMinWidth(300);
        this.setAlignment(Pos.CENTER);
        this.setId("graphicPane"); // for css
    }
    
    public void mouseReleased(MouseEvent e){
        if(e.getSource().equals(getSliderDeltaTime())){
            System.out.println("Slider");
        }
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(btnPlay)){
            getOp().getPath().play();
            System.out.println("Button Play");
        }
        else if(e.getSource().equals(btnPause)){
            getOp().getPath().pause();
            System.out.println("Button Pause");
        }
    }

    /**
     * @return the sliderDeltaTime
     */
    public Slider getSliderDeltaTime() {
        return sliderDeltaTime;
    }

    /**
     * @param sliderDeltaTime the sliderDeltaTime to set
     */
    public void setSliderDeltaTime(Slider sliderDeltaTime) {
        this.sliderDeltaTime = sliderDeltaTime;
    }

    /**
     * @return the btnPlay
     */
    public Button getBtnPlay() {
        return btnPlay;
    }

    /**
     * @param btnPlay the btnPlay to set
     */
    public void setBtnPlay(Button btnPlay) {
        this.btnPlay = btnPlay;
    }

    /**
     * @return the btnPause
     */
    public Button getBtnPause() {
        return btnPause;
    }

    /**
     * @param btnPause the btnPause to set
     */
    public void setBtnPause(Button btnPause) {
        this.btnPause = btnPause;
    }

    /**
     * @return the b1
     */
    public Body getB1() {
        return b1;
    }

    /**
     * @param b1 the b1 to set
     */
    public void setB1(Body b1) {
        this.b1 = b1;
    }

    /**
     * @return the b2
     */
    public Body getB2() {
        return b2;
    }

    /**
     * @param b2 the b2 to set
     */
    public void setB2(Body b2) {
        this.b2 = b2;
    }

    /**
     * @return the orbit path
     */
    public OrbitPath getOp() {
        return op;
    }

    /**
     * @param op the orbit path to set
     */
    public void setOp(OrbitPath op) {
        this.op = op;
    }
    
    
}
