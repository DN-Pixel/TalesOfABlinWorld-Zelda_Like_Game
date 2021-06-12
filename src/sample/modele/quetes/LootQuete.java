package sample.modele.quetes;

public class LootQuete extends Quete{

    private String ressourceToGet;
    private int count;
    private int nbRessource;

    public LootQuete(String titre, String description, int recompenseOr, String recompenseObjet, int quantiteObjet, String ressource, int quantite) {
        super(titre, description, recompenseOr, recompenseObjet, quantiteObjet);
        ressourceToGet = ressource;
        count = 0;
        nbRessource = quantite;
    }

    public String getRessourceToGet() {
        return ressourceToGet;
    }

    public int getCount() {
        return count;
    }

    public void addCount(int count) {
        this.count += count;
    }


    public int getNbRessource() {
        return nbRessource;
    }

}
