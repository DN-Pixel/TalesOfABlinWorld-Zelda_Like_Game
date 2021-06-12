package sample.modele.quetes;

public class KillQuete extends Quete{

    private String ennemyToKill;
    private int nbKills;
    private int count;


    public KillQuete(String titre, String description, int recompenseOr, String recompenseObjet, int quantiteObjet, String ennemy, int nb) {
        super(titre, description, recompenseOr, recompenseObjet, quantiteObjet);
        ennemyToKill = ennemy;
        nbKills = nb;
        count = 0;
    }

    public int getCount() {
        return count;
    }

    public void addCount(int count) {
        this.count += count;
    }

    public String getEnnemyToKill() {
        return ennemyToKill;
    }


    public int getNbKills() {
        return nbKills;
    }

}
