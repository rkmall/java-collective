package i_regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexIntro {

    public static void main(String[] args) {

        //patternAndMatcherInstantiationSequence();

        //escapingMetaChars();
        //matchingAnyChar();
        //matchingDigits();
        //matchingWords();

        //boundaryMatcherEx();

        //quantifiersEx();
        differenceInQuantifiersEx();
    }

    public static void patternAndMatcherInstantiationSequence() {
        String regex = "a*b";
        Pattern pattern = Pattern.compile(regex);      // compile pattern
        Matcher matcher = pattern.matcher("aaaaaab"); // create matcher through pattern
        boolean res = matcher.matches();    // use macther object

        // Same as above three statements
        boolean res1 = Pattern.matches(regex, "aaaaaab");
    }

    public static void escapingMetaChars() {
        /*
         * Java regex uses '\' character as escape character just like Java Strings do.
         * Double backslashes are necessary in order to match period character "."
         *
         *  - First, the compiler interprets the double backslashes "\\" as a
         *    single backslash '\'
         *
         *  - Then, the regex string "\\." becomes "\." that escapes the period char.
         *    The special metacharacter meaning of the metacharacter '.' is ignored and
         *    the actual literal value is used.
         */
        String regex1 = "\\.";
        boolean res = Pattern.matches(regex1, ".");
        System.out.println("\\. matches: " + res);

        /*
         * Four backslashes are necessary in order to match the backslash String "\\".
         * Note: Java String also uses '\' as an escape character, hence "\\"
         *
         *  - First, the compiler interprets first two backslashes in the regex string "\\"
         *    as a single backslash '\' which matches the escape char '\' itself used to
         *    escape '\' in the string input "\\".
         *    Secondly, the rest two backslashes in the regex "\\" is interpreted as '\'
         *    which is the actual backslash char we want to escape.
         *
         *  - Then, the regex string "\\\\" becomes "\\" that matches the string input "\\".
         */
        String regex2 = "\\\\";
        res = Pattern.matches(regex2, "\\"); // note: Java String also uses '\' to escape
        System.out.println("\\\\ matches: " + res);


        /*
         *  - First double backslashes "\\" interpreted as '\' that is used to escape
         *    the bracket '[' since it is an escaped character.
         *
         *  - Then, the regex string "\\[.*]" becomes "\[.*]"
         */
        String regex3 = "\\[.*]";
        res = Pattern.matches(regex3, "[hello]");
        System.out.println("\\[.*] matches: " + res  );
    }

    public static void matchingAnyChar() {
        /*
         * Regex pattern period '.' matches any single character
         * regardless of what char it is.
         */
        String regex = ".";
        System.out.println(". matches: " + Pattern.matches(regex, "a"));
        System.out.println(". matches: " + Pattern.matches(regex, "h"));

        /*
         * Regex "http://.*" matches any string that contains the chars
         * "http://" followed by any characters.
         */
        regex = "http://.*";
        System.out.println("http://.* matches: " + Pattern.matches(regex, "http://www.in.com"));

        /*
         * Character class (set of characters to match) is enclosed in the square
         * bracket.
         * E.g.,  [aei] part of the regex string "H[aei]llo" will match
         * one of the characters inside the brackets but no more than one.
         *  - Only an "a", "e" or "i" is allowed between the "H" and "llo".
         */
        regex = "H[aei]llo";
        System.out.println("H[aei]llo matches: " + Pattern.matches(regex, "Hallo"));
        System.out.println("H[aei]llo matches: " + Pattern.matches(regex, "Hello"));
        System.out.println("H[aei]llo matches: " + Pattern.matches(regex, "Hillo"));

        /*
         * Character class can also contain range of characters.
         * E.g., [a-c] part of the regex string "H[a-c]llo" will match
         * of the characters between a and c inclusive.
         *  - I.e. a, b or c is allowed between "H" and "llo".
         */
        regex = "H[a-c]llo";
        System.out.println("H[a-c]llo matches: " + Pattern.matches(regex, "Hallo"));
        System.out.println("H[a-c]llo matches: " + Pattern.matches(regex, "Hbllo"));
        System.out.println("H[a-c]llo matches: " + Pattern.matches(regex, "Hcllo"));
    }

    public static void matchingDigits() {
        // Regex "\\d" is interpreted as "\d" that matches any single digit.
        String regex = "\\d";
        System.out.println("\\d matches: " + Pattern.matches(regex, "1"));
        System.out.println("\\d matches: " + Pattern.matches(regex, "5"));

        // Regex "\\d" and range "[0-9]" are same.
        regex = "[0-9]";
        System.out.println("[0-9] matches: " + Pattern.matches(regex, "1"));
        System.out.println("[0-9] matches: " + Pattern.matches(regex, "5"));

        // Regex "[0-9]*" matches string containing multiple digits.
        regex = "[0-9]*";
        System.out.println("[0-9]* matches: " + Pattern.matches(regex, "455"));
        System.out.println("[0-9]* matches: " + Pattern.matches(regex, "1899"));

        // Regex "\\D" matches non-digit single character
        regex = "\\D";
        System.out.println("\\D matches: " + Pattern.matches(regex, "a"));
        System.out.println("\\D matches: " + Pattern.matches(regex, "b"));

        // Regex "\\D*" matches non-digit multiple characters
        regex = "\\D*";
        System.out.println("\\D* matches: " + Pattern.matches(regex, "apple"));
        System.out.println("\\D* matches: " + Pattern.matches(regex, "ball"));
    }

    public static void matchingWords() {
        // Regex "\\w" is interpreted as "\w" that matches any single digit.
        String regex = "\\w";
        System.out.println("\\w matches: " + Pattern.matches(regex, "h"));
        System.out.println("\\w matches: " + Pattern.matches(regex, "5"));

        // Regex "\\w" and range "[a-zA-Z0-9]" are same.
        regex = "[a-zA-Z0-9]";
        System.out.println("[a-zA-Z0-9] matches: " + Pattern.matches(regex, "h"));
        System.out.println("[a-zA-Z0-9] matches: " + Pattern.matches(regex, "5"));

        // Regex "[a-zA-Z0-9]*" matches string containing multiple chars within the
        // specified ranges.
        regex = "[a-zA-Z0-9]*";
        System.out.println("[a-zA-Z0-9]* matches: " + Pattern.matches(regex, "hello10"));
        System.out.println("[a-zA-Z0-9]* matches: " + Pattern.matches(regex, "time55"));

        // Regex "\\W" matches non-word single character
        regex = "\\W";
        System.out.println("\\W matches: " + Pattern.matches(regex, "&"));
        System.out.println("\\W matches: " + Pattern.matches(regex, "$"));

        // Regex "\\W*" matches non-word multiple characters
        regex = "\\W*";
        System.out.println("\\W* matches: " + Pattern.matches(regex, "&*^"));
        System.out.println("\\W* matches: " + Pattern.matches(regex, "$£@"));
    }

    public static void boundaryMatcherEx() {
        /*
         * Regex "^" matches the beginning of a line.
         * E.g., below example only gets a single match at index 0.
         * Note: "^" only matches the beginning of the input string,
         *       not the beginning of each line.
         */
        String text = "Line1\nLine2\nLine3";
        Pattern pattern = Pattern.compile("^");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println("^ found match at: " +
                    matcher.start() + " to " +
                    matcher.end());
        }

        /*
         * Regex "^http://" is the combination of "^" with other chars.
         * It checks and matches if the input string begins with the
         * specified chars "http://".
         * Returns the start index (incl.) and the end index (excl.)
         * of the matched substring.
         */
        text = "http://www.in.com";
        pattern = Pattern.compile("^http://");
        matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println("^http:// found match at: " +
                    matcher.start() + " to " +
                    matcher.end());
        }

        /*
         * Regex ".com$" is the combination of "$" with other chars.
         * It checks and matches if the input string ends with the
         * specified chars ".com".
         * Returns the start index (incl.) and the end index (excl.)
         * of the matched substring.
         */
        text = "http://www.in.com";
        pattern = Pattern.compile(".com$");
        matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println(".com$ found match at: " +
                    matcher.start() + " to " +
                    matcher.end());
        }

        /*
         * Breaking down the regex "^http://[a-z]*.*com$"
         *  - "^http://" matches if the beginning of input string
         *    begins with "http://".
         *
         *  - "[a-z]*" matches the multiple characters containing the
         *    specified characters.
         *
         *  - "\\.*" matches period '.' character.
         *
         *  - "com$" matches if the end of input string ends with "com".
         */
        text = "http://in.com";
        String regex = "^http://[a-z]*\\.com$";
        System.out.println("^http://[a-z]*\\.com$ matches: " + Pattern.matches(regex, text));

        /*
         * Regex "\\b" matches all the words including space found in the
         * input string.
         */
        text = "Mary had a little lamb";
        pattern = Pattern.compile("\\b");
        matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println("\\b found match at: " +
                    matcher.start() + " to " +
                    matcher.end());
        }

        /*
         * Regex "\\bl" matches all the words that contains
         * the substring "l".
         * Returns the start index (incl.) and the end index (excl.)
         * of the matched substring.
         */
        text = "Mary had a little lamb";
        pattern = Pattern.compile("\\bl");
        matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println("\\bl found match at: " +
                    matcher.start() + " to " +
                    matcher.end());
        }
    }

    public static void quantifiersEx() {
        /*
         * Quantifier "X?" matches input string containing "X" zero or once.
         * E.g., regex "hello?" matches "hell" followed by "o" zero or once
         * i.e. "hell" or "hello"
         */
        String regex = "hello?";
        System.out.println("hello? matches: " + Pattern.matches(regex, "hell"));
        System.out.println("hello? matches: " + Pattern.matches(regex, "hello"));

        /*
         * Quantifier "X*" matches input string containing "X" zero or more times.
         * E.g., regex "hello*" matches "hell" followed by "o" zero or more
         * i.e. "hell", "hello", "hellooo" etc.
         */
        regex = "hello*";
        System.out.println("hello* matches: " + Pattern.matches(regex, "hell"));
        System.out.println("hello* matches: " + Pattern.matches(regex, "hello"));
        System.out.println("hello* matches: " + Pattern.matches(regex, "hellooo"));

        /*
         * Quantifier "X+" matches input string containing "X" one or more times.
         * E.g., regex "hello*" matches "hell" followed by "o" one or more times
         * i.e. "hell", "hello", "hellooo" etc.
         */
        regex = "hello+";
        System.out.println("hello+ matches: " + Pattern.matches(regex, "hell"));
        System.out.println("hello+ matches: " + Pattern.matches(regex, "hello"));
        System.out.println("hello+ matches: " + Pattern.matches(regex, "hellooo"));

        /*
         * Quantifier "X{n}" matches input string containing "X" exactly n times.
         * E.g., regex "hello{2}" matches "hell" followed by "o" exactly 2 times
         * i.e. "helloo"
         */
        regex = "hello{2}";
        System.out.println("hello{2} matches: " + Pattern.matches(regex, "hello"));
        System.out.println("hello{2} matches: " + Pattern.matches(regex, "helloo"));


        /*
         * Quantifier "X{n,}" matches input string containing "X" at least n times.
         * E.g., regex "hello{2,}" matches "hell" followed by "o" at least 2 times
         * i.e. "helloo", "hellooo", "hellooooo"
         */
        regex = "hello{2,}";
        System.out.println("hello{2,} matches: " + Pattern.matches(regex, "hello"));
        System.out.println("hello{2,} matches: " + Pattern.matches(regex, "helloo"));
        System.out.println("hello{2,} matches: " + Pattern.matches(regex, "hellooooo"));

        /*
         * Quantifier "X{n,m}" matches input string containing "X" at least n times
         * and not more than m times.
         * E.g., regex "hello{2,5}" matches "hell" followed by "o" at least 2 times
         * and not more than 5 times.
         * i.e. "hellooo", "helloooo", "hellooooo"
         */
        regex = "hello{2,5}";
        System.out.println("hello{2,5} matches: " + Pattern.matches(regex, "hello"));
        System.out.println("hello{2,5} matches: " + Pattern.matches(regex, "helloo"));
        System.out.println("hello{2,5} matches: " + Pattern.matches(regex, "hellooooo"));
    }

    public static void differenceInQuantifiersEx() {
        /*
         * Greedy quantifier:
         * Regex ".*foo" matches any chars zero or more times followed by the chars "foo".
         *  - First, greedy quantifier reads the entire string before starting matching.
         *    At this point, the overall expression doesn't match the chars "foo" have
         *    already been consumed.
         *
         *  - So, the matcher backs off one letter at a time until the rightmost occurence
         *    of "foo" has been read again at which point the match succeeds and the search
         *    ends.
         */
        String regex = ".*foo";
        String input = "xfooxxxxxxfoo";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            System.out.println(".*foo found match at: " +
                    matcher.start() + " to " +
                    matcher.end());
        }

        /*
         * Reluctant quantifier:
         *  - First, reluctant quantifier reads noting and starts matching.
         *    Since "foo" doesn't appear at the beginning of the string,
         *    it is forced to read "x", this triggers the first match at 0 to 4.
         *
         *  - Then, the matcher continues to process until the rest of the string is completely
         *    read. It finds another match at 4 to 13.
         */
        regex = ".*?foo";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(input);

        while (matcher.find()) {
            System.out.println(".*?foo found match at: " +
                    matcher.start() + " to " +
                    matcher.end());
        }

        /*
         * Possessive quantifier:
         *  - First, possessive quantifier reads the entire string before matching.
         *
         *  - Since possessive quantifier doesn't back off like greedy quantifier
         *    there's noting left to match the chars "foo" at the end of the expression.
         */
        regex = ".*+foo";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(input);

        while (matcher.find()) {
            System.out.println(".*+foo found match at: " +
                    matcher.start() + " to " +
                    matcher.end());
        }
    }
}
