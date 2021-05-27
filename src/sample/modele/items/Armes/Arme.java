package sample.modele.items.Armes;

public class Arme {
    private int degatsArme;
    private String nom;
    private int range;

    public Arme (String name, int degat, int range){
        this.nom = name;
        this.range = range;
        this.degatsArme = degat;
    }

    public int getDegatsArme() {
        return degatsArme;
    }

    public void setDegatsArme(int degatsArme) {
        this.degatsArme = degatsArme;
    }
    public void upgradeDegats(int degatArme) {
        this.degatsArme += degatArme;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}