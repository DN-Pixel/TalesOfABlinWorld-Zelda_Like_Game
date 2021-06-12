package sample.vue;

import javafx.scene.control.Label;
import sample.modele.items.Armes.ShopInventory;

public class ArmeDescriptionSwitcher {
    private Label armeDescription;
    public ArmeDescriptionSwitcher(Label armeDescription){
        this.armeDescription=armeDescription;
    }

    public void switchArmeDescription (String nom, ShopInventory shopInventory){
        armeDescription.setText(shopInventory.getArmeDescritpion(nom));
    }
}
