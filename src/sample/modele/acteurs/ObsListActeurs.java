package sample.modele.acteurs;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sample.modele.acteurs.ennemis.Ennemi;
import sample.vue.imageMap;

import java.awt.*;
import java.util.List;

public class ObsListActeurs implements ListChangeListener<Acteur> {

    private Pane pane;
    private sample.vue.imageMap imageMap = new imageMap();

    public ObsListActeurs(Pane p){
        pane = p;
    }

    private void createSprite(Acteur a){
        if(a instanceof Ennemi){
            ImageView sprite = new ImageView(imageMap.getImage(a.getClass().getSimpleName())); // récupère l'image de l'ennemi correspondant
            sprite.setTranslateX(a.getX());
            sprite.setTranslateY(a.getY());
            sprite.setId(a.getId());
            sprite.translateXProperty().bind(a.getXProperty());
            sprite.translateYProperty().bind(a.getYProperty());
            pane.getChildren().add(sprite);
        }
    }

    private void removeSprite(Acteur a) {
        pane.getChildren().remove(pane.lookup("#"+a.getId()));
    }

    @Override
    public void onChanged(Change<? extends Acteur> c) {
        List<? extends Acteur> listAjoutes ;
        List<? extends Acteur> listEnleves ;
        while (c.next()){
            listAjoutes = c.getAddedSubList();
            listEnleves = c.getRemoved();
            for(Acteur a : listAjoutes){
                createSprite(a);
            }
            for(Acteur a : listEnleves){
                removeSprite(a);
            }
        }
    }

}
