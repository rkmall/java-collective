package b_oop.d_innerclasses.intro;

public class Outer {
    private String outer_name = "OuterClass";
    private static final int id = 0;
    /**
     * Inner class gets reference to Outer Class as it is not Static
     *  - Can access Outer class fields (even private fields)
     */
    public class Inner {
        public int id = 1;
        private final String inner_name = "InnerClass";

        public void accessOuterClassFields() {
            System.out.println(inner_name + "\n" +  // accessing its own field
                    "ID: " + id +                   // can access static field
                    ", Name: " + outer_name         // can access instance field
            );
        }
    }

    /**
     * Nested class does not get reference to Outer Class as it is Static
     *  - Can only access Outer class static fields
     */
    public static class Nested {
        public int id = 2;
        private final String nested_name = "NestedClass";

        public void accessOuterClassFields() {
            System.out.println(nested_name + "\n" + // accessing its own field
                    "ID: " + id                     // can access only static fields
                    //", Name: " + outer_name +     // can't access instance fields
            );
        }
    }
}
