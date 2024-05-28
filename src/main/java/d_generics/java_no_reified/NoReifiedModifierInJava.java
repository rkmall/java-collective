package d_generics.java_no_reified;

import java.util.ArrayList;
import java.util.List;

public class NoReifiedModifierInJava {

    public static void main(String[] args) {

        //test1();
    }

    public static void test1() {
        String[] words = new String[1];
        words[0] = "a";
        System.out.println(getClassOf(words));
    }

    /**
     * Generic type T gets erased at runtime.
     * There is not reified modifier in Java unlike Kotlin but this hack
     * can be used to find the array type.
     * @param array the generic array
     * @return array type
     * @param <T> the generic type
     */
    public static <T> Class<T> getClassOf(T[] array) {
        return (Class<T>) array.getClass().getComponentType();
    }
}
