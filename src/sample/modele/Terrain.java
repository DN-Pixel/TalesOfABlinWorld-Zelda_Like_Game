package sample.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.controleur.SoundPlayer;
import sample.modele.acteurs.Acteur;
import sample.modele.acteurs.Pnj;
import sample.modele.acteurs.SaveActeurs;
import sample.modele.acteurs.ennemis.*;
import sample.modele.ressources.Ressource;
import sample.modele.ressources.SaveRessources;
import sample.modele.ressources.SourceBois;
import sample.modele.ressources.SourceMinerai;


public class Terrain {

    private String nomDeCarte;
    private int [][] mapObstacles; // MAP DES OBSTACLES ET COLLISIONS
    private SaveActeurs saveActeurs = new SaveActeurs();
    private ObservableList<Projectile> projectiles = FXCollections.observableArrayList();
    private SaveRessources saveRessources = new SaveRessources();
    private int[][] mapSpawn; // ZONE DE SPAWN DES ENNEMIS
    private Console console;

    public SaveActeurs getSaveActeurs() {
        return saveActeurs;
    }

    public SaveRessources getSaveRessources() {
        return saveRessources;
    }

    public Terrain (String nomDeCarte) {
        this.nomDeCarte = nomDeCarte;
    }

    public void setNomDeCarte (String newNom) {
        this.nomDeCarte = newNom;
    }

    public String getNomDeCarte () {
        return this.nomDeCarte;
    }

    public void setMapObstacles (int[][] newMap) {
        this.mapObstacles = newMap;
    }

    public int[][] getMapObstacles () {
        return this.mapObstacles;
    }
    public ObservableList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void loadPnjHitboxes(){
        int numero = getNumeroCarte(); // récupère le numéro de la carte
        Acteur a;
        for(int i=0;i<saveActeurs.getSave(numero).size();i++){
            a = saveActeurs.getSave(numero).get(i);
            if(a instanceof Pnj)
                mapObstacles[a.getY()/16][a.getX()/16] = 7777; // 7777 -> PNJ
        }
    }

    public int limiteVertiMap () {
        int limiteV = 0;
        for (int j = 0; j < this.mapObstacles.length; j++) {
            limiteV++;
        }
        return limiteV;
    }

    public int limiteHorizMap () {
        int limiteH = 0;
        for (int i = 0; i < this.mapObstacles[0].length; i++) {
            limiteH++;
        }
        return limiteH;
    }

    public int getLongueurMap () {
        int longueur = 0;
        for (int i = 0; i < this.mapObstacles.length; i++) {
            for (int j = 0; j < this.mapObstacles[i].length; j++) {
                longueur++;
            }
        }
        return longueur;
    }

    public int getNumeroCarte(){
        return Integer.parseInt(nomDeCarte.substring((nomDeCarte.length()-1)));
    }

    public ObservableList<Acteur> getListeActeurs() {
        int numero = getNumeroCarte();
        return saveActeurs.getSave(numero);
    }

    public ObservableList<Ressource> getListeRessource() {
        int numero = getNumeroCarte();
        return saveRessources.getSave(numero);
    }
    /*
    CHERCHE DANS LA LISTE DES ACTEURS SI IL EXSITE
     */
    public boolean findActeur(String id){
        for(Acteur a: getListeActeurs()){
            if(a.getId().equals(id))
                return true;
        }
        return false;
    }

    /*
    CHERCHE DANS LA LISTE DES RESSOURCES SI IL EXSITE
     */
    public boolean findRessource(String id){
        for(Ressource a: getListeRessource()){
            if(a.getId().equals(id))
                return true;
        }
        return false;
    }


    public void setMapSpawn(int[][] mapSpawn) {
        this.mapSpawn = mapSpawn;
    }

    public void EnemySpawn () {
        //Si la limite d'ennemis est atteinte on ne fait rien spawn, si on est dans la ville (map1) ou la map0 non plus.
        if (saveActeurs.getSave(getNumeroCarte()).size() >= 10 ||
                getNumeroCarte()==1 || getNumeroCarte()==0)
            return;

        //gestion du spawn des ennemis en fonction de la zone.
        double x = Math.random();

        for (int i = 0; i < mapSpawn.length; i++) {
            for (int j = 0; j < mapSpawn[0].length; j++) {
                //l'ennemis spawn dans le map de spawn si l'emplacement est disponible dans la map obstacle.
                if (mapSpawn[i][j] == 3415 && mapObstacles[i][j]==-1 &&  Math.random()<0.015 ) {
                    switch (getNumeroCarte()) {
                        case 2:
                            if (x < .5) saveActeurs.getSave(2).add(new Hibou(j * 16, i * 16));
                            else saveActeurs.getSave(2).add(new Slime(j * 16, i * 16));
                            break;
                        case 3:
                            if (x < .5) saveActeurs.getSave(3).add(new Reptile(j  * 16, i * 16));
                            else saveActeurs.getSave(3).add(new Bambou(j  * 16, i * 16));
                            break;
                        case 4:
                            if (x < .5) saveActeurs.getSave(4).add(new Bete(j  * 16, i * 16));
                            else saveActeurs.getSave(4).add(new Oeil(j * 16, i * 16));
                            break;
                        case 5 :
                            if (x < .5) saveActeurs.getSave(5).add(new Bambou(j * 16, i * 16));
                            else saveActeurs.getSave(5).add(new Hibou(j * 16, i * 16));
                            break;
                        case 6 :
                            saveActeurs.getSave(6).add(new Oeil(j * 16, i * 16));
                            break;
                        default:
                            break;
                    }
                    //mapObstacles[i][j]=6666; // -> apres qu'un ennemi ait spawn, on change le tableau 2D mapObstacle en y ajoutant le-dit ennemi.
                    return;
                }
            }
        }
    }

