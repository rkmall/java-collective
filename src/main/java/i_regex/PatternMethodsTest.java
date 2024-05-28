package i_regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMethodsTest {

    public static void main(String[] args) {

        patternMatchesEx();
        patterCompileEx();
        patternSplit();
        patternPatternEx();

    }

    public static void patternMatchesEx() {
        String regex = ".*and.*";
        String text = "Line 1 and Line 2 and Line 2";
        boolean res = Pattern.matches(regex, text);
        System.out.println(".*and.* matches: " + res);
    }

    public static void patterCompileEx() {
        String regex = ".*http://.*";
        String text = "email: http://www.in.com";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        System.out.println(".*http://.* matches: " + matcher.matches());
    }

    public static void patternSplit() {
        String text = "Line1:Line2:Line3:Line4:Line5";
        Pattern pattern = Pattern.compile(":");
        String[] res = pattern.split(text);

        for (String s : res) {
            System.out.println(s);
        }
    }

    public static void patternPatternEx() {
        String regex = ".*http://.*";
        Pattern pattern = Pattern.compile(regex);
        System.out.println("Regex string: " + pattern.pattern());
    }
}
