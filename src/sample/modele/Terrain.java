package sample.modele;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


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
        for (int j = 0; j < this.mapObstacles[0].length; j++) {
            limiteV++;
        }
        return limiteV;
    }

    public int limiteHorizMap () {
        int limiteH = 0;
        for (int i = 0; i < this.mapObstacles.length; i++) {
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
                if(!(player.getY()>0 && mapObstacles[(int)((player.getCentreJoueurY()-8)/16)][(int)((player.getCentreJoueurX())/16)]==-1))
                    return false;
                break;
            case S:
                if(!(player.getY()<limiteVertiMap()*15 && mapObstacles[(int)((player.getCentreJoueurY()+8)/16)][(int)((player.getCentreJoueurX())/16)]==-1))
                    return false;
                break;
            case Q:
                if(!(player.getX()>0 && mapObstacles[(int)((player.getCentreJoueurY())/16)][(int)((player.getCentreJoueurX()-8)/16)]==-1))
                    return false;
                break;
            case D:
                if(!(player.getX()<limiteHorizMap()*15 && mapObstacles[(int)((player.getCentreJoueurY())/16)][(int)((player.getCentreJoueurX()+8)/16)]==-1))
                    return false;
                break;
        }
        return true;
    }

}
