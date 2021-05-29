package sample.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import sample.vue.TerrainVue;
import sample.vue.ImageMap;
import sample.modele.Joueur;
import sample.modele.Terrain;
import sample.vue.animations.PlayerHPAnimation;
import sample.vue.animations.PlayerMovementAnimation;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    ImageMap imageMap = new ImageMap();
    MapLoader mapLoader = new MapLoader();

    private static int dx = 0;
    private static int dy = 0;

    private static Terrain zoneActuelle = new Terrain("zone");
    private static Joueur joueur = new Joueur(0, 0, zoneActuelle);
    //PANES
    @FXML
    private Pane gamePane;
    @FXML
    private Pane vendeurPane;
    @FXML
    private TilePane tilePane;
    @FXML
    private TilePane tilePaneSolid;
    @FXML
    private TilePane tilePaneDeco;
    //LABELS TEXTFIELDS
    @FXML
    private Label questLabel;
    @FXML
    private TextField shopQuantiteField;
    //IMAGES
    @FXML
    private ImageView hpBar;
    @FXML
    private ImageView player;
    //BUTTONS
    @FXML
    private Button mangerButton;
    //RADIO INVENTAIRE
    @FXML
    private RadioButton noodleRadio;
    @FXML
    private RadioButton mielRadio;
    @FXML
    private RadioButton meatRadio;
    //RADIO SHOP

    private TerrainVue terrainVue; // classe permettant de load la map et charger les textures

    private long temps = 0 ;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player.setId("player");
        terrainVue = new TerrainVue(zoneActuelle, joueur, gamePane, tilePane, tilePaneDeco, tilePaneSolid);
        terrainVue.loadMap("1", 300, 100);
        initListeners();
        initBoucleTemporelle();
        initAnimations();
        gameLoop.play();
    }

    private void initAnimations() {
        PlayerMovementAnimation.initAnimation(player, joueur, imageMap); // INITIALISE LES ANIMATIONS DE DEPLACEMENTS DU JOUEUR
        PlayerHPAnimation.initAnimation(joueur, hpBar, imageMap); // INTIALISE LES ANIMATIONS DE VIE (Coeurs)
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
        joueur.getxProperty().addListener((obse,old,nouv)->{
            //si le joueur est trop pres du bord de la map, le déplacement ne se fait pas.
            if(nouv.intValue()>152 && nouv.intValue()< zoneActuelle.limiteHorizMap()*16-152)
                gamePane.setLayoutX(-(int)nouv+640);
        });

        joueur.getyProperty().addListener((obse,old,nouv)->{
            if(nouv.intValue()>115 && nouv.intValue()< zoneActuelle.limiteVertiMap()*16-115)
                gamePane.setLayoutY(-(int)nouv+360);
        });
    }
    // key initialisé aléatoirement pour éviter une erreur
    private static KeyEvent keyPressed = new KeyEvent(KeyEvent.KEY_PRESSED, "d", "D", KeyCode.Z,false, false, false, false);
    private Timeline gameLoop;
    private void initBoucleTemporelle() {
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),
                (ev ->{
                    movePlayer(); // gère le déplacement à chaque tour de la boucle temporelle
                    timeManager(); // gestion du temps
                    cleanMap(); // lance le nettoyeur de map
                    if (temps%590==0)
                        zoneActuelle.EnemySpawn();// spawn d'ennemi toutes les 10s
                    if(temps%5==0)
                        zoneActuelle.moveEnnemis(); // fais déplacer les ennemis
                    if(temps%177==0)
                        zoneActuelle.lesEnnemisAttaquent(joueur.getCentreJoueurX(), joueur.getCentreJoueurY(), joueur); // fais attaquer les ennemis toutes les 3s
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }
    //gestion du temps
    public void timeManager(){
        if (temps >= 1000000000)
            temps = 0; // reset
        else
            temps++;
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

    public static void keyReleaseManager(KeyEvent e) {
        if (e.getCode() == KeyCode.DIGIT1 || e.getCode() == KeyCode.AMPERSAND) {
            joueur.attaquerEnnemis();
        }
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
            //joueur.setOldTileValue(joueur.getZone().getMapSpawn()[(joueur.getCentreJoueurY()/16)][(joueur.getCentreJoueurX()/16)]);
            if(dx==1) joueur.moveRight();
            if(dx==-1) joueur.moveLeft();
            if(dy==1) joueur.moveDown();
            if(dy==-1) joueur.moveUp();
            joueur.manageAggro();
            //joueur.updatePosition();
        }
    }

    private void cleanMap() {
        zoneActuelle.clean();
    }

    // permettra de changer de map si le joueur arrive dans une zone de transition
    public void initPlayerTransitionsListener(){
        ChangeListener c = new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                switch (joueur.getNumeroZone()){
                    case "1":
                        if(joueur.isCollinding(622, 228))  terrainVue.loadMap("2", 50, 100);
                        break;
                    case "2":
                        if(joueur.isCollinding(0, 100))  terrainVue.loadMap("1", 600, 228);
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
