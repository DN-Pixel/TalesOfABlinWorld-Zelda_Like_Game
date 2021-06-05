package sample.modele.acteurs.ennemis;

import sample.modele.Joueur;
import sample.modele.Projectile;

public class EnnemiDistance extends Ennemi {

    public EnnemiDistance(int x,int y, int pv, int pointDegat, int niveau){
        super(x, y, pv, pointDegat, niveau);
    }

    public Projectile attaquerJoueur(Joueur joueur){
        Projectile p;
        int tailleProjectile;
        if(this instanceof EnnemiBoss) {
            tailleProjectile=16;
            p = new Projectile(this.getX()+4, this.getY()+4, "DOWN", "Ennemi", this.getClass().getSimpleName(), tailleProjectile, 2);
            return p;
        }
        else{
            tailleProjectile=8;
            //calcul quel est l'axe le plus proche ATM du joueur.
            if(Math.abs(joueur.getCentreJoueurX()-getCentreActeurX()) < Math.abs(joueur.getCentreJoueurY()-getCentreActeurY())) {
                if (joueur.getCentreJoueurY() < getCentreActeurY() )
                    p = new Projectile(this.getX()+4, this.getY()+4, "UP", "Ennemi", this.getClass().getSimpleName(), tailleProjectile, 2);
                else
                    p = new Projectile(this.getX()+4, this.getY()+4, "DOWN", "Ennemi", this.getClass().getSimpleName(), tailleProjectile, 2);
            }
            else{
                if(joueur.getCentreJoueurX() < getCentreActeurX())
                    p = new Projectile(this.getX()+4, this.getY()+4, "LEFT", "Ennemi", this.getClass().getSimpleName(), tailleProjectile, 2);
                else
                    p = new Projectile(this.getX()+4, this.getY()+4, "RIGHT", "Ennemi", this.getClass().getSimpleName(), tailleProjectile, 2);
            }
            return  p;
         }

    }
}
