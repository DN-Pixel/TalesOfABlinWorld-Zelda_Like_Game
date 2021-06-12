package sample.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.KeyEvent;
import sample.controleur.SoundPlayer;
import sample.modele.acteurs.Acteur;
import sample.modele.acteurs.Pnj;
import sample.modele.acteurs.ennemis.Ennemi;
import sample.modele.items.Armes.*;
import sample.modele.items.Inventaire;
import sample.modele.quetes.QuestLine;
import sample.modele.ressources.Ressource;

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
    private IntegerProperty maxHP = new SimpleIntegerProperty();
    private IntegerProperty niveau;
    private QuestLine listeQuetes;

    public Joueur(int x, int y, Terrain zone) {
        arme = new Gourdin(); // Le joueur commence avec un gourdin
        this.xProperty.setValue(x);
        this.yProperty.setValue(y);
        this.zone = zone;
        direction.setValue("down");
        hp.setValue(10);
        maxHP.setValue( hp.getValue());
        armeDistance = null;
        niveau = new SimpleIntegerProperty(1);
        this.inventaire = new Inventaire();
        this.listeQuetes = new QuestLine(this);
    }

    public ArmeDistance getArmeDistance() {
        return armeDistance;
    }

    public Arme getArme() {
        return arme;
    }

    public int getNiveau() {
        return niveau.getValue();
    }

    public void lvlUp(){
        SoundPlayer.playerLevelUp();
        niveau.setValue(niveau.getValue()+1);
        maxHP.setValue(maxHP.getValue()+5);
        hp.setValue(maxHP.getValue());
        console.afficherLvlUp();
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

    public IntegerProperty niveauProperty() {
        return niveau;
    }

    public void setConsole(Console console ){
        this.console=console;
    }
    public Console getConsole(){
        return this.console;
    }

    public String getDirection() {
        return direction.getValue();
    }

    public StringProperty directionProperty(){return  this.direction;}

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

    public int getMaxHP() {
        return maxHP.getValue();
    }

    public IntegerProperty maxHPProperty() {
        return maxHP;
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

    public QuestLine getListeQuetes() {
        return listeQuetes;
    }

    public void setListeQuetes(QuestLine listeQuetes) {
        this.listeQuetes = listeQuetes;
    }

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
        SoundPlayer.playerGotHit();
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
                    SoundPlayer.playAttackSound();
                }
                break;
            case "left":
                if (a.getCentreActeurX() >= this.getCentreJoueurX() - arme.getRange() && a.getCentreActeurX() <= this.getCentreJoueurX()
                        && a.getCentreActeurY() <= this.getCentreJoueurY() + 24 && a.getCentreActeurY() >= this.getCentreJoueurY() - 24) {
                    ((Ennemi) a).subirDegat(arme.getDegatsArme());
                    console.afficherDegatsInfliges(arme.getDegatsArme());
                    SoundPlayer.playAttackSound();
                }
                break;
            case "up":
                if (a.getCentreActeurY() >= this.getCentreJoueurY() - arme.getRange() && a.getCentreActeurY() <= this.getCentreJoueurY()
                        && a.getCentreActeurX() >= this.getCentreJoueurX() - 24 && a.getCentreActeurX() <= this.getCentreJoueurX() + 24) {
                    ((Ennemi) a).subirDegat(arme.getDegatsArme());
                    console.afficherDegatsInfliges(arme.getDegatsArme());
                    SoundPlayer.playAttackSound();
                }
                break;

            case "down":
                if (a.getCentreActeurY() <= this.getCentreJoueurY() + arme.getRange() && a.getCentreActeurY() >= this.getCentreJoueurY()
                        && a.getCentreActeurX() >= this.getCentreJoueurX() - 24 && a.getCentreActeurX() <= this.getCentreJoueurX() + 24) {
                    ((Ennemi) a).subirDegat(arme.getDegatsArme());
                    console.afficherDegatsInfliges(arme.getDegatsArme());
                    SoundPlayer.playAttackSound();
                }
                break;
        }
    }

    public void attaquerEnDistance() {
        if(armeDistance==null)
            return;
        Projectile p = new Projectile(this.getX(), this.getY(), direction.getValue().toUpperCase(), "hero", "Joueur", 8, 2);
        this.zone.getProjectiles().add(p);
        SoundPlayer.playSpecificSound("throw.wav");
    }
    public void manger(String selecteRadio) {
        switch (selecteRadio) {
            case "noodleRadio":
                if (getInventaire().estDisponible("Nouilles", 1)) {
                    getInventaire().eneleverObjet("Nouilles", 1);
                    regenerer((int)(maxHP.getValue()*0.75));
                    SoundPlayer.playSpecificSound("eating.wav");
                }
                else console.afficherItemIndisponible("nouille");
                break;
            case "mielRadio":
                if (getInventaire().estDisponible("Miel", 1)) {
                    getInventaire().eneleverObjet("Miel", 1);
                    regenerer((int)(maxHP.getValue()*0.3));
                    SoundPlayer.playSpecificSound("eating.wav");
                }
                else console.afficherItemIndisponible("miel");
                break;
            case "meatRadio":
                if (getInventaire().estDisponible("Viande", 1)) {
                    getInventaire().eneleverObjet("Viande", 1);
                    regenerer((int)(maxHP.getValue()/2));
                    SoundPlayer.playSpecificSound("eating.wav");
                }
                else console.afficherItemIndisponible("viande");
                break;
            case "Potion":
                if (getInventaire().estDisponible("Potion", 1)) {
                    getInventaire().eneleverObjet("Potion", 1);
                    regenerer((int)(maxHP.getValue()));
                    SoundPlayer.playSpecificSound("potion.wav");
                }
                else console.afficherItemIndisponible("potion");
                break;
            default:
                break;
        }
    }
    public void mourrir() {
        SoundPlayer.playerDied();
        zone.getProjectiles().clear();
        setHp(maxHP.getValue());
        inventaire.clearInventaire();
        inventaire.ajouterObjet("Miel", 1);
        console.afficherMort();
    }

    public void regenerer(int hp){
        console.afficherHeal(hp);
        this.hp.setValue(this.hp.getValue()+hp);
        if(this.hp.getValue()>maxHP.getValue()){
            this.hp.setValue(maxHP.getValue());
        }
    }

    public void loot(){
        Ressource r;
        for(int i = zone.getListeRessource().size()-1;i>=0;i--){
            r = zone.getListeRessource().get(i);
            if(r.getCentreRessourceX()<=getCentreJoueurX()+20 && r.getCentreRessourceX()>=getCentreJoueurX()-20
            && r.getCentreRessourceY()<=getCentreJoueurY()+20 && r.getCentreRessourceY()>=getCentreJoueurY()-20) {
                if(r.getRecompense().equals("Bois"))
                    SoundPlayer.playSpecificSound("wood.wav");
                else if(r.getRecompense().equals("MineraiBrut"))
                    SoundPlayer.playSpecificSound("mining.wav");
                getInventaire().ajouterObjet(r.getRecompense(), r.getQuantite());
                console.afficherItemRecup(r.getRecompense(), r.getQuantite());
                zone.getMapObstacles()[r.getY()/16][r.getX()/16] = -1;
                zone.getListeRessource().remove(r);
            }
        }
    }

    public Acteur parler(){
        for(Acteur a : zone.getListeActeurs()){
            if(a instanceof Pnj && isCollinding(a.getX(), a.getY())) {
                listeQuetes.talkTracker(a);
                return a;
            }
        }
        return null;
    }

    public void acheterArme(Arme armechoisie) {
        if(getInventaire().getNbrOr()>=armechoisie.getValue()) {
            getInventaire().setNbrOr(getInventaire().getNbrOr() - armechoisie.getValue());
            setArme(armechoisie);
        }
        else{
            getConsole().afficherArgentManquant();
        }
    }
}
