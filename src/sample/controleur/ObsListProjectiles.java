package sample.controleur;


import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import sample.modele.Projectile;
import sample.vue.modeleVue.ProjectilesVue;

import java.util.List;

public class ObsListProjectiles implements ListChangeListener<Projectile> {

    private ProjectilesVue projectilesVue;

    public ObsListProjectiles (Pane gamePane){
        this.projectilesVue = new ProjectilesVue(gamePane);
    }

    @Override
    public void onChanged(Change<? extends Projectile> list) {
        List<? extends Projectile> listAdded;
        List<? extends Projectile> listRemoved;
        while (list.next()){
            listAdded = list.getAddedSubList();
            listRemoved = list.getRemoved();
            for (Projectile p : listAdded){
                projectilesVue.creerBulletSprite(p);
            }
            for (Projectile p : listRemoved){
                projectilesVue.removeBulletFromPane(p);
            }
        }
    }
}
