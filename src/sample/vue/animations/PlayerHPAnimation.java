package sample.vue.animations;

import javafx.scene.image.ImageView;
import sample.modele.Joueur;
import sample.vue.ImageMap;

public class PlayerHPAnimation {
    public static void initAnimation(Joueur joueur, ImageView hpBar, ImageMap imageMap) {
        joueur.getHp().addListener(e -> {
            hpBar.setImage(imageMap.getImage((int)((double)(joueur.getHp().getValue())/(double)(joueur.getMaxHP())*10)+"HP"));
        });
    }
}
