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

    public Quete(String titre, String description, String typeQuete, int recompenseOr, String recompenseObjet, int quantiteObjet){
        idQuete++;
        this.titre = titre;
        this.description = description;
        this.typeQuete = typeQuete.toUpperCase(Locale.ROOT); // TOUJOURS EN MAJUSCULE - KILL - LOOT - TALK
        this.recompenseOr = recompenseOr;
        this.recompenseObjet = recompenseObjet;
        this.quantiteObjet = quantiteObjet;
    }

    public String getTypeQuete() {
        return typeQuete;
    }

    public void setTypeQuete(String typeQuete) {
        this.typeQuete = typeQuete;
    }

    public int getQuantiteObjet() {
        return quantiteObjet;
    }

    public void setQuantiteObjet(int quantiteObjet) {
        this.quantiteObjet = quantiteObjet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public static int getIdQuete() {
        return idQuete;
    }

    public static void setIdQuete(int idQuete) {
        Quete.idQuete = idQuete;
    }

    public int getRecompenseOr() {
        return recompenseOr;
    }

    public void setRecompenseOr(int recompenseOr) {
        this.recompenseOr = recompenseOr;
    }

    public String getRecompenseObjet() {
        return recompenseObjet;
    }

    public void setRecompenseObjet(String recompenseObjet) {
        this.recompenseObjet = recompenseObjet;
    }
}
