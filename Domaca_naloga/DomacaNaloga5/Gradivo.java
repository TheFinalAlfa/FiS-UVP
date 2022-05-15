import java.io.*;
import java.util.*;

public class Gradivo {
	
	// polja oz. lastnosti objekta
	private String avtor;
	private String naslov;
	private int letoIzdaje;
	private int steviloIzvodov;
	private int maxIzvodov;
	private ArrayList<Status> seznamStatusov;
	
	// konstruktorji
	public Gradivo() {
		avtor = "";
		naslov = "";
		letoIzdaje = 0;
		steviloIzvodov = 0;
		maxIzvodov = 50;
		seznamStatusov = new ArrayList<Status>();
	}
	
	public Gradivo(String avtor, String naslov, int letoIzdaje, int steviloIzvodov) {
		this.avtor = avtor;
		this.naslov = naslov;
		this.letoIzdaje = letoIzdaje;
		this.steviloIzvodov = steviloIzvodov;
		this.maxIzvodov = 50;
		this.seznamStatusov = new ArrayList<Status>();
		for(int i = 0; i < steviloIzvodov; i++) {
			Status s = new Status(i+1);
			this.seznamStatusov.add(s);
		}
	}
	
	public Gradivo(String avtor, String naslov, int letoIzdaje, int steviloIzvodov, int maxIzvodov) {
		this.avtor = avtor;
		this.naslov = naslov;
		this.letoIzdaje = letoIzdaje;
		this.steviloIzvodov = steviloIzvodov;
		this.maxIzvodov = maxIzvodov;
		this.seznamStatusov = new ArrayList<Status>();
		for(int i = 0; i < steviloIzvodov; i++) {
			Status s = new Status(i+1);
			this.seznamStatusov.add(s);
		}
	}

	// metode tipa set
	public void setAvtor(String avtor) {
		this.avtor = avtor;
	}
	
	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
	
	public void setLetoIzdaje(int letoIzdaje) {
		this.letoIzdaje = letoIzdaje;
	}
	
	public void setSteviloIzvodov(int steviloIzvodov) {
		this.steviloIzvodov = steviloIzvodov;
	}
	
	public void setMaxIzvodov(int maxIzvodov) {
		this.maxIzvodov = maxIzvodov;
	}
	
	public void setSeznamStatusov(ArrayList<Status> seznamStatusov) {
		this.seznamStatusov = seznamStatusov;
	}
	
	// metode tipa get
	public String getAvtor() {
		return this.avtor;
	}
	
	public String getNaslov() {
		return this.naslov;
	}
	
	public int getLetoIzdaje() {
		return this.letoIzdaje;
	}
	
	public int getSteviloIzvodov() {
		return this.steviloIzvodov;
	}
	
	public int getMaxIzvodov() {
		return this.maxIzvodov;
	}
	
	public ArrayList<Status> getSeznamStatusov() {
		return this.seznamStatusov;
	}
	
	public void dodajStatus(Status s) {
		this.seznamStatusov.add(s);
	}
	
	public void spremeniSteviloIzvodov(int stevilo) {
		if(this.steviloIzvodov + stevilo > this.maxIzvodov) {
			for(int i = 0; i < this.maxIzvodov - this.steviloIzvodov; i++) {
				Status s = new Status(this.steviloIzvodov+i+1);
				this.seznamStatusov.add(s);
			}
			this.steviloIzvodov = this.maxIzvodov;
		}
		else if(stevilo >= 0) {
			for(int i = 0; i < stevilo; i++) {
				Status s = new Status(this.steviloIzvodov+i+1);
				this.seznamStatusov.add(s);
			}
			this.steviloIzvodov += stevilo;
		}
		else {
			if(this.steviloIzvodov < stevilo * (-1)) {
				this.steviloIzvodov = 0;
				this.seznamStatusov.clear();
			}
			else {
				for(int i = steviloIzvodov - 1; i >= this.steviloIzvodov+stevilo; i--) {
					this.seznamStatusov.remove(i);
				}
				this.steviloIzvodov += stevilo;
			}
		}
	}
	
    public String toStringGradivo() {
		String podatki = "";
		
		podatki += "Avtor: " + this.avtor + "\r\n";
		podatki += "Naslov: " + this.naslov + "\r\n";
		podatki += "Leto izdaje: " + this.letoIzdaje + "\r\n";
		podatki += "Stevilo izvodov: " + this.steviloIzvodov + "\r\n";
		podatki += "Maksimalno izvodov: " + this.maxIzvodov + "\r\n";
		
		return podatki;
	}
	
