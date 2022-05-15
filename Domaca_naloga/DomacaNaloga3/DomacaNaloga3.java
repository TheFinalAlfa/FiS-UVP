public class DomacaNaloga3 {
    
    public static void main(String[] args) {
        Double[] coefficients1 = {-1.0, 2.2, 485.0};
        Double[] coefficients2 = {1.0, 25.0, 54.0, -857.0, 892.4};
        Polinom p1 = new Polinom(coefficients1);
        Polinom p2 = new Polinom(coefficients2);
        p1.addition(p2).print();
    }
}
