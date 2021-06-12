package sample.vue;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class ImageMap {

    private Map<String, Image> map = new HashMap<>();

    public ImageMap(){
        map.put("empty", new Image("sample/ressources/empty.png"));
        map.put("Slime", new Image("sample/ressources/ennemis/slime.png"));
        map.put("Bambou", new Image("sample/ressources/ennemis/Bambou.png"));
        map.put("Bete", new Image("sample/ressources/ennemis/bete.png"));
        map.put("Oeil", new Image("sample/ressources/ennemis/Oeil.png"));
        map.put("Hibou", new Image("sample/ressources/ennemis/Hibou.png"));
        map.put("Reptile", new Image("sample/ressources/ennemis/reptile.png"));
        map.put("EnnemiBoss", new Image("sample/ressources/ennemis/boss.png"));

        map.put("kid", new Image("sample/ressources/pnj/kid.png"));
        map.put("cavegirl", new Image("sample/ressources/pnj/cavegirl.png"));
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
        map.put("chocobo",new Image("sample/ressources/pnj/chocobo.png"));
        map.put("luigi",new Image("sample/ressources/pnj/luigi.png"));
        map.put("panneau",new Image("sample/ressources/pnj/panneau.png"));
        map.put("cloud",new Image("sample/ressources/pnj/cloud.png"));
        map.put("épouvantail",new Image("sample/ressources/pnj/epouvantail.png"));

        map.put("playerDOWN", new Image("sample/ressources/player/playerDOWN.png"));
        map.put("playerUP", new Image("sample/ressources/player/playerUP.png"));
        map.put("playerRIGHT", new Image("sample/ressources/player/playerRIGHT.png"));
        map.put("playerLEFT", new Image("sample/ressources/player/playerLEFT.png"));

        map.put("SourceBois", new Image("sample/ressources/ressourcesImg/bois.png"));
        map.put("SourceTresor", new Image("sample/ressources/ressourcesImg/coffre.png"));
        map.put("SourceMinerai", new Image("sample/ressources/ressourcesImg/minerai.png"));

        map.put("shuriken", new Image("sample/ressources/armes/shuriken.png"));
        map.put("shuriken2", new Image("sample/ressources/armes/shuriken2.png"));
        map.put("shuriken3", new Image("sample/ressources/armes/shuriken3.png"));
        map.put("shuriken4", new Image("sample/ressources/armes/shuriken4.png"));
        map.put("shuriken5", new Image("sample/ressources/armes/shuriken5.png"));
        map.put("shuriken6", new Image("sample/ressources/armes/shuriken6.png"));
        map.put("shuriken7", new Image("sample/ressources/armes/shuriken7.png"));
        map.put("shuriken8", new Image("sample/ressources/armes/shuriken8.png"));
    }

    public Map<String, Image> getMap() {
        return map;
    }

    public Image getImage(String name){
        return map.get(name);
    }
}
