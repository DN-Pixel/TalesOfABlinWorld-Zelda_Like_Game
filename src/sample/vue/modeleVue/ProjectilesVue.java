package sample.vue.modeleVue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sample.modele.Projectile;

public class ProjectilesVue {
    private Pane gamePane;

    private Image imageBambouBullet = new Image("sample/ressources/ennemis/bambouBullet.png");
    private Image imageOeilBullet = new Image("sample/ressources/ennemis/oeilBullet.png");
    private Image imageShuriken = new Image("sample/ressources/armes/shuriken.png");
    private Image imageBossBullet = new Image("sample/ressources/armes/bossBullet.png");

    public ProjectilesVue (Pane gamePane) {
        this.gamePane = gamePane;
    }

    public void creerBulletSprite(Projectile p){
        ImageView i = new ImageView();
        if (p.getOrigine().equals("Bambou"))
            i.setImage(imageBambouBullet);
        else if(p.getOrigine().equals("Oeil"))
            i.setImage(imageOeilBullet);
        else if(p.getOrigine().equals("EnnemiBoss"))
            i.setImage(imageBossBullet);
        else
            i.setImage(imageShuriken);
        i.setId(p.getId());
        i.setLayoutX(p.getX());
        i.setLayoutY(p.getY());
        i.layoutXProperty().bind(p.xProperty());
        i.layoutYProperty().bind(p.yProperty());
        gamePane.getChildren().add(i);
    }
    public void removeBulletFromPane(Projectile p){
        gamePane.getChildren().remove(gamePane.lookup("#"+p.getId()));
    }
}
