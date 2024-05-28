package b_oop.d_innerclasses.nested;

public class NestedDriver {

    public static void main(String[] args) {

        double[] arr = new double[] { 14.77, 19.89, 123.467, 90.45, 1.5 };
        ArrayMinMax.Pair pair = ArrayMinMax.minMax(arr);
        System.out.println("min = " + pair.getFirst() + ", max = " + pair.getSecond());
    }
}
