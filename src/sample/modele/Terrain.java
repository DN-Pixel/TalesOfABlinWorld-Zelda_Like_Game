package sample.modele;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;


public class Terrain {

    private Joueur player;
    private String nomDeCarte;
    private int [][] mapObstacles;


    public Terrain (String nomDeCarte, Joueur j) {
        this.nomDeCarte = nomDeCarte;
        this.player = j;
    }

    public void setJoueur (Joueur newPlayer) {
        this.player = newPlayer;
    }

    public Joueur getJoueur () {
        return this.player;
    }

    public void setNomDeCarte (String newNom) {
        this.nomDeCarte = newNom;
    }

    public String getNomDeCarte () {
        return this.nomDeCarte;
    }

    public void setMap (int[][] newMap) {
        this.mapObstacles = newMap;
    }

    public int[][] getMap () {
        return this.mapObstacles;
    }

    public int limiteVertiMap () {
        int limiteV = 0;
        for (int j = 0; j < this.mapObstacles.length; j++) {
            limiteV++;
        }
        return limiteV;
    }

    public int limiteHorizMap () {
        int limiteH = 0;
        for (int i = 0; i < this.mapObstacles[0].length; i++) {
            limiteH++;
        }
        return limiteH;
    }

    public int getLongueurMap () {
        int longueur = 0;
        for (int i = 0; i < this.mapObstacles.length; i++) {
            for (int j = 0; j < this.mapObstacles[i].length; j++) {
                longueur++;
            }
        }
        return longueur;
    }
    /*
    Gère les collisions du joueur dans le terrain retourne vrai si tout vas bien et faux si il y a un conflit
     */
    //><


    public boolean manageCollisions(KeyEvent e){
        switch (e.getCode()){
            case Z:
                if(!(player.getY()>0 &&
                        mapObstacles[((player.getY())/16)][((player.getX())/16)]==-1 &&
                        mapObstacles[((player.getY())/16)][((player.getX()+16)/16)]==-1))
                    return false;
                break;
            case S:
                if(!(player.getY()<limiteVertiMap()*16-19 &&
                        mapObstacles[((player.getY()+16)/16)][((player.getX())/16)]==-1 &&
                        mapObstacles[((player.getY()+16)/16)][((player.getX()+16)/16)]==-1))
                    return false;
                break;
            case Q:
                if(!(player.getX()>0 &&
                        mapObstacles[((player.getY())/16)][((player.getX())/16)]==-1 &&
                        mapObstacles[((player.getY()+16)/16)][((player.getX())/16)]==-1))
                    return false;
                break;
            case D:
                if(!(player.getX()<limiteHorizMap()*16-19 &&
                        mapObstacles[((player.getY())/16)][((player.getX()+16)/16)]==-1 &&
                        mapObstacles[((player.getY()+16)/16)][((player.getX()+16)/16)]==-1))
                    return false;
                break;
        }
        return true;
    }

    // ADAPTE LA TAILLE DES TILES PANES DE LA VUE EN FONCTION DE LA MAP
    public void updateTilePaneSize(TilePane floor, TilePane deco, TilePane solid, Pane pane){
        floor.setPrefWidth(limiteHorizMap()*16);
        floor.setPrefHeight(limiteVertiMap()*16);
        deco.setPrefWidth(limiteHorizMap()*16);
        deco.setPrefHeight(limiteVertiMap()*16);
        solid.setPrefWidth(limiteHorizMap()*16);
        solid.setPrefHeight(limiteVertiMap()*16);
        pane.setPrefWidth(limiteHorizMap()*16);
        pane.setPrefHeight(limiteVertiMap()*16);
    }
}
