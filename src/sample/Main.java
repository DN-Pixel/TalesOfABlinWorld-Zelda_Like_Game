package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root, 800, 800);
        scene.setOnKeyPressed(e ->Controller.manageMovement(e));

        primaryStage.setTitle("Tales of a Blind World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
