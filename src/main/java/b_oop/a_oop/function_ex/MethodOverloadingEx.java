package b_oop.a_oop.function_ex;

public class MethodOverloadingEx {

    public static void main(String[] args) {
        fun();
        fun(1);
        fun("hi");
        fun("hi", "there");

    }

    public static void fun() { System.out.println("Hello from fun");}

    public static void fun(int x) { System.out.println("Hello from fun, x = " + x);}

    public static void fun(String s) { System.out.println("Hello from fun, s = " + s);}

    public static void fun(String s1, String s2) { System.out.println("Hello from fun, s1 = " + s1 + ", s2 = " + s2); }

    // Method overloading is based on method signature (method name and params)
    // Return type is not part of method signature, therefore, we can;t have
    // two methods with same name and params but different return type.
    /*public static String fun(String s1, String s2) {
        System.out.println("Hello from fun, s1 = " + s1 + ", s2 = " + s2);
    }*/
}
