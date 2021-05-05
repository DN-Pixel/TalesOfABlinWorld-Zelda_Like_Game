package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TilePane tilePane;

    @FXML
    private ImageView testImage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        testImage.setImage(new Image("/sample/Shadow.png"));
    }

}
