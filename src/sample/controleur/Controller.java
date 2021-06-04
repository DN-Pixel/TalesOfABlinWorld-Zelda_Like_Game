package sample.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import sample.modele.items.Armes.Shuriken;
import sample.vue.*;
import sample.modele.Joueur;
import sample.modele.Terrain;
import sample.vue.animations.PlayerHPAnimation;
import sample.vue.animations.PlayerMovementAnimation;
import sample.vue.modeleVue.TerrainVue;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    ImageMap imageMap = new ImageMap();
    private static CooldownManager  cdManager = new CooldownManager();
    private ItemDescriptionSwitcher itemsDescriptionLoader;
    private ListenerLauncher listenerLauncher;

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
    private TextArea console;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label questLabel;
    @FXML
    private TextField shopQuantiteField;
    @FXML
    private Label nbGoldLabel;
    //IMAGES
    @FXML
    private ImageView hpBar;
    @FXML
    private ImageView player;
    //BUTTONS
    @FXML
    private Button vendreButton;
    @FXML
    private Button acheterButton;
    @FXML
    private Button mangerButton;
    //RADIO INVENTAIRE
    @FXML
    private ToggleGroup Nourriture;
    //RADIO SHOP


    private TerrainVue terrainVue; // classe permettant de load la map et charger les textures

    private long temps = 0 ;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        joueur.setArmeDistance(new Shuriken()); // ****************** TEMPORAIRE ******************
        initConsole(); // Charge la console
        itemsDescriptionLoader = new ItemDescriptionSwitcher(descriptionLabel);
        player.setId("player");
        terrainVue = new TerrainVue(zoneActuelle, joueur, gamePane, tilePane, tilePaneDeco, tilePaneSolid);
        terrainVue.loadMap("1", 300, 100); // charge la première map
        listenerLauncher = new ListenerLauncher(joueur, player, terrainVue);
        initListeners(); // initialise les listeners
        initBoucleTemporelle(); // initialise la boucle temporelle
        initAnimations(); // lance les animations
        gameLoop.play(); // Lance la boucle temporelle
    }

    private void initConsole(){
        Console cons = new Console(console);
        joueur.setConsole(cons);
        zoneActuelle.setConsole(cons);
    }

    private void initAnimations() {
        PlayerMovementAnimation.initAnimation(player, joueur, imageMap); // INITIALISE LES ANIMATIONS DE DEPLACEMENTS DU JOUEUR
        PlayerHPAnimation.initAnimation(joueur, hpBar, imageMap); // INTIALISE LES ANIMATIONS DE VIE (Coeurs)
    }

    //initialise tous les listeners
    public void initListeners(){
       listenerLauncher.initPlayerListener();
       listenerLauncher.initPlayerTransitionsListener();
       listenerLauncher.initCameraListener(gamePane);
       listenerLauncher.initInventaireListener(nbGoldLabel, itemsDescriptionLoader);
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
                    if (temps==0)
                        joueur.setYProperty(150); //permet de bouger le personnage au lancement du jeu, afin d'activer le CameraListener.
                    movePlayer(); // gère le déplacement à chaque tour de la boucle temporelle
                    timeManager(); // gestion du temps
                    zoneActuelle.clean(); // lance le nettoyeur de map
                    spawnManager(); // manage le spawn des ennemis et des ressources
                    if(temps%5==0)
                        zoneActuelle.moveEnnemis(); // fais déplacer les ennemis
                    if(temps%177==0) {
                        zoneActuelle.lesEnnemisAttaquent(joueur); // fais attaquer les ennemis toutes les 3s
                        zoneActuelle.spawnProjectile(joueur); // attaques à distance
                    }
                    zoneActuelle.manageProjeciles(joueur);
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }
    //gestion du temps
    public void timeManager(){
        if (temps >= 1000000000)
            temps = 1; // reset
        else
            temps++;
        if (temps%59==0)
            cdManager.incrementCD();
    }
    public void spawnManager(){
        if(temps%590==0)
            zoneActuelle.EnemySpawn();// spawn d'ennemi toutes les 10s
        if(temps%1200==0)
            zoneActuelle.ressourceSpawn(); // spawn de ressources les 20s
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
        if (e.getCode() == KeyCode.DIGIT1 || e.getCode() == KeyCode.AMPERSAND)
            joueur.attaquerEnnemis();
        else if(e.getCode() == KeyCode.F)
            joueur.loot();
        else if(cdManager.getCdShuriken()>=1 && (e.getCode() == KeyCode.DIGIT3 || e.getCode() == KeyCode.QUOTEDBL)){
            joueur.attaquerEnDistance();
            cdManager.setCdShuriken(0);
        }
        else if(e.getCode()== KeyCode.DIGIT2 || e.getCode()==KeyCode.UNDEFINED)
            joueur.manger("Potion");

        switch (e.getCode()){
            //mouvement
            case Z : dy=0;break;
            case D : dx=0;break;
            case S : dy=0;break;
            case Q : dx=0;break;
        }
    }

    //permet de changer la description des item de l'nventaire, en lui envoyant directement le fx:id de l'image cliquée.
    @FXML
    public void setDescription(MouseEvent event) {
        //permet de mémoriser le dernier evenemet effectué. Afin que la description puisse savoir quel item a été séléctionné
        //auparavent et puisse s'actualiser en considerant le changement de BON item.
        listenerLauncher.setInventoryClicEventMemory(event.getPickResult().getIntersectedNode().getId());
        itemsDescriptionLoader.switchDescription(event.getPickResult().getIntersectedNode().getId(), joueur.getInventaire());
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

    @FXML
    void manger(MouseEvent event) {
       RadioButton radioSelected = (RadioButton) Nourriture.getSelectedToggle();
       try {
           joueur.manger(radioSelected.getId());
       }catch (Exception e) {
           joueur.getConsole().afficherErreurConsommable();
       };
    }



}
