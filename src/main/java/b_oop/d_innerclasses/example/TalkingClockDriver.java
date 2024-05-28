package b_oop.d_innerclasses.example;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TalkingClockDriver {

    public static void main(String[] args) {

        //talkingClockTest();
        //talkingClockWithLocalInnerClassTest();
        //talkingClockWithLambdaTest();
    }

    // Inner class example
    public static void talkingClockTest() {
        TalkingClock clock = new TalkingClock(1000, true);
        clock.start();
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }

    // Local inner class example
    public static void  talkingClockWithLocalInnerClassTest() {
        TalkingClockWithLocalInnerClass clock = new TalkingClockWithLocalInnerClass(1000, true);
        clock.start("Time");
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }

    // Anonymous class example
    public static void talkingClockWithLambdaTest() {
        TalkingClockWithLambda clock = new TalkingClockWithLambda(1000, true);
        clock.start("Time");
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }

    public static void anonymousInnerClassSyntaxExample() {
        ArrayList<String> words = new ArrayList<>() {{
            add("apple");
            add("ball");
            add("cat");     // NOTE: the outer braces make an anonymous inner class of ArrayList
        }};                 // and the inner braces are an object construction block
    }
}
