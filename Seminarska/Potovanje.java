import java.io.BufferedReader;

public class Potovanje extends Pocitnice {
    private String vodic;
    private int steviloKM;

    public Potovanje(int maxOseb, String drzava, int cena, String vodic, int steviloKM){
        super(maxOseb, drzava, cena);
        this.vodic = vodic;
        this.steviloKM = steviloKM;
        this.setType("Potovanje");
    }

    public Potovanje(String string) {
        super(string);
        this.setType("Potovanje");
        String[] str = string.split(";");
        this.steviloKM = Integer.parseInt(str[str.length - 1]);
        this.vodic = str[str.length - 2];
    }

    @Override
    public String toString(){
        return super.toString() + this.vodic + ";" + Integer.toString(this.steviloKM);
    }

    public static void vnosNovihPocitnic(BufferedReader bis, int maxOseb, String drzava, int cena) throws Exception {
        System.out.println("Vnesite ime vodica");
        String vodic = bis.readLine();
        System.out.println("Vnesite stevilo prevozenih KM");
        int steviloKM = Integer.parseInt(bis.readLine());        
        new Potovanje(maxOseb, drzava, cena, vodic, steviloKM);
    }
    
    @Override
    public void spremembeSporocilo(){
        super.spremembeSporocilo();
        Potovanje.spremembeSporociloPotovanje();
    }

    private static void spremembeSporociloPotovanje() {
        System.out.println("Za spremembo vodica vnesite (v)");
        System.out.println("Za spremembo dolžine poti v kilometrih vnesite (t)");
    }

    @Override
    public boolean spremeni(String string, BufferedReader bis) throws Exception{
        if (super.spremeni(string, bis)){
            return true;
        }
        else{
            switch (string) {
                case "v":{
                    System.out.println("Vnesite ime novega vodica");
                    this.vodic = bis.readLine();
                }
                return true;
                case "t":{
                    System.out.println("Vnesite novo dolžino poti v KM");
                    this.steviloKM = Integer.parseInt(bis.readLine());
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
        System.out.println("Ime vodica: " + this.vodic);
        System.out.println("Stevilo kilometrov: " + Integer.toString(this.steviloKM));
    }

    // Getters and setters

    public void setVodic(String vodic) {
        this.vodic = vodic;
    }

    public String getVodic() {
        return vodic;
    }

    public void setSteviloKM(int steviloKM) {
        this.steviloKM = steviloKM;
    }

    public int getSteviloKM() {
        return steviloKM;
    }
}
