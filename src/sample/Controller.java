package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private static IntegerProperty dx = new SimpleIntegerProperty(0);
    private static IntegerProperty dy = new SimpleIntegerProperty(0);

    @FXML
    private TilePane tilePane;
    @FXML
    private ImageView player;
    @FXML
    private Pane gamePane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        affichageDeMap();
        initAnimation();
        gameLoop.play();
    }

    private Timeline gameLoop;
    private void initAnimation() {
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),
                (ev ->{
                    movePlayer();
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }
    /*
    Déplacement du joueur
     */
    public void movePlayer(){
        player.setTranslateX(player.getTranslateX()+2*dx.getValue());
        player.setTranslateY(player.getTranslateY()+2*dy.getValue());
    }

    public static void manageMovement(KeyEvent e){
        if(e.getCode() == KeyCode.Z) {
            dy.setValue(-1);
        }
        if(e.getCode() == KeyCode.S) {
            dy.setValue(1);
        }
        if(e.getCode() == KeyCode.Q) {
            dx.setValue(-1);
        }
        if(e.getCode() == KeyCode.D) {
            dx.setValue(1);
        }
    }
    public static void releaseManageMovement(KeyEvent e) {
        if(e.getCode() == KeyCode.Z) {
            dy.setValue(0);
        }
        if(e.getCode() == KeyCode.S) {
            dy.setValue(0);
        }
        if(e.getCode() == KeyCode.Q) {
            dx.setValue(0);
        }
        if(e.getCode() == KeyCode.D) {
            dx.setValue(0);
        }
    }
    /*
    Chargement des textures
     */
    public void affichageDeMap(){
        for(int i = 0; i<400 ; i++){
            tilePane.getChildren().add(new ImageView(new Image("sample/ressources/floor.png")));
        }
    }
}
