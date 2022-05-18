import java.io.*;
import java.util.*;

public class Knjiznica {
	
	// polje za shranjevanje seznama gradiv
	private ArrayList<Gradivo> seznamGradiv;
	
	// konstruktorji
	public Knjiznica() {
		this.seznamGradiv = new ArrayList<Gradivo>();
	}
	
	public Knjiznica(ArrayList<Gradivo> seznam) {
		this.seznamGradiv = seznam;
	}
	
	// metoda get
	public ArrayList<Gradivo> getSeznamGradiv() {
		return this.seznamGradiv;
	}

	// metoda set
	public void setSeznamGradiv(ArrayList<Gradivo> seznam) {
		this.seznamGradiv = seznam;
	}

	public void izposodiGradivo() {
		boolean izposojeno = false;
		boolean najdeno = false;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Vnesite naslov knjige za izposojo:");
		try {
			String naslov = br.readLine();
			for(Gradivo gradivo : this.seznamGradiv) {
				if(gradivo.getNaslov().equals(naslov)) {
					for (Status status : gradivo.getSeznamStatusov()) {
						if (status.getJeProsto()) {
							System.out.println("Vnesite dan izposoje:");
							int danIzposoje = Integer.parseInt(br.readLine());
							System.out.println("Vnesite mesec izposoje:");
							int mesecIzposoje = Integer.parseInt(br.readLine());
							status.setJeProsto(false);
							status.setDanIzposoje(danIzposoje);
							status.setMesecIzposoje(mesecIzposoje);
							izposojeno = true;
							break;
						}
					}
					najdeno = true;
					break;
				}
			}
		}
		catch (Exception ex) {
			System.out.println("Prislo je do nepricakovane napake");
			System.out.println(ex.getMessage());
		};
		
		if(!najdeno) {
			System.out.println("Gradivo s taksnim naslovom ne obstaja!");
			System.out.println();
		}
		else if (!izposojeno){ 
			System.out.println("Gradivo s taksnim naslovom ni na voljo!");
			System.out.println();
		}
	}

	public void vrniGradivo() {
		boolean vrnjeno = false;
		boolean najdeno = false;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Vnesite naslov knjige za izposojo:");
			String naslov = br.readLine();
			for(Gradivo gradivo : this.seznamGradiv) {
				if(gradivo.getNaslov().equals(naslov)) {
					System.out.println();
					System.out.println("Vnesite stevilko izvoda knjige:");
					int stevilkaIzvoda = Integer.parseInt(br.readLine());
					System.out.println();
					Status status = gradivo.getSeznamStatusov().get(stevilkaIzvoda);
					if (!status.getJeProsto()) {
						status.setJeProsto(true);
						status.setDanIzposoje(0);
						status.setMesecIzposoje(0);
						vrnjeno = true;
					}
					najdeno = true;
					break;
				}
			}
		} catch (Exception ex) {
			System.out.println("Prislo je do nepricakovane napake");
			System.out.println(ex.getMessage());
		}
		
