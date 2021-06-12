package sample.modele.quetes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.controleur.SoundPlayer;
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
        quetes.add(new LootQuete("Chapitre 1 - Souvenirs obscurs", "'Si tu veux acheter du nouveau matos c'est ici que ça se passe ! Tiens d'ailleurs, rend moi un service tu veux.'\nMiner une source de minerai à l'est.", 10, "Fer", 1, "SourceMinerai", 1));
        quetes.add(new TalkQuete("Chapitre 1 - Souvenirs obscurs", "'Il faudrait peut-etre que je me renseigne à propos de ce livre, une fois que j'aurai amener la marchandise biensur'\nAmener les minerais au forgeron.", 40, "Fer", 3, "upgrader"));

        quetes.add(new TalkQuete("Chapitre 2 - Une recherche sans fin", "Intérroger l'habitant au centre de la ville concernant le livre de Momoku.", 10, "", 0, "villageois4"));
        quetes.add(new TalkQuete("Chapitre 2 - Une recherche sans fin", "Intérroger l'habitant au sud-ouest de la ville concernant le livre de Momoku", 10, "", 0, "villageois2"));
        quetes.add(new KillQuete("Chapitre 2 - Une recherche sans fin", "'Je crois bien que je peux t'aider, mais avant j'ai besoin de toi pour quelque chose !'\n Tuer 2 Slimes.", 5, "", 0, "Slime", 2));
        quetes.add(new KillQuete("Chapitre 2 - Une recherche sans fin", "Tuer 2 Hiboux.", 5, "", 0, "Hibou", 2));
        quetes.add(new TalkQuete("Chapitre 2 - Une recherche sans fin", "Rendre la quete au villageois au sud-ouest de la ville.", 50, "Miel", 3, "villageois2"));

        quetes.add(new TalkQuete("Chapitre 3 - Injustice", "'Je t'ai bien eu héros, personne ne connaît ce livre laisse tomber !'\nAller parler au maitre du Dojo pour en savoir reellement plus sur le livre de Momoku.", 5, "Niveau", 1, "master"));
        quetes.add(new KillQuete("Chapitre 3 - Injustice", "'Je ne sais pas si tu es a la hauteur pour recevoir ce genre de conaissance, prouve moi ta force avant cela !'\nTuer 2 reptiles au nord.", 5, "", 0, "Reptile", 2));
        quetes.add(new TalkQuete("Chapitre 3 - Injustice", "Retourner voir le maitre du Dojo.", 100, "Viande", 3, "master"));
        quetes.add(new KillQuete("Chapitre 3 - Injustice", "'Vous les avez tue !? Vous etes anormalement rapide ! Dans ce cas allez vous occuper des bambous vivants !!'\nTuer 3 bambous vivants au nord.", 10, "", 0, "Bambou", 3));
        quetes.add(new TalkQuete("Chapitre 3 - Injustice", "Retourner voir le maitre du Dojo.", 150, "Potion", 2, "master"));
        quetes.add(new LootQuete("Chapitre 3 - Injustice", "'Donc vous n'etes pas un menteur, votre tenue semble abimee. Allez me chercher quelque chose pour que je la répare.'\nMiner 3 sources de minerai.", 10, "", 0, "SourceMinerai", 3));
        quetes.add(new TalkQuete("Chapitre 3 - Injustice", "Retourner voir le maitre du Dojo.", 200, "Niveau", 1, "master"));

        quetes.add(new TalkQuete("Chapitre 4 - Le chemin d'un heros", "'Un vieil ami se bat à la frontiere sud pour repousser les assaillants, je pense que vous êtes prets a en savoir plus.'\nAller à la rencontre du moine au sud.", 5, "0", 0, "moine"));
        quetes.add(new KillQuete("Chapitre 4 - Le chemin d'un heros", "'Vite heros, tuez les deux yeux à l'entrée de l'avant poste !!'\nTuer 2 yeux vivants.", 10, "", 0, "Oeil", 2));
        quetes.add(new TalkQuete("Chapitre 4 - Le chemin d'un heros", "Retourner voir le moine.", 50, "Potion", 1, "moine"));
        quetes.add(new LootQuete("Chapitre 4 - Le chemin d'un heros", "'C'est le maitre du Dojo qui vous envoie je suppose, vous savez vous battre ça se voit. Les defenses commencent à faiblir vous allez m'aider à reconstruir ces barricades. J'ai quelque chose pour vous si vous m'aidez.'\nCouper 4 buches de bois.", 15, "", 0, "SourceBois", 4));
        quetes.add(new TalkQuete("Chapitre 4 - Le chemin d'un heros", "Retourner voir le moine.", 50, "Shuriken", 1, "moine"));
        quetes.add(new KillQuete("Chapitre 4 - Le chemin d'un heros", "'Vous semblez honnête héros, je sens que vous êtes quelqu'un de confiance. Prenez ces shurikens, si c'est le livre que vous cherchez, il va falloir se battre.'\nTuer 3 betes.", 10, "", 0, "Bete", 3));
        quetes.add(new KillQuete("Chapitre 4 - Le chemin d'un heros", "Tuer 3 yeux vivants.", 10, "", 0, "Oeil", 3));
        quetes.add(new TalkQuete("Chapitre 4 - Le chemin d'un heros", "Retourner voir le moine.", 200, "Potion", 3, "moine"));
        quetes.add(new TalkQuete("Chapitre 4 - Le chemin d'un heros", "'Vous les avez tués ? Parfait, j'ai reussi à renforcer les barricades. Continuez d'explorer le marais, vous trouverez surement quelque chose au sud !'\nExplorer le sud du marais et trouver des informations.", 10, "", 0, "goldninja"));
        quetes.add(new TalkQuete("Chapitre 4 - Le chemin d'un heros", "'J'ai reussi, j'ai le livre de Momoku !! Je devrai rentrer, je ne me sens pas bien ici...'\nRetourner voir le moine.", 100, "Viande", 2, "moine"));
        quetes.add(new TalkQuete("Chapitre 4 - Le chemin d'un heros", "'Je n'arrive pas à y croire ! Vous l'avez !! Vous avez le livre de Momoku je le sens ! Il faut prevenir le maitre du Dojo de suite !!!'\nRetourner voir le maitre du Dojo.", 30, "Niveau", 1, "master"));

        quetes.add(new TalkQuete("Chapitre 5 - Hallucination collective", "'Vous l'avez... Vous l'avez obtenu heros, laissez moi le toucher.'\n*Le maitre passe son doigt sur le livre...*\n'La naissance du monde a été privé d'un sens primordial !? Comment est-ce possible !?'\nDiscuter avec le maitre.", 30, "", 0, "master"));
        quetes.add(new TalkQuete("Chapitre 5 - Hallucination collective", "'J'ai toujours su que quelque chose n'allais pas, il nous manque quelque chose. Quelque chose de très important. Tout ca a surement un rapport avec les hallucinations à l'ouest vous devriez y jeter un coup d'oeil.'\nSe rendre a l'ouest et enquêter.", 30, "", 0, "cavegirl"));
        quetes.add(new TalkQuete("Chapitre 5 - Hallucination collective", "Discuter avec la fille aux cheveux sales.", 30, "", 0, "cavegirl"));
        quetes.add(new KillQuete("Chapitre 5 - Hallucination collective", "'J'aurai jamais cru demander de l'aide à quelqu'un de puant que vous, mais je n'ai pas le choix... Les bambous commencent a envahir mon espace vital !'\nTuer 2 bambous vivants.", 10, "", 0, "Bambou", 2));
        quetes.add(new KillQuete("Chapitre 5 - Hallucination collective", "'Qu'est ce que c'est que ce monument ? Personne n'a remarque ca ? Je vais tenter de degager la zone et d'en parler à la fille.'\nTuer 2 reptiles au nord de la maison de la fille.", 10, "", 0, "Reptile", 2));
        quetes.add(new TalkQuete("Chapitre 5 - Hallucination collective", "Retourner voir la fille aux cheveux sales.", 200, "Nouilles", 3, "cavegirl"));
        quetes.add(new TalkQuete("Chapitre 5 - Hallucination collective", "'Un monument ? Quoi, au dessus ? Un pont ? Je me disais bien que vous aviez un problème vous, en tout cas merci de votre aide quand meme. Vous devriez peut etre vous faire soigner...'\nRetourner voir le maitre du dojo.", 30, "Niveau", 1, "master"));
        quetes.add(new TalkQuete("Chapitre 5 - Hallucination collective", "'Un monument a l'ouest ? Mais oui biensur ! Seul le proprietaire du livre de Momoku ressent ce qu'autrui ignore! Vous êtes l'elu !!'\nDiscuter avec le maitre du dojo.", 30, "", 0, "master"));
        quetes.add(new KillQuete("Chapitre 5 - Hallucination collective", "'Il est temps pour vous de sauver ce monde, entrez dans le monument ! Prenez le portail devant ce dernier a l'ouest. Bonne chance héros...'\nTuer l'esprit malveillant dans le cauchemar.", 10, "Niveau", 1, "EnnemiBoss", 1));
        quetes.add(new TalkQuete("Chapitre 5 - Hallucination collective", "'Je devrai sortir de là au plus vite!'\nAller parler au spectre du cauchemar.", 30, "", 0, "spectre"));
        quetes.add(new TalkQuete("Chapitre 5 - Hallucination collective", "'Heros, vous avez sauvé ce monde du cauchemar. L'humanite a retrouvé ce qu'on lui a volé. La vision. Vous avez redonné vie à ce monde. Merci.'\nRemercier le spectre.", 1000, "Niveau", 1, "spectre"));
    }

    public ObservableList<Quete> getQuetes() {
        return quetes;
    }

    public void completeQuest(){
        if(getQueteActuelle().getRecompenseObjet().equals("Shuriken"))
            joueur.setArmeDistance(new Shuriken());
        if(getQueteActuelle().getRecompenseObjet().equals("Niveau"))
            joueur.lvlUp();
        else if(!getQueteActuelle().getRecompenseObjet().equals(""))
            joueur.getInventaire().ajouterObjet(getQueteActuelle().getRecompenseObjet(), getQueteActuelle().getQuantiteObjet());
        joueur.getInventaire().ajouterOr(getQueteActuelle().getRecompenseOr());
        quetes.remove(0);
        SoundPlayer.playSpecificSound("quest.wav");
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
