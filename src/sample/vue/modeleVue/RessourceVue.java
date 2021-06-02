package sample.vue.modeleVue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sample.modele.ressources.Ressource;
import sample.modele.ressources.SourceBois;
import sample.modele.ressources.SourceMinerai;

public class RessourceVue {
    private Pane gamePane;

    private Image coffre = new Image("sample/ressources/ressourcesImg/coffre.png");
    private Image bois = new Image("sample/ressources/ressourcesImg/bois.png");
    private Image minerai = new Image("sample/ressources/ressourcesImg/minerai.png");

    public RessourceVue (Pane gamePane) {
        this.gamePane = gamePane;
    }

    public void creerRessourceSprite(Ressource ressource){
        ImageView img;
        if(ressource instanceof SourceBois)
             img = new ImageView(bois);
        else if(ressource instanceof SourceMinerai)
            img = new ImageView(minerai);
        else
            img = new ImageView(coffre);
        img.setId(ressource.getId());
        img.setLayoutX(ressource.getX());
        img.setLayoutY(ressource.getY());
        gamePane.getChildren().add(img);
    }
    public void removeRessourceFromPane(Ressource ressource){
        gamePane.getChildren().remove(gamePane.lookup("#"+ressource.getId()));
    }

}
