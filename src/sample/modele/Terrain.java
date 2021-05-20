package sample.modele;

import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import sample.modele.acteurs.Acteur;
import sample.modele.acteurs.SaveActeurs;
import sample.modele.acteurs.ennemis.Ennemi;

import java.util.ArrayList;


public class Terrain {

    private String nomDeCarte;
    private int [][] mapObstacles; // MAP DES OBSTACLES ET COLLISIONS
    private SaveActeurs saveActeurs = new SaveActeurs();

    private int[][] mapSpawn; // ZONE DE SPAWN DES ENNEMIS

    private ArrayList<Acteur> listeActeurs = new ArrayList<Acteur>();

    public Terrain (String nomDeCarte) {
        this.nomDeCarte = nomDeCarte;
    }

    public void setNomDeCarte (String newNom) {
        this.nomDeCarte = newNom;
    }

    public String getNomDeCarte () {
        return this.nomDeCarte;
    }

    public void setMapObstacles (int[][] newMap) {
        this.mapObstacles = newMap;
    }

    public int[][] getMapObstacles () {
        return this.mapObstacles;
    }

    public void loadSaveActeurs(){
        int numero = Integer.parseInt(nomDeCarte.substring((nomDeCarte.length()-1)));
        Acteur a;
        for(int i=0;i<saveActeurs.getSave(numero).size();i++){
            a = saveActeurs.getSave(numero).get(i);
            if(a instanceof Ennemi)
                mapObstacles[a.getY()/16][a.getX()/16] = 6666; // 6666 -> ENNEMI
            else
                mapObstacles[a.getY()/16][a.getX()/16] = 7777; // 7777 -> PNJ
        }
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

    public ArrayList<Acteur> getListeActeurs() {
        int numero = Integer.parseInt(nomDeCarte.substring((nomDeCarte.length()-1)));
        return saveActeurs.getSave(numero);
    }
    /*
    CHERCHE DANS LA LISTE DES ACTEURS SI IL EXSITE
     */
    public boolean findActeur(String id){
        for(Acteur a: getListeActeurs()){
            if(a.getId().equals(id))
                return true;
        }
        return false;
    }

    public int[][] getMapSpawn() {
        return mapSpawn;
    }

    public void setMapSpawn(int[][] mapSpawn) {
        this.mapSpawn = mapSpawn;
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
