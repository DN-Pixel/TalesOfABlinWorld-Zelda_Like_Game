package sample.modele.acteurs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import sample.modele.acteurs.ennemis.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SaveActeurs {
    /*
    PERMET DE SAUVEGARDER LA LISTE DES ACTEURS (ENNEMIS + PNJ + QUETES)
    -> PERMET DE NE PAS LES PERDRE EN CHANGEANT DE MAP
     */

    private Map<Integer, ObservableList<Acteur>> savesListesActeurs = new HashMap<>();

    public Map<Integer, ObservableList<Acteur>> getSavesListesActeurs() {
        return savesListesActeurs;
    }

    public SaveActeurs(){
        genererSaveZone0();
        genererSaveZone1();
        genererSaveZone2();
        genererSaveZone3();
        genererSaveZone4();
        genererSaveZone5();
        genererSaveZone6();
        genererSaveZone7();
    }

    public ObservableList<Acteur> getSave(int numeroDeZone){
        return savesListesActeurs.get(numeroDeZone);
    }

    public void genererSaveZone0(){
        ObservableList<Acteur> save = FXCollections.observableArrayList();
        save.add(new Pnj(11*16, 2*16, "spectre"));
        savesListesActeurs.put(0, save);
    }

    public void genererSaveZone1(){
        ObservableList<Acteur> save = FXCollections.observableArrayList();
        save.add(new Pnj(37*16, 10*16, "vendeur"));
        save.add(new Pnj(21*16, 18*16, "upgrader"));
        save.add(new Pnj(28*16, 5*16, "master"));
        save.add(new Pnj(13*16, 15*16, "villageois4"));
        save.add(new Pnj(11*16, 5*16, "villageois1"));
        save.add(new Pnj(4*16, 19*16, "villageois2"));
        save.add(new Pnj(22*16, 12*16, "kid"));
        save.add(new Pnj(31*16, 11*16, "vieux"));
        save.add(new Pnj(38*16, 4*16, "villageois5"));
        savesListesActeurs.put(1, save);
    }

    public void genererSaveZone2(){
        ObservableList<Acteur> save = FXCollections.observableArrayList();
        save.add(new Slime(16*15, 16*12));
        save.add(new Hibou(16*5, 16*15));
        save.add(new Hibou(16*15, 16*28));
        save.add(new Slime(16*5, 16*25));
        save.add(new Slime(16*13, 16*16));
        save.add(new Hibou(16*6, 16*19));
        savesListesActeurs.put(2, save);
    }

    private void genererSaveZone3() {
        ObservableList<Acteur> save = FXCollections.observableArrayList();
        save.add(new Bambou(16*20, 16*20));
        save.add(new Reptile(16*32, 16*26));
        save.add(new Reptile(16*34, 16*26));
        save.add(new Bambou(16*8, 16*28));
        save.add(new Reptile(16*8, 16*30));
        save.add(new Bambou(16*16, 16*10));
        save.add(new Reptile(16*33, 16*13));
        save.add(new Bambou(16*33, 16*5));
        save.add(new Pnj(32*16,36*16,"cloud"));
        savesListesActeurs.put(3, save);
    }

    private void genererSaveZone4() {
        ObservableList<Acteur> save = FXCollections.observableArrayList();
        save.add(new Pnj(16*35, 16*7, "moine"));
        save.add(new Pnj(37*16, 33*16, "goldninja"));
        save.add(new Oeil(16*29, 16*18));
        save.add(new Oeil(16*23, 16*17));
        save.add(new Bete(16*13, 16*6));
        save.add(new Bete(16*3, 16*21));
        save.add(new Oeil(16*13, 16*29));
        save.add(new Oeil(16*2, 16*9));
        save.add(new Oeil(16*6, 16*34));
        save.add(new Bete(16*23, 16*37));
        save.add(new Oeil(16*37, 16*34));
        save.add(new Oeil(16*37, 16*38));
        save.add(new Bete(16*3, 16*8));
        savesListesActeurs.put(4, save);
    }

    private void genererSaveZone5(){
        ObservableList<Acteur> save = FXCollections.observableArrayList();
        save.add(new Pnj(16, 16*33,"cavegirl"));
        save.add(new Pnj(3*16, 37*16, "villageois3"));
        save.add(new Bambou(16*13, 16*19));
        save.add(new Bambou(16*16, 16*19));
        save.add(new Reptile(13*16,7*16));
        save.add(new Reptile(16*16,7*16));
        save.add(new Pnj(23*16,21*16,"chocobo"));
        savesListesActeurs.put(5, save);
    }

    private void genererSaveZone6(){
        ObservableList<Acteur> save = FXCollections.observableArrayList();
        save.add(new EnnemiBoss(14*16, 16*2));
        savesListesActeurs.put(6, save);
    }
    private void genererSaveZone7(){
        ObservableList<Acteur> save = FXCollections.observableArrayList();
        save.add(new Pnj(16,8*16,"luigi"));
        save.add(new Pnj(16,14*16,"panneau"));
        save.add(new Pnj(11*16,3*16,"épouvantail"));
        savesListesActeurs.put(7, save);
    }
}
