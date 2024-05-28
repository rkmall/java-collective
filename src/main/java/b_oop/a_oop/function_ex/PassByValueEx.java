package b_oop.a_oop.function_ex;

public class PassByValueEx {

    public static void main(String[] args) {

//        int num = 10;
//        tripleValue(num);
//        System.out.println(num);

//        MyInt num1 = new MyInt(10);
//        tripleValue(num1);
//        System.out.println(num1.getValue());


//        MyInt num2 = new MyInt(1);
//        referenceNotAltered(num1);
//        System.out.println(num2.getValue());

        int x = 10, y = 20;
        swapUnaffected(x, y);
        System.out.println(x + ", " + y);

        y = swap(x, x = y);
        System.out.println(x + ", " + y);
    }


    /**
     * Pass by value: Primitive variable
     *  - Parameter x is a primitive variable. When the method is called:
     *      - x is initialized with copy of actual argument num.
     *      - then, x is modified.
     *      - then, x is destroyed when this method completes.
     *  - The content of the actual argument num is unaffected.
     *  - The only way to deal with this is to return the modified value back
     *    to the caller of this method.
     */
    public static void tripleValue(int x) {
        x *= 3;
    }

    /**
     * Pass by value: Object reference
     *  - Parameter x is an object reference. When the method is called:
     *      - x is initialized with copy of actual argument val, both x and num1
     *        refer to the same object in the memory.
     *      - then, raiseValue() is called on x, value is modified.
     *      - then, x is destroyed when this method completes. But the actual argument
     *        val still refers to the object whose value was modified.
     *  - The content of the actual argument num1 is affected.
     */
    public static void tripleValue(MyInt x) {
        x.raiseValue();
    }

    public static void referenceNotAltered(MyInt x) {
        x = new MyInt(100);  // object reference x is copy itself
    }                           // it is destroyed when this method completes
                                // actual argument is not affected

    // Doesn't work since wrapper class Integer is immutable and final
    public static void swapUnaffected(Integer a, Integer b) {
        Integer temp = a;
        a = b;
        b = temp;
    }

    public static int swap(int a, int b) {
        return a;
    }
}

class MyInt {
    private int value;

    public MyInt(int x) { this.value = x; }
    public int getValue() { return value; }
    public void raiseValue() { value *= 3; }
}
