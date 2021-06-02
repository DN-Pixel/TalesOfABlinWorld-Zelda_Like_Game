package sample.controleur;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import sample.modele.acteurs.Acteur;
import sample.vue.modeleVue.ActeurVue;
import sample.vue.ImageMap;

import java.util.List;

public class ObsListActeurs implements ListChangeListener<Acteur> {

    private Pane pane;
    private ImageMap imageMap = new ImageMap();
    private ActeurVue acteurVue;

    public ObsListActeurs(Pane p){
        pane = p;
        acteurVue = new ActeurVue(p);
    }

    @Override
    public void onChanged(Change<? extends Acteur> c) {
        List<? extends Acteur> listAjoutes ;
        List<? extends Acteur> listEnleves ;
        while (c.next()){
            listAjoutes = c.getAddedSubList();
            listEnleves = c.getRemoved();
            for(Acteur a : listAjoutes){
                acteurVue.createSprite(a);
            }
            for(Acteur a : listEnleves){
                acteurVue.removeSprite(a);
            }
        }
    }

}
