package sample.modele.items.Objets;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Objet {

    private IntegerProperty quantite = new SimpleIntegerProperty();
    private int valeur;
    private String nom;
    private String description;

    public Objet(String nom, int valeur) {
            this.quantite.setValue(0);
            this.valeur = valeur;
            this.nom = nom;
            this.description = "Nom: " + this.nom + "\nQuantité: "+ this.quantite.getValue() + "\nValeur Unitaire: "+ this.valeur;
    }



    public int getValeur() {
        return valeur;
    }

    public String getNom() {
        return nom;
    }

    // UPDATE LA  DESCRIPTION ET LA RETOURNE
    public String getDescription() {
        this.description = "Nom: " + this.nom + "\nQuantité: "+ this.quantite.getValue() + "\nValeur: "+ this.valeur;
        return description;
    }

    public void setNom (String newNom) { this.nom = newNom;}

    public void setQuantite (int quantite) {this.quantite.setValue(quantite);}
    public int getQuantite() { return quantite.getValue(); }
    public IntegerProperty quantiteProperty() { return quantite; }
    public void setValeur (int valeur) { this.valeur = valeur;}

    public void setDescription (String description) {this.description = description;}

}
