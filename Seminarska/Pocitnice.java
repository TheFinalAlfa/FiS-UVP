import java.io.BufferedReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Pocitnice {
    //
    // Razlicne pocitnice in njene funkcionalnosti
    //

    private int id;
    private static int lastId = 0;
    private String type;
    private int maxOseb;
    private String drzava;
    private int cena;
    private LinkedList<Termin> termini;

    public Pocitnice(int maxOseb, String drzava, int cena){
        this.id = Pocitnice.lastId;
        Pocitnice.lastId++;
        this.type = "Pocitnice";
        this.maxOseb = maxOseb;
        this.drzava = drzava;
        this.cena = cena;
        this.termini = new LinkedList<Termin>();
        TuristicnaAgencija.pocitnice.add(this);
    }

    public Pocitnice(){
        this.id = Pocitnice.lastId;
        Pocitnice.lastId++;
        this.termini = new LinkedList<Termin>();
        TuristicnaAgencija.pocitnice.add(this);
    }

    public void print(boolean isAdmin){
        this.printPure(isAdmin);
        System.out.println("\n");
        System.out.println("Termini: ");
        for (Termin termin : this.termini){
            System.out.println("\n");
            termin.print(isAdmin);
            this.printAvailability(termin.getPrihod(), termin.getOdhod());
        }
    }

    public void spremembeSporocilo(){
        Pocitnice.spremembeSporociloPocitnice();
    }
    
    public static void spremembeSporociloPocitnice(){
        System.out.println("Za spremembo tipa vnesite (t)");
        System.out.println("Za spremembo maksimalnega stevila ljudi vnesite (m)");
        System.out.println("Za spremembo drzave vnesite (d)");
        System.out.println("Za spremembo cene vnesite (c)");
        System.out.println("Za dodajanje termina vnesite (a)");
        System.out.println("Za odstranjevanje termina vnesite (r)");
        System.out.println("Za prenehanje sprememb vnesite (q)");
    }

    public boolean spremeni(String string, BufferedReader bis) throws Exception{
        switch (string) {
            case "m":{
                System.out.println("Vnesite novo maksimalno stevilo ljudi");
                this.setMaxOseb(Integer.parseInt(bis.readLine()));
                return true;
            }
            case "d":{
                System.out.println("Vnesite novo drzavo");
                this.setDrzava(bis.readLine());
                return true;
            }
            case "c":{
                System.out.println("Vnesite novo ceno");
                this.setCena(Integer.parseInt(bis.readLine()));
                return true;
            }
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
                this.addTermin(termin);
                return true;
            }
            case "r":{
                Iterator<Termin> iterTermin = this.getTermini();
                while (iterTermin.hasNext()){
                    iterTermin.next().printId();
                }
                System.out.println("Vnesite id zelenega termina");
                int idTermin = Integer.parseInt(bis.readLine());
                this.removeTermin(idTermin);
                return true;
            }
            default:{
                return false;
            }
        }
    }
    
    public void printPure(boolean isAdmin){
        // Prints just the information on the instance and not any Rezervations
        System.out.println("ID: " + this.id);
        System.out.println("Tip: " + this.type);
        System.out.println("Cena: " + this.cena);
        System.out.println("Drzava: " + this.drzava);
        if (isAdmin){
            System.out.println("Maksimalno oseb: " + Integer.toString(this.maxOseb));
        }
    }

    public void printAvailability(Datum prihod, Datum odhod){
        for (Termin t : this.termini){
            if (odhod.before(t.getOdhod()) || t.getPrihod().before(prihod)){
                if (t.getSteviloOseb() < this.maxOseb){
                    System.out.println("Zagotovljeno");
                    return;
                };
            };
        };
        System.out.println("Ni zagotovljeno");
    }

    public boolean isAble(Datum prihod, Datum odhod){
        boolean able = false;
        for (Termin t : this.termini){
            if (odhod.before(t.getOdhod()) || t.getPrihod().before(prihod)){
                if (t.getSteviloOseb() < this.maxOseb){
                    able = true;
                }
            }
        }
        return able;
    }

    public void printTerminiId(boolean isAdmin){
        for (Termin termin : this.termini){
            termin.printId();
        }
    }

    public static int getLastId() {
        return lastId;
    }

    public static void setLastId(int lastId) {
        Pocitnice.lastId = lastId;
    }

    public int getId() {
        return id;
    }

    public Iterator<Termin> getTermini(){
        return this.termini.listIterator();
    }

    @Override
    public String toString(){
        String str = new String();
        str += this.type + ";";
        str += (Integer.toString(this.maxOseb) + ";");
        str += (this.drzava + ";");
        str += Integer.toString(this.cena) + ";";
        if (this.termini.size() > 0){
            Iterator<Termin> iter = this.termini.descendingIterator();
            str += iter.next().toString();
            while (iter.hasNext()){
                str += "~";
                str += iter.next().toString();
            }
        };
        str += ";";
        return str;
    }

    public static void fromString(String string){
        String[] str = string.split(";");
        switch (str[0]){
            case "Pocitnice":{
                new Pocitnice(string);
            }break;
            case "Potovanje":{
                new Potovanje(string);
            }break;
            case "Krizarjenje":{
                new Krizarjenje(string);
            }break;
            case "Kampiranje":{
                new Kampiranje(string);
            }break;
        }
    }
    
    public Pocitnice(String string){
        this.type = "Pocitnice";
        this.id = Pocitnice.lastId;
        Pocitnice.lastId++;
        this.termini = new LinkedList<Termin>();
        TuristicnaAgencija.pocitnice.add(this);
        
        String[] str = string.split(";");
        this.maxOseb = Integer.parseInt(str[1]);
        this.drzava = str[2];
        this.cena = Integer.parseInt(str[3]);
        if (str.length > 4){
            this.assignTermini(str[4]);
        }
    }

    private void assignTermini(String string){
        if (!(string.equals(""))){
            for (String terminRaw : string.split("~")){
                Termin termin = new Termin();
                termin.fromString(terminRaw);
                this.termini.add(termin);
            }
        }
    }

    public void addRezervacija(Termin termin, Rezervacija rezervacija){
        if (this.termini.contains(termin)){
            for (Termin t : this.termini){
                if (termin.equals(t)){
                    t.addRezervacija(rezervacija);
                }
            }
        }
    }

    public void addTermin(Termin termin){
        boolean added = false;
        if (termini.size() <= 0){
            this.termini.addLast(termin);
            added = true;
        }
        else if (this.termini.getLast().getOdhod().before(termin.getPrihod())){
            this.termini.addLast(termin);
            added = true;
        }
        else if (termin.getOdhod().before(this.termini.getFirst().getPrihod())){
            this.termini.addFirst(termin);
            added = true;
        }
        else {
            ListIterator<Termin> iterator = this.termini.listIterator(1);
            Datum odhod = this.termini.getFirst().getOdhod();
            while (iterator.hasNext()){
                Termin obstojecTermin = iterator.next();
                if (obstojecTermin.getPrihod().after(termin.getOdhod()) && 
                    odhod.before(termin.getPrihod())){
                        iterator.add(termin);
                        added = true;
                        break;
                    }
                else {
                    odhod = obstojecTermin.getOdhod();
                }
            }

        }
        if (added){
            System.out.println("Izbrani termin je dodan.");
        }
        else {
            System.out.println("Izbrani termin se prekriva z že obstoječim.");
        }
    }

    public void removeTermin(int id){
        for (ListIterator<Termin> iter = this.termini.listIterator(); iter.hasNext();){
            Termin datum = iter.next();
            if (datum.getId() == id){
                iter.remove();
                break;
            }
        }
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public void setMaxOseb(int maxOseb) {
        this.maxOseb = maxOseb;
    }

    public int getCena() {
        return cena;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDrzava() {
        return drzava;
    }

    public int getMaxOseb() {
        return maxOseb;
    }

    public String getType() {
        return type;
    }
}
