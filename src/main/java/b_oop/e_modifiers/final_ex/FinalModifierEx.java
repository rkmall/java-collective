package b_oop.e_modifiers.final_ex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

public class FinalModifierEx {

    // Class constant:
    // Variable finalImmutableNum is:
    //  - final variable i.e. it can't be re-assigned.
    //  - immutable i.e. there exist only one copy of object referenced by this variable.
    public static final double finalImmutableNum = 1.11111;

    // Object constant:
    // Variable finalNum is:
    //  - final variable i.e. it can't be re-assigned.
    //  - not immutable since each object will have its own copy of this variable
    //    when the object is created.
    private final int finalNum;

    // Compile-time constants:
    public final int n1 = 10;
    private final int n2 = n1 * 2;
    public final String s1 = "compile";
    private final String s2 = s1 + "-time";

    // Run-time constants
    private final static int randNum = new Random().nextInt(100);

    public FinalModifierEx(int finalNum) {
        this.finalNum = finalNum;
    }

    public static void finalVariables() {
        // Final primitive variable
        final int myInt = 10;       // final primitive variable
        //myInt = 20;               // Error: can't be re-assigned

        // Final reference variable
        final ArrayList<Integer> numbers = new ArrayList<>();
        //numbers = new ArrayList<>();  // Error: can't be re-assigned
        numbers.add(0);                 // referenced object may be modified
        numbers.add(1);
        numbers.add(2);
        numbers.forEach(System.out::println);
    }

    public static void immutability() {
        String name = "Foo"; // String object "Foo" is unchangeable
        name = "Bar";        // variable name re-assigned with new String object
        System.out.println(name);
    }
}
