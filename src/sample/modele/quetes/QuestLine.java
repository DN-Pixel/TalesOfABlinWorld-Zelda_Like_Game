package sample.modele.quetes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.controleur.Controller;
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
        quetes.add(new TalkQuete("Chapitre 0 - Perdu", "???????", 10, "Potion", 1, "spectre"));
        quetes.add(new TalkQuete("Chapitre 1 - Souvenirs obscurs", "'Le livre de Momoku ? Je n'ai jamais entendu parler de ça... Je devrai sortir de ce mauvais cauchemar !'\nParler au vendeur devant la boutique à l'est de la ville.", 5, "Miel", 1, "vendeur"));
        quetes.add(new TalkQuete("Chapitre 1 - Souvenirs obscurs", "'Salut à toi héros ici tu trouveras tout ce qu'il te faut, va voir le forgeron pour acheter des armes ou traiter tes minerais'\nAller parler au forgeron au sud de la ville.", 5, "Miel", 1, "upgrader"));
        quetes.add(new LootQuete("Chapitre 1 - Souvenirs obscurs", "'Si tu veux acheter du nouveau matos c'est ici que ça se passe ! Tiens d'ailleurs, rend moi un service tu veux.'\nMiner une source de minerai à l'est.", 10, "Fer", 1, "SourceMinerai", 2));
        quetes.add(new TalkQuete("Chapitre 1 - Souvenirs obscurs", "'Faudrait peut-etre que je me renseigne à propos de ce livre, une fois que j'aurai amener la marchandise biensur'\nAmener les minerais au forgeron.", 40, "Fer", 3, "upgrader"));

        quetes.add(new TalkQuete("Chapitre 2 - A la recherche des renseignements", "Intérroger l'habitant au nord-ouest de la ville concernant le livre de Momoku.", 10, "", 0, "villageois4"));
        quetes.add(new TalkQuete("Chapitre 2 - A la recherche des renseignements", "Intérroger l'habitant au sud-ouest de la ville concernant le livre de Momoku", 10, "", 0, "villageois2"));
        quetes.add(new KillQuete("Chapitre 2 - A la recherche des renseignements", "'Je crois bien que je peux t'aider, mais avant j'ai besoin de toi pour quelque chose !'\n Tuer 2 Slimes.", 5, "", 0, "Slime", 2));
        quetes.add(new KillQuete("Chapitre 2 - A la recherche des renseignements", "Tuer 2 Hiboux.", 5, "", 0, "Hibou", 2));
        quetes.add(new TalkQuete("Chapitre 2 - A la recherche des renseignements", "Rendre la quete au villageois au sud-ouest de la ville.", 50, "Miel", 3, "villageois2"));

        quetes.add(new TalkQuete("Chapitre 3 - Injustice", "'Je t'ai bien eu héros, personne ne connaît ce livre laisse tomber !'\nAller parler au maitre du Dojo pour en savoir reellement plus sur le livre de Momoku.", 5, "", 0, "master"));
    }

    public ObservableList<Quete> getQuetes() {
        return quetes;
    }

    public void completeQuest(){
        if(getQueteActuelle().getRecompenseObjet().equals("Shuriken"))
            joueur.setArmeDistance(new Shuriken());
        else if(!getQueteActuelle().getRecompenseObjet().equals(""))
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
