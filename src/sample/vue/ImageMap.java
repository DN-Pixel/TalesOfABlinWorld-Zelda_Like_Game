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

        map.put("10HP", new Image("sample/ressources/HUD/HPBAR/10HP.png"));
        map.put("9HP", new Image("sample/ressources/HUD/HPBAR/9HP.png"));
        map.put("8HP", new Image("sample/ressources/HUD/HPBAR/8HP.png"));
        map.put("7HP", new Image("sample/ressources/HUD/HPBAR/7HP.png"));
        map.put("6HP", new Image("sample/ressources/HUD/HPBAR/6HP.png"));
        map.put("5HP", new Image("sample/ressources/HUD/HPBAR/5HP.png"));
        map.put("4HP", new Image("sample/ressources/HUD/HPBAR/4HP.png"));
        map.put("3HP", new Image("sample/ressources/HUD/HPBAR/3HP.png"));
        map.put("2HP", new Image("sample/ressources/HUD/HPBAR/2HP.png"));
        map.put("1HP", new Image("sample/ressources/HUD/HPBAR/1HP.png"));

        map.put("playerDOWN", new Image("sample/ressources/player/playerDOWN.png"));
        map.put("playerUP", new Image("sample/ressources/player/playerUP.png"));
        map.put("playerRIGHT", new Image("sample/ressources/player/playerRIGHT.png"));
        map.put("playerLEFT", new Image("sample/ressources/player/playerLEFT.png"));

        map.put("SourceBois", new Image("sample/ressources/ressourcesImg/bois.png"));
        map.put("SourceTresor", new Image("sample/ressources/ressourcesImg/coffre.png"));
        map.put("SourceMinerai", new Image("sample/ressources/ressourcesImg/minerai.png"));
    }

    public Map<String, Image> getMap() {
        return map;
    }

    public Image getImage(String name){
        return map.get(name);
    }
}
