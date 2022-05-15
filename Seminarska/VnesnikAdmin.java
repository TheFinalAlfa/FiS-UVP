import java.io.BufferedReader;
import java.util.Iterator;

public class VnesnikAdmin extends VnesnikUporabnik{
    public VnesnikAdmin(String uporabniskoIme) {
        super(uporabniskoIme);
    }

    @Override
    public void sporociloMoznosti(){
        super.sporociloMoznosti();
        System.out.println("Za brisanje uporabnikov vnesite (b)");
        System.out.println("Za brisanje pocitnic vnesite (e)");
        System.out.println("Za nov vnos pocitnic vnesite (n)");
        System.out.println("Za prikaz vseh uporabnikov ali pocitnic vnesite (a)");
        System.out.println("Za spreminanje pocitnic vnesite (s)");
    } 

    @Override
    public void registrirati(BufferedReader bis) throws Exception{
        // Ta rešitev mi ni všeč...
        System.out.println("\nVnesite ime");
        String ime = bis.readLine();
        System.out.println("\nVnesite priimek");
        String priimek = bis.readLine();
        System.out.println("\nVnesite uporabnisko ime");
        String uporabniskoIme = bis.readLine();
        System.out.println("\nVnesite geslo");
        String geslo = bis.readLine();
        boolean isAdmin = false;
        if (this.getPrijavlenUporabnik().getIsAdmin()){
            System.out.println("\nJe uporabnik administrator?");
            System.out.println("(t)rue or (f)alse\n");
            if ((bis.readLine() == "t")){
                isAdmin = true;
            }
        }
        Vnesnik.registriratiNovo(uporabniskoIme, geslo, ime, priimek, isAdmin);
    };

    @Override
    public void brisiUporabnik(BufferedReader bis) throws Exception {
        System.out.print("\nVnesite uporabnisko ime za brisanje\n");
        String uporabniskoIme = "";
        uporabniskoIme = bis.readLine();
        if (Vnesnik.uporabniki.containsKey(uporabniskoIme)){
            if (!(Vnesnik.uporabniki.get(uporabniskoIme).getIsAdmin() &&
                Uporabnik.getSteviloAdministratorjev() == 1)){
                    System.out.print("Število administratorjev ne sme biti manj kot eden.");
                }
                Vnesnik.uporabniki.remove(uporabniskoIme);
                for (Pocitnice pocitnice : TuristicnaAgencija.pocitnice){
                    Iterator<Termin> iteratorTermin = pocitnice.getTermini();
                    if (iteratorTermin.hasNext()){
                        Termin termin = iteratorTermin.next();
                        termin.removeRezervacija(uporabniskoIme);
                    }
                }
            }
        else {
            System.out.println("Uporabnik s tem uporabniskim imenom ne obstaja.");
        }
    }

    @Override
    public void vnosNovihPocitnic(BufferedReader bis) throws Exception{
        // new Pocitnice(maxOseb, drzava, cena);
        System.out.println("Vnesite maksimalno stevilo oseb s stevilko");
        int maxOseb = Integer.parseInt(bis.readLine());
        System.out.println("Vnesite drzavo");
        String drzava = bis.readLine();
        System.out.println("Vnesite ceno");
        int cena = Integer.parseInt(bis.readLine());
        new Pocitnice(maxOseb, drzava, cena);
    }

    @Override
    public void pokaziVse(BufferedReader bis) throws Exception{
        System.out.println("Za prikaz vseh uporabnikov vnesite (u)");
        System.out.println("Za prikaz vseh pocitnice vnesite (p)");
        System.out.println("\n");
        String vnos = bis.readLine();
        System.out.println("\n");
        switch (vnos){
            case "u":{
                for (Uporabnik u : Vnesnik.uporabniki.values()){
                    u.print();
                    System.out.println("\n");
                }
            }
            break;
            case "p":{
                for (Pocitnice p : TuristicnaAgencija.pocitnice){
                    p.print(this.isAdmin());
                    System.out.println("\n");
                }
            }
            break;
            default:{
                System.out.println("Ne veljaven vnos");
            }
        }
    }

