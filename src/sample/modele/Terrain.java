package sample.modele;

import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import sample.modele.acteurs.Acteur;
import sample.modele.acteurs.SaveActeurs;
import sample.modele.acteurs.ennemis.*;

import java.util.ArrayList;


public class Terrain {

    private String nomDeCarte;
    private int [][] mapObstacles; // MAP DES OBSTACLES ET COLLISIONS
    private SaveActeurs saveActeurs = new SaveActeurs();

    private int[][] mapSpawn; // ZONE DE SPAWN DES ENNEMIS



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
        int numero = Integer.parseInt(nomDeCarte.substring((nomDeCarte.length()-1))); // récupère le numéro de la carte
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

    public ObservableList<Acteur> getListeActeurs() {
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

    public void EnemySpawn () {
        //><
        //Si la limite d'ennemis est atteinte on ne fait rien spawn, si on est dans une ville non plus.
        if (saveActeurs.getSave(Integer.parseInt(nomDeCarte.substring((nomDeCarte.length() - 1)))).size() >= 10 ||
                Integer.parseInt(nomDeCarte.substring((nomDeCarte.length() - 1)))==1)
            return;

        //gestion du spawn des ennemis en fonction de la zone.
        double x = Math.random();

        for (int i = 0; i < mapSpawn.length; i++) {
            for (int j = 0; j < mapSpawn[0].length; j++) {
                //l'ennemis spawn dans le map de spawn si l'emplacement est disponible dans la map obstacle.
                if (mapSpawn[i][j] == 3415 && mapObstacles[i][j]==-1 &&  Math.random()<0.015 ) {
                    switch (Integer.parseInt(nomDeCarte.substring((nomDeCarte.length() - 1)))) {
                        case 2:
                            if (x < .5) saveActeurs.getSave(2).add(new Hibou(j * 16, i * 16));
                            else saveActeurs.getSave(2).add(new Slime(j * 16, i * 16));
                            break;
                        case 4:
                            if (x < .5) saveActeurs.getSave(4).add(new Reptile(j  * 16, i * 16));
                            else saveActeurs.getSave(4).add(new Bambou(j  * 16, i * 16));
                            break;
                        case 6:
                            if (x < .5) saveActeurs.getSave(6).add(new Bete(j  * 16, i * 16));
                            else saveActeurs.getSave(6).add(new Oeil(j * 16, i * 16));
                            break;
                        default:
                            break;
                    }
                    //mapObstacles[i][j]=6666; // -> apres qu'un ennemi ait spawn, on change le tableau 2D mapObstacle en y ajoutant le-dit ennemi.
                    return;
                }
            }
        }
    }
    // parcours la liste des acteurs pour faire bouger uniquement les ennemis
    public void moveEnnemis(){
        for(Acteur a : getListeActeurs()){
            if(a instanceof Ennemi)
                ((Ennemi) a).moveEnnemi(mapObstacles);
        }
    }

    public void lesEnnemisAttaquent(int posJoueurX, int posJoueurY, Joueur joueur){
        for(Acteur a : getListeActeurs()){
            if(a instanceof Ennemi)
                ((Ennemi) a).attaquerJoueur(posJoueurX, posJoueurY, joueur);
        }
    }
    // supprime les morts de la liste
    public void clean() {
        for(int i = getListeActeurs().size()-1;i>=0;i--){
            Acteur a = getListeActeurs().get(i);
            if(a instanceof Ennemi && ((Ennemi) a).getPv()<=0)
                getListeActeurs().remove(a);
        }
    }
}
