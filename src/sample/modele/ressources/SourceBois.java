package sample.modele.ressources;

import sample.modele.items.Objets.Bois;
import sample.modele.items.Objets.Objet;

public class SourceBois extends Ressource{
    public SourceBois(int posX, int posY) {
        super(posX, posY);
        super.setQuantite((int)(Math.random()*9)+1);
        super.setRecompense("Bois");
    }
}
