package sample.controleur;


import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import sample.modele.Projectiles;
import sample.vue.ProjectilesVue;

import java.util.List;

public class ObsListProjectiles implements ListChangeListener<Projectiles> {

    private ProjectilesVue projectilesVue;

    public ObsListProjectiles (Pane gamePane){
        this.projectilesVue = new ProjectilesVue(gamePane);
    }

    @Override
    public void onChanged(Change<? extends Projectiles> list) {
        List<? extends Projectiles> listAdded;
        List<? extends Projectiles> listRemoved;
        while (list.next()){
            listAdded = list.getAddedSubList();
            listRemoved = list.getRemoved();
            for (Projectiles p : listAdded){
                projectilesVue.creerBulletSprite(p);
            }
            for (Projectiles p : listRemoved){
                projectilesVue.removeBulletFromPane(p);
            }
        }
    }
}
