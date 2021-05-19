package sample.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import sample.vue.imageMap;
import sample.modele.Joueur;
import sample.modele.Terrain;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    sample.vue.imageMap imageMap = new imageMap();
    MapLoader mapLoader = new MapLoader();

    private static int dx = 0;
    private static int dy = 0;

    private static Terrain zoneActuelle ;
    private static Joueur joueur = new Joueur(0, 0, zoneActuelle);

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
        loadMap("1", 300, 100);
        initListeners();
        initAnimation();
        gameLoop.play();
    }
    //initialise tous les listeners
    public void initListeners(){
       initPlayerListener();
       initPlayerTransitionsListener();
       //initCameraListener();
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
        if (joueur.manageCollisions(e)){
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
        if(joueur.manageCollisions(keyPressed)){
            if(dx==1) joueur.moveRight();
            if(dx==-1) joueur.moveLeft();
            if(dy==1) joueur.moveDown();
            if(dy==-1) joueur.moveUp();
        }
    }

    //charge le fichier de la premiere map.
    public void loadMap(String numero, int spawnX, int spawnY){
        try {
            zoneActuelle =  new Terrain("zone"+numero);
            zoneActuelle.setMap(mapLoader.LoadTileMap("map"+numero+"/Map"+numero+"Obstacles"));
            joueur.setZone(zoneActuelle);
            joueur.setXProperty(spawnX);
            joueur.setYProperty(spawnY);
            affichageDeMap(numero);
        } catch (IOException e) { e.printStackTrace(); }
    }
    /*
    Chargement des textures
     */
    Image tileSet = new Image("sample/ressources/tilemaps/allTiles.png");
    public void affichageDeMap(String numero) throws IOException {
        int floor[][] = mapLoader.LoadTileMap("map"+numero+"/Map"+numero+"Floor");
        int deco[][] = mapLoader.LoadTileMap("map"+numero+"/Map"+numero+"Décoration");

        zoneActuelle.updateTilePaneSize(tilePane, tilePaneDeco, tilePaneSolid, gamePane);

        // affiche chacune des couches
        chargerTextures(floor,tilePane);
        chargerTextures(deco,tilePaneDeco);
        chargerTextures(zoneActuelle.getMap(),tilePaneSolid);
    }

    public void chargerTextures (int [][] tab,TilePane tilepane){
        tilepane.getChildren().clear();
        for(int i = 0; i<tab.length ; i++){
            for(int j = 0; j<tab[i].length ; j++){
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

    // permettra de changer de map si le joueur arrive dans une zone de transition
    public void initPlayerTransitionsListener(){
        ChangeListener c = new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                switch (joueur.getNumeroZone()){
                    case "1":
                        if(joueur.isCollinding(622, 228)) loadMap("2", 50, 100);
                        break;
                    case "2":
                        if(joueur.isCollinding(0, 100)) loadMap("1", 600, 228);
                        break;
                    default:
                        break;
                }
            }
        };
        player.translateXProperty().addListener(c);
        player.translateYProperty().addListener(c);
    }


}