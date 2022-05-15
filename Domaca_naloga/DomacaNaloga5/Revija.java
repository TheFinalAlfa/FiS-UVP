import java.io.*;
import java.util.*;

public class Revija extends Gradivo {
	
	// polja Revije
	private String podrocje;
	private boolean jeZnanstvena;
	
	// konstruktorji Revije
	public Revija() {
		super(); // klicemo konstruktor nadrazreda
		this.podrocje = "";
		this.jeZnanstvena = true;
	}
	
	public Revija(String avtor, String naslov, int letoIzdaje, 
				int steviloIzvodov,	String podrocje, boolean jeZnanstvena) {
		
		super(avtor, naslov, letoIzdaje, steviloIzvodov);
		this.podrocje = podrocje;
		this.jeZnanstvena = jeZnanstvena;
	}
	
	public Revija(String avtor, String naslov, int letoIzdaje, 
				int steviloIzvodov,	int maxIzvodov, 
				String podrocje, boolean jeZnanstvena) {
		
		super(avtor, naslov, letoIzdaje, steviloIzvodov, maxIzvodov);
		this.podrocje = podrocje;
		this.jeZnanstvena = jeZnanstvena;
	}
	
	// metode tipa get
	public String getPodrocje() {
		return this.podrocje;
	}
	
	public boolean getJeZnanstvena() {
		return this.jeZnanstvena;
	}
	
	// metode tipa set
	public void setPodrocje(String podrocje) {
		this.podrocje = podrocje;
	}
	
	public void setJeZnanstvena(boolean jeZnanstvena) {
		this.jeZnanstvena = jeZnanstvena;
	}
	
	@Override
    public String toString() {
		String podatki = "";
		
		podatki += "*****   Podatki o reviji   *****\r\n";
		podatki += "--------------------------------\r\n";
		podatki += super.toStringGradivo();
		podatki += "Podrocje: " + this.podrocje + "\r\n";
		podatki += "Je znanstvena: " + this.jeZnanstvena + "\r\n";
		podatki += "\r\n";
		
		for(Status s : this.getSeznamStatusov()) {
			podatki += s.toString();
			podatki += "\r\n";
		}
		
		return podatki;
	}
	
	public static Revija ustvariRevijo() throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		System.out.println("***   Vnos nove revije   ***");
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
		
		System.out.println("Vnesi podrocje: ");
		String podrocje = br.readLine().trim();
		System.out.println();
		
		boolean znanstvena = true;
		while (true) {
			System.out.println("Vnesi ali je znanstvena (DA/NE): ");
			String niz = br.readLine().trim();
			System.out.println();
			
			if(niz.equals("DA")) {
				break;
			} else if (niz.equals("NE")) {
				znanstvena = false;
				break;
			} else {
				System.out.println("Napacen format vnosa!");
				System.out.println();
			}
		}
		
		Revija r = new Revija(avtor, naslov, leto, stevilo, podrocje, znanstvena);
		return r;
	}
	
	public String shraniKotNiz()
	{
		return "";
	}
	
	public static Revija preberiIzNiza(ArrayList<String> zapis)
	{
		return new Revija();
	}
	
}