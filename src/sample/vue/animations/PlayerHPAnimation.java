package sample.vue.animations;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import sample.modele.Joueur;
import sample.vue.ImageMap;

public class PlayerHPAnimation {
    public static void initAnimation(Joueur joueur, ImageView hpBar, ImageMap imageMap, Label pourcentage) {
        joueur.getHp().addListener(e -> {
            //hpBar.setImage(imageMap.getImage((int)((double)(joueur.getHp().getValue())/(double)(joueur.getMaxHP())*10)+"HP"));
            hpBar.setScaleX(((double)joueur.getHp().getValue())/(double)(joueur.getMaxHP()));
            pourcentage.setText(((double)joueur.getHp().getValue()/(double)(joueur.getMaxHP())*100)+"%");
        });
    }
}
