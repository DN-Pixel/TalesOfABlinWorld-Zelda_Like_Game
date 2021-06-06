package sample.vue;

import javafx.scene.control.TextArea;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Console {
    private TextArea console;
    private static String star = "**";

    public Console(TextArea console){
        this.console=console;
    }

    public String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        return "("+formatter.format(date)+")";
    }

    public void afficherDegatsRecus(int degats){
        console.appendText("\n"+star+" Vous avez perdu " + degats + " points de vie ! "+ getTime() );
        setStar();
    }
    public void afficherDegatsInfliges(int degats){
        console.appendText("\n"+star+" Vous avez infligé " + degats + " dégâts ! "+ getTime());
        setStar();
    }
    public void afficherZoneActuelle(String numeroZone){
        console.appendText("\nBienvenue en zone " + numeroZone +" ! "+ getTime());
    }
    public void afficherMort(){
        console.appendText("\nVous êtes mort(e) ! "+ getTime());
    }

    public void afficherItemIndisponible(String itemManquant){
        console.appendText("\nVous n'avez plus de "+itemManquant + " ! "+ getTime());
    }

    public void afficherHeal(int healAmount){
        console.appendText("\nVous vous soignez de " + healAmount + " HP ! "+ getTime());
    }
    public void afficherErreurConsommable(){
        console.appendText("\n"+star+"Veuillez selectionner un consommable");
        setStar();
    }
    public void afficherErreurShopNonSelected(){
        console.appendText("\n"+star+"Veuillez selectionner un objet à acheter");
        setStar();
    }
    public void afficherErreurDeSaisie(){
        console.appendText("\n" +star +" Veuillez saisir une quantité valide!");
        setStar();
    }
    public void afficherArgentManquant(){
        console.appendText("\nVous n'avez pas assez d'argent");
    }
    public void afficherGainArgent(int gain){
        if (Math.random()<0.05)
            console.appendText("\nVous empochez "+gain+" BITCOINS !?");
        else
            console.appendText("\nVous empochez "+gain+" or!");
    }
    public void afficherPerteArgent(int perte){
        console.appendText("\nVous voila deboursé de "+perte+" or!");
    }


//permet de différencier les messages identiques successifs dans la console.
    public static void setStar(){
        if (star.equals("**"))
            star="*";
        else
            star="**";
    }
    public void afficherItemRecup(String item, int quantite){
        console.appendText("\nVous avez trouver "+quantite+" "+item+"(s) ! "+ getTime());
    }
}
