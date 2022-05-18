import java.util.ArrayList;

public class Skatla {
    public ArrayList<Integer> wholeInt = new ArrayList<>();
    public int lowest;

    public Skatla(int value){
        this.wholeInt.add(value);
        this.lowest = value;
    }

    public void PrintInt()
    {
        for (int i : wholeInt)
        {
            System.out.print(i);
            System.out.print(" ");
        };
        System.out.print("\n");
    };

    public void AddInt(int newInt)
    {
        this.wholeInt.add(newInt);
    };
}