	@Override
	public String toString() {
		String podatki = "";
		
		podatki += "*****   Podatki o gradivu   *****\r\n";
		podatki += "---------------------------------\r\n";
		podatki += "Avtor: " + this.avtor + "\r\n";
		podatki += "Naslov: " + this.naslov + "\r\n";
		podatki += "Leto izdaje: " + this.letoIzdaje + "\r\n";
		podatki += "Stevilo izvodov: " + this.steviloIzvodov + "\r\n";
		podatki += "Maksimalno izvodov: " + this.maxIzvodov + "\r\n";
		podatki += "\r\n";
		
		for(Status s : this.seznamStatusov) {
			podatki += s.toString();
			podatki += "\r\n";
		}
		
		return podatki;
	}
	
	public static Gradivo ustvariGradivo() throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		System.out.println("***   Vnos novega gradiva   ***");
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
		
		Gradivo g = new Gradivo(avtor, naslov, leto, stevilo);
		return g;
	}
	
	/*
	 *	Metoda zapiše podatke o gradivu v poseben niz, iz katerega znamo 
	 *  enolicno razbrati, za katero gradivo gre.
	 *  Implementiramo jo kot objektno metodo
	 */
	public String shraniKotNiz()
	{
		String zapis = "*G\r\n";			// Zapišemo kodo "G", ki oznacuje gradivo
		zapis += this.avtor + "\r\n";		// Zapišemo avtorja
		zapis += this.naslov + "\r\n";		// Zapišemo naslov
		zapis += this.letoIzdaje + "\r\n";	// Zapišemo leto izdaje
		zapis += this.steviloIzvodov + "\r\n";	// Zapišemo število izvodov

		for(Status s : this.seznamStatusov) // Zapišemo še vsak status posebej
		{
			zapis += s.shraniKotNiz();
		}
		zapis += "##\r\n";					// Oznacimo konec branja
		return zapis;
	}

	/*
	 *	Metoda iz danega seznama nizov rekonstruira avto, ki je bil z danimi podatki shranjen.
	 *  Implementiramo jo kot statièno metodo
	 */
	public static Gradivo preberiIzNiza(ArrayList<String> zapis)
	{
		Gradivo gradivo = new Gradivo();  // Najprej ustvarimo objekt, kateremu bomo nastavili podane lastnosti
		try
		{
			// Prvi element v seznamu je avtor
			gradivo.setAvtor(zapis.get(0));
			// Drugi element v seznamu je naslov
			gradivo.setNaslov(zapis.get(1));
			// Tretji element v seznamu je leto izdaje
			gradivo.setLetoIzdaje(Integer.parseInt(zapis.get(2)));
			// Cetrti element v seznamu je stevilo izvodov
			gradivo.setSteviloIzvodov(Integer.parseInt(zapis.get(3)));

			// Preostali elementi so podatki o statusih
			ArrayList<String> statusPodatki;
			for(int i=4; i < zapis.size(); i++)
			{
				if(zapis.get(i).trim().equals("*S"))	// Ce vrstica vsebuje *S, imamo zapis o statusu
				{
					statusPodatki = new ArrayList<String>();	// Pripravimo nov seznam, v katerega bomo dodajali podatke o trenutnem statusu
					i++;
					while(!zapis.get(i).trim().equals("#"))	// Dokler se zapis o statusu ne konca (dokler se ne pojavi #), dodajamo podatke v seznam
					{
						statusPodatki.add(zapis.get(i));
						i++;
					}
					Status status = Status.preberiIzNiza(statusPodatki);

					gradivo.dodajStatus(status);
				}
			}
			return gradivo;
		}
		catch(Exception ex)
		{
			System.out.println("Prišlo je do napake v zapisu!");
			System.out.println();
			throw ex;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		Gradivo g = new Gradivo("A", "N", 2022, 15, 50);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			System.out.println("Vnesi stevilo:");
			int stevilo = Integer.parseInt(br.readLine());
			
			if(stevilo == 0) {
				return;
			}
			g.spremeniSteviloIzvodov(stevilo);
			System.out.println(g.toString());
		}
		
	}
	
}