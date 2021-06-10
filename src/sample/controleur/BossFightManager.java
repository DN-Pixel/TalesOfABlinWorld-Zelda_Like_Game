package sample.controleur;

import sample.modele.Projectile;
import sample.modele.Terrain;

public class BossFightManager {
    private Terrain terrain;

    public BossFightManager(Terrain terrain){
        this.terrain = terrain;
    }

    public void manageBossFight(long temps){
        if (temps % 590==0)
            bossSpecialAttack();
        if(temps%180==0){
            bossSideAttack();
            bossSideAttack();
        }
    }
//pluie de balles.
    public void bossSpecialAttack(){
        double x = Math.random();
        int safeZoneLeftPos, safeZoneRightPos;
        if(x<=0.25){
            safeZoneLeftPos = 8;
            safeZoneRightPos = 12;
        }
        else if(x<=0.5){
            safeZoneLeftPos = 4;
            safeZoneRightPos = 8;
        }
        else if(x<=0.75){
            safeZoneLeftPos = 22;
            safeZoneRightPos = 26;
        }
        else {
            safeZoneLeftPos = 18;
            safeZoneRightPos = 22;
        }
        for (int i = 0; i<30;i=i+2){
            if (!(i>safeZoneLeftPos&&i<safeZoneRightPos)) //exclusion de la zone safe.
                terrain.getProjectiles().add(new Projectile(i*16,0,"DOWN","Ennemi","EnnemiBossSPE", 16, 1));
        }
    }

    public void bossSideAttack(){
        String direction;
        int x;
        if(Math.random()<=0.5){
            x = 0;
            direction = "RIGHT";
        }
        else{
            x = 30;
            direction = "LEFT";
        }
        terrain.getProjectiles().add(new Projectile(x*16, 16*((int)(Math.random()*22)+6), direction,"Ennemi","EnnemiBoss", 16, 3));
    }

}
