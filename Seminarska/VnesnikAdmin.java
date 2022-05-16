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
        System.out.println("Za iskanje pocitnic po ID vnesite (w)");
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
        loopVnosNovihPocitnic:
        while (true) {
            System.out.println("Vnesite stevilko pred tipom pocitnic");
            System.out.println("(0): Pocitnice");
            System.out.println("(1): Potovanje");
            System.out.println("(2): Kampiranje");
            System.out.println("(3): Krizarjenje");
            String type = bis.readLine();
            switch (type){
                case "0":{
                    new Pocitnice(maxOseb, drzava, cena);
                };break loopVnosNovihPocitnic;
                case "1":{
                    Potovanje.vnosNovihPocitnic(bis, maxOseb, drzava, cena);
                };break loopVnosNovihPocitnic;
                case "2":{
                    Kampiranje.vnosNovihPocitnic(bis, maxOseb, drzava, cena);
                };break loopVnosNovihPocitnic;
                case "3":{
                    Krizarjenje.vnosNovihPocitnic(bis, maxOseb, drzava, cena);
                };break loopVnosNovihPocitnic;
                default:{
                    System.out.println("Ni pravilen vnos tipa pocitnic");
                }
            }
        }
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
        for (Pocitnice pocitnice : TuristicnaAgencija.pocitnice){
            if (pocitnice.getId() == id){
                loopSpreminjanje:
                while (true){
                    pocitnice.spremembeSporocilo();
                    String vnos = bis.readLine();
                    switch (vnos){
                        case "q":{
                            break loopSpreminjanje;
                        }
                        default:{
                            if (!pocitnice.spremeni(vnos, bis)){
                                System.out.println("Napacen vnos");
                            }
                        }
                    }
                }
                return;
            }
        }
        System.out.println("Ta id ne obstaja");
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
