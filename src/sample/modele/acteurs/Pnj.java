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
                lines.add("Ma mere m'a que j'etais quelqu'un de special !!");
                lines.add("Cache-cache est un jeu bien trop difficile, je ne sais pas qui a inventer ce jeu ?!");
                break;
            case "cavegirl":
                lines.add("N'approchez pas trop...");
                lines.add("Je vous sens d'ici... prenez donc une douche ?");
                lines.add("Vous avez l'air d'aimer ce parfum... pas moi.");
                break;
            case "goldninja":
                lines.add("momoku... ? vous ... ?");
                lines.add("Vous en savez beaucoup... beaucoup trop.");
                lines.add("Vous etes donc celui qu'il a choisit...");
                break;
            case "master":
                lines.add("Les tenebres et la lumieres sont les deux faces d'un meme monde... ils ne peuvent exister l'un sans l'autre...");
                lines.add("La course du Temps est cruelle. Nul ne peut la modifier. Seule la memoire des jours anciens n'est pas alteree.");
                lines.add("hmmmmm.... ?");
                break;
            case "moine":
                lines.add("Maitre vous a parler de moi ? Interessant...");
                lines.add("Vous cherchez quelque chose peut-etre ?");
                lines.add("Maitre m'a parlé de vous, vous etes a la recherche de reponses ?");
                break;
            case "spectre":
                lines.add("Cherchez... Momoku... trouvez ... le livre ... ");
                lines.add("Vous devez... pour ramener la lumiere...le livre... Momoku...");
                lines.add("Vous etes le seul... trouvez-le... le livre de Momoku...");
                break;
            case "vieux":
                lines.add("Mon tresor? vous le cherchez ?! Je l'ai laissé quelque part... vers l'Ouest");
                lines.add("Ma femme me manque...");
                lines.add("Vous avez entendu les dernieres nouvelles ? ");
                break;
            case "villageois1":
                lines.add("J'ai repris le vielle scierie de mon pere, chop chop!");
                lines.add("Vous avez beaucoup de bois sur vous... Vous en avez de la place dans ses poches!");
                lines.add("Hey un buisson qu parle ?!");
                break;
            case "villageois2":
                lines.add("nieh nieh");
                lines.add("Un probleme ?");
                lines.add("Quoi ?!");
                break;
            case "villageois3":
                lines.add("Momokoi ?!");
                lines.add("J'ai pu gouter aux cuisses de hiboux avec maman! C'est délicieux !");
                lines.add("");
                break;
            case "villageois4":
                lines.add("Laissez-moi tranquille...");
                lines.add("Je ne peux pas vous aider, je n'ai jamais entendu parler de Momoku.");
                lines.add("Momoku ? Jamais entendu parler.");
                break;
            case "villageois5":
                lines.add("J'ai entendu dire que boire des 'Potion' pouvait faire pousser les cheveux !");
                lines.add("J'aime votre style... c'est tres... vert ! ");
                lines.add("Brando m'a dit que vous cherchiez un livre... hmm?");
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
                endGameLine.add("Vous etes... tres vert...");
                break;
            case "goldninja":
                endGameLine.add("Vous avez reussi... vous l'avez fait");
                break;
            case "master":
                endGameLine.add("Je suis fier de vous...");
                break;
            case "moine":
                endGameLine.add("Haha! J'aime beaucoup votre style! Feliciations!");
                break;
            case "spectre":
                endGameLine.add("Mon instinct ne donc pas trompé");
                break;
            case "vieux":
                endGameLine.add("Je vois! Je vois! TENEZ DONC ! Mon trésor est a l'ouest du pont au nord-ouest de la ville!");
                break;
            case "villageois1":
                endGameLine.add("Merci enormement... vous voulez du bois peut-etre ?");
                break;
            case "villageois2":
                endGameLine.add("Je.. je... merci....");
                break;
            case "villageois3":
                endGameLine.add("Que m'arrive-t-il ? c'est... coloré...");
                break;
            case "villageois4":
                endGameLine.add("Je... je suis desole, je suis... merci, merci enormement.");
                break;
            case "villageois5":
                endGameLine.add("Kyahahaha! Vous plutot etes mignon quand on vous voit! ");
                break;
            default:
                break;
        }
        return endGameLine;
    }
}
