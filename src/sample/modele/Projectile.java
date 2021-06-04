package sample.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Projectile {
    private IntegerProperty x= new SimpleIntegerProperty(0);
    private IntegerProperty y= new SimpleIntegerProperty(0);
    private String direction;
    private String origine;
    private String id;
    private static int b=0;


    private int tailleProjectile;

    public Projectile(int x, int y, String direction, String id, String o, int taille){
        this.id = id+b;
        b++;
        this.x.setValue(x);
        this.y.setValue(y);
        origine = o;
        this.direction=direction;
        this.tailleProjectile = taille;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
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
    public int getTailleProjectile() {
        return tailleProjectile;
    }
    public void setTailleProjectile(int tailleProjectile) {
        this.tailleProjectile = tailleProjectile;
    }

    public void moveProjectile() {
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
