package sample.modele.acteurs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import sample.modele.acteurs.ennemis.Slime;

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
        Acteur ennemi1 = new Slime(100, 100);
        save.add(ennemi1);
        savesListesActeurs.put(2, save);
    }
}
