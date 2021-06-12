package sample.vue.modeleVue;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sample.modele.acteurs.Acteur;
import sample.modele.acteurs.Pnj;
import sample.modele.acteurs.ennemis.Ennemi;
import sample.vue.ImageMap;

public class ActeurVue {

    private ImageMap imageMap = new ImageMap();
    private Pane pane;

    public ActeurVue(Pane pane){
        this.pane = pane;
    }

    public void createSprite(Acteur a){
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

    public void removeSprite(Acteur a) {
        pane.getChildren().remove(pane.lookup("#"+a.getId()));
    }
}
