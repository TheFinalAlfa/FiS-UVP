import java.io.*;

public class FileManager {
    // 
    // Razred s staticnimi metodami za shranjevanje na datoteke in nalaganje iz datotek
    //
    public static void readFromFileUporabniki(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String s = "";
        while ((s = br.readLine()) != null){
            String[] str = s.split(";");
            Vnesnik.uporabniki.put(str[2], new Uporabnik(str[0], str[1], str[2], str[3],
                Boolean.parseBoolean(str[4])));
        };
        br.close();
    }

    public static void writeToFileUporabniki(String filename) throws Exception{
        FileWriter file = new FileWriter(new File("uporabniki.txt"));
        for (Uporabnik up : Vnesnik.uporabniki.values()){
            file.write(up.toString() + "\n");
        };
        file.close();
    };

    public static void writeToFilePocitnice(String pathname) throws Exception{
        FileWriter file = new FileWriter(new File(pathname));
        for (Pocitnice p : TuristicnaAgencija.pocitnice){
            file.write(p.toString() + "\n");
        }
        file.close();
    }

    public static void readFromFilePocitnice(String fileName) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String s = "";
        while ((s = br.readLine()) != null){
            Pocitnice pocitnice = new Pocitnice();
            pocitnice.fromString(s);
        };
        br.close();
    }
}
