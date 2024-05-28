package b_oop.a_oop.function_ex;

import java.util.Arrays;

public class VarargMethodsEx {

    public static void main(String[] args) {

        printVarArg("A", "B", "C"); // passing 3 args
        printVarArg();                      // passing 0 arg

        String[] strings = new String[] { "A", "B", "C" };
        printVarArg(strings);               // passing array instead of variable arg
    }

    // Vararg element is equivalent to array
    public static void printVarArg(String...values) {
        System.out.println(values instanceof String[]);         // true
        for (String s : values) { System.out.println(s); }
    }
}
