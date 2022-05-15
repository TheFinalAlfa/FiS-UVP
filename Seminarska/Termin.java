import java.util.HashSet;
import java.util.Iterator;

public class Termin {
    private int id;
    private static int lastId = 0;
    private Datum prihod;
    private Datum odhod;
    private int steviloOseb;
    private HashSet<Rezervacija> rezervacija = new HashSet<Rezervacija>();

    public boolean equals(Termin other){
        return other.getPrihod().equals(this.prihod) && other.getOdhod().equals(this.odhod);
    }

    public Termin (){
        this.steviloOseb = 0;
        this.id = Termin.lastId;
        Termin.lastId++;
    }

    public Termin(Datum prihod, Datum odhod){
        this.odhod = odhod;
        this.prihod = prihod;
        this.steviloOseb = 0;
        this.id = Termin.lastId;
        Termin.lastId++;
    }

    public void removeRezervacija(String uporabniskoIme){
        for (Rezervacija rezervacija : this.rezervacija){
            if (rezervacija.getUsernameUporabnika().equals(uporabniskoIme)){
                this.rezervacija.remove(rezervacija);
            }
        }
    }

    public boolean hasRezervacija(String uporabniskoIme){
        for (Rezervacija rezervacija : this.rezervacija){
            if (rezervacija.getUsernameUporabnika().equals(uporabniskoIme)){
                return true;
            }
        }
        return false;
    } 

    public void print(boolean isAdmin){
        System.out.println("Trajanje od: " + prihod.toString() + 
                           " do: " + odhod.toString());
        if (isAdmin){
            System.out.println("Trenutno stevilo oseb: " + this.steviloOseb);
        }
    }

    public void printId(){
        this.print(false);
        System.out.println("ID: " + Integer.toString(this.id));
    }

    public void addRezervacija(Rezervacija rezervacija){
        this.rezervacija.add(rezervacija);
        this.steviloOseb += rezervacija.getSteviloOseb();
    }

    @Override
    public String toString(){
        String str = "";
        Iterator<Rezervacija> iter = this.rezervacija.iterator();
        while (iter.hasNext()){
            str += iter.next().toString() + "%";
        }
        return prihod.toString() + "&" + odhod.toString() + "&" + str;
    }

    public void fromString(String string){
        String[] str = string.split("&");
        Datum prihod = new Datum();
        prihod.fromString(str[0]);
        Datum odhod = new Datum();
        odhod.fromString(str[1]);
        this.prihod = prihod;
        this.odhod = odhod;
        if (str.length > 2){
            str = str[2].split("%");
            for (String s : str){
                Rezervacija rezervacija = new Rezervacija();
                rezervacija.fromString(s);
                this.steviloOseb += rezervacija.getSteviloOseb();
                this.rezervacija.add(rezervacija);
            }
        }
    }

    public int getId() {
        return id;
    }

    public int getSteviloOseb() {
        return steviloOseb;
    }

    public void setSteviloOseb(int steviloOseb) {
        this.steviloOseb = steviloOseb;
    }

    public Datum getPrihod() {
        return prihod;
    }

    public void setPrihod(Datum prihod) {
        this.prihod = prihod;
    }

    public Datum getOdhod() {
        return odhod;
    }

    public void setOdhod(Datum odhod) {
        this.odhod = odhod;
    }
}
