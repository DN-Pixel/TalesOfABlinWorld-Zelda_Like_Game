package sample.modele.items.Armes;

public class Arme {
    private int degatsArme;
    private String nom;
    private int range;
    private String description;
    private int value;

    public Arme (String name, int degat, int range,int value){
        this.nom = name;
        this.range = range;
        this.degatsArme = degat;
        this.description = "Nom: " + this.nom + "\nDegats: "+ this.degatsArme + "\nPortee: " + this.range;
        this.value=value;
    }

    public int getDegatsArme() {
        return degatsArme;
    }

    public String getDescription() {
        return description;
    }
    //purement esthetique
    public String getShopDescription(){
        return "Nom: " + this.getNom() + "\nDegats: "+ this.getDegatsArme() + "\nPortee: " + this.getRange() + "\nValeur: " + this.getValue();
    }
    public void setDegatsArme(int degatsArme) {
        this.degatsArme = degatsArme;
    }
    public void upgradeDegats(int degatArme) {
        this.degatsArme += degatArme;
    }

    public int getValue() { return value; }

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
