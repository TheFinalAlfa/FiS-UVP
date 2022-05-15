public class Main {
    //
    // Glavni uporabniski vnesnik. ki nato naprej kliče 
    //
    public static void main(String[] args) throws Exception{
        TuristicnaAgencija agencija = new TuristicnaAgencija();
        FileManager.readFromFileUporabniki("uporabniki.txt");
        FileManager.readFromFilePocitnice("pocitnice.txt");
        agencija.mainLoop();
    }
}
