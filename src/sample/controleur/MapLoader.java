package sample.controleur;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//><

public class MapLoader {


    public MapLoader() {
    }

    public int[][] LoadTileMap(String mapName) throws IOException {
        int TileValue;
        int numeroLigne = 0;
        int nombreDeLigne = 0;
        int nombreDeColonne = 1;
        try {
            FileReader fr = new FileReader("src/sample/ressources/tilemaps/"+mapName+".csv");

            BufferedReader csvReader = new BufferedReader(fr);

            FileReader fr2 = new FileReader("src/sample/ressources/tilemaps/"+mapName+".csv");

            BufferedReader csvReader2 = new BufferedReader(fr2);


            // compteur de lignes dans le fichier lu.
            while (csvReader.readLine() != null) {
                nombreDeLigne++;
            }

            csvReader.close();

            String lignelue = csvReader2.readLine();

            for (int i = 0; i < lignelue.length(); i++) {
                if (lignelue.charAt(i) == ',')
                    nombreDeColonne++;
            }

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

            return map;
        } catch (Exception e) {
            System.out.println("fichier introuvable");
            return null;
        }
    }
}