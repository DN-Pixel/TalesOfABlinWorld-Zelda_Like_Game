package sample.vue.animations;

import javafx.scene.image.ImageView;
import sample.modele.Joueur;
import sample.vue.ImageMap;


public class PlayerMovementAnimation {

    public static void initAnimation(ImageView playerImage, Joueur joueur, ImageMap imageMap){
        joueur.getxProperty().addListener(e ->{
                if(joueur.getDirection().equals("up"))
                    playerImage.setImage(imageMap.getImage("playerUP"));
                else if(joueur.getDirection().equals("down"))
                    playerImage.setImage(imageMap.getImage("playerDOWN"));
        });
        joueur.getyProperty().addListener(e ->{
            if(joueur.getDirection().equals("right"))
                playerImage.setImage(imageMap.getImage("playerRIGHT"));
            else if(joueur.getDirection().equals("left"))
                playerImage.setImage(imageMap.getImage("playerLEFT"));
        });
    }

}
