package a_fundamentals.e_arrays;

public class MultiDimensionArrays {

    public static void main(String[] args) {

        //multiDimenArrayConcept();
        //multiDimenArrayInitialization();
        //threeDimenArray();

    }

    public static void multiDimenArrayConcept() {
        // Single dimen arrays
        int[] x = { 1, 2, 3 };              // array of ints
        int[] y = { 10, 20, 30, 40, 50 };
        int[] z = { 100, 200, 300 };

        // Multi dimen array
        int[][] multi = new int[3][];       // array of int references
        multi[0] = x;
        multi[1] = y;
        multi[2] = z;

        for (int i = 0; i < multi.length; i++) {
            for (int j = 0; j < multi[i].length; j++) {
                System.out.format("multi[%d][%d] = %d\n", i, j, multi[i][j]);
            }
        }
    }

    public static void multiDimenArrayInitialization() {
        // Using new array expression
        int size = 3;
        int[][] A = new int[size][];
        A[0] = new int[] { 1, 2, 3 };
        A[1] = new int[] { 10, 20, 30 };
        A[2] = new int[] { 100, 200, 300 };

        // Inline initialization
        int[][] B = {
                { 1 ,2 ,3 },
                { 10, 20, 30 },
                { 100, 200, 300}
        };

        // Using initializer
        int[] a1 = { 1, 2, 3 };
        int[] a2 = { 10, 20, 30 };
        int[] a3 = { 100, 200, 300 };
        int[][] C = new int[][] { a1, a2, a3 };

        // Initializing multi dimen string array
        String[] s1 = { "abc", "xyz" };
        String[] s2 = { "ijk", "mno" };
        String[][] strings = new String[][] { s1, s2 };
    }

    public static void threeDimenArray() {
        // array of int ref[2] to array of int ref[3] to array of int[4]
        int[][][] mat = new int[2][3][4];
        mat[0][0][0] = 'O';
        mat[0][0][1] = 'F';
        mat[0][0][2] = 'F';
        mat[1][0][0] = 'O';
        mat[1][0][1] = 'N';

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                for (int k = 0; k < mat[i][j].length; k++) {
                    System.out.format("mat[%d][%d][%d] = %d\n", i, j, k, mat[i][j][k]);
                }
            }
        }
    }
}











