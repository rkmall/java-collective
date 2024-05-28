package b_oop.a_oop.static_ex;

public class StaticEx {


    public static void main(String[] args) {
        System.out.println("Result: " + compareStrings("abc", "abc"));
    }





    public static int compareStrings(String s1, String s2) {
        return s1.compareTo(s2);
    }
}
