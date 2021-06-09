package sample.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;
import sample.modele.Projectile;
import sample.modele.acteurs.Acteur;
import sample.modele.acteurs.Pnj;
import sample.modele.items.Armes.Shuriken;
import sample.modele.quetes.Quete;
import sample.vue.*;
import sample.modele.Joueur;
import sample.modele.Terrain;
import sample.vue.animations.PlayerHPAnimation;
import sample.vue.animations.PlayerMovementAnimation;
import sample.vue.modeleVue.QueteVue;
import sample.vue.modeleVue.TerrainVue;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
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
    private  BossFightManager bossFightManager = new BossFightManager(zoneActuelle);
    //PANES
    @FXML
    private Pane gamePane;
    @FXML
    private Pane vendeurPane;
    private static Pane vendeurInterface;
    @FXML
    private TilePane tilePane;
    @FXML
    private TilePane tilePaneSolid;
    @FXML
    private TilePane tilePaneDeco;
    @FXML
    private Pane dialoguePane;
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
    @FXML
    private Label dialogueLabel;
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
    //RADIO INVENTAIRE / SHOP
    @FXML
    private ToggleGroup Nourriture;
    @FXML
    private ToggleGroup shopRadio;
    //RADIO SHOP
    // QUESTS
    @FXML
    private Label questTitle;

    @FXML
    private Text questDescription;

    @FXML
    private Label recompenseOr;

    @FXML
    private Label recompenseObjet;

    private QueteVue queteVue;
    public TerrainVue terrainVue; // classe permettant de load la map et charger les textures
    private long temps = 0 ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vendeurInterface = vendeurPane;
        initConsole(); // Charge la console
        itemsDescriptionLoader = new ItemDescriptionSwitcher(descriptionLabel);
        player.setId("player");
        terrainVue = new TerrainVue(zoneActuelle, joueur, gamePane, tilePane, tilePaneDeco, tilePaneSolid);
        terrainVue.loadMap("0", 9*16, 27*16); // charge la première map (cauchemar)
        player.toFront(); // met le joueur devant tout le monde
        listenerLauncher = new ListenerLauncher(joueur, player, terrainVue);
        initListeners(); // initialise les listeners
        initBoucleTemporelle(); // initialise la boucle temporelle
        initAnimations(); // lance les animations
        loadQuests();
        gameLoop.play(); // Lance la boucle temporelle
    }

    public void loadQuests(){
        queteVue = new QueteVue(questTitle, questDescription, recompenseOr, recompenseObjet);
        joueur.getListeQuetes().getQuetes().addListener(new ObsListQuetes(queteVue));
        joueur.getListeQuetes().getQuetes().add(joueur.getListeQuetes().getQuetes().size(), new Quete("Felicitations !", "Vous avez rempli toutes les quetes disponibles !", 2727, "Nice", 69));
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
                    if(!vendeurPane.isVisible()) // Si le joueur n'a pas de discussion en cours
                        movePlayer(); // gère le déplacement à chaque tour de la boucle temporelle
                    timeManager(); // gestion du temps
                    zoneActuelle.clean(); // lance le nettoyeur de map
                    spawnManager(); // manage le spawn des ennemis et des ressources
                    if(temps%5==0)
                        zoneActuelle.moveEnnemis();
                    if(temps%177==0) {
                        zoneActuelle.lesEnnemisAttaquent(joueur); // fais attaquer les ennemis toutes les 3s
                        zoneActuelle.spawnProjectile(joueur); // attaques à distance
                    }
                    if (zoneActuelle.getNumeroCarte()==6 && zoneActuelle.findActeur("aBoss"))
                        bossFightManager.manageBossFight(temps);

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
        if(temps%590==0 && zoneActuelle.getNumeroCarte()!=6)
            zoneActuelle.EnemySpawn();// spawn d'ennemi toutes les 10s
        else if(temps%700==0)
            zoneActuelle.EnemySpawn();// spawn d'ennemi toutes les 15s
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
        else if(e.getCode() == KeyCode.E)
            interagirAvecPnj();
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
            if(dx==1) joueur.moveRight();
            if(dx==-1) joueur.moveLeft();
            if(dy==1) joueur.moveDown();
            if(dy==-1) joueur.moveUp();
            joueur.manageAggro();
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

    @FXML
    void vendre(ActionEvent event) {
        RadioButton shopRadioSelected = (RadioButton) shopRadio.getSelectedToggle();
        int quantitéVoulue =0;

        try { quantitéVoulue = Integer.parseInt(this.shopQuantiteField.getText());
        }catch (Exception e){ joueur.getConsole().afficherErreurDeSaisie(); }

        try{ joueur.getInventaire().vendreObjet(shopRadioSelected.getId(),quantitéVoulue);
        }catch(Exception exception){joueur.getConsole().afficherErreurShopNonSelected(); }
    }

    @FXML
    void acheter(ActionEvent event) {
        RadioButton shopRadioSelected = (RadioButton) shopRadio.getSelectedToggle();
        int quantitéVoulue=0;

        try { quantitéVoulue = Integer.parseInt(this.shopQuantiteField.getText());
        }catch (Exception exception){ joueur.getConsole().afficherErreurDeSaisie(); }

        try { joueur.getInventaire().acheterObjet(shopRadioSelected.getId(), quantitéVoulue);
        }catch (Exception e){ joueur.getConsole().afficherErreurShopNonSelected(); }
    }


    private static void interagirAvecPnj() {
        Pnj a = (Pnj) joueur.parler();
        if(a!=null && a.getNom().equals("vendeur")){
            if(vendeurInterface.isVisible()){
                vendeurInterface.setVisible(false);
                vendeurInterface.setDisable(true);
            }
            else{
                vendeurInterface.setVisible(true);
                vendeurInterface.setDisable(false);
            }
        }
    }

}
