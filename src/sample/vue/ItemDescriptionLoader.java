package sample.vue;

import javafx.event.Event;
import javafx.scene.control.Label;
import sample.modele.items.Inventaire;

public class ItemDescriptionLoader {
    private Label description;

    public ItemDescriptionLoader(Label description){ this.description=description; }

    public void refreshDescription(String clicSource, Inventaire inventaire){

        switch (clicSource){
            case "inventoryArgent" :
                description.setText(inventaire.getListObjet().get(0).getDescription());
                break;
            case "inventoryBois" :
                description.setText(inventaire.getListObjet().get(1).getDescription());
                break;
            case "inventoryDiamant" :
                description.setText(inventaire.getListObjet().get(2).getDescription());
            break;
            case "inventoryFer" :
                description.setText(inventaire.getListObjet().get(3).getDescription());
                break;
            case "inventoryMiel" :
                description.setText(inventaire.getListObjet().get(4).getDescription());
                break;
            case "inventoryMineraiBrut" :
                description.setText(inventaire.getListObjet().get(5).getDescription());
                break;
            case "inventoryNoodle" :
                description.setText(inventaire.getListObjet().get(6).getDescription());
                break;
            case "inventoryPotion" :
                description.setText(inventaire.getListObjet().get(7).getDescription());
                break;
            case "inventoryBeef" :
                description.setText(inventaire.getListObjet().get(8).getDescription());
                break;
            default:
                description.setText("Inventory");
                break;
        }
    }
}
