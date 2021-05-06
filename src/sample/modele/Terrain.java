package sample.modele;

public class Terrain {

    private Joueur player;
    private String nomDeCarte;
    private int [][] map;

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
        this.map = newMap;
    }

    public int[][] getMap () {
        return this.map;
    }

    public int limiteVertiMap () {
        int limiteV = 0;
        for (int j = 0; j < this.map[0].length; j++) {
            limiteV++;
        }
        return limiteV;
    }

    public int limiteHorizMap () {
        int limiteH = 0;
        for (int i = 0; i < this.map.length; i++) {
            limiteH++;
        }
        return limiteH;
    }

    public int getLongueurMap () {
        int longueur = 0;
        for (int i = 0; i < this.map.length; i++) {
            for (int j = 0; j < this.map[i].length; j++) {
                longueur++;
            }
        }
        return longueur;
    }


}
