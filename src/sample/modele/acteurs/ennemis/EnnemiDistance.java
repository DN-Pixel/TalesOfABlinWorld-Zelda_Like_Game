package sample.modele.acteurs.ennemis;

import sample.modele.Joueur;
import sample.modele.Projectiles;

public class EnnemiDistance extends Ennemi {

    public EnnemiDistance(int x,int y, int pv, int pointDegat, int niveau){
        super(x, y, pv, pointDegat, niveau);
    }

    @Override
    public void moveEnnemi(int[][] mapObstacle){

    }

    public Projectiles attaquerJoueur(Joueur joueur){
        Projectiles p = new Projectiles(this.getX(),this.getY(),"DOWN","Ennemi");
        return  p;
    }

}
