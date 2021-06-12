package sample.modele.acteurs.ennemis;

public class EnnemiBoss extends EnnemiDistance{
    public EnnemiBoss(int x, int y) {
        super(x, y, 100, 5, 99);
    }

    @Override
    public void moveEnnemi(int [][] mapObstacle){}
}
