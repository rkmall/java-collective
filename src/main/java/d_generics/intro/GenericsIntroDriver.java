package d_generics.intro;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Objects;

public class GenericsIntroDriver {

    public static void main(String[] args) {

        //beforeGenerics();
        typeParameter();
    }

    public static void beforeGenerics() {
        Object[] elements = new Object[3];  // array of objects
        elements[0] = "apple";
        elements[1] = "ball";
        elements[2] = 10;   // compiles and runs without error

        String[] strings = new String[3];   // array of strings
        for (int i = 0; i < elements.length; i++) {
            strings[i] = (String)elements[i];   // Runtime error: ClassCastException
        }                                       // element at index 2 is int
    }

    public static void typeParameter() {
        // Without type parameter
        ArrayList list = new ArrayList<>();
        list.add("apple");
        list.add("ball");
        list.add(10);
        //String s = list.iterator().next();  // Compile error: No type parameter specified
                                              // when instantiating the ArrayList.

        // With type parameter
        ArrayList<String> strings = new ArrayList<>();  // type parameter specified
        strings.add("apple");
        strings.add("ball");
        //strings.add(10);      // Compile error: ArrayList of String

        String res;
        for (String s : strings) {
            res = s;            // OK: no cast required
            System.out.println(res);
        }
    }

}
