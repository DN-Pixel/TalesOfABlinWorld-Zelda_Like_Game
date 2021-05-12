package sample;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class imageMap {

    private Map<Integer, Image> map = new HashMap<>();

    public imageMap(){
        map.put(-1, new Image("sample/ressources/empty.png"));
    }

    public Map<Integer, Image> getMap() {
        return map;
    }

    public Image getImage(int index){
        return map.get(index);
    }
}
