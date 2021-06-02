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
        Projectiles p;
        //calcul quel est l'axe le plus proche ATM du joueur.
        if(Math.abs(joueur.getCentreJoueurX()-getCentreActeurX()) < Math.abs(joueur.getCentreJoueurY()-getCentreActeurY())) {
            if (joueur.getCentreJoueurY() < getCentreActeurY() )
                p = new Projectiles(this.getX(), this.getY(), "UP", "Ennemi");
            else
                p = new Projectiles(this.getX(), this.getY(), "DOWN", "Ennemi");
        }
        else{
            if(joueur.getCentreJoueurX() < getCentreActeurX())
                p = new Projectiles(this.getX(), this.getY(), "LEFT", "Ennemi");
            else
                p = new Projectiles(this.getX(), this.getY(), "RIGHT", "Ennemi");
        }
        return  p;
    }

}
