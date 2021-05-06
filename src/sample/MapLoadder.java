package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;




public class MapLoadder {
    public static void main(String[] args) {
        mapLoad();
    }
    public static void mapLoad(){
        try {
            FileReader fr=new FileReader("sample/tilemaps/HerbeDeFond.csv");
            BufferedReader csvReader = new BufferedReader(fr);
            String lignelue = csvReader.readLine();
            while (lignelue != null) {
                System.out.println(lignelue);
                lignelue=csvReader.readLine();
            }
        }catch(Exception e){
            System.out.println("pas trouvéeee");
        };
    }
}
