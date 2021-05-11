package sample.controleur;

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
import sample.MapLoadder;
import sample.imageMap;
import sample.modele.Joueur;
import sample.modele.Terrain;


import java.io.IOException;
import java.net.URL;
import java.security.Key;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    sample.imageMap imageMap = new imageMap();
    MapLoadder mapLoadder = new MapLoadder();

    private static int dx = 0;
    private static int dy = 0;

    private static Joueur joueur = new Joueur(10.0, 10.0);
    private static Terrain zoneActuelle = new Terrain("zone1", joueur);


    @FXML
    private TilePane tilePane;
    @FXML
    private ImageView player;
    @FXML
    private Pane gamePane;
    @FXML
    private TilePane tilePaneSolid;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            zoneActuelle.setMap(mapLoadder.LoadTileMap("testBuissons"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        zoneActuelle.setJoueur(joueur);
        player.translateXProperty().bind(joueur.getxProperty());
        player.translateYProperty().bind(joueur.getyProperty());

        affichageDeMap();
        initAnimation();
        gameLoop.play();
    }
    // key initialisé aléatoirement pour éviter une erreur
    private static KeyEvent keyPressed = new KeyEvent(KeyEvent.KEY_PRESSED, "d", "D", KeyCode.Z,false, false, false, false);
    private Timeline gameLoop;
    private void initAnimation() {
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),
                (ev ->{
                    movePlayer(); // gère le déplacement à chaque tour de la boucle temporelle
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    public static void keyManager(KeyEvent e){
        if (zoneActuelle.manageCollisions(e)){
            keyPressed = e;
            switch (e.getCode()){
                //mouvement
                case Z : dy=-1;dx=0;break;
                case D : dx=1;dy=0;break;
                case S : dy=1;dx=0;break;
                case Q : dx=-1;dy=0;break;
            }
        }
        else {
            dx = 0;
            dy = 0;
        }
    }

    public static void keyReleaseManager(KeyEvent e){
        switch (e.getCode()){
            //mouvement
            case Z : dy=0;break;
            case D : dx=0;break;
            case S : dy=0;break;
            case Q : dx=0;break;
        }
    }

    public void movePlayer(){
        if(zoneActuelle.manageCollisions(keyPressed)){
            if(dx==1) joueur.moveRight();
            if(dx==-1) joueur.moveLeft();
            if(dy==1) joueur.moveDown();
            if(dy==-1) joueur.moveUp();
        }
    }

    /*
    Chargement des textures
     */
    public void affichageDeMap(){
        // affiche la couche du sol de la map
        for(int i = 0; i< zoneActuelle.getLongueurMap(); i++){
            tilePane.getChildren().add(new ImageView(imageMap.getImage(244)));
        }
        // affiche la couche solide de la map
        for(int i = 0; i<zoneActuelle.getMap().length ; i++){
            for(int j = 0; j<zoneActuelle.getMap()[i].length ; j++){
                tilePaneSolid.getChildren().add(new ImageView(imageMap.getImage(zoneActuelle.getMap()[i][j])));
            }
        }
    }


}
