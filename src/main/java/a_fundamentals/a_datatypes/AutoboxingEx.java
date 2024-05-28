package a_fundamentals.a_datatypes;

import java.util.ArrayList;

public class AutoboxingEx {

    public static void main(String[] args) {
        autoBoxingInt(12);
        autoBoxingChar('a');

    }

    public static void autoBoxingInt(int num) {
        Integer intObj = num;       // autoboxing int to Integer

        // Using wrapper class functions
        System.out.println(num + " equals 12 = " + intObj.equals(12));
        System.out.println(num + " - 100 = " + intObj.compareTo(100));
    }

    public static void autoBoxingChar(char ch) {
        Character chObj = ch;    // autoboxing char to Character

        // Using wrapper class functions
        System.out.println(ch + " equals a = " + chObj.equals('a'));
        System.out.println(ch + " compare to z = " + chObj.compareTo('z'));
    }

    public static void unboxingInt() {
        Integer obj = 55;
        int x = obj.intValue();     // unboxing
        int i  = obj;               // unboxing, compiler applies obj.intValue() internally
    }


    /**
     * Built-in data types such as ArrayList, LinkedList, HashMap do not take
     * primitive type. In such case, autoboxing is necessary.
     */
    public static void needForAutoboxing() {
        //ArrayList<int> list1 = new ArrayList<int>();  // error: type argument cannot be primitive
        ArrayList<Integer> list1 = new ArrayList<>();   // ok, Integer wrapper type, ArrayList only store object type

        ArrayList<Integer> numList = new ArrayList<>();
        numList.add(7);	        // compiler translates to numList.add(Integer.valueOf(7))
        int n = numList.get(0); // compiler translates to numList.get(i).intValue()
    }


    public static void swap(Integer x, Integer y) {
        Integer temp = x;
        x = y;
        y = temp;
    }
}
