package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;

public class imageMap {

    private Map<Integer, Image> map = new HashMap<>();

    public imageMap(){
        map.put(-1, new Image("sample/ressources/empty.png"));
        map.put(244, new Image("sample/ressources/floor.png"));
        map.put(996, new Image("sample/ressources/bambouzel.png"));
        map.put(990, new Image("sample/ressources/fleurJ.png"));
        map.put(161, new Image("sample/ressources/buisson.png"));
    }

    public Map<Integer, Image> getMap() {
        return map;
    }

    public Image getImage(int index){
        return map.get(index);
    }
}
