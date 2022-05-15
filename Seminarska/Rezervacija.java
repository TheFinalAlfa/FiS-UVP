public class Rezervacija {
    
    //
    // Osnovni razred za shranjevanje in prikaz rezervacij
    //

    private String usernameUporabnika;
    private int steviloOseb;

    public Rezervacija(String usernameUporabnika, int steviloOseb) {
        this.usernameUporabnika = usernameUporabnika;
        this.steviloOseb = steviloOseb;
    }

    public Rezervacija() {
    }

    @Override
    public String toString() {
        return usernameUporabnika + "#" + Integer.toString(steviloOseb);
    }

    public void fromString(String string) {
        String[] str = string.split("#");
        this.usernameUporabnika = str[0];
        this.steviloOseb = Integer.parseInt(str[1]);
    }

    public void setSteviloOseb(int steviloOseb) {
        this.steviloOseb = steviloOseb;
    }

    public int getSteviloOseb() {
        return steviloOseb;
    }

    public String getUsernameUporabnika() {
        return usernameUporabnika;
    }
}
