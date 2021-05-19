package sample.modele.acteurs;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Acteur {

    private IntegerProperty x, y;

    public Acteur(int x, int y) {

        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
    }


    public void setXProperty(int x) { this.x.setValue(x);}
    public void setYProperty(int y) {this.y.setValue(y);}

    public int getX() {return this.x.getValue();}
    public int getY() {return this.y.getValue();}
    public IntegerProperty getXProperty() {return this.x;}
    public IntegerProperty getYProperty() {return this.y;}

}




