package sample.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
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

    private static Joueur joueur = new Joueur(0, 0);
    private static Terrain zoneActuelle = new Terrain("zone1", joueur);

    @FXML
    private TilePane tilePane;
    @FXML
    private ImageView player;
    @FXML
    private Pane gamePane;
    @FXML
    private TilePane tilePaneSolid;
    @FXML
    private TilePane tilePaneDeco;
    @FXML
    private Pane camera;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadFirstMap();
        zoneActuelle.setJoueur(joueur);
        initListeners();
        try { affichageDeMap(); } catch (IOException e) { e.printStackTrace(); }
        initAnimation();
        gameLoop.play();
    }
    //initialise tous les listeners
    public void initListeners(){
       initPlayerListener();
       initCameraListener();
    }
    public void initPlayerListener(){
        player.translateXProperty().bind(joueur.getxProperty());
        player.translateYProperty().bind(joueur.getyProperty());
    }
    public void initCameraListener(){
        //cast en int necessaire sinon ca gène le CUT des textures. (idk man... )
        camera.layoutXProperty().bind(joueur.getxProperty().multiply(-1).add((int)camera.getPrefWidth()/2));
        camera.layoutYProperty().bind(joueur.getyProperty().multiply(-1).add((int)camera.getPrefHeight()/2));
        //faire un listener a la place des binds pour verifier si le heros est dans
        //la bonne position pour bouger la map (sinon le héros se deplace seul sur le pane)
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
        else
            dx = dy = 0 ;
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
    //charge le fichier de la premiere map.
    public void loadFirstMap(){
        try {
            joueur.setXProperty(300);
            joueur.setYProperty(100);
            zoneActuelle.setMap(mapLoadder.LoadTileMap("map1/Map1_Map1Obstacles"));
        } catch (IOException e) { e.printStackTrace(); }
    }
    /*
    Chargement des textures
     */
    Image tileSet = new Image("sample/tilemaps/allTiles.png");
    public void affichageDeMap() throws IOException {
        int floor[][] = mapLoadder.LoadTileMap("map1/Map1_Map1Floor");
        int deco[][] = mapLoadder.LoadTileMap("map1/Map1_Map1Décoration");

        gamePane.setPrefWidth(zoneActuelle.limiteHorizMap()*16);
        gamePane.setPrefHeight(zoneActuelle.limiteVertiMap()*16);

        tilePane.setPrefWidth(zoneActuelle.limiteHorizMap()*16);
        tilePane.setPrefHeight(zoneActuelle.limiteVertiMap()*16);

        tilePaneDeco.setPrefWidth(zoneActuelle.limiteHorizMap()*16);
        tilePaneDeco.setPrefHeight(zoneActuelle.limiteVertiMap()*16);

        tilePaneSolid.setPrefWidth(zoneActuelle.limiteHorizMap()*16);
        tilePaneSolid.setPrefHeight(zoneActuelle.limiteVertiMap()*16);

        // affiche chacune des couches
        chargerTextures(floor,tilePane);
        chargerTextures(deco,tilePaneDeco);
        chargerTextures(zoneActuelle.getMap(),tilePaneSolid);
    }

    public void chargerTextures (int [][] tab,TilePane tilepane){
        for(int i = 0; i<tab.length ; i++){
            for(int j = 0; j<tab[i].length ; j++){
                //tilePaneSolid.getChildren().add(new ImageView(imageMap.getImage(zoneActuelle.getMap()[i][j])));
                if(tab[i][j]!=-1) {
                    ImageView tile = new ImageView(tileSet);
                    Rectangle2D cut = new Rectangle2D((int)(tab[i][j]%(tileSet.getWidth()/16))*16,
                            (int) (tab[i][j]/(tileSet.getWidth()/16))*16, 16, 16);
                    tile.setViewport(cut);
                    tilepane.getChildren().add(tile);
                }
                else{
                    tilepane.getChildren().add(new ImageView(imageMap.getImage(-1)));
                }
            }
        }
    }


}
