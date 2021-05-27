package sample.modele.items.Objets;

import org.omg.PortableInterceptor.ObjectReferenceFactory;
import sample.modele.items.Objets.Objet;

import java.util.ArrayList;

public class Inventaire {

    private ArrayList<Objet> listObjet;
    private int nbrOr;


    public Inventaire(){
        this.listObjet = new ArrayList<>();
        this.nbrOr = 100;
    }

    public void ajouterObjet(String item, int quantite) {
        for (Objet i : this.listObjet ) {
            if (i.getClass().getSimpleName().equals(item))
                i.setQuantite(i.getQuantite()+quantite);
        }
    }

    public void eneleverObjet(String item, int quantite) {
        for (Objet i : this.listObjet ) {
            if (i.getClass().getSimpleName().equals(item)) {
                i.setQuantite(i.getQuantite() - quantite);
                if (i.getQuantite()<0)
                    i.setQuantite(0);
            }
        }
    }

    public boolean estDisponible (String item, int quantite) {
        for (Objet i : this.listObjet ) {
            if (i.getClass().getSimpleName().equals(item) && i.getQuantite()>=quantite) {
                return true;
            }
        }
        return false;
    }

    public int getNbrOr() {
        return nbrOr;
    }

    public void setNbrOr(int nbrOr) {
        this.nbrOr = nbrOr;
    }

    public ArrayList<Objet> getListObjet() {
        return listObjet;
    }
}
