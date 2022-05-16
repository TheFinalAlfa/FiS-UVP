import java.io.BufferedReader;
import java.util.Iterator;

public class VnesnikUporabnik extends Vnesnik {
    
    @Override
    public void sporociloMoznosti(){
        super.sporociloMoznosti();
        System.out.println("Za vse vase rezervacije vnesite (v)");
        System.out.println("Za iskanje vnesite (d)");
        System.out.println("Za novo rezervacijo vnesite (t)");
    }

    @Override
    public void mojeRezervacije(BufferedReader bis){
        System.out.println("\nVaše rezervacije so: \n");
        for (Pocitnice pocitnice : TuristicnaAgencija.pocitnice){
            Iterator<Termin> iterTermini = pocitnice.getTermini();
            while (iterTermini.hasNext()){
                Termin t = iterTermini.next();
                if (t.hasRezervacija(this.getPrijavlenUporabnik().getUporabniskoIme())){
                    pocitnice.printPure(this.isAdmin());
                    t.print(this.isAdmin());
                    System.out.println("\n");
                }
            }
        }
    }

    @Override
    public void iskanje(BufferedReader bis) throws Exception{
        Datum prihod = null;
        Datum odhod = null;
        int cenaZgornja = 0;
        int cenaSpodnja = 0;
        String drzava = null;
        String type = null;
        loopIskanje:
        while (true) {
            System.out.println("Za iskanje po casovnem okviru vnesite (d)");
            System.out.println("Za iskanje po cenovnem okviru vnesite (c)");
            System.out.println("Za iskanje po drzavi vnesite (r)");
            System.out.println("Za iskanje po tipu pocitnic vnesite (t)");
            System.out.println("Za zacetek iskanja vnesite (a)");
            System.out.println("Za istop iz iskanja vnesite (q)");
            System.out.println();
            switch (bis.readLine()){
                case "d":{
                    System.out.println("Vnesite leto prihoda");
                    long leto = Long.parseLong(bis.readLine());
                    System.out.println("Vnesite mesec prihoda");
                    int mesec = Integer.parseInt(bis.readLine());
                    System.out.println("Vnesite dan prihoda");
                    int dan = Integer.parseInt(bis.readLine());
                    System.out.println("Vnesite uro prihoda");
                    int ura = Integer.parseInt(bis.readLine());
                    System.out.println("Vnesite leto odhoda");
                    long letoOdhod = Long.parseLong(bis.readLine());
                    System.out.println("Vnesite mesec odhoda");
                    int mesecOdhod = Integer.parseInt(bis.readLine());
                    System.out.println("Vnesite dan odhoda");
                    int danOdhod = Integer.parseInt(bis.readLine());
                    System.out.println("Vnesite uro odhoda");
                    int uraOdhod = Integer.parseInt(bis.readLine());
                    odhod = new Datum(letoOdhod, mesecOdhod, danOdhod, uraOdhod);
                    prihod = new Datum(leto, mesec, dan, ura);
                }
                break;
                case "c":{
                    System.out.println("Vnesite zgornjo mejo cene");
                    cenaZgornja = Integer.parseInt(bis.readLine());
                    System.out.println("Vnesite spodnjo mejo cene");
                    cenaSpodnja = Integer.parseInt(bis.readLine());
                }
                break;
                case "r":{
                    System.out.println("Vnesite zeleno drzavo");
                    drzava = bis.readLine();
                }
                break;
                case "t":{
                    System.out.println("Vnesite zeleni tip pocitnic");
                    type = bis.readLine();
                }
                break;
                case "a":{
                    if (prihod == null && odhod == null && cenaSpodnja == 0 && cenaZgornja == 0 && drzava == null && type == null){
                        System.out.println("Vnesite vsaj eden kriterij");
                    }
                    else{
                        for (Pocitnice p : TuristicnaAgencija.pocitnice){
                            if (drzava != null && !(p.getDrzava().equals(drzava))){
                                continue;
                            }
                            if (type != null && !(p.getType().equals(type))){
                                continue;
                            }
                            if ((cenaSpodnja != 0 && cenaZgornja != 0 && !(cenaSpodnja >= p.getCena() && cenaZgornja <= p.getCena()))){
                                continue;
                            }
                            boolean avaible = false;
                            if (odhod != null && prihod == null){
                                Iterator<Termin> iterTermin = p.getTermini();
                                while (iterTermin.hasNext()){
                                    Termin termin = iterTermin.next();
                                    if (termin.getPrihod().before(prihod) && 
                                        odhod.before(termin.getOdhod())){
                                            avaible = true;
                                            break;
                                        };
                                    }
                                    if (!avaible){
                                        continue;
                                    }
                                }
                            p.print(this.getPrijavlenUporabnik().getIsAdmin());
                        }
                    }
                }
                break loopIskanje;
                case "q":{}
                break loopIskanje;
                default:{
                    System.out.println("Napacen vnos");
                }
            }
        }
    }

    @Override
    public void rezervirati(BufferedReader bis) throws Exception{
        System.out.println("Vnesite ID pocitnic");
        int idPocitnic = Integer.parseInt(bis.readLine());
        for (Pocitnice pocitnice : TuristicnaAgencija.pocitnice){
            if (pocitnice.getId() == idPocitnic){
                pocitnice.printTerminiId(this.getPrijavlenUporabnik().getIsAdmin());
                System.out.println("Vnesite ID termina");
                int idTermin = Integer.parseInt(bis.readLine());
                System.out.println("Vnesite stevilo oseb");
                int steviloOseb = Integer.parseInt(bis.readLine());
                Iterator<Termin> iterTermin = pocitnice.getTermini();
                while (iterTermin.hasNext()){
                    Termin termin = iterTermin.next();
                    if (termin.getId() == idTermin){
                        if ((termin.getSteviloOseb() + steviloOseb) 
                                <= pocitnice.getMaxOseb()){
                            termin.addRezervacija(new Rezervacija(
                                this.getPrijavlenUporabnik().getUporabniskoIme(),
                                steviloOseb));
                        }
                        else {
                            System.out.println("Presezeno maksimalno stevilo ljudi");
                        }
                        break; 
                    }
                }
            break;
            }
        }
    }

    public VnesnikUporabnik(){}

    public VnesnikUporabnik(String uporabniskoIme){
        super(uporabniskoIme);
    }
}
