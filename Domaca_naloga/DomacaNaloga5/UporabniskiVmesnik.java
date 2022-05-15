import java.io.*;
import java.util.*;

public class UporabniskiVmesnik {
	
	public static void main(String[] args) throws Exception {
		
		Knjiznica knjiznica = new Knjiznica();
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		char izbira;
		
		while(true) {
			
			System.out.println("Pritisni (g) za dodajanje gradiva.");
			System.out.println("Pritisni (k) za dodajanje knjige.");
			System.out.println("Pritisni (r) za dodajanje revije.");
			System.out.println("Pritisni (i) za izpis vseh gradiv.");
			System.out.println("Pritisni (l) za izpis po letu izdaje.");
			System.out.println("Pritisni (p) za branje iz datoteke.");
			System.out.println("Pritisni (s) za zapis v datoteko.");
			System.out.println("Pritisni (z) za izposojo gradiva.");
			System.out.println("Pritisni (v) za vrnitev gradiva.");
			System.out.println("Pritisni (q) za prekinitev programa.");
			System.out.println();
			
			izbira = br.readLine().trim().charAt(0);
			String imeDat = "";
			
			switch(izbira) {
				case 'v':
					knjiznica.vrniGradivo();
					break;
				case 'z':
					knjiznica.izposodiGradivo();
					break;
				case 'g':
					Gradivo g = Gradivo.ustvariGradivo();
					knjiznica.dodajGradivo(g);
					break;
				case 'k':
					Knjiga k = Knjiga.ustvariKnjigo();
					knjiznica.dodajGradivo(k);
					break;
				case 'r':
					Revija r = Revija.ustvariRevijo();
					knjiznica.dodajGradivo(r);
					break;
				case 'i':
					System.out.println(knjiznica.toString());
					break;
				case 'l':
					System.out.println(knjiznica.izpisPoLetu());
					break;
				case 'p':
					System.out.println("Vnesite ime datoteke: ");
					imeDat = br.readLine().trim();
					knjiznica.dodajIzDatoteke(imeDat);
					System.out.println();
					break;
				case 's':
					System.out.println("Vnesite ime datoteke: ");
					imeDat = br.readLine().trim();
					knjiznica.shraniVDatoteko(imeDat);
					System.out.println();
					break;
				case 'q':
					return;
				default:
					System.out.println("Pritisnili ste napacno izbiro!");
					System.out.println();
			}
		}
		
	}
	
}