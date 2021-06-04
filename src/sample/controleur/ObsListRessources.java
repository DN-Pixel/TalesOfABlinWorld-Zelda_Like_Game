package sample.controleur;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import sample.modele.ressources.Ressource;
import sample.vue.modeleVue.RessourceVue;

import java.util.List;

public class ObsListRessources implements ListChangeListener<Ressource> {

    private RessourceVue ressourceVue;

    public ObsListRessources(Pane p){
        ressourceVue = new RessourceVue(p);
    }

    @Override
    public void onChanged(Change<? extends Ressource> list) {
        List<? extends Ressource> listAdded;
        List<? extends Ressource> listRemoved;
        while (list.next()){
            listAdded = list.getAddedSubList();
            listRemoved = list.getRemoved();
            for (Ressource r : listAdded){
                ressourceVue.creerRessourceSprite(r);
            }
            for (Ressource r : listRemoved){
                ressourceVue.removeRessourceFromPane(r);
            }
        }
    }
}
