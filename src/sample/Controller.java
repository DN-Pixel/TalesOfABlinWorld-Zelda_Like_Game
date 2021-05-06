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
import sample.modele.Joueur;
import sample.modele.Terrain;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    imageMap imageMap = new imageMap();
    MapLoadder mapLoadder = new MapLoadder();

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

    private Timeline gameLoop;
    private void initAnimation() {
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),
                (ev ->{

                })
        );
        gameLoop.getKeyFrames().add(kf);
    }
    /*
    Déplacement du joueur
     */
    /*
    public void movePlayer(){
        player.setTranslateX(player.getTranslateX()+2*dx.getValue());
        player.setTranslateY(player.getTranslateY()+2*dy.getValue());
    }
    */

    public static void manageMovement(KeyEvent e){
        if(e.getCode() == KeyCode.Z && joueur.getY()-2>0)
            joueur.moveUp();
        if(e.getCode() == KeyCode.S && joueur.getY()+2<zoneActuelle.limiteVertiMap()*15) // *15 car l'axe central du joueur est en haut a gauche du sprite
            joueur.moveDown();
        if(e.getCode() == KeyCode.Q && joueur.getX()+2>0)
            joueur.moveLeft();
        if(e.getCode() == KeyCode.D && joueur.getX()+2<zoneActuelle.limiteHorizMap()*15 && zoneActuelle.getMap()[(int)(joueur.getX()+2/16)][(int)(joueur.getY()+2/16)]==-1)
            joueur.moveRight();
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
