package sample.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Joueur {

    private DoubleProperty xProperty = new SimpleDoubleProperty(0);
    private DoubleProperty yProperty = new SimpleDoubleProperty(0);

    public Joueur(Double x, Double y) {
        this.xProperty.setValue(x);
        this.yProperty.setValue(y);
    }

    public DoubleProperty getxProperty() {
        return this.xProperty;
    }

    public DoubleProperty getyProperty() {
        return this.yProperty;
    }

    public Double getX () {
        return this.xProperty.getValue();
    }

    public Double getY () {
        return this.yProperty.getValue();
    }

    public void setXProperty(Double newX) {
        this.xProperty.setValue(newX);
    }

    public void setYProperty(Double newY) {
        this.yProperty.setValue(newY);
    }

    public void moveUp () {
        this.yProperty.setValue(this.yProperty.getValue()-2);
    }

    public void moveDown () {
        this.yProperty.setValue(this.yProperty.getValue()+2);
    }

    public void moveRight () {
        this.xProperty.setValue(this.xProperty.getValue()+2);
    }

    public void moveLeft () {
        this.xProperty.setValue(this.xProperty.getValue()-2);
    }

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


}
