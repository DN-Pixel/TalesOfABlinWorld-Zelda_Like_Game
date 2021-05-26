package sample.modele.acteurs.ennemis;

import java.util.Stack;

public class BFS {

    private static int[][] mapBFS;

    public static Stack<Integer> start(int posEnnemiX, int posEnnemiY, int posJoueurX, int posJoueurY, int[][] mapObstacle){
        mapBFS = new int[mapObstacle.length][mapObstacle[0].length];
        Stack<Integer> path = new Stack<>();
        for(int i=0;i<mapObstacle.length;i++){
            for(int j=0;j<mapObstacle[i].length;j++){
                if(mapObstacle[i][j]==-1)
                    mapBFS[i][j]=0; // EMPLACEMENT LIBRE
                else
                    mapBFS[i][j]=-1; // OBSTACLE
            }
        }
        mapBFS[posEnnemiY][posEnnemiX] = 1; // PLACE L'ENNEMI
        mapBFS[posJoueurY][posJoueurX] = 8888; // PLACE LE JOUEUR
        int cpt = 1;
        boolean found = false;
        while(!found){
            for(int i=0;i<mapBFS.length;i++){
                for(int j=0;j<mapBFS[i].length;j++) {
                    if(mapBFS[i][j]==cpt){
                        if(i-1>=0 && (mapBFS[i-1][j]==0 || mapBFS[i-1][j]==8888)){
                            if(mapBFS[i-1][j]==8888)
                                found = true;
                            else
                                mapBFS[i-1][j]=cpt+1;
                        }
                        if(i+1<=mapBFS.length && (mapBFS[i+1][j]==0 || mapBFS[i+1][j]==8888)){
                            if(mapBFS[i+1][j]==8888)
                                found = true;
                            else
                                mapBFS[i+1][j]=cpt+1;
                        }
                        if(j-1>=0 && (mapBFS[i][j-1]==0 || mapBFS[i][j-1]==8888)){
                            if(mapBFS[i][j-1]==8888)
                                found = true;
                            else
                                mapBFS[i][j-1]=cpt+1;
                        }
                        if(j+1<=mapBFS[0].length && (mapBFS[i][j+1]==0 || mapBFS[i][j+1]==8888)){
                            if(mapBFS[i][j+1]==8888)
                                found = true;
                            else
                                mapBFS[i][j+1]=cpt+1;
                        }
                    }
                }
            }
            for(int i=0;i<mapBFS.length;i++){
                for(int j=0;j<mapBFS[i].length;j++) {
                }
            }
            cpt++;
        }
        return path;
    }
}