package b_oop.d_innerclasses.nested;

public class ArrayMinMax {
    // Nested Class
    public static class Pair {
        private double first;
        private double second;

        public Pair(double first, double second) {
            this.first = first;
            this.second = second;
        }

        public double getFirst() {
            return first;
        }

        public double getSecond() {
            return second;
        }
    }

    // Method outside the nested class returns instance of nested class
    public static Pair minMax(double[] values) {
        double min = Double.POSITIVE_INFINITY;  // NOTE: min is the greatest value
        double max = Double.NEGATIVE_INFINITY;  // and max is the smalled value
        for (double v : values) {
            if (min > v) min = v;
            if (max < v) max = v;
        }
        return new Pair(min, max);
    }
}
