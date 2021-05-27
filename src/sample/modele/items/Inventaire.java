package sample.modele.items;

import org.omg.PortableInterceptor.ObjectReferenceFactory;
import sample.modele.items.Objets.*;

import java.util.ArrayList;

public class Inventaire {

    private ArrayList<Objet> listObjet;
    private int nbrOr;


    public Inventaire(){
        this.listObjet = new ArrayList<>();
        listObjet.add(new Argent());
        listObjet.add(new Bois());
        listObjet.add(new Diamant());
        listObjet.add(new Fer());
        listObjet.add(new Miel());
        listObjet.add(new MineraiBrut());
        listObjet.add(new Nouilles());
        listObjet.add(new Potion());
        listObjet.add(new Viande());
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

    public void vendreObjet(String item, int quantite){
        if(estDisponible(item, quantite)){
            for (Objet i : this.listObjet ) {
                if (i.getClass().getSimpleName().equals(item)) {
                    ajouterOr(quantite*i.getValeur());
                    break;
                }
            }
            eneleverObjet(item, quantite);
        }
    }

    public void acheterObjet(String item, int quantiteAchetee){
        int valeurTotale = 999999999;
        for (Objet i : this.listObjet ) {
            if (i.getClass().getSimpleName().equals(item)) {
                valeurTotale = i.getValeur()*quantiteAchetee;
                break;
            }
        }
        if(getNbrOr()>=valeurTotale){
            enleverOr(valeurTotale);
            ajouterObjet(item, quantiteAchetee);
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
    public void ajouterOr(int nbrOr) {
        this.nbrOr += nbrOr;
    }
    public void enleverOr(int nbrOr) {
        this.nbrOr -= nbrOr;
        if(this.nbrOr<=0)
            this.setNbrOr(0);
    }

    public ArrayList<Objet> getListObjet() {
        return listObjet;
    }
}
