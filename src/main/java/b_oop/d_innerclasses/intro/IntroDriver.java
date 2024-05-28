package b_oop.d_innerclasses.intro;

import java.util.Scanner;

public class IntroDriver {

    public static void main(String[] args) {

        // Create inner class, approach 1
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();

        // Create inner class, approach 2
        Outer.Inner inner1 = new Outer().new Inner();

        // Create nested class
        Outer.Nested nested = new Outer.Nested();
    }
}
