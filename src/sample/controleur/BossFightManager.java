package sample.controleur;

import sample.modele.Projectile;
import sample.modele.Terrain;

public class BossFightManager {
    private Terrain terrain;

    public BossFightManager(Terrain terrain){
        this.terrain = terrain;
    }

    public void manageBossFight(long temps){
        if (temps % 590==0){
            bossSpecialAttack();
        }
    }
//pluie de balles.
    public void bossSpecialAttack(){
        for (int i = 0; i<=20;i=i+2){
            if (!(i>8&&i<12)) //exclusion de la zone safe.
                terrain.getProjectiles().add(new Projectile(i,0,"down","Ennemi","EnnemiBoss"));
        }
    }

    public void bossNormalBulletPattern(){

    }

}
