package sample.modele.ressources;

import sample.modele.items.Objets.MineraiBrut;

public class SourceMinerai extends Ressource{

    public SourceMinerai(int posX, int posY) {
        super(posX, posY);
        super.setQuantite((int)(Math.random()*5)+1);
        super.setRecompense("MineraiBrut");
    }
}
