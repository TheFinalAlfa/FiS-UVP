public class Datum{
    public long year;
    public int mounth;
    public int day;
    public int hour;

    public Datum(long year, int mounth, int day, int hour){
        this.year = year;
        if (mounth <= 12 && mounth > 0 && hour <= 23 && hour >= 0 && day > 0 && day <= 31){
            this.mounth = mounth;
            this.hour = hour;
            this.day = day;
        }
        else {
            System.out.println("Mesec ali ura sta izven pravilnega intervala.");
        }
    }

    public Datum(){};

    boolean equals(Datum date){
        return (this.year == date.year && this.mounth == date.mounth && 
                this.hour == date.hour && this.day == date.day);
    }

    boolean before(Datum date){
        if (this.year == date.year){
            if (this.mounth == date.mounth){
                if (this.day == date.day){
                    return this.hour <= date.hour;
                }
                else {
                    return this.day <= date.day;
                }
            }
            else {
                return this.mounth <= date.mounth;
            }
        }
        else {
            return this.year <= date.year;
        }
    }

    boolean after(Datum date){
        return !this.after(date);
    }

    @Override
    public String toString(){
        String str = "";
        str += (Long.toString(this.year) + "-");
        str += (Integer.toString(this.mounth) + "-");
        str += (Integer.toString(this.day) + "-");
        return str + Integer.toString(this.hour);
    }

    public void fromString(String string){
        String[] array = string.split("-");
        this.year = Integer.parseInt(array[0]); 
        this.mounth = Integer.parseInt(array[1]);
        this.day = Integer.parseInt(array[2]);
        this.hour = Integer.parseInt(array[3]);
    }
}