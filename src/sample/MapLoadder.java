package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

public class MapLoadder {
    public static void main(String[] args) {
        mapLoad();
    }
    public static void mapLoad(){
        try {
            FileReader fr=new FileReader("sample/ressources/HerbeDeFond.csv");
            BufferedReader csvReader = new BufferedReader(fr);
            String lignelue = csvReader.readLine();
            while (lignelue != null) {
                System.out.println(lignelue);
            }
        }catch(Exception e){
            System.out.println("pas trouvéeee");
        };
    }
}
