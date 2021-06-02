package sample.modele.acteurs.ennemis;

import sample.modele.Joueur;
import sample.modele.Projectile;

public class EnnemiDistance extends Ennemi {

    public EnnemiDistance(int x,int y, int pv, int pointDegat, int niveau){
        super(x, y, pv, pointDegat, niveau);
    }

    @Override
    public void moveEnnemi(int[][] mapObstacle){

    }

    public Projectile attaquerJoueur(Joueur joueur){
        System.out.println(this.getClass().getSimpleName());
        Projectile p = new Projectile(this.getX(),this.getY(),"DOWN","Ennemi", this.getClass().getSimpleName());
        return  p;
    }

}
