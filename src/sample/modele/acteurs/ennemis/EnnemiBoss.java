package sample.modele.acteurs.ennemis;

public class EnnemiBoss extends EnnemiDistance{
    public EnnemiBoss(int x, int y, int pv, int pointDegat, int niveau) {
        super(x, y, pv, pointDegat, niveau);
    }

    @Override
    public void moveEnnemi(int [][] mapObstacle){}
}
