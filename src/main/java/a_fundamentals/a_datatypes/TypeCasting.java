package a_fundamentals.a_datatypes;

public class TypeCasting {

    public static void main(String[] args) {

        //wideTypeCasting();
        //narrowTypeCasting();
        //integerOverflow();
    }

    public static void wideTypeCasting() {
        int x = 10;
        double y = 12.50;
        System.out.println("Sum = " + (x + y));     // int implicitly converted to double by compiler
    }

    public static void narrowTypeCasting() {
        int x = 10;
        double y = 12.50;
        System.out.println("Sum = " + (x + (int)y));// double manually cast to int, loss of decimal digit
    }

    public static void integerOverflow() {
        long l = 2147483648L;
        int i = (int) l;                    // double manually cast to int
        System.out.println("int i: " + i);  // prints -2147483648, overflow since Integer.max = 2147483647
    }
}
