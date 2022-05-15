import java.io.*;
import java.util.*;

public class Knjiga extends Gradivo {
	
	// polja Knjige
	private int steviloStrani;
	private String povzetek;
	
	// konstruktorji Knjige
	public Knjiga() {
		super(); // klicemo konstruktor nadrazreda
		this.steviloStrani = 0;
		this.povzetek = "";
	}
	
	public Knjiga(String avtor, String naslov, int letoIzdaje, 
				int steviloIzvodov, int steviloStrani,	String povzetek) {
		
		super(avtor, naslov, letoIzdaje, steviloIzvodov);
		this.steviloStrani = steviloStrani;
		this.povzetek = povzetek;
	}
	
	public Knjiga(String avtor, String naslov, int letoIzdaje, 
				int steviloIzvodov,	int maxIzvodov, 
				int steviloStrani,	String povzetek) {
		
		super(avtor, naslov, letoIzdaje, steviloIzvodov, maxIzvodov);
		this.steviloStrani = steviloStrani;
		this.povzetek = povzetek;
	}
	
	// metode tipa get
	public int getSteviloStrani() {
		return this.steviloStrani;
	}
	
	public String getPovzetek() {
		return this.povzetek;
	}
	
	// metode tipa set
	public void setSteviloStrani(int steviloStrani) {
		this.steviloStrani = steviloStrani;
	}
	
	public void setPovzetek(String povzetek) {
		this.povzetek = povzetek;
	}

	@Override
    public String toString() {
		String podatki = "";
		
		podatki += "*****   Podatki o knjigi   *****\r\n";
		podatki += "--------------------------------\r\n";
		podatki += super.toStringGradivo();
		podatki += "Stevilo stani: " + this.steviloStrani + "\r\n";
		podatki += "Povzetek: " + this.povzetek + "\r\n";
		podatki += "\r\n";
		
		for(Status s : this.getSeznamStatusov()) {
			podatki += s.toString();
			podatki += "\r\n";
		}
		
		return podatki;
	}
	
	public static Knjiga ustvariKnjigo() throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		System.out.println("***   Vnos nove knjige   ***");
		System.out.println();
		System.out.println("Vnesi avtorja: ");
		String avtor = br.readLine().trim();
		System.out.println();
		System.out.println("Vnesi naslov: ");
		String naslov = br.readLine().trim();
		System.out.println();
		
		int leto = 0;
		while(true) {
			try {
				System.out.println("Vnesi leto izdaje: ");
				leto = Integer.parseInt(br.readLine().trim());
				System.out.println();
				break;
			} 
			catch (Exception e) {
				System.out.println("Napacen format vnosa!");
				System.out.println();
			}
		}
		
		int stevilo = 0;
		while(true) {
			try {
				System.out.println("Vnesi stevilo izvodov: ");
				stevilo = Integer.parseInt(br.readLine().trim());
				System.out.println();
				break;
			}
			catch (Exception e) {
				System.out.println("Napacen format vnosa!");
				System.out.println();
			}
		}
		
		int strani = 0;
		while(true) {
			try {
				System.out.println("Vnesi stevilo strani: ");
				strani = Integer.parseInt(br.readLine().trim());
				System.out.println();
				break;
			}
			catch (Exception e) {
				System.out.println("Napacen format vnosa!");
				System.out.println();
			}
		}
		
		System.out.println("Vnesi povzetek: ");
		String povzetek = br.readLine().trim();
		System.out.println();
		
		Knjiga k = new Knjiga(avtor, naslov, leto, stevilo, strani, povzetek);
		return k;
	}
	
	public String shraniKotNiz()
	{
		return "";
	}
	
	public static Knjiga preberiIzNiza(ArrayList<String> zapis)
	{
		return new Knjiga();
	}
	
}