import java.util.HashSet;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TuristicnaAgencija  {
    public static HashSet<Pocitnice> pocitnice = new HashSet<Pocitnice>();

    private Vnesnik prijava;

    //Methodes

    public void mainLoop() throws Exception{
        BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
        mainLoop:
        while (true){
            System.out.println("\n");
            this.prijava.sporociloMoznosti();
            String input = bis.readLine();
            System.out.println("\n");
            switch (input){
                case "q":
                break mainLoop;
                case "r":{
                    this.prijava.registrirati(bis);
                    FileManager.writeToFileUporabniki("uporabniki.txt");
                    }
                break;
                case "p":{
                    // this.prijava
                    System.out.print("\nVnesite uporabnisko ime:\n");
                    String uporabniskoIme = bis.readLine();
                    System.out.print("\nVnesite geslo:\n");
                    String geslo = bis.readLine();
                    this.prijava = this.prijava.prijaviti(uporabniskoIme, geslo);
                    }
                break;
                case "m":{
                    this.prijava.mojeRezervacije(bis);
                }
                break;
                case "b":{
                    this.prijava.brisiUporabnik(bis);
                    FileManager.writeToFileUporabniki("uporabniki.txt");
                }
                break;
                case "n":{
                    this.prijava.vnosNovihPocitnic(bis);
                    FileManager.writeToFilePocitnice("pocitnice.txt");
                }
                break;
                case "a":{
                    this.prijava.pokaziVse(bis);
                }
                break;
                case "s":{
                    this.prijava.spremeniPocitnice(bis);
                    FileManager.writeToFilePocitnice("pocitnice.txt");
                }
                break;
                case "e":{
                    this.prijava.odstraniPocitnice(bis);
                    FileManager.writeToFilePocitnice("pocitnice.txt");
                }
                break;
                case "v":{
                    this.prijava.mojeRezervacije(bis);
                }
                break;
                case "d":{
                    this.prijava.iskanje(bis);
                }
                break;
                case "t":{
                    this.prijava.rezervirati(bis);
                    FileManager.writeToFilePocitnice("pocitnice.txt");
                }
                break;
                default:
                    System.out.println("\nVnos je napacen\n");
            }
        };
        bis.close();
    }; 

    // Constructor

    public TuristicnaAgencija(){
        this.prijava = new Vnesnik();
    }
}
