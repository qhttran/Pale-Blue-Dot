/*
 * This is the pane that contains the animation with the bodies, play/pause buttons,
 * and the deltaTimeSlider.

I AM WORKING ON THE BOTTOM TOOL BAR LAYOUT
 */
package newtonlawofgravity;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
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
    public HBox bottomToolbar;
    public Group graphicController;
    private Slider sliderDeltaTime;
    private Button btnPlay, btnPause;
    
    private Body b1, b2;
    private OrbitPath op;
    
    public Label timeSliderLabel, forceLabel;
    public Group forceGroup;
    private Label forceAnswer;
    
    public GraphicPane(){
        this(new Body(12225, 20, "Body1", Color.GREY), new Body(1455874, 40, "Body2", Color.ORANGE));
    }
    
    public GraphicPane(Body b1, Body b2){
        super();
        StackPane testPane = new StackPane();
        testPane.setId("testPane");
        this.setCenter(testPane);
        
        btnPlay = new Button("PLAY");
        btnPlay.setOnAction(e -> actionPerformed(e));
        btnPause = new Button("PAUSE");
        btnPause.setOnAction(e -> actionPerformed(e));
        timeSliderLabel = new Label("TIME SLIDER: ");
        sliderDeltaTime = new Slider();
        sliderDeltaTime.setOnMouseReleased(e -> mouseReleased(e));
        graphicController = new Group();
        HBox controller = new HBox();
        controller.getChildren().addAll(btnPlay, btnPause, 
                                            timeSliderLabel, sliderDeltaTime);
        graphicController.getChildren().add(controller);
        
        forceLabel = new Label("RESULTANT FORCE: ");
        forceAnswer = new Label("");
        forceAnswer.setStyle("-fx-border-color: gray");
        forceAnswer.setPrefWidth(150);
        forceGroup = new Group();
        forceGroup.getChildren().addAll(forceLabel, forceAnswer);
        
        
        bottomToolbar = new HBox();
        bottomToolbar.setId("bottomToolbar");
        bottomToolbar.setSpacing(10);
        bottomToolbar.setPadding(new Insets(10, 12, 10, 12));
        bottomToolbar.getChildren().addAll(graphicController, forceGroup);
        this.setBottom(bottomToolbar);
        
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
