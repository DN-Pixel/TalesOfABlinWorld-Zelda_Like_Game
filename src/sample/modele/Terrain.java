package sample.modele;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;


public class Terrain {

    private String nomDeCarte;
    private int [][] mapObstacles;


    public Terrain (String nomDeCarte) {
        this.nomDeCarte = nomDeCarte;
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
