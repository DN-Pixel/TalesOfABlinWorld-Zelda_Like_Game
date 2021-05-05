package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
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
        testImage.setImage(new Image(""));
        tilePane  = new TilePane(Orientation.VERTICAL);
        tilePane.setTileAlignment(Pos.CENTER_LEFT);
        tilePane.setPrefRows(10);
        for (int i = 0; i < 50; i++) {
            tilePane.getChildren().add(new ImageView(new Image("")));
        }}

}
