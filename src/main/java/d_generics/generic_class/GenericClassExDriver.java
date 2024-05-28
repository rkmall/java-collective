package d_generics.generic_class;

public class GenericClassExDriver {

    public static void main(String[] args) {

        //pairOfSingleTypeTest();

        //pairOfMultipleTypesTest();

        genericArrayTest();

    }

    public static void pairOfSingleTypeTest() {
        SingleTypePair<String> stringPair = new SingleTypePair<>("app", "program");
        String e1 = stringPair.getFirst();
        String e2 = stringPair.getSecond();
        System.out.println("First = " + e1 + ", Second = " + e2);
    }

    public static void pairOfMultipleTypesTest() {
        KeyValPair<Integer, String> pair = new KeyValPair<>(10, "Ten");
        Integer e1 = pair.getKey();
        String e2 = pair.getValue();
        System.out.println("First = " + e1 + ", Second = " + e2);
    }

    public static void genericArrayTest() {
        MyGenericArray<String> words = new MyGenericArray<>(3);
        words.addElement("apple", 0);
        words.addElement("ball", 1);
        //words.addElements(10, 2);     // compiler error: element type is String
        words.addElement("cat", 2);

        String e2 = words.getElement(1);
        System.out.println(e2);
        words.printElements();
    }
}
