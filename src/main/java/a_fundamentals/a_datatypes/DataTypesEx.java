package a_fundamentals.a_datatypes;

public class DataTypesEx {

    /**
     * Integer Types:
     * <pre>
     * Byte => 1 byte
     * Short => 2 bytes
     * Int => 4 bytes
     * Long => 8 bytes
     * </pre>
     * <p>{@code long} should be explicitly assigned using {@code L}
     * otherwise the variable will be assigned with {@code int}.</p>
     *
     * Floating-Point Types:
     * <pre>
     * Float => 4 bytes
     * Double => 8 bytes
     * </pre>
     * <p>{@code float} should be explicitly assigned using {@code F}
     * otherwise the variable will be assigned with {@code double}.</p>
     */
    public static void primitiveNumericTypes() {
        byte myByte = 127;
        short myShort = 32767;
        int myInt = 2147483647;
        long myLong = 9_223_372_036_854_775_807L;   // long explicitly assigned using L
        System.out.println("Size of byte: " + Byte.BYTES + " bytes");
        System.out.println("Size of short: " + Short.BYTES + " bytes");
        System.out.println("Size of int: " + Integer.BYTES + " bytes");
        System.out.println("Size of long: " + Long.BYTES + " bytes");

        float myFloat = 13.1789F;                   // float explicitly assigned using F
        double myDouble = 13562334.19771234;
        System.out.println("Size of long: " + Float.BYTES + " bytes");
        System.out.println("Size of long: " + Double.BYTES + " bytes");
    }

    /**
     * Char Types:
     * <pre>
     * 16 bits = 2 bytes
     * </pre>
     *
     * Boolean TYPES:
     * <pre>
     * True => 1 bit
     * False => 1 bit
     * </pre>
     */
    public static void primitiveNonNumericTypes() {
        char ch1 = 'a';
        char ch2 = '\u2122';
        System.out.println("Size of char: " + Character.BYTES + " bytes");

        boolean isOpen = false;
        boolean isClosed = true;
        System.out.println("Size of long: " + 1 + " bytes");
    }

    public static void numberSystem() {
        int decVal = 26;		    // 26 in decimal
        int hexVal = 0x1a;		    // 26 in hexadecimal
        int octVal = 032;		    // 26 in octal
        int binVal = 0b11010;	    // 26 in binary
        double d1 = 123.4;
        double d2 = 1.234e2;	    // in scientific notation
        float f1 = 123.4f; 		    // in float

        // Using underscore
        long creditCardNumber = 1234_5678_9876_6543L;
        float pi = 3.14_15F;
    }

    public static void charAsciiValue() {
        int c = 'a';                    // NOTE: int c
        System.out.printf("%d\n", c);   // formatter %d
    }

    public static void variableName() {
        // Same name but different variables, every char is significant and case-sensitive
        int empId;
        int empID;

        // Multiple variable in a single line
        int i, j;
    }
}