		if(!najdeno) {
			System.out.println("Gradivo s taksnim naslovom ne obstaja!");
			System.out.println();
		}
		else if (!vrnjeno){ 
			System.out.println("Gradivo s to stevilko izvoda ni bilo mogoce vrniti!");
			System.out.println();
		} 
	}

	public void dodajGradivo(Gradivo g) {
		boolean pogoj = false;
		
		for(Gradivo gradivo : this.seznamGradiv) {
			
			if(gradivo.getNaslov().equals(g.getNaslov())) {
				pogoj = true;
				break;
			}
		}
		
		if(!pogoj) {
			this.seznamGradiv.add(g);
		}
		else {
			System.out.println("Gradivo s taksnim naslovom ze obstaja!");
			System.out.println();
		}
	}
	
	@Override
	public String toString() {
		String podatki = "";
		
		podatki += "\r\n";
		podatki += "-----------------------------------\r\n";
		podatki += "**********   KNJIZNICA   **********\r\n";
		podatki += "-----------------------------------\r\n";
		podatki += "\r\n";
		
		for(Gradivo g : this.seznamGradiv) {
			podatki += g.toString();
			podatki += "\r\n";
		}
		
		return podatki;
	}
	
	public String izpisPoLetu() throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		int leto = 0;
		while(true) {
			try {
				System.out.println("Vnesi leto za iskanje: ");
				leto = Integer.parseInt(br.readLine().trim());
				System.out.println();
				break;
			}
			catch (Exception e) {
				System.out.println("Napacen format vnosa!");
				System.out.println();
			}
		}
		String podatki = "";
		
		for(Gradivo g : this.seznamGradiv) {
			if(g.getLetoIzdaje() == leto) {
				podatki += g.toString();
				podatki += "\r\n";
			}
		}
		
		if(podatki.equals("")) {
			return "V izbranem letu ni bilo izdanih gradiv!\r\n";
		}
		
		return podatki;
	}
	
	// Object je nadrazred vseh razredov
	public static void main(String[] args) {
		Knjiznica k = new Knjiznica();
		Gradivo g = new Gradivo("A", "N", 2022, 2, 10);
		Knjiga knjiga = new Knjiga("Ar", "Na", 2022, 2, 5, 20, "P");
		
		k.dodajGradivo(g);
		k.dodajGradivo(knjiga);
		
		System.out.println(k.toString());
	}
	
	// Metoda shrani podatke o avtomobilih na parkirišèu v datoteko
	public void shraniVDatoteko(String imeDatoteke) throws IOException
	{
		//FileWriter fw = new FileWriter(imeDatoteke, true); // Drugi parameter doloèa, da se že obstojeèi datoteki zapis doda
		FileWriter fw = new FileWriter(imeDatoteke, false);
		PrintWriter dat = new PrintWriter(fw);

		for(Gradivo g : this.seznamGradiv)
		{
			dat.print(g.shraniKotNiz());
		}
		dat.println("###");

		dat.close();
	}

	// Metoda doda gradiva, shranjena v datoteki, v knjiznico
	public void dodajIzDatoteke(String imeDatoteke) throws Exception
	{
		FileReader fr = new FileReader(imeDatoteke);
		BufferedReader dat = new BufferedReader(fr);

		ArrayList<String> gradivoPodatki;
		
		while(dat.ready())
		{
			String vrstica = dat.readLine().trim();
			if(vrstica.equals("*G"))	// Ce se vrstica zacne z *G, zacnemo brati podatke za gradivo
			{
				gradivoPodatki = new ArrayList<String>();
				while(dat.ready() && !vrstica.equals("##"))
				{
					vrstica = dat.readLine().trim();
					gradivoPodatki.add(vrstica);
				}

				Gradivo novoGradivo = Gradivo.preberiIzNiza(gradivoPodatki);
				this.seznamGradiv.add(novoGradivo);
			}
			else if(vrstica.equals("*K"))	// Ce se vrstica zacne s *K, zacnemo brati podatke za knjigo
			{
				gradivoPodatki = new ArrayList<String>();
				while(dat.ready() && !vrstica.equals("##"))
				{
					vrstica = dat.readLine().trim();
					gradivoPodatki.add(vrstica);
				}

				Knjiga novaKnjiga = Knjiga.preberiIzNiza(gradivoPodatki);
				this.seznamGradiv.add(novaKnjiga);
			}
			else if(vrstica.equals("*R"))	// Ce se vrstica zacne z *R, zacnemo brati podatke za revijo
			{
				gradivoPodatki = new ArrayList<String>();
				while(dat.ready() && !vrstica.equals("##"))
				{
					vrstica = dat.readLine().trim();
					gradivoPodatki.add(vrstica);
				}

				Revija novaRevija = Revija.preberiIzNiza(gradivoPodatki);
				this.seznamGradiv.add(novaRevija);
			}
		}
		dat.close();
	}
}