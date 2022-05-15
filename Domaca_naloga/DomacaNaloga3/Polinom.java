import java.lang.Math;
public class Polinom {

    public Polinom() {
    }

    public Polinom(Double[] args) {
        this.coefficients = args;
    }

    public void print()
    {
        for (int index = 0; index < coefficients.length; index++)
        {
            if (coefficients[index] != 0)
            {
                if (coefficients.length - 1 - index != 0)
                {
                    if (coefficients[index] > 0)
                    {
                        System.out.print("+");
                    };
                    System.out.print(coefficients[index].toString() + "x^" + Integer.toString(coefficients.length - 1 -index));
                }
                else
                {
                    if (coefficients[index] > 0)
                    {
                        System.out.print("+");
                    };
                    System.out.print(coefficients[index].toString() + "\n");
                }
            };
        };
    }
    
    public Polinom addition(Polinom other)
    {
        int size = coefficients.length - other.coefficients.length;
        Double [] value = new Double[Math.max(coefficients.length, other.coefficients.length)];
        if (size != 0)
        {
            for (int i = 0; i < Math.abs(size); i++)
            {
                if (size < 0)
                {
                    value[i] = other.coefficients[i];
                }
                else
                {
                    value[i] = coefficients[i];
                }
            }
        }
        for (int i = 0; i < coefficients.length - Math.abs(size) ||
             i < other.coefficients.length - Math.abs(size); i++)
        {
            if (size < 0)
            {
                value[Math.abs(size) + i] = coefficients[i] + other.coefficients[Math.abs(size) + i];
            }
            else
            {
                value[Math.abs(size) + i] = coefficients[Math.abs(size) + i] + other.coefficients[+ i];
            }
        };
        return new Polinom(value);
    }

    private Double[] coefficients;

    public Double[] getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(Double[] coefficients) {
        this.coefficients = coefficients;
    }

}
