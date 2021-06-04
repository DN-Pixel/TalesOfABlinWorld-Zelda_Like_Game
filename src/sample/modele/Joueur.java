package sample.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.RadioButton;
import javafx.scene.input.KeyEvent;
import sample.modele.acteurs.Acteur;
import sample.modele.acteurs.ennemis.Ennemi;
import sample.modele.items.Armes.*;
import sample.modele.items.Inventaire;
import sample.modele.ressources.Ressource;
import sample.vue.Console;

public class Joueur {

    private IntegerProperty hp = new SimpleIntegerProperty();
    private Arme arme;
    private ArmeDistance armeDistance;
    private Console console;
    private IntegerProperty xProperty = new SimpleIntegerProperty(0);
    private IntegerProperty yProperty = new SimpleIntegerProperty(0);
    private static int vitesseDeDeplacement = 2 ;
    private StringProperty direction = new SimpleStringProperty();
    private Terrain zone;
    private Inventaire inventaire;
    private int maxHP;

    public Joueur(int x, int y, Terrain zone) {
        arme = new Gourdin(); // Le joueur commence avec un gourdin
        this.xProperty.setValue(x);
        this.yProperty.setValue(y);
        this.zone = zone;
        direction.setValue("down");
        hp.setValue(10);
        maxHP = hp.getValue();
        armeDistance = null;
        this.inventaire = new Inventaire();
    }

    public ArmeDistance getArmeDistance() {
        return armeDistance;
    }

    public void setArmeDistance(ArmeDistance armeDistance) {
        this.armeDistance = armeDistance;
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
        return direction.getValue();
    }

    public StringProperty directionProperty(){return  this.direction;}
    public void setDirection(String direction) {
        this.direction.setValue(direction);
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

    public Inventaire getInventaire() { return this.inventaire; }

    public void moveUp () {
        this.yProperty.setValue(this.yProperty.getValue()-vitesseDeDeplacement);
        direction.setValue("up");
    }

    public void moveDown () {
        this.yProperty.setValue(this.yProperty.getValue()+vitesseDeDeplacement);
        direction.setValue("down");
    }

    public void moveRight () {
        this.xProperty.setValue(this.xProperty.getValue()+vitesseDeDeplacement);
        direction.setValue("right");
    }

    public void moveLeft () {
        this.xProperty.setValue(this.xProperty.getValue()-vitesseDeDeplacement);
        direction.setValue("left");
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
            if(a instanceof Ennemi && a.getCentreActeurX()>=getCentreJoueurX()-16 && a.getCentreActeurX()<=getCentreJoueurX()+16 &&
                    a.getCentreActeurY()>=getCentreJoueurY()-16 && a.getCentreActeurY()<=getCentreJoueurY()+16){
                ((Ennemi) a).setVitesse(0);
            }
            else if(a instanceof Ennemi && (getCentreJoueurX()>=a.getCentreActeurX()-((Ennemi) a).getAggroRange() &&
                    getCentreJoueurX()<=a.getCentreActeurX()+((Ennemi) a).getAggroRange()) && (getCentreJoueurY()>=a.getCentreActeurY()-((Ennemi) a).getAggroRange() &&
                    getCentreJoueurY()<=a.getCentreActeurY()+((Ennemi) a).getAggroRange())) {
                ((Ennemi) a).launchBFS(getCentreJoueurX()/16, getCentreJoueurY()/16, getZone().getMapObstacles());
            }
        }
    }

