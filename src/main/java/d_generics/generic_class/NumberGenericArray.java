package d_generics.generic_class;

public class NumberGenericArray<E extends Number> extends MyGenericArray<E> {
    public NumberGenericArray(int length) {
        super(length);
    }
}
