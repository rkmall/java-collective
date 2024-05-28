package a_fundamentals.c_controlflow;

import java.util.Scanner;

public class LoopsEx {

    public static void main(String[] args) {

        //sum(10);
        //printChars("Hello!");
        //rangeBasedLoop();
        //doModuloWhile(255);
        //assignmentAsExpr();
    }

    public static void sum(int n)
    {
        int start = 0;
        int sum = 0;

        while (start < n) {
            sum += start;       // this can also be written as sum += start++
            start++;
        }
        System.out.println("Sum = " + sum);
    }

    public static void printChars(String s)
    {
        int i;
        int serialNo;
        for (i = s.length() - 1, serialNo = 0; i >= 0; i--, serialNo++) {
            System.out.println(serialNo + ": " + s.charAt(i));
        }
    }

    public static void rangeBasedLoop()
    {
        int[] values = new int[] { 1,2,3,4,5 };
        for (int value : values) {          // read as for value in values
            System.out.println("Value: " + value);
        }
    }

    public static void doModuloWhile(int n) {
        int mod;
        do {
            mod = n % 10;
            System.out.println("n % 10 = " + mod);
        } while ((n /= 10) > 0);
    }

    public static void assignmentAsExpr() {
        int input;
        Scanner scanner = new Scanner(System.in);

        while ((input = scanner.nextInt()) != 0) {
            System.out.println("Input = " + input);
        }
    }
}
