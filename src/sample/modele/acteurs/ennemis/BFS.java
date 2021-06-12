package sample.modele.acteurs.ennemis;

import java.util.Stack;

public class BFS {

    private int[][] mapBFS;

    public BFS(){

    }

    public  Stack<Integer> start(int posEnnemiX, int posEnnemiY, int posJoueurX, int posJoueurY, int[][] mapObstacle){
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
        int actualI = 0;
        int actualJ = 0;
        while(!found){
            for(int i=0;i<mapBFS.length;i++){
                for(int j=0;j<mapBFS[i].length;j++) {
                    if(mapBFS[i][j]==cpt){
                        if(i-1>=0 && (mapBFS[i-1][j]==0 || mapBFS[i-1][j]==8888)){
                            if(mapBFS[i-1][j]==8888){
                                found = true;
                                actualI = i-1;
                                actualJ = j;
                            }
                            else
                                mapBFS[i-1][j]=cpt+1;
                        }
                        if(i+1<mapBFS.length && (mapBFS[i+1][j]==0 || mapBFS[i+1][j]==8888)){
                            if(mapBFS[i+1][j]==8888){
                                found = true;
                                actualI = i+1;
                                actualJ = j;
                            }
                            else
                                mapBFS[i+1][j]=cpt+1;
                        }
                        if(j-1>=0 && (mapBFS[i][j-1]==0 || mapBFS[i][j-1]==8888)){
                            if(mapBFS[i][j-1]==8888){
                                found = true;
                                actualI = i;
                                actualJ = j-1;
                            }
                            else
                                mapBFS[i][j-1]=cpt+1;
                        }
                        if(j+1<mapBFS[0].length && (mapBFS[i][j+1]==0 || mapBFS[i][j+1]==8888)){
                            if(mapBFS[i][j+1]==8888){
                                found = true;
                                actualI = i;
                                actualJ = j+1;
                            }
                            else
                                mapBFS[i][j+1]=cpt+1;
                        }
                    }
                }
            }
            cpt++;
        }

        found = false;
        //1 pour down; 2 pour up; 3 pour right; 4 pour left; 5 pour none
        while (!found){
            if(actualI-1>=0 && mapBFS[actualI-1][actualJ]==cpt-1){
                path.add(1);
                if(actualI!=1)
                    actualI--;
                if(mapBFS[actualI-1][actualJ]==1)
                    found = true;
            }
            else if(actualI+1<mapBFS.length && mapBFS[actualI+1][actualJ]==cpt-1){
                path.add(2);
                if(actualI!=mapBFS.length-2)
                    actualI++;
                if(mapBFS[actualI+1][actualJ]==1)
                    found = true;
            }
            else if(actualJ-1>=0 && mapBFS[actualI][actualJ-1]==cpt-1){
                path.add(3);
                if(actualJ!=1)
                    actualJ--;
                if(mapBFS[actualI][actualJ-1]==1)
                    found = true;
            }
            else if(actualJ+1<mapBFS[0].length && mapBFS[actualI][actualJ+1]==cpt-1){
                path.add(4);
                if(actualJ!=mapBFS[0].length-2)
                    actualJ++;
                if(mapBFS[actualI][actualJ+1]==1)
                    found = true;
            }
            else{
                found = true;
            }
            cpt--;
        }
        return path;
    }
}