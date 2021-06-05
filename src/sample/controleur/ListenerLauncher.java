package sample.controleur;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sample.modele.Joueur;
import sample.vue.ItemDescriptionSwitcher;
import sample.vue.modeleVue.TerrainVue;


public class ListenerLauncher {

    private Joueur joueur;
    private ImageView player;
    private TerrainVue terrainVue;
    private String inventoryClicEventMemory = "";

    public ListenerLauncher(Joueur joueur, ImageView player, TerrainVue terrainVue){
        this.joueur = joueur;
        this.player = player;
        this.terrainVue = terrainVue;
    }

    public void setInventoryClicEventMemory(String inventoryClicEventMemory) {
        this.inventoryClicEventMemory = inventoryClicEventMemory;
    }

    public void initPlayerListener() {
        player.translateXProperty().bind(joueur.getxProperty());
        player.translateYProperty().bind(joueur.getyProperty());
        joueur.getHp().addListener((obs, old, nouv) ->{
            if(nouv.intValue()<=0){
                joueur.mourrir();
                terrainVue.loadMap("1", 30*16, 6*16);
            }
        });
    }

    // permettra de changer de map si le joueur arrive dans une zone de transition
    public void initPlayerTransitionsListener(){
        ChangeListener c = new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                switch (joueur.getNumeroZone()){
                    case "1":
                        if(joueur.isCollinding(622, 228))  terrainVue.loadMap("2", 50, 100);
                        else if(joueur.isCollinding(415, 300) || joueur.isCollinding(430, 300))  terrainVue.loadMap("4", 500, 80);
                        else if ( joueur.isCollinding(18*16,10) || joueur.isCollinding(19*16,10) || joueur.isCollinding(20*16,10) || joueur.isCollinding(21*16,10)) terrainVue.loadMap("3",21*16,36*16);
                        else if (joueur.isCollinding(10,12*16) || joueur.isCollinding(10,13*16)) terrainVue.loadMap("5",29*16,30*16);
                        break;
                    case "2":
                        if(joueur.isCollinding(0, 100))  terrainVue.loadMap("1", 600, 250);
                        break;
                    case "3" :
                        if (joueur.isCollinding(20*16,38*16) || (joueur.isCollinding(19*16,38*16))) terrainVue.loadMap("1",21*16,40);
                        break;
                    case "4":
                        if(joueur.isCollinding(500, 16))  terrainVue.loadMap("1", 415, 260);
                        break;
                    case "5":
                        if(joueur.isCollinding(29*16,32*16)) terrainVue.loadMap("1",30,12*16);
                        break;
                    default:
                        break;
                }
            }
        };
        player.translateXProperty().addListener(c);
        player.translateYProperty().addListener(c);
    }


    public void initCameraListener(Pane gamePane){
        joueur.getxProperty().addListener((obse,old,nouv)->{
            //si le joueur est trop pres du bord de la map, le déplacement ne se fait pas.
            if(nouv.intValue()>152 && nouv.intValue()< joueur.getZone().limiteHorizMap()*16-152)
                gamePane.setLayoutX(-(int)nouv+640);
        });
        joueur.getyProperty().addListener((obse,old,nouv)->{
            if(nouv.intValue()>115 && nouv.intValue()< joueur.getZone().limiteVertiMap()*16-115)
                gamePane.setLayoutY(-(int)nouv+360);
        });
    }


    public void initInventaireListener(Label nbGoldLabel, ItemDescriptionSwitcher itemsDescriptionLoader){
        nbGoldLabel.textProperty().bind(joueur.getInventaire().nbrOrProperty().asString());
        for (int i = joueur.getInventaire().getListObjet().size()-1;i>=0;i--){
            joueur.getInventaire().getListObjet().get(i).quantiteProperty().addListener((e)-> itemsDescriptionLoader.switchDescription(inventoryClicEventMemory, joueur.getInventaire()));
        }
    }
}