    public void subirDegats(int degats){
        hp.setValue(hp.getValue()-degats);
        console.afficherDegatsRecus(degats);
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
                if (this.arme instanceof Melee || this.arme instanceof MeleeRange)
                    attaquerCorpsACorps(a);
            }
        }
    }

    public void attaquerCorpsACorps (Acteur a) {
        switch (direction.getValue()) {
            case "right":
                if (a.getCentreActeurX() <= this.getCentreJoueurX() + arme.getRange() && a.getCentreActeurX() >= this.getCentreJoueurX()
                        && a.getCentreActeurY() <= this.getCentreJoueurY() + 24 && a.getCentreActeurY() >= this.getCentreJoueurY() - 24) {
                    ((Ennemi) a).subirDegat(arme.getDegatsArme());
                    console.afficherDegatsInfliges(arme.getDegatsArme());
                }
                break;
            case "left":
                if (a.getCentreActeurX() >= this.getCentreJoueurX() - arme.getRange() && a.getCentreActeurX() <= this.getCentreJoueurX()
                        && a.getCentreActeurY() <= this.getCentreJoueurY() + 24 && a.getCentreActeurY() >= this.getCentreJoueurY() - 24) {
                    ((Ennemi) a).subirDegat(arme.getDegatsArme());
                    console.afficherDegatsInfliges(arme.getDegatsArme());
                }
                break;
            case "up":
                if (a.getCentreActeurY() >= this.getCentreJoueurY() - arme.getRange() && a.getCentreActeurY() <= this.getCentreJoueurY()
                        && a.getCentreActeurX() >= this.getCentreJoueurX() - 24 && a.getCentreActeurX() <= this.getCentreJoueurX() + 24) {
                    ((Ennemi) a).subirDegat(arme.getDegatsArme());
                    console.afficherDegatsInfliges(arme.getDegatsArme());
                }
                break;

            case "down":
                if (a.getCentreActeurY() <= this.getCentreJoueurY() + arme.getRange() && a.getCentreActeurY() >= this.getCentreJoueurY()
                        && a.getCentreActeurX() >= this.getCentreJoueurX() - 24 && a.getCentreActeurX() <= this.getCentreJoueurX() + 24) {
                    ((Ennemi) a).subirDegat(arme.getDegatsArme());
                    console.afficherDegatsInfliges(arme.getDegatsArme());
                }
                break;
        }
    }

    public void attaquerEnDistance() {
        Projectile p = new Projectile(this.getX(), this.getY(), direction.getValue().toUpperCase(), "hero", "Joueur");
        this.zone.getProjectiles().add(p);
    }
    public void manger(String selecteRadio) {

        switch (selecteRadio) {
            case "noodleRadio":
                if (getInventaire().estDisponible("Nouilles", 1)) {
                    getInventaire().eneleverObjet("Nouilles", 1);
                    regenerer((int)(maxHP*0.75));
                }
                else console.afficherItemIndisponible("nouille");
                break;
            case "mielRadio":
                if (getInventaire().estDisponible("Miel", 1)) {
                    getInventaire().eneleverObjet("Miel", 1);
                    regenerer((int)(maxHP*0.3));
                }
                else console.afficherItemIndisponible("miel");
                break;
            case "meatRadio":
                if (getInventaire().estDisponible("Viande", 1)) {
                    getInventaire().eneleverObjet("Viande", 1);
                    regenerer((int)(maxHP/2));
                }
                else console.afficherItemIndisponible("viande");
                break;
            case "Potion":
                if (getInventaire().estDisponible("Potion", 1)) {
                    getInventaire().eneleverObjet("Potion", 1);
                    regenerer((int)(maxHP));
                }
                else console.afficherItemIndisponible("potion");
                break;
            default:
                break;
        }
    }
    public void mourrir() {
        zone.getProjectiles().clear();
        console.afficherMort();
        setHp(maxHP);
        inventaire.clearInventaire();
        inventaire.ajouterObjet("Miel", 1);
    }

    public void regenerer(int hp){
        console.afficherHeal(hp);
        this.hp.setValue(this.hp.getValue()+hp);
        if(this.hp.getValue()>maxHP){
            this.hp.setValue(maxHP);
        }
    }

    public void loot(){
        Ressource r;
        for(int i = zone.getListeRessource().size()-1;i>=0;i--){
            r = zone.getListeRessource().get(i);
            if(r.getCentreRessourceX()<=getCentreJoueurX()+20 && r.getCentreRessourceX()>=getCentreJoueurX()-20
            && r.getCentreRessourceY()<=getCentreJoueurY()+20 && r.getCentreRessourceY()>=getCentreJoueurY()-20) {
                getInventaire().ajouterObjet(r.getRecompense(), r.getQuantite());
                console.afficherItemRecup(r.getRecompense(), r.getQuantite());
                zone.getMapObstacles()[r.getY()/16][r.getX()/16] = -1;
                zone.getListeRessource().remove(r);
            }
        }
    }

}
