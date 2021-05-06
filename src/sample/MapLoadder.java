package sample;

import com.sun.xml.internal.ws.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//><

public class MapLoadder {
    public MapLoadder() {
    }

    public int[][] LoadTileMap() throws IOException {
        int TileValue;
        int numeroLigne = 0;
        int nombreDeLigne = 1;
        int nombreDeColonne = 1;
        try {
            FileReader fr = new FileReader("/home/clone/IdeaProjects/DAS-LinkGame/src/sample/tilemaps/HerbeDeFond.csv");
            //FileReader fr = new FileReader("/sample/tilemaps/" + folderName);

            BufferedReader csvReader = new BufferedReader(fr);

            String lignelue = csvReader.readLine();

            // compteur de lignes dans le fichier lu.
            while (csvReader.readLine() != null) {
                nombreDeLigne++;
            }
            //test si nombre de ligne marche
            //System.out.println(nombreDeLigne);


            for (int i = 0; i < lignelue.length(); i++) {
                if (lignelue.charAt(i) == ',')
                    nombreDeColonne++;
            }
            //test si nombre de colonne marche
            //System.out.println(nombreDeColonne);

            int[][] map = new int[nombreDeLigne][nombreDeColonne];

            //Remplissage du tebleau de INT avec ce qui a été lu précedement
            while (lignelue != null) {
                String[] values = lignelue.split(",");
                for (int i = 0; i < values.length; i++) {
                    TileValue = Integer.parseInt(values[i]);
                    map[numeroLigne][i] = TileValue;
                }
                lignelue = csvReader.readLine();
                numeroLigne++;
            }


            //test d'affichage de l'array.
            /*for (int i = 0; i < map.length;i++){
                for (int j = 0; j < map[0].length;j++){
                    System.out.print(map[i][j]+ " ");
                }
                System.out.println();
            }*/
            return map;
        } catch (Exception e) {
            System.out.println("fichier introuvable");
            return null;
        }
    }
}