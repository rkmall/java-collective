package a_fundamentals.b_operators;

import java.util.ArrayList;
import java.util.List;

public class OperatorsEx {

    public static void main(String[] args) {
        //assignmentOps();

//        String s1 = "Xdigit";
//        String s2 = "Xtorfor";
//        String s3 = "Fornicate";
//        String[] words = new String[] { s1, s2, s3 };
//
//        for (int i = 0; i < 3; i++) {
//            logicalOpsAnd(words[i]);
//        }
//
//        for (int i = 0; i < 3; i++) {
//            logicalOpsOr(words[i]);
//        }

        //prefixVsPostfix();
        //conditionalExpr(1);
        //instanceOfOp();

    }

    public static void assignmentOps() {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += i + 10;           // Add and assign: sum = sum + (i + 10)
        }
        System.out.println("Sum = " + sum);
    }

    public static void logicalOpsAnd(String s) {
        int len = 10;
        CharSequence sequence = "for";

        if (s.length() < len && s.charAt(0) == 'X' && s.contains(sequence)) {
            System.out.println("Valid: " + s);
        } else {
            System.out.println("Invalid: " + s);
        }
    }

    public static void logicalOpsOr(String s) {
        int len = 10;
        CharSequence sequence = "for";

        if (s.length() >= len || s.charAt(0) != 'X' || !s.contains(sequence)) {
            System.out.println("Invalid: " + s);
        } else {
            System.out.println("Valid: " + s);
        }
    }

    public static void prefixVsPostfix() {
        // Prefix: First increment, then assign
        int a = 5;
        int b = ++a;
        System.out.println("a = " + a + ", b = " + b);

        // Postfix: First assign, then increment
        int x = 5;
        int y = x++;    // subsequent access to x will have update value
        System.out.println("x = " + x + ", y = " + y);
    }

    public static void conditionalExpr(int num)
    {
        String res = num == 0 ? "false" : "true";
        System.out.println("Result: " + res);
    }

    public static void instanceOfOp() {
        String s = "Hello there";
        System.out.println("Result: " + (s instanceof String));
    }
}
