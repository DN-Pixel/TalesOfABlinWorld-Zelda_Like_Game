package sample.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyEvent;
import sample.modele.acteurs.Acteur;
import sample.modele.acteurs.ennemis.Ennemi;
import sample.modele.items.Armes.Arme;
import sample.modele.items.Armes.Gourdin;
import sample.modele.items.Inventaire;
import sample.vue.Console;

public class Joueur {

    private IntegerProperty hp = new SimpleIntegerProperty();
    private Arme arme;
    private Console console;
    private IntegerProperty xProperty = new SimpleIntegerProperty(0);
    private IntegerProperty yProperty = new SimpleIntegerProperty(0);
    private static int vitesseDeDeplacement = 2 ;
    private String direction;
    private Terrain zone;
    private Inventaire inventaire;
    private int maxHP;

    public Joueur(int x, int y, Terrain zone) {
        arme = new Gourdin(); // Le joueur commence avec un gourdin
        this.xProperty.setValue(x);
        this.yProperty.setValue(y);
        this.zone = zone;
        direction = "down";
        hp.setValue(10);
        maxHP = hp.getValue();
        this.inventaire = new Inventaire();
    }

    public IntegerProperty getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp.setValue(hp);
    }

    public void setConsole(Console console ){
        this.console=console;
    }
    public Console getConsole(){
        return this.console;
    }
    public int getPointAttaque() {
        return arme.getDegatsArme();
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setArme(Arme arme) {
        this.arme = arme;
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

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }


    public void moveUp () {
        this.yProperty.setValue(this.yProperty.getValue()-vitesseDeDeplacement);
        direction = "up";
    }

    public void moveDown () {
        this.yProperty.setValue(this.yProperty.getValue()+vitesseDeDeplacement);
        direction = "down";
    }

    public void moveRight () {
        this.xProperty.setValue(this.xProperty.getValue()+vitesseDeDeplacement);
        direction = "right";
    }

    public void moveLeft () {
        this.xProperty.setValue(this.xProperty.getValue()-vitesseDeDeplacement);
        direction="left";
    }
    /*
    private int oldTileValue;
    private int oldPlayerX =getCentreJoueurX()/16;
    private int oldPlayerY =getCentreJoueurY()/16;

    public void setOldTileValue (int Value){
        oldTileValue=Value;
    }

    public void updatePosition(){
        int newTile = this.zone.getMapSpawn()[getCentreJoueurY()/16][getCentreJoueurX()/16];
        System.out.println(newTile);
        if(newTile != oldTileValue){
            this.zone.getMapSpawn()[oldPlayerY][oldPlayerX] = oldTileValue;
            oldPlayerX =getCentreJoueurX()/16;
            oldPlayerY =getCentreJoueurY()/16;
            this.zone.getMapSpawn()[getCentreJoueurY()/16][getCentreJoueurX()/16]=99;
            manageAggro();
        }
    }
*/
    public void manageAggro(){
        Acteur a;
        for(int i=getZone().getListeActeurs().size()-1; i>=0;i--){
            a = getZone().getListeActeurs().get(i);
            //l'aggro s'arrete lorsque l'ennemi est dans un carré de 16/16 autout de moi.
            if(a instanceof Ennemi && (getCentreJoueurX()>=a.getCentreActeurX()-80 &&
                    getCentreJoueurX()<=a.getCentreActeurX()+80) && (getCentreJoueurY()>=a.getCentreActeurY()-80 &&
                    getCentreJoueurY()<=a.getCentreActeurY()+80)) {
                ((Ennemi) a).launchBFS(getCentreJoueurX()/16, getCentreJoueurY()/16, getZone().getMapObstacles());
            }
        }
    }

    public void subirDegats(int degats){
        hp.setValue(hp.getValue()-degats);
        console.afficherDegatsRecus(degats);
        if(hp.getValue()<=0){
            mourrir();
        }
    }

    /*
    retourne le centre X du sprite du joueur
     */
    public int getCentreJoueurX(){
        return this.getX()+8;
    }
    /*
    retourne le centre Y du sprite du joueur
     */
    public int getCentreJoueurY(){
        return this.getY()+8;
    }

    public String getNumeroZone(){
        return getZone().getNomDeCarte().substring(getZone().getNomDeCarte().length()-1);
    }

    public boolean isCollinding(double x, double y){
        return this.getX() >= x - 16 && this.getX() <= x + 16 && this.getY() >= y - 16 && this.getY() <= y + 16;
    }

    /*
    Gère les collisions du joueur dans le terrain retourne vrai si tout vas bien et faux si il y a un conflit
     */
    public boolean manageCollisions(KeyEvent e){
        switch (e.getCode()){
            case Z:
                if(!(getY()>0 && zone.getMapObstacles()[((getCentreJoueurY()-8)/16)][((getCentreJoueurX())/16)]==-1))
                    return false;
                break;
            case S:
                if(!(getY()<zone.limiteVertiMap()*16-19 && zone.getMapObstacles()[((getCentreJoueurY()+8)/16)][((getCentreJoueurX())/16)]==-1 ))
                    return false;
                break;
            case Q:
                if(!(getX()>0 &&  zone.getMapObstacles()[((getCentreJoueurY())/16)][((getCentreJoueurX()-8)/16)]==-1 ))
                    return false;
                break;
            case D:
                if(!(getX()< zone.limiteHorizMap()*16-19 && zone.getMapObstacles()[((getCentreJoueurY())/16)][((getCentreJoueurX()+8)/16)]==-1))
                    return false;
                break;
            default:
                return false;
        }
        return true;
    }



    public void attaquerEnnemis() {
        for (int i = 0; i <this.zone.getListeActeurs().size() ; i++) {
            Acteur a = this.zone.getListeActeurs().get(i);
            if (a instanceof Ennemi) {
                switch (direction){
                    case "right" :
                        if (a.getCentreActeurX()<=this.getCentreJoueurX()+arme.getRange() && a.getCentreActeurX()>=this.getCentreJoueurX()
                        && a.getCentreActeurY()<=this.getCentreJoueurY()+24 && a.getCentreActeurY() >= this.getCentreJoueurY()-24 ) {
                            ((Ennemi) a).subirDegat(arme.getDegatsArme());
                            console.afficherDegatsInfliges(arme.getDegatsArme());
                        }
                        break;
                    case "left" :
                        if (a.getCentreActeurX()>=this.getCentreJoueurX()-arme.getRange() && a.getCentreActeurX()<=this.getCentreJoueurX()
                                && a.getCentreActeurY()<=this.getCentreJoueurY()+24 && a.getCentreActeurY() >= this.getCentreJoueurY()-24 ) {
                            ((Ennemi) a).subirDegat(arme.getDegatsArme());
                            console.afficherDegatsInfliges(arme.getDegatsArme());
                        }
                        break;
                    case "up" :
                        if (a.getCentreActeurY()>=this.getCentreJoueurY()-arme.getRange() && a.getCentreActeurY() <= this.getCentreJoueurY()
                                && a.getCentreActeurX()>=this.getCentreJoueurX()-24 && a.getCentreActeurX()<=this.getCentreJoueurX()+24 ) {
                            ((Ennemi) a).subirDegat(arme.getDegatsArme());
                            console.afficherDegatsInfliges(arme.getDegatsArme());
                        }
                        break;

                    case "down" :
                        if (a.getCentreActeurY()<=this.getCentreJoueurY()+arme.getRange() && a.getCentreActeurY() >= this.getCentreJoueurY()
                                && a.getCentreActeurX()>=this.getCentreJoueurX()-24 && a.getCentreActeurX()<=this.getCentreJoueurX()+24) {
                            ((Ennemi) a).subirDegat(arme.getDegatsArme());
                            console.afficherDegatsInfliges(arme.getDegatsArme());
                        }
                        break;
                }

            }
        }
    }

    private void mourrir() {
        console.afficherMort();
    }

    public void regenerer(int hp){
        this.hp.setValue(this.hp.getValue()+hp);
        if(this.hp.getValue()>maxHP){
            this.hp.setValue(maxHP);
        }
    }

    public Inventaire getInventaire() {
        return inventaire;
    }
}