    public void ressourceSpawn(){
        // si il y a deja 10 ressources ou nous sommes dans la zone 1, rien ne spawn
        if(getListeRessource().size()>10 || getNumeroCarte()==1  || getNumeroCarte()==6 || getNumeroCarte()==0)
            return;
        boolean spawned = false;
        int i = 0;
        int j = 0;
        while(!spawned){
            i = (int)(Math.random()*mapObstacles.length);
            j = (int)(Math.random()*mapObstacles[0].length);
            if(mapObstacles[i][j]==-1){
                if(Math.random()<=0.5)
                    getListeRessource().add(new SourceBois(j*16, i*16));
                else
                    getListeRessource().add(new SourceMinerai(j*16, i*16));
                mapObstacles[i][j] = 1880;
                spawned = true;
            }
        }
    }

    // parcours la liste des acteurs pour faire bouger uniquement les ennemis
    public void moveEnnemis(){
        for(Acteur a : getListeActeurs()){
            if(a instanceof Ennemi)
                ((Ennemi) a).moveEnnemi(mapObstacles);
        }
    }

    public void lesEnnemisAttaquent(Joueur joueur){
        for(Acteur a : getListeActeurs()){
            if(a instanceof Ennemi &&  !(a instanceof EnnemiDistance))
                ((Ennemi) a).attaquerJoueur(joueur.getCentreJoueurX(), joueur.getCentreJoueurY(), joueur);
        }
    }
    // supprime les morts de la liste
    public void clean() {
        for(int i = getListeActeurs().size()-1;i>=0;i--){
            Acteur a = getListeActeurs().get(i);
            if(a instanceof Ennemi && ((Ennemi) a).getPv()<=0)
                getListeActeurs().remove(a);
        }
    }
    
    public void spawnProjectile (Joueur joueur){
        for (int i=getListeActeurs().size()-1;i>=0;i--) {
            if (getListeActeurs().get(i) instanceof EnnemiDistance && ((EnnemiDistance) getListeActeurs().get(i)).isAggroing() ) {
                //permet de creer un Projectile ayant pour ID le nom de celui qui le lance.
                Projectile p = ((EnnemiDistance) getListeActeurs().get(i)).attaquerJoueur(joueur);
                //Projectiles p = new Projectiles(getListeActeurs().get(i).getCentreActeurX(), getListeActeurs().get(i).getCentreActeurY(), "DOWN", getListeActeurs().get(i).getClass().getSimpleName());
                projectiles.add(p);
            }
        }
    }

    public void manageProjeciles(Joueur joueur){
        Projectile p;
        for (int i= projectiles.size()-1;i >=0 ;i--) {
            if(projectiles.size()<=0)
                return;
            //si je suis hors map. alos je remove l'objet de la liste
            p = projectiles.get(i);
            p.moveProjectile();
            if (p.getY() > limiteVertiMap() * 17 ||
                    p.getY() < -16 ||
                    p.getX() > limiteHorizMap() * 17 ||
                    p.getX() < -16) {
                projectiles.remove(p);
            }
            else{
                for (int j = this.getListeActeurs().size() - 1; j >= 0; j--) {
                    Acteur a = this.getListeActeurs().get(j);
                    if(a instanceof Ennemi) {
                        //projectiles lancees par moi (joueur)
                        if (p.getId().startsWith("hero") && p.getY()+8 >= a.getCentreActeurY() - ((Ennemi) a).getLargeur() &&
                                p.getY()+8 <= a.getCentreActeurY() + ((Ennemi) a).getLargeur() &&
                                p.getX()+8 <= a.getCentreActeurX() + ((Ennemi) a).getLargeur() &&
                                p.getX()+8 >= a.getCentreActeurX() - ((Ennemi) a).getLargeur()) {
                            projectiles.remove(p);
                            ((Ennemi) a).subirDegat(joueur.getArmeDistance().getDegatsArme());
                            console.afficherDegatsInfliges(joueur.getArmeDistance().getDegatsArme());
                            SoundPlayer.playSpecificSound("shurikenHit.wav");
                        }
                        //projectiles lancees par les ennemis
                        else if (p.getId().startsWith("Ennemi") &&
                                joueur.getCentreJoueurX()<=p.getCentreX()+p.getTailleProjectile() &&
                                joueur.getCentreJoueurX()>=p.getCentreX() - p.getTailleProjectile() &&
                                joueur.getCentreJoueurY()<=p.getCentreY()+ p.getTailleProjectile() &&
                                joueur.getCentreJoueurY()>=p.getCentreY() - p.getTailleProjectile()
                                && a instanceof EnnemiDistance) {
                            projectiles.remove(p);
                            joueur.subirDegats(((Ennemi) a).getPointDegat());
                            break;
                        }
                    }
                }
            }
        }
    }

    public void setConsole(Console console) {
        this.console = console;
    }
}
