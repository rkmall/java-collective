package d_generics.generic_class;

public class MyGenericArray<E> {
    private final int length;
    private final E[] elements;

    public MyGenericArray(int length) {
        this.length = length;
        this.elements = (E[]) new Object[length];
    }

    public E getElement(int index) {
        E res = null;
        if (index >= 0 && index < length) {
            res = elements[index];
        }
        return res;
    }

    public void addElement(E element, int index) {
        if (index >= 0 && index < length) {
            elements[index] = element;      // no cast required
        }
    }

    public int length() {
        return length;
    }

    public void printElements() {
        for (E e : elements) {
            System.out.println(e);
        }
    }
}
