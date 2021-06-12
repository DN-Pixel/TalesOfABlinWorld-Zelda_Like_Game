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

    //purement esthetique
    public String getShopDescription(){
        return "Nom: " + this.getNom() + "\nDegats: "+ this.getDegatsArme() + "\nPortee: " + this.getRange() + "\nValeur: " + this.getValue();
    }
    public int getValue() { return value; }

    public String getNom() {
        return nom;
    }

    public int getRange() {
        return range;
    }

}
