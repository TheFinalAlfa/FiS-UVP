import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DomacaNaloga1 {
    public static void main(String[] args) throws Exception {
        System.out.println("Vnesite poljubno celo stevilo:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String vnos = br.readLine();
            char[] array = vnos.toCharArray();
            for (int i = array.length - 1; i >= 0; i--){
                System.out.print(array[i]);
            }
            System.out.println();
        }
        catch (Exception e){
            System.out.println("Prislo je do napake");
            System.out.println(e.getMessage());
        }
    } 
}