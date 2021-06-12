package sample.modele.acteurs;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Pnj extends Acteur {

    private String nom;
    private ArrayList<String> repliques;
    private ArrayList<String> repliquesFinDuJeu;
    public Pnj(int x, int y, String n){
        super(x, y);
        nom = n;
        this.repliques = addReplique(nom);
        this.repliquesFinDuJeu = addRepliqueDeFin(nom);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<String> getRepliques() {
        return repliques;
    }
    public ArrayList<String> getRepliquesFinDuJeu() {
        return repliquesFinDuJeu;
    }

    public ArrayList<String> addReplique(String nom){
        ArrayList<String> lines = new ArrayList<>();
        switch (nom) {
            case "kid":
                lines.add("Viens on joue au ballon ! ");
                lines.add("Ma mere m'a dit que j'étais quelqu'un de spécial !!");
                lines.add("Cache-cache est un jeu bien trop difficile, je ne sais pas qui a inventé ce jeu ?!");
                break;
            case "cavegirl":
                lines.add("N'approchez pas trop...");
                lines.add("Je vous sens d'ici... prenez une douche non ?");
                lines.add("Vous avez l'air d'aimer ce parfum... pas moi.");
                break;
            case "goldninja":
                lines.add("Momoku... ? vous ... ?");
                lines.add("Vous en savez beaucoup... beaucoup trop.");
                lines.add("Vous êtes donc celui qu'il a choisit...");
                break;
            case "master":
                lines.add("Les ténèbres et la lumière sont les deux faces d'un même monde... ils ne peuvent exister l'un sans l'autre...");
                lines.add("La course du Temps est cruelle. Nul ne peut la modifier. Seule la mémoire des jours anciens n'est pas alterée.");
                lines.add("hmmmmm.... ?");
                break;
            case "moine":
                lines.add("Maître vous a parlé de moi ? Interessant...");
                lines.add("Vous cherchez quelque chose peut-etre ?");
                lines.add("Maître m'a parlé de vous, vous êtes à la recherche de réponses ?");
                break;
            case "spectre":
                lines.add("Cherchez... Momoku... trouvez ... le livre ... ");
                lines.add("Vous devez... pour ramener la lumière...le livre... Momoku...");
                lines.add("Vous êtes le seul... trouvez-le... le livre de Momoku...");
                break;
            case "vieux":
                lines.add("Mon trésor? vous le cherchez ?! Je l'ai laissé quelque part...");
                lines.add("Ma femme me manque...");
                lines.add("Vous avez entendu les dernieres nouvelles ? ");
                break;
            case "villageois1":
                lines.add("J'ai repris le vielle scierie de mon père, chop chop!");
                lines.add("Vous avez beaucoup de bois sur vous... Vous en avez de la place dans ces poches!");
                lines.add("On vous entend à l'autre bout de la ville! Pourquoi tant d'empressement ?!");
                break;
            case "villageois2":
                lines.add("nieh nieh");
                lines.add("Un problème ?");
                lines.add("Quoi ?!");
                break;
            case "villageois3":
                lines.add("Momokoi ?!");
                lines.add("J'ai pu gouter aux cuisses de hiboux avec maman! C'est délicieux !");
                lines.add("Comment ça les cuisses de hiboux n'existent pas !?");
                break;
            case "villageois4":
                lines.add("Laissez-moi tranquille...");
                lines.add("Je ne peux pas vous aider, je n'ai jamais entendu parler de Momoku.");
                lines.add("Momoku ? Jamais entendu parler.");
                break;
            case "villageois5":
                lines.add("J'ai entendu dire que boire des 'Potion' pouvait faire pousser les cheveux!");
                lines.add("J'ai déjà un copain... désolée...");
                lines.add("Brando m'a dit qu'il avait un nouveau stock de ramen");
                break;
            case "chocobo":
                lines.add("Kweh? (Ca va ?)");
                lines.add("Coo !? (Cloud?!)");
                lines.add("CooKweh! (Salut!)");
                break;
            case "cloud":
                lines.add("Mon épée? Elle n'est pas à vendre...");
                lines.add("J'étais un soldat aussi avant, et j'ai pris une flèche dans le genoux.");
                lines.add("Zack...");
                break;
            case "luigi":
                lines.add("Ya! ");
                lines.add("mon ami est à l'interieur! Yahoo! ");
                lines.add("Il a oublié son chapeau! Ya! ");
                break;
            case "panneau":
                lines.add("Trésor très précieux, ne pas toucher");
                lines.add("Dur dur la vie de panneau...");
                lines.add("Le 'Une pièce'... Il existe!");
                break;
            case "épouvantail":
                lines.add("*Vous frappez l'épouvantail*\nPourquoi faites vous cela ?");
                lines.add("*Vous donnez une pièce à l'épouvantail*\nIl vous l'a rend de suite");
                lines.add("*Vous câlinez l'épouvantail*\nIl rougit.");
                break;
            default:
                break;
        }
        return lines;
    }
    public ArrayList<String> addRepliqueDeFin(String nom){
        ArrayList<String> endGameLine = new ArrayList<>();
        switch (nom) {
            case "kid":
                endGameLine.add("Cache-Cache est devenu bien plus facile, mais ca reste dur !");
                break;
            case "cavegirl":
                endGameLine.add("Vous êtes... très vert...");
                break;
            case "goldninja":
                endGameLine.add("Vous avez réussi... vous l'avez fait");
                break;
            case "master":
                endGameLine.add("Je suis fier de vous...");
                break;
            case "moine":
                endGameLine.add("Haha! J'aime beaucoup votre style! Féliciations!");
                break;
            case "spectre":
                endGameLine.add("Mon instinct ne m'a donc pas trompé");
                break;
            case "vieux":
                endGameLine.add("Je vois! Je vois! Tenez, prenez ça: Mon trésor est à l'ouest du pont au nord-ouest de la ville!");
                break;
            case "villageois1":
                endGameLine.add("Merci énormément... Vous voulez du bois peut-être?");
                break;
            case "villageois2":
                endGameLine.add("Je.. je... merci....");
                break;
            case "villageois3":
                endGameLine.add("Que m'arrive-t-il ? c'est... intéressant...");
                break;
            case "villageois4":
                endGameLine.add("Je... je suis désolé, je suis... merci, merci énormément.");
                break;
            case "villageois5":
                endGameLine.add("Kyahahaha! Vous plutot etes mignon quand on vous distingue!");
                break;
            case "chocobo":
                endGameLine.add("Woheeho (thank you!)");
                break;
            case "panneau":
                endGameLine.add("LE ONE PIECE! IL EXISTE!");
                break;
            case "luigi":
                endGameLine.add("Il n'est toujours pas sorti... est-ce que Bowser a...");
                break;
            case "cloud":
                endGameLine.add("Mon chocobo... vous l'avez vu?");
                break;
            default:
                break;
        }
        return endGameLine;
    }
}
