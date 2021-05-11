package sample;

import com.sun.xml.internal.ws.util.StringUtils;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.bind.JAXBException;
//><

public class MapLoadder {


    public MapLoadder() {
    }

    public int[][] LoadTileMap(String mapName) throws IOException {
        int TileValue;
        int numeroLigne = 0;
        int nombreDeLigne = 0;
        int nombreDeColonne = 1;
        try {
            FileReader fr = new FileReader("src/sample/tilemaps/"+mapName+".csv");
            //FileReader fr = new FileReader("/sample/tilemaps/" + folderName);

            BufferedReader csvReader = new BufferedReader(fr);

            FileReader fr2 = new FileReader("src/sample/tilemaps/"+mapName+".csv");

            BufferedReader csvReader2 = new BufferedReader(fr2);


            // compteur de lignes dans le fichier lu.
            while (csvReader.readLine() != null) {
                nombreDeLigne++;
            }
            //test si nombre de ligne marche
            //System.out.println(nombreDeLigne);

            csvReader.close();

            String lignelue = csvReader2.readLine();

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
                lignelue = csvReader2.readLine();
                numeroLigne++;
            }


            //test d'affichage de l'array.
            /*
            for (int i = 0; i < map.length;i++){
                for (int j = 0; j < map[i].length;j++){
                    System.out.print(map[i][j]+ " ");
                }
                System.out.println();
            }
            */
            return map;
        } catch (Exception e) {
            System.out.println("fichier introuvable");
            return null;
        }
    }
}