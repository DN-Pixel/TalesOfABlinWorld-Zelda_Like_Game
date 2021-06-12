package sample.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import sample.modele.Console;
import sample.modele.acteurs.Acteur;
import sample.modele.acteurs.Pnj;
import sample.modele.items.Armes.Arme;
import sample.modele.items.Armes.ShopInventory;
import sample.modele.items.InventaireException;
import sample.modele.quetes.Quete;
import sample.vue.*;
import sample.modele.Joueur;
import sample.modele.Terrain;
import sample.vue.animations.PlayerHPAnimation;
import sample.vue.animations.PlayerMovementAnimation;
import sample.vue.animations.ShurikenAnimation;
import sample.vue.modeleVue.*;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    ImageMap imageMap = new ImageMap();
    private static CooldownManager  cdManager = new CooldownManager();

    private ItemDescriptionSwitcher itemsDescriptionLoader;
    private ArmeDescriptionSwitcher armeDescriptionSwitcher;
    private ListenerLauncher listenerLauncher;

    private static int dx = 0;
    private static int dy = 0;

    private static Terrain zoneActuelle = new Terrain("zone");
    private static Joueur joueur = new Joueur(0, 0, zoneActuelle);
    private BossFightManager bossFightManager = new BossFightManager(zoneActuelle);
    private ShopInventory shopInventory = new ShopInventory();
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
    @FXML
    private Pane upgraderPane;
    private static Pane upgraderPaneInterface;
    private static Pane dialogueGlobalInterface;
    @FXML
    private Pane descriptionPane;
    @FXML
    private Pane menuStatsPane;
    private static Pane menuStatInterface;
    //LABELS TEXTFIELDS
    @FXML
    private Label nbMineraiLabel;
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
    @FXML
    private Label descriptionArmeLabel;
    private static Label dialogueInterface;
    @FXML
    private Label pourcentageHPLabel;

    //labelstats
    @FXML
    private Label statLevelLabel;
    @FXML
    private Label statHPMAXLabel;
    @FXML
    private Label statArmeLabel;
    @FXML
    private Label statRangeLabel;
    @FXML
    private Label statAttaqueLabel;
    @FXML
    private Label statArmeDistanceLabel;

    //IMAGES
    @FXML
    private ImageView hpBar;
    @FXML
    private ImageView player;
    @FXML
    private ImageView rainImageView;
    //BUTTONS
    @FXML
    private Button vendreButton;
    @FXML
    private Button acheterButton;
    @FXML
    private Button mangerButton;
    @FXML
    private Button traiterButton;
    //RADIO INVENTAIRE / SHOP
    @FXML
    private ToggleGroup Nourriture;
    @FXML
    private ToggleGroup shopRadio;
    @FXML
    private ToggleGroup upgradeRadio;
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
    private DialogueVue dialogueVue;
    private static DialogueVue dialogueVueInterface;//classe permettant d'afficher les dialogues.
    private QueteVue queteVue;
    public TerrainVue terrainVue; // classe permettant de load la map et charger les textures
    public static TerrainVue terrainVueInterface;
    public static HUDVue hudVue;
    private static long temps = 0 ;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hudVue = new HUDVue(statArmeLabel,statRangeLabel,statAttaqueLabel,statArmeDistanceLabel,joueur);
        initConsole(); // Charge la console
        itemsDescriptionLoader = new ItemDescriptionSwitcher(descriptionLabel);
        armeDescriptionSwitcher= new ArmeDescriptionSwitcher(descriptionArmeLabel);
        player.setId("player");
        dialogueVue = new DialogueVue(dialogueLabel,dialoguePane,joueur);
        terrainVue = new TerrainVue(zoneActuelle, joueur, gamePane, tilePane, tilePaneDeco, tilePaneSolid);
        terrainVue.loadMap("0", 9*16, 27*16); // charge la première map (cauchemar)
        terrainVue.creerNuage();
        listenerLauncher = new ListenerLauncher(joueur, player, terrainVue);
        initListeners();
        initBoucleTemporelle();
        initAnimations();
        loadQuests();
        swapStatic();
        gameLoop.play();
    }
    // Permet de contourner le problème des éléments statics du FXML
    private void swapStatic() {
        dialogueVueInterface=dialogueVue;
        terrainVueInterface=terrainVue;
        vendeurInterface = vendeurPane;
        dialogueInterface = dialogueLabel;
        upgraderPaneInterface = upgraderPane;
        dialogueGlobalInterface=dialoguePane;
        menuStatInterface=menuStatsPane;
    }

    public void loadQuests(){
        queteVue = new QueteVue(questTitle, questDescription, recompenseOr, recompenseObjet);
        joueur.getListeQuetes().getQuetes().addListener(new ObsListQuetes(queteVue));
        joueur.getListeQuetes().getQuetes().add(joueur.getListeQuetes().getQuetes().size(), new Quete("Felicitations !", "Vous avez rempli toutes les quetes disponibles !", 2727, "Nice", 69));
    }

    private void initConsole(){
        Console cons = new Console();
        cons.setConsoleVue(new ConsoleVue(console));
        joueur.setConsole(cons);
        zoneActuelle.setConsole(cons);
    }

    private void initAnimations() {
        PlayerMovementAnimation.initAnimation(player, joueur, imageMap); // INITIALISE LES ANIMATIONS DE DEPLACEMENTS DU JOUEUR
        PlayerHPAnimation.initAnimation(joueur, hpBar, imageMap,pourcentageHPLabel); // INTIALISE LES ANIMATIONS DE VIE (Coeurs)
    }

    //initialise tous les listeners
    public void initListeners(){
       listenerLauncher.initPlayerListener();
       listenerLauncher.initPlayerTransitionsListener();
       listenerLauncher.initCameraListener(gamePane);
       listenerLauncher.initInventaireListener(nbGoldLabel, itemsDescriptionLoader,nbMineraiLabel);
       listenerLauncher.initStatsListener(statLevelLabel,statHPMAXLabel,joueur);
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
                    manageShurikenAnimation();
                    if(!(vendeurPane.isVisible()|| upgraderPane.isVisible())) // Si le joueur n'a pas de discussion en cours
                        movePlayer(); // gère le déplacement à chaque tour de la boucle temporelle
                    timeManager(); // gestion du temps
                    zoneActuelle.clean(); // lance le nettoyeur de map
                    spawnManager(); // manage le spawn des ennemis et des ressources
                    if(temps%5==0) {
                        terrainVue.bougerNuages();
                        zoneActuelle.moveEnnemis();
                        terrainVue.rainManager(rainImageView); // Affiche la pluie et son animation
                    }
                    if(temps%177==0) {
                        zoneActuelle.lesEnnemisAttaquent(joueur); // fais attaquer les ennemis toutes les 3s
                        zoneActuelle.spawnProjectile(joueur); // attaques à distance
                    }
                    if (zoneActuelle.getNumeroCarte()==6 && zoneActuelle.findActeur("aBoss"))
                        bossFightManager.manageBossFight(temps);

                    zoneActuelle.manageProjeciles(joueur);
                    dialogueVueInterface.checkDialogueTimerOut(temps);
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }


    //gestion du temps
    public void timeManager(){
        if (temps >= 1000000000) {
            temps = 1; // reset
            dialogueVueInterface.debutDialogueTimeSetter(120);
        }
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
        else if(e.getCode() == KeyCode.E){
            interagirAvecPnj();
            parlerAvecActeur();
            dialogueVueInterface.debutDialogueTimeSetter(temps);//permet de mémoriser le timer du clic
        }
        else if(cdManager.getCdShuriken()>=1 && (e.getCode() == KeyCode.DIGIT3 || e.getCode() == KeyCode.QUOTEDBL)){
            joueur.attaquerEnDistance();
            cdManager.setCdShuriken(0);
        }
        else if(e.getCode()== KeyCode.DIGIT2 || e.getCode()==KeyCode.UNDEFINED)
            joueur.manger("Potion");
        else if(e.getCode()==KeyCode.B)
            hudVue.statMenuManager(menuStatInterface);

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
    @FXML
    public void setDescriptionArme(MouseEvent event){
        descriptionPane.setVisible(true);
        armeDescriptionSwitcher.switchArmeDescription(event.getPickResult().getIntersectedNode().getId(),shopInventory);
        descriptionPane.setLayoutX(event.getPickResult().getIntersectedNode().getLayoutX()+70);
    }
    @FXML
    public void disableDescription(MouseEvent event){
        descriptionPane.setVisible(false);
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
        }catch (Exception e){
            if (e instanceof InventaireException)
                joueur.getConsole().afficherArgentManquant();
            else
                joueur.getConsole().afficherErreurShopNonSelected(); }
    }


    private static void interagirAvecPnj() {
        Pnj a = (Pnj) joueur.parler();
        if(a!=null && a.getNom().equals("vendeur")){
            if(vendeurInterface.isVisible()){
                vendeurInterface.setVisible(false);
                vendeurInterface.setDisable(true);
            }
            else{
                SoundPlayer.playSpecificSound("shop.wav");
                vendeurInterface.setVisible(true);
                vendeurInterface.setDisable(false);
            }
        }
        else if(a!=null && a.getNom().equals("upgrader")){
            if(upgraderPaneInterface.isVisible()){
                upgraderPaneInterface.setVisible(false);
                upgraderPaneInterface.setDisable(true);
            }
            else{
                SoundPlayer.playSpecificSound("forge.wav");
                upgraderPaneInterface.setVisible(true);
                upgraderPaneInterface.setDisable(false);
            }
        }
    }
    @FXML
    public void traiterMinerai (){
        try {
            joueur.getConsole().afficherMineraiCrafted(joueur.getInventaire().traiterMinerai());
        }
        catch (InventaireException e){
            joueur.getConsole().afficherItemIndisponible("MineraiBrut");
        }
    }
    @FXML
    public void acheterArme (){
        RadioButton radioSelected = (RadioButton) upgradeRadio.getSelectedToggle();
        try {
            Arme armechoisie = shopInventory.chercherArme(radioSelected.getId());
            joueur.acheterArme(armechoisie);
        }catch (Exception e) { joueur.getConsole().afficherErreurArmeNotSelected(); };
    }
    private static void parlerAvecActeur(){
        for(Acteur a : joueur.getZone().getListeActeurs()){
            if(a instanceof Pnj && joueur.isCollinding(a.getX(), a.getY()) && !(((Pnj) a).getNom().equals("vendeur")|| ((Pnj) a).getNom().equals("upgrader")))
               dialogueVueInterface.afficherDialogue(a);
        }
    }

    public void manageShurikenAnimation(){
        for(int i = gamePane.getChildren().size()-1;i>=0;i--){
            Node n = gamePane.getChildren().get(i);
            if(n.getId().startsWith("hero") && n instanceof ImageView){
                ShurikenAnimation.animate(temps, (ImageView) n, imageMap);
            }
        }
    }


    @FXML
    private Pane start;
    @FXML
    private void commencer (MouseEvent e) {
        hudVue.commencer(start);
    }

}