    @Override
    public void spremeniPocitnice(BufferedReader bis) throws Exception{
        System.out.println("\n");
        System.out.println("Vnesite id pocitnic, ki jih zelite spremeniti");
        int id = Integer.parseInt(bis.readLine());
        Pocitnice p = new Pocitnice();
        Iterator<Pocitnice> iter = TuristicnaAgencija.pocitnice.iterator();
        boolean wrongId = true;
        while (iter.hasNext()){
            p = iter.next();
            if (p.getId() == id){
                wrongId = false;
                break;
            }
        }
        if (wrongId){
            System.out.println("Ta id ne obstaja");
            return;
        }
        loopSpreminjanje:
        while (true){
            Pocitnice.spremembeSporocilo();
            String vnos = bis.readLine();
            switch (vnos){
                case "q":{}
                break loopSpreminjanje;
                case "t":{
                    System.out.println("Vnesite nov tip pocitnic");
                    p.setType(bis.readLine());
                }
                break;
                case "m":{
                    System.out.println("Vnesite novo maksimalno stevilo ljudi");
                    p.setMaxOseb(Integer.parseInt(bis.readLine()));
                }
                break;
                case "d":{
                    System.out.println("Vnesite novo drzavo");
                    p.setDrzava(bis.readLine());
                }
                break;
                case "c":{
                    System.out.println("Vnesite novo ceno");
                    p.setCena(Integer.parseInt(bis.readLine()));
                }
                break;
                case "a":{
                    System.out.println("Vnesite leto prihoda");
                    long year = Long.parseLong(bis.readLine());
                    System.out.println("Vnesite mesec prihoda");
                    int mounth = Integer.parseInt(bis.readLine());
                    System.out.println("Vnesite dan prihoda");
                    int day = Integer.parseInt(bis.readLine());
                    System.out.println("Vnesite uro prihoda");
                    int hour = Integer.parseInt(bis.readLine());
                    
                    System.out.println("Vnesite leto odhoda");
                    long yearOdhoda = Long.parseLong(bis.readLine());
                    System.out.println("Vnesite mesec odhoda");
                    int mounthOdhoda = Integer.parseInt(bis.readLine());
                    System.out.println("Vnesite dan odhoda");
                    int dayOdhoda = Integer.parseInt(bis.readLine());
                    System.out.println("Vnesite uro odhoda");
                    int hourOdhoda = Integer.parseInt(bis.readLine());

                    Termin termin = new Termin(new Datum(year, mounth, day, hour),
                        new Datum(yearOdhoda, mounthOdhoda, dayOdhoda, hourOdhoda));
                    p.addTermin(termin);
                }
                break;
                case "r":{
                    Iterator<Termin> iterTermin = p.getTermini();
                    while (iterTermin.hasNext()){
                        iterTermin.next().printId();
                    }
                    System.out.println("Vnesite id zelenega termina");
                    int idTermin = Integer.parseInt(bis.readLine());
                    p.removeTermin(idTermin);
                }
                break;
                default:{
                    System.out.println("Napačen vnos");
                }
            }
        };
    }

    @Override
    public void iskanjePocitnicId(BufferedReader bis) throws Exception{
        System.out.println("Vnesite ID pocitnic");
        int id = Integer.parseInt(bis.readLine());
        Iterator<Pocitnice> iter = TuristicnaAgencija.pocitnice.iterator();
        while (iter.hasNext()){
            Pocitnice pocitnice = iter.next();
            if (pocitnice.getId() == id){
                pocitnice.print(this.getPrijavlenUporabnik().getIsAdmin());
                return;
            }
        }
        System.out.println("Pocitnic z izbranim id ni mogoce najti");
    }

    @Override
    public void odstraniPocitnice(BufferedReader bis) throws Exception{
        System.out.println("Vnesi ID pocitnic za odstraniti");
        int id = Integer.parseInt(bis.readLine());
        Iterator<Pocitnice> iter = TuristicnaAgencija.pocitnice.iterator();
        while (iter.hasNext()){
            Pocitnice pocitnice = iter.next();
            if (pocitnice.getId() == id){
                iter.remove();
                System.out.println("Uspesno odstranjeno");
                return;
            }
        }
        System.out.println("Neuspesno odstranjeno");
    }
}
