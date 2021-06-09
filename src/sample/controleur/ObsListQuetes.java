package sample.controleur;

import javafx.collections.ListChangeListener;
import sample.modele.quetes.Quete;
import sample.vue.modeleVue.QueteVue;

public class ObsListQuetes implements ListChangeListener<Quete> {

    private QueteVue queteVue;

    public ObsListQuetes(QueteVue queteVue){
        this.queteVue = queteVue;
    }

    @Override
    public void onChanged(Change<? extends Quete> c) {

        queteVue.displayQuest(c.getList().get(0));
    }
}
