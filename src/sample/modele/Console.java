package sample.modele;

import javafx.scene.control.TextArea;
import sample.modele.items.InventaireException;
import sample.vue.modeleVue.ConsoleVue;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Console {
    ConsoleVue console;

    public Console(){
    }

    public void setConsoleVue(ConsoleVue console) {
        this.console = console;
    }

    public void afficherDegatsRecus(int degats){
        console.afficherDegatsRecus(degats);
    }
    public void afficherDegatsInfliges(int degats){
        console.afficherDegatsInfliges(degats);
    }
    public void afficherZoneActuelle(String numeroZone){
        console.afficherZoneActuelle(numeroZone);
    }
    public void afficherMort(){
        console.afficherMort();
    }

    public void afficherItemIndisponible(String itemManquant){
        console.afficherItemIndisponible(itemManquant);
    }

    public void afficherHeal(int healAmount){
        console.afficherHeal(healAmount);
    }
    public void afficherErreurConsommable(){
        console.afficherErreurConsommable();
    }
    public void afficherErreurShopNonSelected(){
        console.afficherErreurShopNonSelected();
    }
    public void afficherErreurDeSaisie(){
        console.afficherErreurDeSaisie();
    }
    public void afficherArgentManquant(){
        console.afficherArgentManquant();
    }
    public void afficherGainArgent(int gain){
        console.afficherGainArgent(gain);
    }
    public void afficherPerteArgent(int perte){
        console.afficherPerteArgent(perte);
    }
    public void afficherLvlUp(){
        console.afficherLvlUp();
    }


    public void afficherItemRecup(String item, int quantite){
        console.afficherItemRecup(item, quantite);
    }

    public void afficherMineraiCrafted(String item) {
        console.afficherItemCrafted(item);
    }

    public void afficherErreurArmeNotSelected() {
        console.afficherErreurArmeNotSelected();
    }
}
