package i_regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherMethodsTest {

    public static void main(String[] args) {

        //matcherMatchesEx();
        //matcherFindStartEndEx();
        //matcherLookingAt();

        //matcherReset();

        //matcherGroup();
        //matcherMultipleGroups();
        //matcherNestedGroups();

        //matcherReplaceAllReplaceFirst();
        //matcherAppendReplacementAppendTail();
    }

    /**
     * matches() matches the regex against the whole input string.
     */
    public static void matcherMatchesEx() {
        String regex = ".*http://.*";
        String text = "email: http://www.in.com";
        Pattern pattern = Pattern.compile(regex);   // compile regex
        Matcher matcher = pattern.matcher(text);    // create matcher from pattern
        System.out.println(".*http://.* matches: " + matcher.matches());
    }

    /**
     * find() matches the regex against multiple matches if any in
     * the input string.
     *  - First, find() finds the first match.
     *  - Then, the subsequent call to find() moves to next match if any.
     *  start() returns the index where the match starts
     *  end() returns the offset after the end of the match.
     */
    public static void matcherFindStartEndEx() {
        String text = "This is the text which is to be searched";
        Pattern pattern = Pattern.compile("is");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println("is found match at: " +
                    matcher.start() + " to " +
                    matcher.end());
        }
    }

    /**
     * lookingFor() is same as matches() except it only matches the regex
     * against the beginning of the input string while matches() matches
     * the regex against the whole input string.
     */
    public static void matcherLookingAt() {
        String text = "Text to search for the occurrence of http:// pattern";
        Pattern pattern = Pattern.compile("Text to");
        Matcher matcher = pattern.matcher(text);
        System.out.println("matches: " + matcher.matches());    // false
        System.out.println("find: " + matcher.find());          // true
        System.out.println("lookingAt: " + matcher.lookingAt());// true
    }

    /**
     * reset() resets the matching state internally in the Matcher
     * so that the matching can start from the beginning.
     */
    public static void matcherReset() {
        String text = "Text to search for the occurrence of http:// pattern";
        Pattern pattern = Pattern.compile("Text to");
        Matcher matcher = pattern.matcher(text);

        System.out.println("lookingAt: " + matcher.lookingAt());// true
        System.out.println("find: " + matcher.find());          // false

        matcher.reset(text);                                        // reset
        System.out.println("after reset, find: " + matcher.find()); // true
    }

    /**
     * Groups are marked using parenthesis "()" in the regex. E.g. (is)
     *  - Regex (is) matches the text "is" where parenthesis are not part of
     *    the text to be matched.
     *
     * Regex can have more than one group where each group is marked with a
     * separate set of parenthesis. These group can be accessed using group number
     *  - group 0 is always the whole regex.
     *  - To access group marked by parenthesis, start with group 1.
     */
    public static void matcherGroup() {
        String text = "This is the text which is to be searched";
        Pattern pattern = Pattern.compile("(is)");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println("found: " + matcher.group(1));
        }
    }

    /**
     * Regex "(is) (.+?) " has two groups with the following meanings:
     *  - group1 "(is) " matches the input "is" with space after it
     *
     *  - group2 "(.+?) " has:
     *      - '.' means match any character.
     *      - '+?' a quantifier meaning match one or more times but start
     *        from the beginning and until the entire string completely read.
     */
    public static void matcherMultipleGroups() {
        String text = "This is the text which is to be searched";
        Pattern pattern = Pattern.compile("(is) (.+?) ");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println("found: " + matcher.group(1) + " " + matcher.group(2));
        }
    }

    /**
     * Groups can be nested inside other group. E.g., "((is) (.+?) )".
     *  - group1 is the whole group i.e. "((is) (.+?) )".
     *  - group2 is the nested group starting from left i.e. "(is) ".
     *  - group3 is the next nested group i.e. "(.+?) ".
     */
    public static void matcherNestedGroups() {
        String text = "This is the text which is to be searched";
        Pattern pattern = Pattern.compile("((is) (.+?)) ");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println("found: <" + matcher.group(1) + "> <" +
                    matcher.group(2) + "> <" +
                    matcher.group(3) + ">");
        }
    }

    public static void matcherReplaceAllReplaceFirst() {
        String text = "Jim was on the beach, Jim wrote a poem there.";
        Pattern pattern = Pattern.compile("(Jim) ");
        Matcher matcher = pattern.matcher(text);

        String replaceAll = matcher.replaceAll("Gary ");
        System.out.println("replaceAll: " + replaceAll);

        String replaceFirst = matcher.replaceFirst("Gary ");
        System.out.println("replaceFirst: " + replaceFirst);
    }

    public static void matcherAppendReplacementAppendTail() {
        String text = "Jim was on the beach, Jim wrote a poem there.";
        Pattern pattern = Pattern.compile("(Jim) ");
        Matcher matcher = pattern.matcher(text);

        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(sb, "Gary ");
            System.out.println("sb: " + sb);
        }
        matcher.appendTail(sb);
        System.out.println("Result: " + sb);
    }
}
