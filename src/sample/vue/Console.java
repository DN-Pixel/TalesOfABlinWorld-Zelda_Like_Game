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

    public String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        return "("+formatter.format(date)+")";
    }

    public void afficherDegatsRecus(int degats){
        console.appendText("\n"+star+" Vous avez perdu " + degats + " points de vie ! "+ getDate() );
        setStar();
    }
    public void afficherDegatsInfliges(int degats){
        console.appendText("\n"+star+" Vous avez infligé " + degats + " dégâts ! "+getDate());
        setStar();
    }
    public void afficherZoneActuelle(String numeroZone){
        console.appendText("\nBienvenue en zone " + numeroZone +" ! "+getDate());
    }
    public void afficherMort(){
        console.appendText("\nVous êtes mort(e) ! "+getDate());
    }

    public void afficherItemIndisponible(String itemManquant){
        console.appendText("\nVous n'avez plus de "+itemManquant + " ! "+getDate());
    }

    public void afficherHeal(int healAmount){
        console.appendText("\nVous vous soignez de " + healAmount + " HP ! "+getDate());
    }

//permet de différencier les messages identiques successifs dans la console.
    public static void setStar(){
        if (star.equals("**"))
            star="*";
        else
            star="**";
    }

    public void afficherItemRecup(String item, int quantite){
        console.appendText("\nVous avez trouver "+quantite+" "+item+"(s) ! "+getDate());
    }
}
