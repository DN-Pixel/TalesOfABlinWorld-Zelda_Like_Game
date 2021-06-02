package sample.vue;

import javafx.scene.control.TextArea;
import sample.modele.Joueur;

public class Console {
    private TextArea console;
    private static String star = "**";

    public Console(TextArea console){
        this.console=console;
    }

    public void afficherDegatsRecus(int degats){
        console.appendText("\n"+star+" Vous avez perdu " + degats + " points de vie!");
        setStar();
    }
    public void afficherDegatsInfliges(int degats){
        console.appendText("\n"+star+" Vous avez infligé " + degats + " dégâts! ");
        setStar();
    }
    public void afficherZoneActuelle(String numeroZone){
        console.appendText("\nBienvenue en zone " + numeroZone +" !");
    }
    public void afficherMort(){
        console.appendText("\nVous êtes mort(e) !");
    }

    public void afficherItemIndisponible(String itemManquant){
        console.appendText("\n"+star+"Vous n'avez plus de "+itemManquant + "!");
        setStar();
    }

    public void afficherHeal(int healAmount){
        console.appendText("\n"+star+"Vous vous soignez de " + healAmount + " HP!");
        setStar();
    }

//permet de différencier les messages identiques successifs dans la console.
    public static void setStar(){
        if (star.equals("**"))
            star="*";
        else
            star="**";
    }

    public void afficherItemRecup(String item, int quantite){
        console.appendText("\nVous avez trouver "+quantite+" "+item+"(s) !");
    }
}
