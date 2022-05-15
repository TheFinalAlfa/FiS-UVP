import java.io.*;

public class FileManager {

    // 
    // Razred za shranjevanje na datoteke in nalaganje iz datotek
    //
    
    public static void readFromFileUporabniki() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(Main.uporabnikiFilename));
        String s = "";
        while ((s = br.readLine()) != null){
            String[] str = s.split(";");
            Vnesnik.uporabniki.put(str[2], new Uporabnik(str[0], str[1], str[2], str[3],
                Boolean.parseBoolean(str[4])));
        };
        br.close();
    }

    public static void writeToFileUporabniki() throws Exception{
        FileWriter file = new FileWriter(new File(Main.uporabnikiFilename));
        for (Uporabnik up : Vnesnik.uporabniki.values()){
            file.write(up.toString() + "\n");
        };
        file.close();
    };

    public static void writeToFilePocitnice() throws Exception{
        FileWriter file = new FileWriter(new File(Main.pocitniceFilename));
        for (Pocitnice p : TuristicnaAgencija.pocitnice){
            file.write(p.toString() + "\n");
        }
        file.close();
    }

    public static void readFromFilePocitnice() throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(Main.pocitniceFilename));
        String s = "";
        while ((s = br.readLine()) != null){
            Pocitnice.fromString(s);
        };
        br.close();
    }
}
