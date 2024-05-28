package d_generics.generic_method;

import java.util.ArrayList;
import java.util.List;

public class GenericMethodExDriver {

    public static void main(String[] args) {

        //genericMethod();
        //boundedTypeParams();
        //typeParamsWithMultipleBounds();
    }

    public static void genericMethod() {
        String middle = ArrayAlg.getMid("apple", "ball", "cat");
        System.out.println(middle);

        String[] strings = { "abc","bcd", "cba" };
        ArrayAlg.printArray(strings);

        ArrayList<Integer> numbers = new ArrayList<>(List.of(1,2,3));
        ArrayAlg.printList(numbers);
    }

    public static void boundedTypeParams() {
        String[] words = { "ball", "dog", "cat", "apple" };     // String implements Comparable interface
        System.out.println("Min = " + ArrayAlg.min(words));
    }

    public static void typeParamsWithMultipleBounds() {
        Integer[] numbers = { 15, 58, 35, 89, 17, 73, 67, 98 };
        ArrayAlg.MPair<Integer> intPair =  ArrayAlg.minMax(numbers);
        System.out.println("First = " + intPair.getFirst() + ", Second = " + intPair.getSecond());

        Double[] doubles = { 15.5, 58.5, 35.5, 89.5, 17.5, 73.5, 67.5, 98.5 };
        ArrayAlg.MPair<Double> doublePair =  ArrayAlg.minMax(doubles);
        System.out.println("First = " + doublePair.getFirst() + ", Second = " + doublePair.getSecond());

        //String[] strings = { "abc", "bcd", "cbd" };
        //ArrayAlg.MPair<String> strPair = ArrayAlg.minMax(strings); // compiler error: T extends Numbers
    }
}
