package sample.vue.animations;

import javafx.scene.image.ImageView;
import sample.vue.ImageMap;

public class ShurikenAnimation {
    public static void animate(long temps, ImageView n, ImageMap imageMap){
        switch ((int) (temps%16)){
            case 0:
                n.setImage(imageMap.getImage("shuriken"));
                break;
            case 2:
                n.setImage(imageMap.getImage("shuriken2"));
                break;
            case 4:
                n.setImage(imageMap.getImage("shuriken3"));
                break;
            case 6:
                n.setImage(imageMap.getImage("shuriken4"));
                break;
            case 8:
                n.setImage(imageMap.getImage("shuriken5"));
                break;
            case 10:
                n.setImage(imageMap.getImage("shuriken6"));
                break;
            case 12:
                n.setImage(imageMap.getImage("shuriken7"));
                break;
            case 14:
                n.setImage(imageMap.getImage("shuriken8"));
                break;
        }

    }
}
