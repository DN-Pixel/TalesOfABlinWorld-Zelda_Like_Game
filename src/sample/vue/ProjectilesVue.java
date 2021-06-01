package sample.vue;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sample.controleur.ObsListActeurs;
import sample.modele.Projectiles;
import sample.modele.acteurs.ennemis.Bambou;

public class ProjectilesVue {
    private Pane gamePane;

    private Image imageBambouBullet = new Image("sample/ressources/ennemis/bambouBullet.png");
    private Image imageOeilBullet = new Image("sample/ressources/ennemis/oeilBullet.png");

    public ProjectilesVue (Pane gamePane) {
        this.gamePane = gamePane;
    }

    public void creerBulletSprite(Projectiles p){
        ImageView i = new ImageView();
        if (p.getId().startsWith("Bambou"))
            i.setImage(imageBambouBullet);
        else
            i.setImage(imageOeilBullet);
        i.setId(p.getId());
        i.setLayoutX(p.getX());
        i.setLayoutY(p.getY());
        i.layoutXProperty().bind(p.xProperty());
        i.layoutYProperty().bind(p.yProperty());
        gamePane.getChildren().add(i);
    }
    public void removeBulletFromPane(Projectiles p){
        gamePane.getChildren().remove(gamePane.lookup("#"+p.getId()));
    }
}
