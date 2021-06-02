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
        Projectile p;
        //calcul quel est l'axe le plus proche ATM du joueur.
        if(Math.abs(joueur.getCentreJoueurX()-getCentreActeurX()) < Math.abs(joueur.getCentreJoueurY()-getCentreActeurY())) {
            if (joueur.getCentreJoueurY() < getCentreActeurY() )
                p = new Projectile(this.getX(), this.getY(), "UP", "Ennemi", this.getClass().getSimpleName());
            else
                p = new Projectile(this.getX(), this.getY(), "DOWN", "Ennemi", this.getClass().getSimpleName());
        }
        else{
            if(joueur.getCentreJoueurX() < getCentreActeurX())
                p = new Projectile(this.getX(), this.getY(), "LEFT", "Ennemi", this.getClass().getSimpleName());
            else
                p = new Projectile(this.getX(), this.getY(), "RIGHT", "Ennemi", this.getClass().getSimpleName());
        }
        return  p;
    }

}
