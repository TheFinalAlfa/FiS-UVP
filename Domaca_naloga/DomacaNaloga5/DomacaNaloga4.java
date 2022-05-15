import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DomacaNaloga4 {
    
    public static void main(String[] args) throws Exception {
        InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

        ArrayList<Skatla> boxes = new ArrayList<>();
        mainloop:
        while (true)
        {
            System.out.println("To add a new number input a number.");
            System.out.println("To end the program press Enter.");
            System.out.println();
            String choice = br.readLine();
            if (choice == "") {
                break mainloop;
            }
            else {
                int choiceParsed = Integer.parseInt(choice);
                if (boxes.size() == 0) {
                    boxes.add(new Skatla(choiceParsed));
                }
                else {
                    boolean added = false;
                    for (Skatla i : boxes) {
                        if (i.lowest > choiceParsed) {
                            i.lowest = choiceParsed;
                            i.AddInt(choiceParsed);
                            added = true;
                            break;
                        }
                    }
                    if (!added) {
                        boxes.add(new Skatla(choiceParsed));
                    }
                }
            };
        };
        for (Skatla skatla : boxes) {
            skatla.PrintInt();
        }
    }
}
