import java.io.*;
import java.util.*;

public class Status {
	
	private int stevilkaIzvoda;
	private boolean jeProsto;
	private int danIzposoje;
	private int mesecIzposoje;
	
	public Status() {
		this.stevilkaIzvoda = 0;
		this.jeProsto = false;
		this.danIzposoje = 0;
		this.mesecIzposoje = 0;
	}
	
	public Status(int stevilkaIzvoda) {
		this.stevilkaIzvoda = stevilkaIzvoda;
		this.jeProsto = true;
		this.danIzposoje = 0;
		this.mesecIzposoje = 0;
	}
	
	public Status(int stevilka, boolean prosto, int dan, int mesec) {
		this.stevilkaIzvoda = stevilka;
		this.jeProsto = prosto;
		this.danIzposoje = dan;
		this.mesecIzposoje = mesec;
	}
	
	public int getStevilkaIzvoda() {
		return this.stevilkaIzvoda;
	}
	
	public boolean getJeProsto() {
		return this.jeProsto;
	}
	
	public int getDanIzposoje() {
		return this.danIzposoje;
	}
	
	public int getMesecIzposoje() {
		return this.mesecIzposoje;
	}
	
	public void setStevilkaIzvoda(int stevilka) {
		this.stevilkaIzvoda = stevilka;
	}
	
	public void setJeProsto(boolean prosto) {
		this.jeProsto = prosto;
	}
	
	public void setDanIzposoje(int dan) {
		this.danIzposoje = dan;
	}
	
	public void setMesecIzposoje(int mesec) {
		this.mesecIzposoje = mesec;
	}
	
	@Override
    public String toString() {
		String podatki = "";
		
		podatki += "***   Podatki o izvodu   ***\r\n";
		podatki += "Stevilka izvoda: " + this.stevilkaIzvoda + "\r\n";
		podatki += "Je prosto: " + this.jeProsto + "\r\n";
		podatki += "Dan izposoje: " + this.danIzposoje + "\r\n";
		podatki += "Mesec izposoje: " + this.mesecIzposoje + "\r\n";
		
		return podatki;
	}
	
	public String shraniKotNiz() {
		String zapis = "*S\r\n";			// Zapišemo kodo "S", ki oznacuje status
		zapis += this.stevilkaIzvoda + "\r\n";
		zapis += this.jeProsto + "\r\n";
		zapis += this.danIzposoje + "\r\n";
		zapis += this.mesecIzposoje + "\r\n";
		zapis += "#\r\n";
		return zapis;
	}
	
	public static Status preberiIzNiza(ArrayList<String> zapis)
	{
		Status status = new Status();  // Najprej ustvarimo objekt, kateremu bomo nastavili podane lastnosti
		try
		{
			// Prvi element v seznamu je stevilka izvoda
			status.setStevilkaIzvoda(Integer.parseInt(zapis.get(0)));
			// Drugi element v seznamu je ali je prosto
			if(zapis.get(1).equals("true")) {
				status.setJeProsto(true);
			} else {
				status.setJeProsto(false);
			}
			// Tretji element v seznamu je dan izposoje
			status.setDanIzposoje(Integer.parseInt(zapis.get(2)));
			// Cetrti element v seznamu je mesec izposoje
			status.setMesecIzposoje(Integer.parseInt(zapis.get(3)));
			return status;
		}
		catch(Exception ex)
		{
			System.out.println("Prišlo je do napake v zapisu!");
			System.out.println();
			throw ex;
		}
	}
	
}