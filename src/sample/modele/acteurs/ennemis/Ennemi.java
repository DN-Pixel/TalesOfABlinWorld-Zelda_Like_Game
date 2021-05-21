package sample.modele.acteurs.ennemis;

import sample.modele.acteurs.Acteur;

public abstract class Ennemi extends Acteur {


    private int pv;
    private int pointDegat;
    private int niveau;
    private int moveDirection; //1 pour up; 2 pour down; 3 pour right; 4 pour left; 5 pour none

    public Ennemi(int x, int y, int pv, int pointDegat, int niveau){
        super(x, y);
        this.pv = pv;
        this.pointDegat = pointDegat;
        this.niveau = niveau;
        this.moveDirection = 5;
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
    //le 5 represente aucun movement
    public  void moveEnnemi(int [][] mapObstacle){
        this.moveDirection = (int) (Math.random() * (5-1)) +1;
        while (!verifieDeplacement(mapObstacle))
            this.moveDirection = (int) (Math.random() * (5 - 1)) + 1;
        if (moveDirection == 1)
            setY(getY()+1);
        else if (moveDirection == 2)
            setY(getY()-1);
        else if (moveDirection == 3)
            setX(getX()+1);
        else if (moveDirection == 4)
            setX(getX()-1);
    }

    //verifie si l'ennemi peut se deplacer
    public boolean verifieDeplacement(int [][] mapObstacle){
        if  (moveDirection == 1) {
            if ((getY()/16<mapObstacle.length) && (mapObstacle[(getY()+1)/16][getX()/16]== -1) )
                return true;
        }
        else if  (moveDirection == 2) {
            if ((getY()/16>0) && (mapObstacle[(getY() - 1)/16][getX()/16] == -1))
                return true;
        }
        else if (moveDirection == 3) {
            if ((getX()/16<mapObstacle[0].length) && (mapObstacle[getY()/16][(getX() + 1)/16] == -1))
                return true;
        }
        else if (moveDirection == 4){
           if ((getX()/16>0) && (mapObstacle[getY()/16][(getX()-1)/16]== -1))
            return true;
        }
        return false;
    }


    //gere les attaques
    public void attaquerJoueur(){

    }

    //tuer l'ennemis
    public void mourir() {

    }


}
