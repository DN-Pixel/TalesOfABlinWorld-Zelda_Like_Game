package sample.vue;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class imageMap {

    private Map<String, Image> map = new HashMap<>();

    public imageMap(){
        map.put("empty", new Image("sample/ressources/empty.png"));
        map.put("Slime", new Image("sample/ressources/ennemis/slime.png"));
        map.put("Bambou", new Image("sample/ressources/ennemis/Bambou.png"));
        map.put("Bete", new Image("sample/ressources/ennemis/bete.png"));
        map.put("Oeil", new Image("sample/ressources/ennemis/Oeil.png"));
        map.put("Hibou", new Image("sample/ressources/ennemis/Hibou.png"));
        map.put("Reptile", new Image("sample/ressources/ennemis/reptile.png"));
        map.put("Boss", new Image("sample/ressources/ennemis/boss.png"));

        map.put("Enfant", new Image("sample/ressources/pnj/kid.png"));
        map.put("Cavegirl", new Image("sample/ressources/pnj/cavegirl.png"));
        map.put("goldninja", new Image("sample/ressources/pnj/goldninja.png"));
        map.put("master", new Image("sample/ressources/pnj/master.png"));
        map.put("moine", new Image("sample/ressources/pnj/moine.png"));
        map.put("spectre", new Image("sample/ressources/pnj/spectre.png"));
        map.put("upgrader", new Image("sample/ressources/pnj/upgrader.png"));
        map.put("vendeur", new Image("sample/ressources/pnj/vendeur.png"));
        map.put("vieux", new Image("sample/ressources/pnj/vieux.png"));
        map.put("villageois1", new Image("sample/ressources/pnj/villageois1.png"));
        map.put("villageois2", new Image("sample/ressources/pnj/villageois2.png"));
        map.put("villageois3", new Image("sample/ressources/pnj/villageois3.png"));
        map.put("villageois4", new Image("sample/ressources/pnj/villageois4.png"));
        map.put("villageois5", new Image("sample/ressources/pnj/villageois5.png"));
    }

    public Map<String, Image> getMap() {
        return map;
    }

    public Image getImage(String name){
        return map.get(name);
    }
}
