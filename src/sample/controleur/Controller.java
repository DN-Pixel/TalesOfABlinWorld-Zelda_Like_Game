package sample.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import sample.modele.acteurs.Acteur;
import sample.modele.acteurs.ObsListActeurs;
import sample.vue.imageMap;
import sample.modele.Joueur;
import sample.modele.Terrain;


import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    sample.vue.imageMap imageMap = new imageMap();
    MapLoader mapLoader = new MapLoader();

    private static int dx = 0;
    private static int dy = 0;

    private static Terrain zoneActuelle = new Terrain("zone");
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
    private long temps = 0 ;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player.setId("player");
        loadMap("1", 300, 100);
        initListeners();
        initAnimation();
        gameLoop.play();
    }
    //initialise tous les listeners
    public void initListeners(){
       initPlayerListener();
       initPlayerTransitionsListener();
       initCameraListener();
    }
    public void initPlayerListener(){
        player.translateXProperty().bind(joueur.getxProperty());
        player.translateYProperty().bind(joueur.getyProperty());
    }
    public void initCameraListener(){
        gamePane.layoutXProperty().bind(joueur.getxProperty().multiply(-1).add(640));
        gamePane.layoutYProperty().bind(joueur.getyProperty().multiply(-1).add(360));
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
                    if (temps%590==0) // spawn d'ennemi toutes les 10s
                        zoneActuelle.EnemySpawn();
                    if(temps%5==0)
                        zoneActuelle.moveEnnemis();
                    timeManager(); // gestion du temps
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

    //gestion du temps
    public void timeManager(){
        if (temps >= 1000000000)
            temps = 0; // reset
        else
            temps++;
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
            //permet de récuperer l'ancienne valeur de la Tile du tableau de Spawn.
            //joueur.setOldTleValue(joueur.getZone().getMapSpawn()[(joueur.getCentreJoueurY()/16)][(joueur.getCentreJoueurX()/16)]);
            if(dx==1) joueur.moveRight();
            if(dx==-1) joueur.moveLeft();
            if(dy==1) joueur.moveDown();
            if(dy==-1) joueur.moveUp();
            //joueur.updatePosition();
        }
    }

    public void updatePaneWhenLoadingMap(){
        Acteur a;
        Node b;
        for(int i = zoneActuelle.getListeActeurs().size()-1; i>=0;i--){
            a = zoneActuelle.getListeActeurs().get(i);
            if(gamePane.lookup("#"+a.getId())==null){
                ImageView sprite = new ImageView(imageMap.getImage(a.getClass().getSimpleName())); // récupère l'image de l'ennemi correspondant
                sprite.setId(a.getId());
                sprite.translateXProperty().bind(a.getXProperty());
                sprite.translateYProperty().bind(a.getYProperty());
                gamePane.getChildren().add(sprite);
            }
        }
        // GERE LE CHANGEMENT DE ZONE
        for(int j = gamePane.getChildren().size()-1;j>=0;j--){
            b = gamePane.getChildren().get(j);
            if(b.getId().startsWith("a") && !zoneActuelle.findActeur(b.getId()))
                gamePane.getChildren().remove(b);
        }
    }

    //charge le fichier de la premiere map.
    public void loadMap(String numero, int spawnX, int spawnY){
        try {
            zoneActuelle.setNomDeCarte("zone"+numero);
            zoneActuelle.setMapObstacles(mapLoader.LoadTileMap("map"+numero+"/Map"+numero+"Obstacles"));
            zoneActuelle.setMapSpawn(mapLoader.LoadTileMap("map"+numero+"/Map"+numero+"Spawn"));
            zoneActuelle.getListeActeurs().addListener(new ObsListActeurs(gamePane));
            updatePaneWhenLoadingMap();
            joueur.setZone(zoneActuelle);
            joueur.setXProperty(spawnX);
            joueur.setYProperty(spawnY);
            affichageDeMap(numero);
            //joueur.updatePosition();
            //zoneActuelle.loadSaveActeurs();
        } catch (IOException e) { e.printStackTrace(); }
    }

    //Chargement des textures
    Image tileSet = new Image("sample/ressources/tilemaps/allTiles.png");
    public void affichageDeMap(String numero) throws IOException {
        int floor[][] = mapLoader.LoadTileMap("map"+numero+"/Map"+numero+"Floor");
        int deco[][] = mapLoader.LoadTileMap("map"+numero+"/Map"+numero+"Décoration");
        zoneActuelle.updateTilePaneSize(tilePane, tilePaneDeco, tilePaneSolid, gamePane);
        // affiche chacune des couches
        chargerTextures(floor,tilePane);
        chargerTextures(deco,tilePaneDeco);
        chargerTextures(zoneActuelle.getMapObstacles(),tilePaneSolid);
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
                    tilepane.getChildren().add(new ImageView(imageMap.getImage("empty")));
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
