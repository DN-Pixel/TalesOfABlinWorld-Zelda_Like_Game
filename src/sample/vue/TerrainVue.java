package sample.vue;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import sample.controleur.MapLoader;
import sample.modele.Joueur;
import sample.modele.Terrain;
import sample.modele.acteurs.Acteur;
import sample.controleur.ObsListActeurs;

import java.io.IOException;

public class TerrainVue {

    /*
    PERMET DE CHARGER LA MAP ET LES TEXTURES
     */

    private Terrain zoneActuelle;
    private Joueur joueur;
    private MapLoader mapLoader = new MapLoader();
    private ImageMap imageMap = new ImageMap();

    private Pane gamePane;

    private TilePane tilePane;
    private TilePane tilePaneDeco;
    private TilePane tilePaneSolid;

    public TerrainVue(Terrain zoneActuelle, Joueur joueur, Pane gamePane, TilePane tilePane, TilePane tilePaneDeco, TilePane tilePaneSolid){
        this.zoneActuelle = zoneActuelle;
        this.joueur = joueur;
        this.gamePane = gamePane;
        this.tilePane = tilePane;
        this.tilePaneDeco = tilePaneDeco;
        this.tilePaneSolid = tilePaneSolid;
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
            joueur.getConsole().afficherZoneActuelle(numero);
            //permet de mettre la camera au bon endroit dès le loading de map.
            setCameraOnSpawn(-spawnX+640,-spawnY+360);
            //joueur.updatePosition();
            //zoneActuelle.loadSaveActeurs();
        } catch (IOException e) { e.printStackTrace(); }
    }
    public void setCameraOnSpawn(int spawnX, int spawnY){
        //verifie si, la position du spawn est sur une limite de la map,
        //ajuste la position de la camera en fonction.
        if(spawnX<152) {
            gamePane.setLayoutX(spawnX + 152);
            gamePane.setLayoutY(spawnY);
        }
        else if(spawnX>zoneActuelle.limiteHorizMap()*16-152){
            gamePane.setLayoutY(spawnY);
            gamePane.setLayoutX(spawnX-152);}
        else if(spawnY<115) {
            gamePane.setLayoutX(spawnX);
            gamePane.setLayoutY(spawnY+115);
        }
        else if(spawnY>zoneActuelle.limiteVertiMap()*16-115) {
            gamePane.setLayoutX(spawnX);
            gamePane.setLayoutY(spawnY-115);
        }
        else {
            gamePane.setLayoutX(spawnX);
            gamePane.setLayoutY(spawnY);
        }
    }
    //Chargement des textures
    Image tileSet = new Image("sample/ressources/tilemaps/allTiles.png");
    public void affichageDeMap(String numero) throws IOException {
        int floor[][] = mapLoader.LoadTileMap("map"+numero+"/Map"+numero+"Floor");
        int deco[][] = mapLoader.LoadTileMap("map"+numero+"/Map"+numero+"Décoration");
        updateTilePaneSize(tilePane, tilePaneDeco, tilePaneSolid, gamePane);
        // affiche chacune des couches
        chargerTextures(floor,tilePane);
        chargerTextures(deco,tilePaneDeco);
        chargerTextures(zoneActuelle.getMapObstacles(),tilePaneSolid);
    }

    public void chargerTextures (int [][] tab, TilePane tilepane){
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

    // ADAPTE LA TAILLE DES TILES PANES DE LA VUE EN FONCTION DE LA MAP
    public void updateTilePaneSize(TilePane floor, TilePane deco, TilePane solid, Pane pane){
        floor.setPrefWidth(zoneActuelle.limiteHorizMap()*16);
        floor.setPrefHeight(zoneActuelle.limiteVertiMap()*16);
        deco.setPrefWidth(zoneActuelle.limiteHorizMap()*16);
        deco.setPrefHeight(zoneActuelle.limiteVertiMap()*16);
        solid.setPrefWidth(zoneActuelle.limiteHorizMap()*16);
        solid.setPrefHeight(zoneActuelle.limiteVertiMap()*16);
        pane.setPrefWidth(zoneActuelle.limiteHorizMap()*16);
        pane.setPrefHeight(zoneActuelle.limiteVertiMap()*16);
    }
}
