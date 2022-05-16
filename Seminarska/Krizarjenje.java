import java.io.BufferedReader;
import java.util.ArrayList;

public class Krizarjenje extends Pocitnice {
    private String imeLadje;
    private ArrayList<String> pristanisca;

    public Krizarjenje(int maxOseb, String drzava, int cena, String imeLadje, ArrayList<String> pristanisca){
        super(maxOseb, drzava, cena);
        this.imeLadje = imeLadje;
        this.pristanisca = pristanisca;
        this.setType("Krizarjenje");
    }

    public Krizarjenje(String string){
        super(string);
        this.setType("Krizarjenje");
        String[] str = string.split(";");
        this.imeLadje = str[str.length - 2];
        str = str[str.length - 1].split("~");
        this.pristanisca = new ArrayList<String>();
        for (String s : str){
            this.pristanisca.add(s);
        };
    }

    @Override
    public String toString(){
        String string = super.toString() + this.imeLadje + ";";
        for (String p : this.pristanisca){
            string += p + "~";
        }
        return string;
    }

    public static void vnosNovihPocitnic(BufferedReader bis, int maxOseb, String drzava, int cena) throws Exception {
        System.out.println("Vnesite ime ladje");
        String imeLadje = bis.readLine();
        ArrayList<String> pristanisca = new ArrayList<String>();
        loopKrizarjenje:
        while (true){
            System.out.println("Vnesite ime obiskanega pristanisca");
            System.out.println("Za konec vnasanja pristanisc vnesite (q)");
            String vnos = bis.readLine();
            switch (vnos){
                case "":{
                    System.out.println("Vnesite ime");
                }break;
                case "q":{
                    break loopKrizarjenje;
                }
                default:{
                    pristanisca.add(vnos);
                }
            }
        }
        new Krizarjenje(maxOseb, drzava, cena, imeLadje, pristanisca);
    }

    @Override
    public void spremembeSporocilo(){
        super.spremembeSporocilo();
        Krizarjenje.spremembeSporociloPotovanje();
    }

    private static void spremembeSporociloPotovanje() {
        System.out.println("Za spremembo imena ladje vnesite (l)");
        System.out.println("Za dodajanje novega pristanisca vnesite (pa)");
        System.out.println("Za odstranitev pristanisca vnesite (pb)");
    }

    @Override
    public boolean spremeni(String string, BufferedReader bis) throws Exception{
        if (super.spremeni(string, bis)){
            return true;
        }
        else{
            switch (string) {
                case "l":{
                    System.out.println("Vnesite novo ime ladje");
                    this.imeLadje = bis.readLine();
                }
                return true;
                case "pa":{
                    System.out.println("Vnesite ime novega pristanisca");
                    this.pristanisca.add(bis.readLine());
                }
                return true;
                case "pb":{
                    System.out.println("Vnesite ime pristanisca za odstranitev");
                    String vnos = bis.readLine();
                    if (this.pristanisca.contains(vnos)){
                        this.pristanisca.remove(this.pristanisca.indexOf(vnos));
                    }
                    else {
                        System.out.println("Napacen vnos");
                    }
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
        System.out.println("Ime ladje: " + this.imeLadje);
        System.out.println("Pristanisca:");
        for (String pristanisce : this.pristanisca){
            System.out.println("    " + pristanisce);
        }
    }

    // Getters and setters

    public void setImeLadje(String imeLadje) {
        this.imeLadje = imeLadje;
    }

    public String getImeLadje() {
        return imeLadje;
    }

    public void setPristanisca(ArrayList<String> pristanisca) {
        this.pristanisca = pristanisca;
    }

    public ArrayList<String> getPristanisca() {
        return pristanisca;
    }
}
