package sample.vue.modeleVue;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import sample.modele.Joueur;
import sample.modele.acteurs.Acteur;
import sample.modele.acteurs.Pnj;

public class DialogueVue {
    private Label label;
    private Pane pane;
    private Joueur joueur;
    public static long tempsDebutDialogue;
    public DialogueVue(Label label, Pane pane, Joueur joueur){
        this.label=label;
        this.pane=pane;
        this.joueur=joueur;
    }

    public void debutDialogueTimeSetter(long temps){
        tempsDebutDialogue = temps;
    }
    public void checkDialogueTimerOut(long tempsActuel){
        //si on reste 5 seconde la fenetre de dialogue disparait.
        if (tempsActuel == tempsDebutDialogue+200)
           pane.setVisible(false);
    }

    public void afficherDialogue(Acteur a){
        //partie visibilité
        if(this.pane.isVisible())
            this.pane.setVisible(false);
        else
            this.pane.setVisible(true);
        //partie set text

        if (!joueur.getListeQuetes().getQueteActuelle().getTitre().startsWith("Felicitation")) {
            this.label.setText(((Pnj) a).getRepliques().get((int)(Math.random()*3)));
        }
        else{ this.label.setText(((Pnj) a).getRepliquesFinDuJeu().get(0)); }
    }
}

