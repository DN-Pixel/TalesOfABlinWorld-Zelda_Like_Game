package sample.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Joueur {

    private IntegerProperty xProperty = new SimpleIntegerProperty(0);
    private IntegerProperty yProperty = new SimpleIntegerProperty(0);

    public Joueur(int x, int y) {
        this.xProperty.setValue(x);
        this.yProperty.setValue(y);
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
