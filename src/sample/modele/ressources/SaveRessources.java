package sample.modele.ressources;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.modele.acteurs.Acteur;
import sample.modele.acteurs.ennemis.Bete;
import sample.modele.acteurs.ennemis.Hibou;
import sample.modele.acteurs.ennemis.Oeil;
import sample.modele.acteurs.ennemis.Slime;

import java.util.HashMap;
import java.util.Map;

public class SaveRessources {

    // PERMET DE SAUVEGARDER LES RESSOURCES D'UNE MAP

    private Map<Integer, ObservableList<Ressource>> saveListesRessources = new HashMap<>();

    public SaveRessources(){
        genererSaveZone1();
        genererSaveZone2();
        genererSaveZone3();
        genererSaveZone4();
    }

    public ObservableList<Ressource> getSave(int numeroDeZone){
        return saveListesRessources.get(numeroDeZone);
    }

    public void genererSaveZone1(){
        ObservableList<Ressource> save = FXCollections.observableArrayList();
        saveListesRessources.put(1, save);
    }

    public void genererSaveZone2(){
        ObservableList<Ressource> save = FXCollections.observableArrayList();
        save.add(new SourceTresor(19*16, 27*16));
        saveListesRessources.put(2, save);
    }

    private void genererSaveZone3() {
        ObservableList<Ressource> save = FXCollections.observableArrayList();
        saveListesRessources.put(3, save);
    }

    private void genererSaveZone4() {
        ObservableList<Ressource> save = FXCollections.observableArrayList();
        save.add(new SourceTresor(6*16, 27*16));
        save.add(new SourceTresor(33*16, 30*16));
        save.add(new SourceTresor(39*16, 33*16));
        saveListesRessources.put(4, save);
    }
}


