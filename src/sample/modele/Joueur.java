package sample.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyEvent;

public class Joueur {

    private IntegerProperty xProperty = new SimpleIntegerProperty(0);
    private IntegerProperty yProperty = new SimpleIntegerProperty(0);
    private static int vitesseDeDeplacement = 2 ;

    private Terrain zone;
    
    public Joueur(int x, int y, Terrain zone) {
        this.xProperty.setValue(x);
        this.yProperty.setValue(y);
        this.zone = zone;
    }


    public Terrain getZone() {
        return zone;
    }

    public void setZone(Terrain zone) {
        this.zone = zone;
    }
    public IntegerProperty getxProperty() {
        return this.xProperty;
    }

    public IntegerProperty getyProperty() {
        return this.yProperty;
    }

    public int getX () {
        return this.xProperty.getValue();
    }

    public int getY () {
        return this.yProperty.getValue();
    }

    public void setXProperty(int newX) {
        this.xProperty.setValue(newX);
    }

    public void setYProperty(int newY) {
        this.yProperty.setValue(newY);
    }

    public int getVitesseDeDeplacement() { return vitesseDeDeplacement; }
    public void setVitesseDeDeplacement(int vitesseDeDeplacement) { Joueur.vitesseDeDeplacement = vitesseDeDeplacement; }

    public void moveUp () {
        this.yProperty.setValue(this.yProperty.getValue()-vitesseDeDeplacement);
    }

    public void moveDown () {
        this.yProperty.setValue(this.yProperty.getValue()+vitesseDeDeplacement);
    }

    public void moveRight () {
        this.xProperty.setValue(this.xProperty.getValue()+vitesseDeDeplacement);
    }

    public void moveLeft () { this.xProperty.setValue(this.xProperty.getValue()-vitesseDeDeplacement); }

    /*
    retourne le centre X du sprite du joueur
     */
    public double getCentreJoueurX(){
        return this.getX()+8;
    }
    /*
    retourne le centre Y du sprite du joueur
     */
    public double getCentreJoueurY(){
        return this.getY()+8;
    }

    public String getNumeroZone(){
        return getZone().getNomDeCarte().substring(getZone().getNomDeCarte().length()-1);
    }

    public boolean isCollinding(double x, double y){
        if (this.getX()>=x-16 && this.getX()<=x+16 && this.getY()>=y-16 && this.getY()<=y+16)
            return true;
        else
            return false;
    }

    /*
    Gère les collisions du joueur dans le terrain retourne vrai si tout vas bien et faux si il y a un conflit
     */
    //><
    public boolean manageCollisions(KeyEvent e){
        switch (e.getCode()){
            case Z:
                if(!(getY()>0 &&
                        zone.getMapObstacles()[((getY())/16)][((getX())/16)]==-1 &&
                        zone.getMapObstacles()[((getY())/16)][((getX()+16)/16)]==-1))
                    return false;
                break;
            case S:
                if(!(getY()<zone.limiteVertiMap()*16-19 &&
                        zone.getMapObstacles()[((getY()+16)/16)][((getX())/16)]==-1 &&
                        zone.getMapObstacles()[((getY()+16)/16)][((getX()+16)/16)]==-1))
                    return false;
                break;
            case Q:
                if(!(getX()>0 &&
                        zone.getMapObstacles()[((getY())/16)][((getX())/16)]==-1 &&
                        zone.getMapObstacles()[((getY()+16)/16)][((getX())/16)]==-1))
                    return false;
                break;
            case D:
                if(!(getX()< zone.limiteHorizMap()*16-19 &&
                        zone.getMapObstacles()[((getY())/16)][((getX()+16)/16)]==-1 &&
                        zone.getMapObstacles()[((getY()+16)/16)][((getX()+16)/16)]==-1))
                    return false;
                break;
        }
        return true;
    }

}
