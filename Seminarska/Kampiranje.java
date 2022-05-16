import java.io.BufferedReader;

public class Kampiranje extends Pocitnice{
    private String imeKampa;
    private long oddaljenostOdMorja;

    public Kampiranje(int maxOseb, String drzava, int cena, String imeKampa, long oddaljenostOdMorja){
        super(maxOseb, drzava, cena);
        this.imeKampa = imeKampa;
        this.oddaljenostOdMorja = oddaljenostOdMorja;
        this.setType("Kampiranje");
    }

    public Kampiranje(String string){
        super(string);
        this.setType("Kampiranje");
        String[] str = string.split(";");
        this.imeKampa = str[str.length - 2];
        this.oddaljenostOdMorja = Integer.parseInt(str[str.length - 1]);        
    }

    @Override
    public String toString(){
        return super.toString() + this.imeKampa + ";" + Long.toString(this.oddaljenostOdMorja);
    }

    public static void vnosNovihPocitnic(BufferedReader bis, int maxOseb, String drzava, int cena) throws Exception {
        System.out.println("Vnesite ime kampa");
        String imeKampa = bis.readLine();
        System.out.println("Vnesite oddaljenost od morja v m");
        long oddaljenostOdMorja = Integer.parseInt(bis.readLine());        
        new Kampiranje(maxOseb, drzava, cena, imeKampa, oddaljenostOdMorja);
    }

    @Override
    public void spremembeSporocilo(){
        super.spremembeSporocilo();
        Kampiranje.spremembeSporociloPotovanje();
    }

    private static void spremembeSporociloPotovanje() {
        System.out.println("Za spremembo odaljenosti od morja vnesite (o)");
        System.out.println("Za spremembo imena kampa vnesite (k)");
    }

    @Override
    public boolean spremeni(String string, BufferedReader bis) throws Exception{
        if (super.spremeni(string, bis)){
            return true;
        }
        else{
            switch (string) {
                case "o": {
                    System.out.println("Vnesite novo ime kampa");
                    this.imeKampa = bis.readLine();
                }
                return true;
                case "k": {
                    System.out.println("Vnesite novo oddaljenost od morja");
                    this.oddaljenostOdMorja = Long.parseLong(bis.readLine());
                }
                return true;
                default:{
                    return false;
                }
            }
        }
    }

    @Override
    public void printPure(boolean isAdmin){
        super.printPure(isAdmin);
        System.out.println("Ime kampa: " + this.imeKampa);
        System.out.println("Oddaljenost od morja: " + Long.toString(this.oddaljenostOdMorja));
    }

    // Getters and setters

    public void setImeKampa(String imeKampa) {
        this.imeKampa = imeKampa;
    }

    public String getImeKampa() {
        return imeKampa;
    }

    public void setOddaljenostOdMorja(long oddaljenostOdMorja) {
        this.oddaljenostOdMorja = oddaljenostOdMorja;
    }

    public long getOddaljenostOdMorja() {
        return oddaljenostOdMorja;
    }
}
