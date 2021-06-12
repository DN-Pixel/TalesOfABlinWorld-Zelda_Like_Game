package sample.modele.items;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import sample.modele.acteurs.Acteur;
import sample.modele.items.Objets.*;

import java.util.ArrayList;

public class Inventaire {

    private ArrayList<Objet> listObjet;
    private IntegerProperty nbrOr = new SimpleIntegerProperty();
    private IntegerProperty quantiteGlobale = new SimpleIntegerProperty();

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
        this.nbrOr.setValue(100);
    }

    public void ajouterObjet(String item, int quantite) {
        if (quantite>=0) {
            for (Objet i : this.listObjet) {
                if (i.getClass().getSimpleName().equals(item))
                    i.setQuantite(i.getQuantite() + quantite);
            }
        }
    }

    public void eneleverObjet(String item, int quantite) {
        if (quantite >= 0) {
            for (Objet i : this.listObjet) {
                if (i.getClass().getSimpleName().equals(item)) {
                    i.setQuantite(i.getQuantite() - quantite);
                    if (i.getQuantite() < 0)
                        i.setQuantite(0);
                }
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

    public void acheterObjet(String item, int quantiteAchetee) throws InventaireException{
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
        else{
            throw new InventaireException();
        }
    }

    public boolean estDisponible (String item, int quantite) {
        if(quantite<0) return false;
        for (Objet i : this.listObjet ) {
            if (i.getClass().getSimpleName().equals(item) && i.getQuantite()>=quantite) {
                return true;
            }
        }
        return false;
    }
    public int trouverObjet(String nom){
       for (int i=0; i<getListObjet().size();i++) {
            if (nom.endsWith(listObjet.get(i).getClass().getSimpleName())) { return i; }
        }
        return 0;
    }
    public int getNbrOr() { return nbrOr.getValue(); }
    public IntegerProperty nbrOrProperty() { return nbrOr; }

    public void setNbrOr(int nbrOr) {
        this.nbrOr.setValue(nbrOr);
    }
    public void ajouterOr(int nbrOr) {
        this.nbrOr.setValue(this.nbrOr.getValue()+nbrOr);
    }
    public void enleverOr(int nbrOr) {
        this.nbrOr.setValue(this.nbrOr.getValue()-nbrOr);
        if(this.nbrOr.getValue()<=0)
            this.setNbrOr(0);
    }

    public void clearInventaire(){
        for(Objet o : getListObjet()){
            o.setQuantite(0);
        }
        setNbrOr((int)(getNbrOr()*0.70));
    }

    public ArrayList<Objet> getListObjet() {
        return listObjet;
    }

    public String traiterMinerai()throws InventaireException {
        if (!estDisponible("MineraiBrut",1)) throw new InventaireException();
        double x = Math.random();
        //<>
        if (x < 0.05) {
            eneleverObjet("MineraiBrut",1);
            ajouterObjet("Diamant",1);
            return "Diamant";
        }
        else if (x < 0.30) {
            eneleverObjet("MineraiBrut",1);
            ajouterObjet("Argent",1);
            return  "Argent";
        }
        else {
            eneleverObjet("MineraiBrut",1);
            ajouterObjet("Fer",1);
            return "Fer";
        }
    }
}
