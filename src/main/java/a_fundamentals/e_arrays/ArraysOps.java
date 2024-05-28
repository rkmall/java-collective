package a_fundamentals.e_arrays;

import java.util.Arrays;
import java.util.List;

public class ArraysOps {

    public static void main(String[] args) {

        //arrayAsList();
        //checkArraysEquality();
        //fillArray();

        //copyArray();
        //sortArrays();
        //binarySearchArray();

    }

    public static void arrayAsList() {
        List<Integer> list = Arrays.asList( 1,2,3);
        list.forEach(System.out::println);

        List<String> strings = Arrays.asList("Jim", "Kim", "Joe");
        strings.forEach(System.out::println);
    }

    public static void checkArraysEquality() {
        String[] a = { "abc", "ijk", "xyz" };
        String[] b = { "abc", "ijk", "xyz" };
        System.out.println("Result: " + Arrays.equals(a, b));
    }

    public static void fillArray() {
        // Fill array of int
        int[] ints = new int[3];
        Arrays.fill(ints, 5);
        for (int i : ints)
            System.out.println(i);

        // Fill array of String
        String[] strings = new String[3];
        Arrays.fill(strings, "x");
        for (String s : strings)
            System.out.println(s);
    }

    public static void copyArray() {
        // Both variables A and B contains same address referencing
        // the same array object in memory, no new array object is created.
        // It creates a shallow copy
        int[] A = { 2, 3, 5 };
        int[] B = A;

        // copyOf() creates a new array C and copies the elements from the original array A
        // Hence, A and C point to different array object in memory, it is a deep copy.
        int[] C = Arrays.copyOf(A, A.length);

        // Deep copy array using System.arraycopy()
        int[] D = new int[A.length];
        System.arraycopy(A, 0, D, 0, 3 );

        System.out.println("A reference: " + A.toString());
        System.out.println("B reference: " + B.toString());
        System.out.println("C reference: " + C.toString());
        System.out.println("D reference: " + D.toString());
    }

    public static void sortArrays() {
        // Sort ints
        int[] unsortedInts = { 16, 14, 89, 1, 56 };
        Arrays.sort(unsortedInts);      // sort
        for (int i : unsortedInts)
            System.out.println(i);

        // Sort doubles
        // Search in strings
        String[] strings = {"dog", "cat", "apple", "ball" };
        Arrays.sort(strings);
        for (String s : strings)
            System.out.println(s);
    }

    public static void binarySearchArray() {
        int position = -1;

        // Search in ints
        int[] unsortedInts = { 16, 14, 89, 1, 56 };
        Arrays.sort(unsortedInts); // array must be sorted before calling binarySearch
        position = Arrays.binarySearch(unsortedInts, 89 );  // search
        System.out.println("Key position: " + position );

        // Search in strings
        String[] strings = {"dog", "cat", "apple", "ball" };
        Arrays.sort(strings);
        position = Arrays.binarySearch(strings, "cat");
        System.out.println("Key position: " + position );
    }
}
