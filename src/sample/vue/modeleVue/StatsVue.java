package sample.vue.modeleVue;

import javafx.scene.control.Label;
import sample.modele.Joueur;



public class StatsVue {
    private Label arme;
    private Label range;
    private Label atk;
    private Label armeDistance;
    private Joueur j1;
    public StatsVue(Label arme, Label range, Label attaque, Label armeDistance, Joueur joueur){
        this.arme=arme;
        this.range=range;
        this.atk=attaque;
        this.armeDistance=armeDistance;
        this.j1=joueur;
    }

    public void switchWeeponDesc(){
        arme.setText(j1.getArme().getNom());
        range.setText(""+j1.getArme().getRange());
        atk.setText(""+j1.getArme().getDegatsArme());
        if(j1.getArmeDistance()==null)
            armeDistance.setText("pas d'arme distance");
        else
            armeDistance.setText("Shuriken");
    }
}
