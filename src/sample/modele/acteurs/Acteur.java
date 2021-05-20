package sample.modele.acteurs;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Acteur {

    private IntegerProperty x, y;
    private String id;
    private static int a = 0;

    public Acteur(int x, int y) {
        id="a"+a;
        a++;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
    }



    public IntegerProperty xProperty() {
        return x;
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public IntegerProperty yProperty() {
        return y;
    }

    public void setY(int y) {
        this.y.set(y);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setXProperty(int x) { this.x.setValue(x);}
    public void setYProperty(int y) {this.y.setValue(y);}

    public int getX() {return this.x.getValue();}
    public int getY() {return this.y.getValue();}
    public IntegerProperty getXProperty() {return this.x;}
    public IntegerProperty getYProperty() {return this.y;}

}




