package sample.modele.acteurs.ennemis;

import sample.modele.Joueur;
import sample.modele.acteurs.Acteur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public abstract class Ennemi extends Acteur {


    private int pv;
    private int aggroRange;
    private int pointDegat;
    private int vitesse;
    private int niveau;
    private int moveDirection; //1 pour down; 2 pour up; 3 pour right; 4 pour left; 5 pour none
    private BFS bfs = new BFS();
    private int largeur;

    private Stack<Integer> path = new Stack<Integer>(); // direction à prendre pour rejoindre le joueur (BFS PATHFINDING)

    public Ennemi(int x, int y, int pv, int pointDegat, int niveau){
        super(x, y);
        this.pv = pv;
        this.vitesse = 2;
        this.pointDegat = pointDegat;
        this.niveau = niveau;
        this.moveDirection = 5;
        if(this instanceof EnnemiDistance)
            this.aggroRange = 8*16;
        else
            this.aggroRange = 5 * 16;
        if (this instanceof EnnemiBoss) {
            this.largeur=24;
            this.aggroRange = 10000;
        }
        else this.largeur=12;
    }

    //setters
    public void setPointDegat(int pointDegat) {this.pointDegat=pointDegat;}
    public void setPV(int newPv) {this.pv = newPv;}
    public void setNiveau (int niveau) {this.niveau = niveau;
    System.out.println("mfdslih");}

    //getters
    public int getPv() {return this.pv;}
    public int getPointDegat() {return this.pointDegat;}
    public int getNiveau() {return this.niveau;}
    public int getLargeur() {
        return largeur;
    }

    //autres
//    public void seSoigner(int gainPv) {
//        this.pv += gainPv;
//        if(pv>=this.limitPv){
//            this.pv=this.limitPv;
//        }
//    }

    public void subirDegat(int pertePv) {
        this.pv -= pertePv;
    }

    private int oldX =getCentreActeurX()/16;
    private int oldY =getCentreActeurY()/16;

    //gere les deplacements
    //le 5 represente aucun movement
    public  void moveEnnemi(int [][] mapObstacle){
        if(isAggroing()){
            moveDirection = pathfinding();
            setVitesse(3);
        }
        else{
            setVitesse(2);
            if(Math.random()<0.2)
                this.moveDirection = (int) (Math.random() * (5)+1);
            while (!verifieDeplacement(mapObstacle))
                this.moveDirection = (int) (Math.random() * (5)+1);
        }
        oldX = getCentreActeurX()/16;
        oldY = getCentreActeurY()/16;

        if (moveDirection == 1)
            setY(getY()+vitesse);
        else if (moveDirection == 2)
            setY(getY()-vitesse);
        else if (moveDirection == 3)
            setX(getX()+vitesse);
        else if (moveDirection == 4)
            setX(getX()-vitesse);
    }
    // retourne la prochaine direction
    public int pathfinding(){
        // Si on est en BFS et qu'on a une nouvelle position
        int direction = path.lastElement();
        if(getCentreActeurX()/16!=oldX || getCentreActeurY()/16!=oldY)
            path.pop();
        return direction;
    }

    public void launchBFS(int playerPosX, int playerPosY, int[][] mapObstacle){
        path = bfs.start(getCentreActeurX()/16, getCentreActeurY()/16, playerPosX, playerPosY, mapObstacle);
    }

    // si l'ennemi cherche a attaquer l'ennemi (BFS lancé)
    public boolean isAggroing() {
        return !path.empty();
    }

    //verifie si l'ennemi peut se deplacer
    public boolean verifieDeplacement(int [][] mapObstacle){
        if  (moveDirection == 1) {
            if (((getY()+16)/16<mapObstacle.length) && (mapObstacle[(getCentreActeurY()+8)/16][getCentreActeurX()/16]== -1) )
                return true;
        }
        else if  (moveDirection == 2) {
            if ((getY()/16>0) && (mapObstacle[(getCentreActeurY() - 8)/16][getCentreActeurX()/16] == -1))
                return true;
        }
        else if (moveDirection == 3) {
            if (((getX()+16)/16<mapObstacle[0].length) && (mapObstacle[getCentreActeurY()/16][(getCentreActeurX() + 8)/16] == -1))
                return true;
        }
        else if (moveDirection == 4){
           if ((getX()/16>0) && (mapObstacle[getCentreActeurY()/16][(getCentreActeurX()-8)/16]== -1))
            return true;
        }
        else if (moveDirection == 5){
            return true;
        }
        return false;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    //gere les attaques
    public void attaquerJoueur(int posJoueurX, int posJoueurY, Joueur joueur){
        if(posJoueurX<=getCentreActeurX()+24 && posJoueurX>=getCentreActeurX()-24
                && posJoueurY<=getCentreActeurY()+24 && posJoueurY>=getCentreActeurY()-24){
            joueur.subirDegats(getPointDegat());
        }
    }

    public int getAggroRange() {
        return aggroRange;
    }
}
