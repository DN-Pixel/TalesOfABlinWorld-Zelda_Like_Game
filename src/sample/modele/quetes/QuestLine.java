package sample.modele.quetes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.modele.Joueur;
import sample.modele.acteurs.Acteur;
import sample.modele.acteurs.Pnj;
import sample.modele.items.Armes.Shuriken;
import sample.modele.ressources.Ressource;

public class QuestLine {
    private ObservableList<Quete> quetes;
    private Joueur joueur;

    public QuestLine(Joueur j){
        joueur = j;
        quetes = FXCollections.observableArrayList();
        quetes.add(new TalkQuete("Chapitre 1 - Souvenirs obscurs", "Parler au vendeur devant la boutique à l'est de la ville", 100, "Shuriken", 1, "vendeur"));
        quetes.add(new KillQuete("Chapitre 1 - Sku", "Tue le", 100, "Potion", 1, "Slime", 1));
        quetes.add(new LootQuete("Chapitre 1 - Sku", "Tue le", 100, "Bois", 3, "SourceBois", 1));
    }

    public ObservableList<Quete> getQuetes() {
        return quetes;
    }

    public void completeQuest(){
        if(getQueteActuelle().getRecompenseObjet().equals("Shuriken")){
            joueur.setArmeDistance(new Shuriken());
        }
        else
            joueur.getInventaire().ajouterObjet(getQueteActuelle().getRecompenseObjet(), getQueteActuelle().getQuantiteObjet());
        joueur.getInventaire().ajouterOr(getQueteActuelle().getRecompenseOr());
        quetes.remove(0);
    }

    public Quete getQueteActuelle(){
        if(quetes.isEmpty())
            return null;
        return quetes.get(0);
    }

    public void killTracker(Acteur a){
        if(!(getQueteActuelle() instanceof KillQuete))
            return;
        KillQuete quete = (KillQuete) getQueteActuelle();
        if(a.getClass().getSimpleName().equals(quete.getEnnemyToKill())){
            quete.addCount(1);
        }
        if(quete.getCount()>=quete.getNbKills()){
            completeQuest();
        }
    }

    public void ressourceTracker(Ressource r) {
        if(!(getQueteActuelle() instanceof LootQuete))
            return;
        LootQuete quete = (LootQuete) getQueteActuelle();
        if(r.getClass().getSimpleName().equals(quete.getRessourceToGet())){
            quete.addCount(1);
        }
        if(quete.getCount()>=quete.getNbRessource()){
            completeQuest();
        }
    }

    public void talkTracker(Acteur a) {
        if(!(getQueteActuelle() instanceof TalkQuete))
            return;
        TalkQuete quete = (TalkQuete) getQueteActuelle();
        if(a instanceof Pnj && ((Pnj) a).getNom().equals(quete.getPnjName())) {
            completeQuest();
        }
    }
}
