package sample.vue;

import javafx.scene.control.Label;
import sample.modele.items.Inventaire;

public class ItemDescriptionSwitcher {
    private Label description;
    public ItemDescriptionSwitcher(Label description){ this.description=description; }
    public void switchDescription(String clicSource, Inventaire inventaire){
            description.setText(inventaire.getListObjet().get(inventaire.trouverObjet(clicSource)).getDescription());
    }
}
