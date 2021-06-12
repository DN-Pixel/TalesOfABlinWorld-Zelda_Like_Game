package sample.modele.quetes;

import sample.modele.items.Armes.Arme;
import sample.modele.items.Objets.Objet;

import java.util.Locale;


public class Quete {

    private String description;
    private String titre;
    private String typeQuete;
    private static int idQuete;
    private int recompenseOr;
    private String recompenseObjet;
    private int quantiteObjet;

    public Quete(String titre, String description, int recompenseOr, String recompenseObjet, int quantiteObjet){
        idQuete++;
        this.titre = titre;
        this.description = description;
        this.recompenseOr = recompenseOr;
        this.recompenseObjet = recompenseObjet;
        this.quantiteObjet = quantiteObjet;
    }

    public int getQuantiteObjet() {
        return quantiteObjet;
    }
    public String getDescription() {
        return description;
    }

    public String getTitre() {
        return titre;
    }


    public int getRecompenseOr() {
        return recompenseOr;
    }


    public String getRecompenseObjet() {
        return recompenseObjet;
    }

}
