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
    private int vitesse;


    private int tailleProjectile;

    public Projectile(int x, int y, String direction, String id, String origine, int taille, int vitesse){
        this.id = id+b;
        b++;
        this.x.setValue(x);
        this.y.setValue(y);
        this.origine = origine;
        this.direction=direction;
        this.tailleProjectile = taille;
        this.vitesse = vitesse;
    }

    public String getOrigine() {
        return origine;
    }

    public String getId() {
        return id;
    }

    public int getX() { return x.getValue(); }
    public int getCentreX(){ return getX() + getTailleProjectile()/2;}
    public IntegerProperty xProperty() { return x; }
    public void setX(int x) { this.x.set(x); }
    public int getY() { return y.getValue(); }
    public int getCentreY(){ return getY() + getTailleProjectile()/2;}
    public IntegerProperty yProperty() { return y; }
    public void setY(int y) { this.y.set(y); }
    public String getDirection() { return direction; }
    public int getTailleProjectile() {
        return tailleProjectile;
    }

    public void moveProjectile() {
        switch (this.getDirection()) {
            case "UP":
                this.setY(this.getY() - vitesse);
                break;
            case "DOWN":
                this.setY(this.getY() + vitesse);
                break;
            case "LEFT":
                this.setX(this.getX() - vitesse);
                break;
            case "RIGHT":
                this.setX(this.getX() + vitesse);
                break;
            default:
                break;
        }
    }

}
