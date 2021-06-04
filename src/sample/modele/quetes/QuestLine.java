package sample.modele.quetes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.modele.Joueur;
import sample.modele.items.Objets.Potion;

import java.util.ArrayList;
import java.util.Observable;

public class QuestLine {
    private ObservableList<Quete> quetes;

    public QuestLine(){
        quetes = FXCollections.observableArrayList();
        quetes.add(new Quete("Chapitre 1 - Sku", "Tue le", "KILL", 100, "Potion", 1));
    }

    public ObservableList<Quete> getQuetes() {
        return quetes;
    }

    public void completeQuest(Joueur joueur){
        joueur.getInventaire().ajouterObjet(getQueteActuelle().getRecompenseObjet(), getQueteActuelle().getQuantiteObjet());
        joueur.getInventaire().ajouterOr(getQueteActuelle().getRecompenseOr());
        quetes.remove(quetes.size()-1);
    }

    public Quete getQueteActuelle(){
        return quetes.get(quetes.size()-1);
    }
}
