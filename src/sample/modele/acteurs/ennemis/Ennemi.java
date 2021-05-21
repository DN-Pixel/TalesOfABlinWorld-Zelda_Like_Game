package sample.modele.acteurs.ennemis;

import sample.modele.acteurs.Acteur;

public abstract class Ennemi extends Acteur {


    private int pv;
    private int pointDegat;
    private int niveau;

    public Ennemi(int x, int y, int pv, int pointDegat, int niveau){
        super(x, y);
        this.pv = pv;
        this.pointDegat = pointDegat;
        this.niveau = niveau;
    }

    //setters
    public void setPointDegat(int pointDegat) {this.pointDegat=pointDegat;}
    public void setPV(int newPv) {this.pv = newPv;}
    public void setNiveau (int niveau) {this.niveau = niveau;}

    //getters
    public int getPv() {return this.pv;}
    public int getPointDegat() {return this.pointDegat;}
    public int getNiveau() {return this.niveau;}


    //autres
//    public void seSoigner(int gainPv) {
//        this.pv += gainPv;
//        if(pv>=this.limitPv){
//            this.pv=this.limitPv;
//        }
//    }

    public void subirDegat(int pertePv) {
        this.pv -= pertePv;
        if(pv<=0){
            this.pv=0;
            mourir();
        }
    }

    //gere les deplacements
    public  void moveEnnemi(){

    }

    //gere les attaques
    public void attaquerJoueur(){

    }

    //tuer l'ennemis
    public void mourir() {

    }


}
