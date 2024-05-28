package d_generics.generic_class;

public class SingleTypePair<T> { // type parameter specified at the end of Class header
    private final T first;  // variable of type T
    private final T second; // variable of type T

    public SingleTypePair(T first, T second) {    // constructor parameter of type T
        this.first = first;
        this.second = second;
    }

    public T getFirst() {   // returns type T
        return first;
    }

    public T getSecond() {  // returns type T
        return second;
    }
}
