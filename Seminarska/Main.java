public class Main {
    //
    // Razred, ki nalozi podatke ob zagonu 
    //

    public static String uporabnikiFilename = "uporabniki.txt";
    public static String pocitniceFilename = "pocitnice.txt";

    public static void main(String[] args) throws Exception{
        TuristicnaAgencija agencija = new TuristicnaAgencija();
        FileManager.readFromFileUporabniki();
        FileManager.readFromFilePocitnice();
        agencija.mainLoop();
    }
}
