package a_fundamentals.d_strings;

import java.util.Scanner;

public class StringEx {

    public static void main(String[] args) {

        //stringInitialization();
        //stringLength();
        //copyStrings();

        //concatenatingStrings();
        //changeStringContent();
        //subStringEx();

        //compareStrings();
        //emptyOrNullString();

        containsSearch();

        //codeUnitAndCodePoint();

        //simpleStringIO();
    }

    public static void stringInitialization() {
        // charArray and str are equivalent
        char[] charArray = { 'a', 'b', 'c' };
        String str = "abc";

        // Recommended, uses same string from the String pool if other
        // strings are also declared with the same sequence of chars.
        String s = "Hello";

        // Initializes String with empty character sequence.
        String s1 = new String();

        // Don't do this, new String is created each time this method is called.
        String s2 = new String("hello");

        // Allocates new String that contains the chars contained in
        // the arg StringBuilder.
        StringBuilder sb = new StringBuilder("hello");
        sb.append(" there");
        String s3 = new String(sb);
        System.out.println(s3); // print "hello there"

        // Allocates new String that contains the chars contained in
        // the arg StringBuffer.
        StringBuffer sBuf = new StringBuffer("hello");
        sBuf.append(" hi");
        String s4 = new String(sBuf);
        System.out.println(s4); // print "hello hi"

        // Allocates new String that contains chars from the arg char array.
        char[] chars = { 'h', 'e', 'l', 'l', 'o' };
        String s5 = new String(chars);

        // Allocates new String that contains chars from the sub-array of
        // the arg char array.
        String s6 = new String(chars, 1, 3);
        System.out.println(s6); // prints "ell"

        // Allocates new String that contains chars from the sub-array of
        // the arg codepoint array.
        int[] codePoints = { 104, 101, 108, 108, 111 }; // "hello"
        String s7 = new String(codePoints, 0, 5);
        System.out.println(s7); // print "hello"

        // Allocates new String by decoding the specified sub-array of bytes
        // using platforms default charset
        byte[] bytes = { 65, 66, 67 };  // "ABC"
        String s8 = new String(bytes, 0, 3);
        System.out.println(s8); // print "ABC"
    }

    public static void stringLength() {
        String s = "hello hi";
        System.out.println(s.length()); // length = 8
    }


    public static void copyStrings() {
        String s = "hello"; // creates a new string and assigns to address s

        String newStr = s;  // newStr is initialized with the string object
                            // referenced by s

        s = "changed";      // creates new string and assigns to address s

        System.out.println(s);      // prints "changed"
        System.out.println(newStr); // prints "hello"
    }

    public static void concatenatingStrings() {
        String name = "name";       // String obj
        int id = 10;                // int
        System.out.println("Info: " + name + id);   // print "Info: name10"

        String all = String.join("-", "Name", "ID", "Location", "Contact");
        System.out.println(all);    // prints "Name-ID-Location-Contact"
    }

    public static  void changeStringContent() {
        String s = "hello";
        s = s.substring(0, 1) + "ola";
        System.out.println(s);  // prints "Hola"
    }

    public static void subStringEx() {
        String s = "hamburger";
        String subStr1 = s.substring(3);
        System.out.println(subStr1);    // prints "burger"

        String subStr2 = s.substring(3, 7);
        System.out.println(subStr2);    // prints "burg"
    }

    public static void compareStrings() {
        String s1 = "hello";
        String s2 = "there";

        System.out.println("Equals: " + s1.equals(s2));                             // false
        System.out.println("Equals: " + s1.equalsIgnoreCase("Hello"));  // true

        // h - t = 104 - 116 = -12
        System.out.println("Result: " + s1.compareTo(s2));

        String s3 = "held";

        // l - d = 108 - 100 = 8
        System.out.println("Result: " + s1.compareTo(s3));

        // Equal => 0
        System.out.println("Result: " + s1.compareTo("hello"));

        // Equal => 0
        System.out.println("Result: " + s1.compareToIgnoreCase("Hello"));

        StringBuffer sb = new StringBuffer("hello");
        System.out.println("ContentEquals: " + s1.contentEquals(sb)); // true

        CharSequence cs = new StringBuilder("Hello");
        System.out.println("ContentEquals: " + s1.contentEquals(cs)); // false

        // "hel" == "hel" => false since different location
        System.out.println(s1.substring(0, 3) ==  "hel");
    }

    public static void emptyOrNullString() {
        String s = new String();

        // Test for empty
        System.out.println("IsEmpty: " + s.isEmpty());       // method, uses length == 0 internally
        System.out.println("IsEmpty: " + (s.length() == 0)); // using length
        System.out.println("IsEmpty: " + s.equals(""));      // using empty

        // Test for null
        System.out.println("IsNull: " + (s == null));

        // Test both null and empty
        // Always test for null first
        if ((s != null) && !s.equals("")) {
            System.out.println("Is not null and not empty");
        } else {
            if (s.equals("")) {
                System.out.println("Is not null but is empty");
            }
        }

        // Test for blank
        String s1 = "\t\n";
        System.out.println("IsBlank: " + s1.isBlank());     // blank
        System.out.println("IsEmpty: " + s1.isEmpty());     // not empty
    }

    public static void containsSearch() {
        // Using contains()
        String s = "Hello there helix nebula";
        System.out.println("Contains: " + s.contains("lix"));

        // Using matches()
        System.out.println("Matches: " + s.matches("helix"));
        System.out.println("Matches: " + s.matches("(.*)helix(.*)"));
    }

    public static void codeUnitAndCodePoint() {
        String s = "hello";
        System.out.println("Length = # of code units = " + s.length());
        System.out.println("True length = # code points = " + s.codePointCount(0, s.length()));

        String s1 = "\uD835\uDD4F";
        System.out.println(s1);
        System.out.println("Length = # of code units = " + s1.length());
        System.out.println("True length = # code points = " + s1.codePointCount(0, s1.length()));

        int i;
        String line = "\uD835\uDD4F is a logo";
        for (i = 0; i < line.length(); i++ ) {
            if (Character.isSupplementaryCodePoint(line.codePointAt(i))) {
                i = i + 2;
            }

            if (line.charAt(i) == ' ')
                System.out.println("space");
            else
                System.out.println(line.charAt(i));
        }

        int[] codepoints = line.codePoints().toArray();
        for (int cp : codepoints) {
            System.out.println(cp);
        }
    }

    public static void simpleStringIO() {
        int id;
        String name;
        double height;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your id: ");
        id = scanner.nextInt();
        scanner.nextLine();     // consume newline char '\n' left in the buffer
        System.out.print("Enter your full name: ");
        name = scanner.nextLine();
        System.out.print("Enter your height: ");
        height = scanner.nextDouble();
        System.out.println("Id: " + id + ", Name: " + name + ", Height: " + height);
    }
}
