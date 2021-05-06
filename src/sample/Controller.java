package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import org.tiledreader.TiledMap;
import org.tiledreader.TiledReader;
import sample.modele.Terrain;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Terrain terrain = new Terrain();


    private ImageView player = new ImageView();

    private static IntegerProperty dx = new SimpleIntegerProperty(0);
    private static IntegerProperty dy = new SimpleIntegerProperty(0);


    @FXML
    private TilePane tilePane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
                    player.setTranslateX(player.getLayoutX()+10*dx.getValue());
                    player.setTranslateY(player.getLayoutY()+10*dy.getValue());
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }


    public static void manageMovement(KeyEvent e){
        if(e.getCode() == KeyCode.Z) {
            dx.setValue(0);
            dy.setValue(-1);
        }
        if(e.getCode() == KeyCode.S) {
            dx.setValue(0);
            dy.setValue(1);
        }
        if(e.getCode() == KeyCode.Q) {
            dx.setValue(-1);
            dy.setValue(0);
        }
        if(e.getCode() == KeyCode.D) {
            dx.setValue(1);
            dy.setValue(0);
        }
    }
}
