package sample.modele.acteurs;

import sample.modele.quetes.Quete;

import java.util.ArrayList;

public class Pnj extends Acteur {

    private String nom;

    public Pnj(int x, int y, String n){
        super(x, y);
        nom = n;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
