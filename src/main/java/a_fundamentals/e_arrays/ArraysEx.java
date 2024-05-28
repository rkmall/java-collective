package a_fundamentals.e_arrays;

import java.util.Arrays;
import java.util.Collection;

public class ArraysEx {

    public static void main(String[] args) {
        //arrayIsObjectType();
        //arrayDeclarationAndInitialization();
        //arrayMembers();
        //defaultInitialization();
    }

    public static void arrayIsObjectType() {
        // Array can be assigned to Object variable
        int[] a = new int[] { 1, 2, 3 };// primitive int array type
        Object obj = a;                 // array assigned to Object type
        int[] b = (int[]) obj;          // Object cast as int array
        for (int i : b)
            System.out.println(i);

        // Test to see primitive int[] type class attributes
        Class clazz = int[].class;

        System.out.println("Name: " + clazz.getName());
        System.out.println("Simple name: " + clazz.getSimpleName());
        System.out.println("Canonical name: " + clazz.getCanonicalName());
        System.out.println("Type name: " + clazz.getTypeName());
        System.out.println("Package name: " + clazz.getPackageName());
        System.out.println("Class loader: " + clazz.getClassLoader());
        System.out.println("Super class: " + clazz.getSuperclass());
        for (Class interF: clazz.getInterfaces()) {
            System.out.println("Interface: " + interF);
        }
    }

    public static void arrayDeclarationAndInitialization() {
        // These declarations do not create array objects.
        // It only creates array variable that can contain reference to an array.
        int[] intArr;               // array of int
        short[] shortArr;           // array of short
        Object[] objArr;            // array of Object
        Collection<?>[] collArray;  // array of Collection of unknown type
        int[][] twoDimeArr;         // array of array to int

        // Declaration with initialization creates array objects.
        int[] ints = new int[10];                   // ints holds ref to newly created int array
        short[] shorts = new short[] { 1, 2, 3 };   // shorts holds ref to newly created short array
        Object[] objects = new String[] { "abc", "xyz" };   // objects holds ref to newly created Object array

        // Using array initializer
        int[] A = new int[] { 1, 2, 3 }; // length is equal to the number of elements in the initializer
        String[] s = {"abc", "xyz" };
        int[][] multiDimen = { { 1, 2 }, { 3, 4 }, null };
    }

    public static void arrayMembers() {
        int[] intA = new int[] { 11, 22, 33 };

        System.out.println("Length = " + intA.length);  // length
        int[] intB = intA.clone();                      // clone
        for (int i : intB)
            System.out.println(i);

        // Clones are copy. Not equal objects, as they have different references
        System.out.println("intA == intB: " + (intA == intB));
    }

    public static void defaultInitialization() {
        int[] A = new int[3];
        double[] F = new double[3];
        String[] S = new String[3];
        boolean[] B = new boolean[3];
        char[] C = new char[3];

        printObjects(Arrays.stream(A).boxed().toArray(Integer[]::new));
        printObjects(Arrays.stream(F).boxed().toArray(Double[]::new));
        printObjects(S);

        for (boolean b : B)
            System.out.println(b + " ");

        for (char c : C)
            System.out.println(c + " ");
    }

    public static <T> void printObjects(T[] arr) {
        for (T type : arr) {
            System.out.println(type + " ");
        }
    }
}