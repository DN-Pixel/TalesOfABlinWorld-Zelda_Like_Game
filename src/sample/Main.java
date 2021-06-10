package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.controleur.Controller;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = FXMLLoader.load(getClass().getResource("vue/sample.fxml"));
        Scene scene = new Scene(root,1280,720);
        primaryStage.getIcons().add(new Image("sample/ressources/logo.png"));
        scene.setOnKeyPressed(Controller::keyManager);
        scene.setOnKeyReleased(Controller::keyReleaseManager);
        primaryStage.setTitle("Tales of a Blind World");
        primaryStage.setScene(scene);


        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
