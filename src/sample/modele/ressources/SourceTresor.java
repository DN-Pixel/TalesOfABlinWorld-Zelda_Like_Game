package sample.modele.ressources;

import sample.modele.items.Objets.Argent;
import sample.modele.items.Objets.Diamant;
import sample.modele.items.Objets.Fer;
import sample.modele.items.Objets.MineraiBrut;

public class SourceTresor extends Ressource{
    public SourceTresor(int posX, int posY) {
        super(posX, posY);
        double x = Math.random();
        if(x<=0.1){
            super.setRecompense("Diamant");
            super.setQuantite((int)(Math.random()*5)+1);
        }
        else if(x<0.3){
            super.setRecompense("Argent");
            super.setQuantite((int)(Math.random()*5)+1);
        }else if(x<0.6){
            super.setRecompense("Fer");
            super.setQuantite((int)(Math.random()*8)+1);
        }
        else {
            super.setRecompense("MineraiBrut");
            super.setQuantite((int)(Math.random()*3)+1);
        }
    }
}
