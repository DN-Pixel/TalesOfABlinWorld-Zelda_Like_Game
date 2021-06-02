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

    public SaveActeurs(){
        genererSaveZone1();
        genererSaveZone2();
        genererSaveZone3();
        genererSaveZone4();
    }

    public ObservableList<Acteur> getSave(int numeroDeZone){
        return savesListesActeurs.get(numeroDeZone);
    }

    public void genererSaveZone1(){
        ObservableList<Acteur> save = FXCollections.observableArrayList();
        savesListesActeurs.put(1, save);
    }

    public void genererSaveZone2(){
        ObservableList<Acteur> save = FXCollections.observableArrayList();
        save.add( new Slime(16*15, 16*12));
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
        savesListesActeurs.put(3, save);
    }

    private void genererSaveZone4() {
        ObservableList<Acteur> save = FXCollections.observableArrayList();
        save.add(new Oeil(16*29, 16*18));
        save.add(new Oeil(16*23, 16*13));
        save.add(new Bete(16*13, 16*6));
        save.add(new Bete(16*3, 16*21));
        save.add(new Oeil(16*6, 16*34));
        save.add(new Bete(16*23, 16*37));
        save.add(new Oeil(16*37, 16*34));
        save.add(new Oeil(16*37, 16*38));
        save.add(new Bete(16*3, 16*8));
        savesListesActeurs.put(4, save);
    }
}
