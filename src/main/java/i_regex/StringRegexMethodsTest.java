package i_regex;

public class StringRegexMethodsTest {

    public static void main(String[] args) {

        stringRegexMethods();

    }



    public static void stringRegexMethods() {
        // string.matches()
        String text = "one two three two one";
        System.out.println(".*two.* matches: " + text.matches(".*two.*"));

        // string.split()
        String[] res = text.split(" ");
        for (String s: res) {
            System.out.println(s);
        }

        // string.replaceFirst()
        String replaced = text.replaceFirst("three", "five");
        System.out.println("Replaced three with five: " + replaced);

        // string.replaceAll()
        replaced = text.replaceAll("two", "five");
        System.out.println("Replaced all two with five: " + replaced);
    }







}
