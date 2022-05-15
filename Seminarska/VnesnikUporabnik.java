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
        loopIskanje:
        while (true) {
            System.out.println("Za iskanje po casovnem okviru vnesite (d)");
            System.out.println("Za iskanje po cenovnem okviru vnesite (c)");
            System.out.println("Za iskanje po drzavi vnesite (r)");
            System.out.println("Za iskanje po tipu pocitnic vnesite (t)");
            System.out.println("Za zacetek iskanja vnesite (a)");
            System.out.println("Za istop iz iskanja vnesite (q)");
            Iterator<Pocitnice> iterator = TuristicnaAgencija.pocitnice.iterator();
            Datum prihod = null;
            Datum odhod = null;
            int cenaZgornja = (Integer) null;
            int cenaSpodnja = (Integer) null;
            String drzava = null;
            String type = null;
            
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
                    while (iterator.hasNext()){
                        Pocitnice p = iterator.next();
                        if (!(drzava != null && p.getDrzava().equals(drzava))){
                            continue;
                        }
                        if (!(type != null && p.getType().equals(type))){
                            continue;
                        }
                        if (!(cenaSpodnja != (Integer) null && 
                                cenaZgornja != (Integer) null &&
                                cenaSpodnja >= p.getCena() &&
                                cenaZgornja <= p.getCena())){
                            continue;
                        }
                        boolean avaible = false;
                        if (!(odhod != null && prihod != null)){
                            continue;
                        }
                        else {
                            Iterator<Termin> iterTermin = p.getTermini();
                            while (iterTermin.hasNext()){
                                Termin termin = iterTermin.next();
                                if (termin.getPrihod().before(prihod) && 
                                    odhod.before(termin.getOdhod())){
                                        avaible = true;
                                    };
                            }
                        }
                        if (!(odhod != null && prihod != null) &&
                                avaible){
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
