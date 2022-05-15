public class Main {
    //
    // Glavni uporabniski vnesnik. ki nato naprej kliče 
    //

    public static String uporabnikiFilename = "Seminarska/txt/uporabniki.txt";
    public static String pocitniceFilename = "Seminarska/txt/pocitnice.txt";

    public static void main(String[] args) throws Exception{
        TuristicnaAgencija agencija = new TuristicnaAgencija();
        FileManager.readFromFileUporabniki();
        FileManager.readFromFilePocitnice();
        agencija.mainLoop();
    }
}
