package sample.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Projectiles {
    private IntegerProperty x= new SimpleIntegerProperty(0);
    private IntegerProperty y= new SimpleIntegerProperty(0);
    private String direction;
    private String id;
    private static int b=0;

    public Projectiles(int x, int y, String direction,String id){
        this.id = id+b;
        b++;
        this.x.setValue(x);
        this.y.setValue(y);
        this.direction=direction;
    }

    public String getId() {
        return id;
    }

    public static int getB() {
        return b;
    }

    public int getX() { return x.getValue(); }
    public IntegerProperty xProperty() { return x; }
    public void setX(int x) { this.x.set(x); }
    public int getY() { return y.getValue(); }
    public IntegerProperty yProperty() { return y; }
    public void setY(int y) { this.y.set(y); }
    public String getDirection() { return direction; }

    public void moveProjectiles() {
        switch (this.getDirection()) {
            case "UP":
                this.setY(this.getY() - 2);
                break;
            case "DOWN":
                this.setY(this.getY() + 2);
                break;
            case "LEFT":
                this.setX(this.getX() - 2);
                break;
            case "RIGHT":
                this.setX(this.getX() + 2);
                break;
            default:
                break;
        }
    }

}
