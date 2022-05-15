public class DomacaNaloga2 {

    public static void main(String[] args)
    {
        check_arg_polindrom(args);
    }

    static boolean is_polindrom(String arg){
        if (arg.length() % 2 == 0)
        {
            for (int i = 0; i < ((arg.length() / 2)); i++)
            {
                if (arg.charAt(i) != arg.charAt(arg.length() - 1 - i))
                {
                    return false;
                };
            };
            return true;
        }
        else{
            for (int i = 0; i < (((arg.length() - 1) / 2)); i++)
            {
                if (arg.charAt(i) != arg.charAt(arg.length() - 1 - i)) return false;
            };  
            return true;
        }
    }

    static void check_arg_polindrom(String[] args)
    {
        String longest = new String("");
        String value = args[0];
        for (Integer start_pos = 0; start_pos < value.length(); start_pos++)
        {
            for (Integer end_pos = start_pos; end_pos < value.length(); end_pos++)
            {
                String substr = value.substring(start_pos, end_pos + 1);
                if ((longest.length() < substr.length()) && is_polindrom(substr))
                {
                    /*
                    System.out.println("start_pos: " + Integer.toString(start_pos));
                    System.out.println("end_pos: " + Integer.toString(end_pos));
                    System.out.println(substr + "\n");
                     */
                    longest = substr;
                };
            }
        }
        System.out.println(longest);
    }
}