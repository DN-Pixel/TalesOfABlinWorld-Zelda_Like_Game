package sample.modele.items.Objets;

public class Objet {

    private int quantite;
    private int valeur;
    private String nom;
    private String description;

    public Objet(String nom, int valeur) {
            this.quantite = 0;
            this.valeur = valeur;
            this.nom = nom;
            this.description = "Nom: " + this.nom + "\nQuantité: "+ this.quantite + "\nValeur: "+ this.valeur;
    }

    public int getQuantite() {
        return quantite;
    }

    public int getValeur() {
        return valeur;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public void setNom (String newNom) { this.nom = newNom;}

    public void setQuantite (int quantite) {this.quantite = quantite;}

    public void setValeur (int valeur) { this.valeur = valeur;}

    public void setDescription (String description) {this.description = description;}

}
