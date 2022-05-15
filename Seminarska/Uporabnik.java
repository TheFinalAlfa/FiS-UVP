public class Uporabnik extends Object{
    
    //
    // Shranjuje osnovne podatke o uporabnikih in
    // preverja pravilnost gesla
    //
    
    private static int steviloAdministratorjev = 0;

    private static int lastId = 0;
    private int id;
    private String ime;
    private String priimek;
    private String uporabniskoIme;
    private String geslo;
    private boolean isAdmin;

    // Constructors

    public Uporabnik(String ime, String priimek, String uporabniskoIme, 
                     String geslo, boolean isAdmin){
            this.id = Uporabnik.lastId;
            Uporabnik.lastId++;
            this.ime = ime;
            this.priimek = priimek;
            this.uporabniskoIme = uporabniskoIme;
            this.geslo = geslo;
            this.isAdmin = isAdmin;
            if (isAdmin){
                Uporabnik.steviloAdministratorjev++;
            }
        }

    public Uporabnik(String ime, String priimek, String uporabniskoIme, 
                     String geslo){
        this.id = Uporabnik.lastId;
        Uporabnik.lastId++;
        this.ime = ime;
        this.priimek = priimek;
        this.uporabniskoIme = uporabniskoIme;
        this.geslo = geslo;
        this.isAdmin = false;
    }

    boolean PravilnoGeslo(String geslo){
        return this.geslo.equals(geslo);
    }

    public void print(){
        System.out.println("Uporabnisko ime: " + this.uporabniskoIme);
        System.out.println("ID: " + Integer.toString(this.id));
    }

    @Override
    public String toString(){
        String spr = "";
        spr += this.ime + ";";
        spr += this.priimek + ";";
        spr += this.uporabniskoIme + ";";
        spr += this.geslo + ";";
        spr += Boolean.toString(this.isAdmin);
        return spr;
    }

    public static int getLastId() {
        return lastId;
    }

    // Getters adn setters
    //
    // Except for geslo

    public static int getSteviloAdministratorjev() {
        return steviloAdministratorjev;
    }

    public static void setSteviloAdministratorjev(int steviloAdministratorjev) {
        Uporabnik.steviloAdministratorjev = steviloAdministratorjev;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getIme() {
        return ime;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setUporabniskoIme(String uporabniskoIme) {
        this.uporabniskoIme = uporabniskoIme;
    }

    public String getUporabniskoIme() {
        return uporabniskoIme;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}