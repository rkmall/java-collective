package a_fundamentals.c_controlflow;

import java.util.ArrayList;
import java.util.Arrays;

public class BreakAndContinueEx {

    public static void main(String[] args) {

        //forLoopBreak();
        //forLoopContinue("AxxMaxx");

        //nestedLoopBreak();
        //nestedLoopContinue();

        //nestedLoopWithoutLabel();
        nestedLoopWithLabel();
    }

    public static void forLoopBreak() {
        int i;
        for (i = 0; i < 5; i++) {
            System.out.println("index = " + i);
            if (i == 3)
                break;
        }
        System.out.println("index = " + i);
    }

    public static void forLoopContinue(String s)
    {
        int i;
        char c;
        for (i = 0; i < s.length(); i++) {
            if ((c = s.charAt(i)) == 'x')
                continue;   // skip rest of the part below
            System.out.println("char " + i + ": " + c);
        }
    }

    public static void nestedLoopBreak() {
        int i, j;
        for (i = 0; i < 2; i++) {
            for (j = 0; j < 5; j++) {
                if (j == 3)
                    break;  // break out of this inner loop
                System.out.println("i = " + i + " j = " + j);
            }
        }
    }

    public static void nestedLoopContinue() {
        int i, j;
        for (i = 0; i < 2; i++) {
            for (j = 0; j < 5; j++) {
                if (j == 3)
                    continue; // continue to next iteration of this inner loop
                System.out.println("i = " + i + " j = " + j);
            }
        }
    }

    public static void nestedLoopWithoutLabel() {
        int i, j = 0;
        boolean stop = false;

        for (i = 0; i < 2 && !stop; i++) {
            for (j = 0; j < 5 && !stop; j++) {
                if (j == 3)
                    stop = true;
                System.out.println("i = " + i + " j = " + j);
            }
        }
        System.out.println("Stopped at: i = " + i + " j = " + j);
    }

    public static void nestedLoopWithLabel() {
        int i, j = 0;

        STOP:                           // Label
        for (i = 0; i < 2; i++) {
            for (j = 0; j < 5; j++) {
                if (j == 3)
                    break STOP;
                System.out.println("i = " + i + " j = " + j);
            }
        }
        System.out.println("Stopped at: i = " + i + " j = " + j);
    }
}
