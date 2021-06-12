package sample.vue.modeleVue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import sample.controleur.SoundPlayer;
import sample.modele.Joueur;



public class HUDVue {
    private Label arme;
    private Label range;
    private Label atk;
    private Label armeDistance;
    private Joueur j1;
    public HUDVue(Label arme, Label range, Label attaque, Label armeDistance, Joueur joueur){
        this.arme=arme;
        this.range=range;
        this.atk=attaque;
        this.armeDistance=armeDistance;
        this.j1=joueur;
    }

    public void statMenuManager(Pane menuPane){
        if(menuPane.isVisible()) {
            menuPane.setVisible(false);
            SoundPlayer.playSpecificSound("statsClose.wav");
        }
        else{
            menuPane.setVisible(true);
            SoundPlayer.playSpecificSound("statsOpen.wav");
        }
        arme.setText(j1.getArme().getNom());
        range.setText(""+j1.getArme().getRange());
        atk.setText(""+j1.getArme().getDegatsArme());
        if(j1.getArmeDistance()==null) armeDistance.setText("Aucune");
        else armeDistance.setText("Shuriken");
    }

    private double opacity = 1;
    public void commencer (Pane start) {
        Timeline loadingMenu = new Timeline();
        loadingMenu.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.06),
                (ev ->{
                    opacity -= 0.1;
                    start.setOpacity(opacity);
                    if(opacity<=0){
                        loadingMenu.stop();
                        start.setVisible(false);
                    }
                })
        );
        loadingMenu.getKeyFrames().add(kf);
        loadingMenu.play();
    }
}
