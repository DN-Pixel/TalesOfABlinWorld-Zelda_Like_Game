package sample.controleur;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sample.modele.Joueur;
import sample.modele.items.Armes.ShopInventory;
import sample.vue.ArmeDescriptionSwitcher;
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
                terrainVue.loadMap("1", 30*16, 6*16);
                joueur.mourrir();
            }
        });
    }

    // permettra de changer de map si le joueur arrive dans une zone de transition
    public void initPlayerTransitionsListener(){
        ChangeListener c = new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                switch (joueur.getNumeroZone()){
                    case "0":
                        if(joueur.isCollinding(9*16, 16) && !joueur.getListeQuetes().getQueteActuelle().getTitre().startsWith("Chapitre 0"))  terrainVue.loadMap("1", 36*16, 3*16);
                        break;
                    case "1":
                        if(joueur.isCollinding(622, 228))  terrainVue.loadMap("2", 50, 100);
                        else if(joueur.getNiveau()>=3 && (joueur.isCollinding(415, 300) || joueur.isCollinding(430, 300)))  terrainVue.loadMap("4", 500, 80);
                        else if (joueur.getNiveau()>=2 && (joueur.isCollinding(18*16,10) || joueur.isCollinding(19*16,10) || joueur.isCollinding(20*16,10) || joueur.isCollinding(21*16,10))) terrainVue.loadMap("3",21*16,36*16);
                        else if (joueur.getNiveau()>=4 && (joueur.isCollinding(10,12*16) || joueur.isCollinding(10,13*16))) terrainVue.loadMap("5",29*16,30*16);
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
                        if(joueur.getNiveau()>=5 && joueur.isCollinding(14*16,2*16)) terrainVue.loadMap("6",14*16,23*16);
                        if(joueur.isCollinding(0,7*16)) terrainVue.loadMap("7",45*16,7*16);
                        break;
                    case"6":
                        if (joueur.getNiveau()>=6 && joueur.isCollinding(14*16,25*16))terrainVue.loadMap("0", 9*16, 27*16); // charge la première map (cauchemar)
                        break;
                    case"7":
                        if (joueur.isCollinding(48*16,8*16)) terrainVue.loadMap("5",20,7*16);
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


    public void initInventaireListener(Label nbGoldLabel, ItemDescriptionSwitcher itemsDescriptionLoader,Label nbMinerai){
        nbGoldLabel.textProperty().bind(joueur.getInventaire().nbrOrProperty().asString());
        nbMinerai.textProperty().bind(joueur.getInventaire().getListObjet().get(5).quantiteProperty().asString());
        joueur.getInventaire().nbrOrProperty().addListener((observable, oldValue, newValue) ->{
            if(newValue.intValue()-oldValue.intValue()<0)
                joueur.getConsole().afficherPerteArgent(oldValue.intValue()-newValue.intValue());
            else
                joueur.getConsole().afficherGainArgent(newValue.intValue()-oldValue.intValue());
        });
        for (int i = joueur.getInventaire().getListObjet().size()-1;i>=0;i--){
            joueur.getInventaire().getListObjet().get(i).quantiteProperty().addListener((e)-> itemsDescriptionLoader.switchDescription(inventoryClicEventMemory, joueur.getInventaire()));
        }
    }

    public void initStatsListener(Label Level,Label statHP,Joueur j){
        Level.textProperty().bind(j.niveauProperty().asString());
        statHP.textProperty().bind(j.maxHPProperty().asString());

    }
}
