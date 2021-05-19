package sample.modele.acteurs;

import sample.modele.quetes.Quete;

import java.util.ArrayList;

public class Pnj extends Acteur {

    private ArrayList<Quete> listQuete;

    public Pnj(int x, int y){
        super(x, y);
        this.listQuete = new ArrayList<>();
    }

    public void setListQuete(Quete q){this.listQuete.add(q);}
    public int getNbrQuete() { return this.listQuete.size();}

    public void removeQuete(Quete q){
        for (int i=0; i<this.listQuete.size(); i++){
            if (this.listQuete.get(i)==q)
                this.listQuete.remove(i);
        }
    }

    public void queteAccepte() {

    }

}
