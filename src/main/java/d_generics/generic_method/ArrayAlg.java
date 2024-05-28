package d_generics.generic_method;

import java.util.List;

public class ArrayAlg {
    static class MPair<T> {
        private final T first;
        private final T second;

        public MPair(T first, T second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() { return first; }

        public T getSecond() { return second; }
    }

    public static <T> T getMid(T... data) { // type param placed after modifiers and before return type T
        return data[data.length/2];
    }

    public static <E> void printArray(E[] elements) {       // array of type E
        for (E e : elements) {
            System.out.println(e);
        }
    }

    public static <E> void printList(List<E> elements) {    // list of type E
        for (E e : elements) {
            System.out.println(e);
        }
    }

    // Bounded type parameter:
    // Type parameter is restricted to Comparable interface i.e.
    // the method takes only types that implements Comparable interface
    public static <T extends Comparable<T>> T min(T[] data) {
        if (data == null || data.length == 0) return null;
        T smallest = data[0];
        for (int i = 1; i < data.length; i++) {
            if (smallest.compareTo(data[i]) > 0) {
                smallest = data[i];
            }
        }
        return smallest;
    }

    // Type parameter with multiple bounding types:
    // Type parameter is restricted to Number class and Comparable interface i.e.
    // the method takes only type that extends Number class and implements Comparable interface.
    // In case of multiple bounds, Class bound must be placed first.
    public static <T extends Number & Comparable<T>> MPair<T> minMax(T[] data) {
        if (data == null || data.length == 0) return null;
        T min = data[0];
        T max = data[0];
        for (int i = 1; i < data.length; i++) {
            if (min.compareTo(data[i]) > 0) min = data[i];
            if (max.compareTo(data[i]) < 0) max = data[i];
        }
        return new MPair<>(min, max);
    }
}
