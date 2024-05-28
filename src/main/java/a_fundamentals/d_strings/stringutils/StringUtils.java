package a_fundamentals.d_strings.stringutils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.Objects;
import java.util.Random;

public class StringUtils {

    public static final String EMPTY_STRING = "";
    public static final String COLON = ":";
    public static final String SPACE = " ";
    public static final String VERTICAL_PIPE = "|";
    public static final String SLASH = "/";
    public static final String UNDERSCORE = "_";
    public static final String HYPHEN = "-";
    public static final String TRUE = "true";
    public static final String FALSE = "false";

    public static final int INDEX_NOT_FOUND = -1;
    public static final int STRING_BUILDER_SIZE = 256;
    public static final int PAD_LIMIT = 8192;

    private StringUtils() throws IllegalAccessException {
        throw new IllegalAccessException("No instances allowed");
    }


    /**
     * Checks to see if any of the prefixes are present in the input String.
     *
     * @param input    String to analyze
     * @param prefixes String array to loop through
     * @return True if any of the prefixes were found at the start of the input String
     */
    public static boolean startsWIthAny(String input, String[] prefixes) {
        if (input == null || prefixes == null|| prefixes.length < 1) {
            return false;
        }

        int count = 0;
        boolean match = false;

        while (!match && count < prefixes.length) {
            match = input.startsWith(prefixes[count]);
            count++;
        }
        return match;
    }

    /**
     * Checks String is empty or null.
     *
     * @param cs char sequence
     * @return true if empty
     */
    public static boolean isEmpty(CharSequence cs) {
        return cs == null ||cs.length() == 0;
    }

    /**
     * Checks String is neither empty nor null.
     *
     * @param cs char sequence
     * @return true if not empty
     */
    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    @Nonnull
    public static <T extends CharSequence> T defaultIfEmpty(T str, T defaultStr) {
        return isEmpty(str)? defaultStr : str;
    }

    @Nonnull
    public static CharSequence emptyIfNull(@Nullable final CharSequence str) {
        return defaultIfEmpty(str, EMPTY_STRING);
    }

    @Nonnull
    public static String emptyIfNull(@Nullable final String str) {
        return defaultIfEmpty(str, EMPTY_STRING);
    }

    public static boolean isNumeric(CharSequence cs) {
        if (isEmpty(cs)) {
            return false;
        } else {
            for (int i = 0; i < cs.length(); ++i) {
                if (!Character.isDigit(cs.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks two strings for equality. If they're both null, they're considered equal.
     * @param s1 String one
     * @param s2 String two
     * @return True if both strings are equal
     */
    public static boolean equals(@Nullable String s1, @Nullable String s2) {
        return emptyIfNull(s1).equals(emptyIfNull(s2));
    }

    @Nonnull
    public static String takeLast(@Nonnull String str, int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Error: count can't be negative");
        }
        if (str.length() <= count) {
            return str;
        }
        return str.substring(str.length() - count);
    }

    /**
     * <p>Checks if a CharSequence is empty (""), null or whitespace only.</p>
     *
     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}. </p>
     *
     * <pre>
     * StringUtils.isBlank(null)        = true
     * StringUtils.isBlank("")          = true
     * StringUtils.isBlank(" ")         = true
     * StringUtils.isBlank("jim")       = false
     * StringUtils.isBlank("  jim  ")   = false
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is null, empty or whitespace only
     * @implNote Implementation taken from Apache StringUtils
     */
    public static boolean isBlank(@Nullable final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }

        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Checks if a CharSequence is not empty (""), null or whitespace only.</p>
     *
     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}. </p>
     *
     * <pre>
     * StringUtils.isBlank(null)        = false
     * StringUtils.isBlank("")          = false
     * StringUtils.isBlank(" ")         = false
     * StringUtils.isBlank("jim")       = true
     * StringUtils.isBlank("  jim  ")   = true
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is null, empty or whitespace only
     * @implNote Implementation taken from Apache StringUtils
     */
    public static boolean isNotBlank(@Nullable final CharSequence cs) {
        return !isBlank(cs);
    }


    /**
     * <p>Removes control characters (char &lt;= 32) from both
     * ends of this String, handling {@code null} by returning {@code null}.</p>
     *
     * <p>The String is trimmed using {@link String#trim()}.
     * Trim removes start and end characters &lt;= 32</p>
     *
     * <pre>
     * StringUtils.trim(null)        = null
     * StringUtils.trim("")          = ""
     * StringUtils.trim("   ")       = ""
     * StringUtils.trim("abc")       = "abc"
     * StringUtils.trim("   abc   ") = "abc"
     * </pre>
     *
     * @param str the String to be trimmed check, may be null
     * @return the trimmed string {@code null} if null String input
     * @implNote Implementation taken from Apache StringUtils
     */
    @Nullable
    public static String trim(@Nullable final String str) {
        return str == null? null : str.trim();
    }

    /**
     * <p>Strips any of a set of characters from the end of a String.</p>
     *
     * <p>A {@code null} input String returns {@code null}.
     * An empty string ("") input returns the empty string.</p>
     *
     * <pre>
     * StringUtils.stripEnd(null, *)          = null
     * StringUtils.stripEnd("", *)            = ""
     * StringUtils.stripEnd("abc", "")        = "abc"
     * StringUtils.stripEnd("abc", null)      = "abc"
     * StringUtils.stripEnd("  abc", null)    = "  abc"
     * StringUtils.stripEnd("abc  ", null)    = "abc"
     * StringUtils.stripEnd(" abc ", null)    = " abc"
     * StringUtils.stripEnd("  abcyx", "xyz") = "  abc"
     * StringUtils.stripEnd("120.00", ".0" )  = "12"
     * </pre>
     *
     * @param str        the String to remove characters from, may be null
     * @param stripChars the set of characters to remove, null treated as whitespace
     * @return the stripped string, {@code null} if null String input
     * @implNote Implementation taken from Apache StringUtils
     */
    @Nullable
    public static String stripString(@Nullable final String str, @Nullable final String stripChars) {
        int end;
        if (str == null || (end = str.length()) == 0) {
            return str;
        }
        if (stripChars == null) {
            while (end != 0 && Character.isWhitespace(str.charAt(end - 1))) {
                end--;
            }
        } else if (stripChars.isEmpty()) {
            return str;
        } else {
            while (end != 0 && stripChars.indexOf(str.charAt(end - 1)) != INDEX_NOT_FOUND) {
                end--;
            }
        }
        return str.substring(0, end);
    }

    /**
     * <p>Gets a substring from the specified String avoiding exceptions.</p>
     *
     * <p>A negative start position can be used to start/end {@code n}
     * characters from the end of the String</p>
     *
     * <p>The returned substring starts with the character in the {@code start}
     * position and ends before the {@code end} position. All position counting is
     * zero-based -- i.e., start at the beginning of the string use
     * {@code start = 0}. Negative start and end positions can be used to
     * specify offsets relative to the end of the String.</p>
     *
     * <p>If {@code start} is not strictly to the left of {@code end}, "" is returned.</p>
     *
     * <pre>
     * StringUtils.substring(null, *, *)    = null
     * StringUtils.substring("", *, *)      = ""
     * StringUtils.substring("abc", 0, 2)   = "ab"
     * StringUtils.substring("abc", 2, 0)   = ""
     * StringUtils.substring("abc", 2, 4)   = "c"
     * StringUtils.substring("abc", 4, 6)   = ""
     * StringUtils.substring( "abc", 2, 2)  = ""
     * StringUtils.substring("abc", -2, -1) = "b"
     * StringUtils.substring("abc", -4, 2)  = "ab"
     * </pre>
     *
     * @param str   the String to get the substring from, may be null
     * @param start the position to start from, negative means
     *              count back from the end of the String by this many characters
     * @param end   the position to end at (exclusive), negative means
     *              count back from the end of the String by this many characters
     * @return substring from start position to end position,
     * (@code null) if null String input
     * @implNote Implementation taken from Apache StringUtils
     */
    @Nullable
    public static String subString(@Nullable final String str, int start, int end) {
        if (str == null) {
            return null;
        } else {
            if (end < 0) {
                end += str.length();
            }
            if (start < 0) {
                start += str.length();
            }
            if (end > str.length()) {
                end = str.length();
            }
            if (start > end) {
                return EMPTY_STRING;
            } else {
                if (start < 0) {
                    start = 0;
                }
                if (end < 0) {
                    end = 0;
                }
                return str.substring(start, end);
            }
        }
    }

    /**
     * <p>Joins the elements of the String array into a single String
     * containing the provided list of elements.</p>
     *
     * <p>No delimiter is added before or after the list.
     * A {@code null} separator is the same as empty String {""}.
     * Null objects or empty strings within array are represented by
     * empty strings</p>
     *
     * <pre>
     * StringUtils.join(null, *)              = null
     * StringUtils.join({}, *)                = ""
     * StringUtils.join({null}, *)            = ""
     * StringUtils.join({"a","b", "c"}, "--") = "a--b--c"
     * StringUtils.join({"a","b", "c"}, null) = "abc"
     * StringUtils.join( {"a","b", "c"}, "")  = "abc"
     * StringUtils.join({null,"", "a"}, ",")  = ",,a"
     * </pre>
     *
     * @param strArray  the array of values to join together, may be null
     * @param separator the separator character to use, null treated as ""
     * @return the joined String, {@code null} if null array input
     */
    @Nullable
    public static String join(@Nullable final String[] strArray, @Nullable String separator) {
        if (strArray == null) {
            return null;
        }
        if (separator == null) {
            separator = EMPTY_STRING;
        }

        final int strNumber = strArray.length;
        final StringBuilder stringBuilder = new StringBuilder(strNumber);

        for (int i = 0; i < strNumber; i++) {
            if (strArray[i] != null) {
                stringBuilder.append(strArray[i]);
            }
            if (i < strNumber - 1) {
                stringBuilder.append(separator);
            }
        }
        return stringBuilder.toString();
    }


    /**
     * <p>Joins the String Varargs into a single String.</p>
     *
     * <p>No separator is added to the joined String.
     * Null objects or empty strings within array are represented by
     * empty strings</p>
     *
     * <pre>
     * StringUtils.join(null)           = null
     * StringUtils.join({})             = ""
     * StringUtils.join({null})         = ""
     * StringUtils.join({"a","b", "c"}) = "abc"
     * StringUtils.join({null,"", "a"}) = "a"
     * </pre>
     *
     * @param strings the values to join together, may be null
     * @return the Joined String, {@code null} if null array input
     */
    @Nullable
    public static String join(@Nullable final String... strings) {
        return join(strings, EMPTY_STRING);
    }


    /**
     * <p>Joins the elements of the provided {@code Iterator} into
     * a single String containing the provided elements.</p>
     *
     * <p>No delimiter is added before or after the list.
     * A {@code null} separator is the same as an empty String ("").</p>
     *
     * @param iterator  the {@code Iterator} of values to join together, may be null
     * @param separator the separator character to use, null treated as ""
     * @return the joined String, {@code null} if null iterator input
     * @implNote Implementation taken from Apache StringUtils
     */
    @Nullable
    public static String join(@Nullable final Iterator<?> iterator, final String separator) {
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return EMPTY_STRING;
        }

        final Object first = iterator.next();
        if (!iterator.hasNext()) {
            return Objects.toString(first, EMPTY_STRING);
        }

        final StringBuilder buf = new StringBuilder(STRING_BUILDER_SIZE);
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            if (separator != null) {
                buf.append(separator);
            }
            final Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

    /**
     * <p>Joins the elements of the provided {@code Iterable} into
     * a single String containing the provided elements.</p>
     *
     * <p>No delimiter is added before or after the list. Null objects or empty
     * strings within the iteration are represented by empty strings.</p>
     *
     * @param iterable  the {@code Iterable} providing the values to join together, may be null
     * @param separator the separator character to use
     * @return the joined String, {@code null} if null iterable input
     * @implNote Implementation taken from Apache StringUtils
     */
    @Nullable
    public static String join(@Nullable final Iterable<?> iterable, final char separator) {
        if (iterable == null) {
            return null;
        }
        return join(iterable.iterator(), String.valueOf(separator));
    }


    /**
     * <p>Joins the elements of the provided {@code Iterable} into
     * a single String containing the provided elements.</p>
     *
     * <p>No delimiter is added before or after the list.
     * A {@code null} separator is the same as an empty String ("")</p>
     *
     * @param iterable  the {@code Iterable} providing the values to join together, may be null
     * @param separator the separator character to use
     * @return the joined String, {@code null} if null iterable input
     * @implNote Implementation taken from Apache StringUtils
     */
    @Nullable
    public static String join(@Nullable final Iterable<?> iterable, final String separator) {
        if (iterable == null) {
            return null;
        }
        return join(iterable.iterator(), separator);
    }

    /**
     * <p>Capitalizes a String changing the first character to title case as
     * per {@link Character#toTitleCase(int)}. No other characters are changed.</p>
     *
     * <p>A {@code null} input String returns {@code null}.</p>
     *
     * <pre>
     * StringUtils.capitalize(null) = null
     * StringUtils.capitalize("") = ""
     * StringUtils.capitalize("cat") = "Cat"
     * StringUtils.capitalize("cAt") = "CAt"
     * StringUtils.capitalize("'cat") = "'cat"
     * </pre>
     *
     * @param str the String to capitalize, may be null
     * @return the capitalized String, {@code null} if null String input
     */
    @Nullable
    public static String capitalize(@Nullable final String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }

        final int firstCodePoint = str.codePointAt(0);
        final int newCodePoint = Character.toTitleCase(firstCodePoint);
        if (firstCodePoint == newCodePoint) {
            // already capitalized
            return str;
        }

        final int newCodePoints[] = new int[strLen];
        int outOffset = 0;
        newCodePoints[outOffset++] = newCodePoint;

        for (int inOffset = Character.charCount(firstCodePoint); inOffset < strLen; ) {
            final int codepoint = str.codePointAt(inOffset);
            // copy the remaining once
            newCodePoints[outOffset++] = codepoint;
            inOffset += Character.charCount(codepoint);
        }
        return new String(newCodePoints, 0, outOffset);
    }

    /**
     * <p>Returns padding using the specified delimiter repeated
     * to a given length.</p>
     *
     * <pre>
     * StringUtils.repeat('e', 0) = ""
     * StringUtils.repeat('e', 3) = "eee"
     * StringUtils.repeat('e', -2) = ""
     * </pre>
     *
     * <p>Note: this method does not support padding with
     * <a href="http://www.unicode.org/glossary/#supplementary_character">Unicode Supplementary Characters</a>
     * as they require a pair of {@code char}s to be represented.
     * If you are needing to support full I18N of your applications
     * consider using {@link #repeat(String, int)} instead.</p>
     *
     * @param ch     character to repeat
     * @param repeat number of times to repeat char, negative treated as zero
     * @return String with repeated character
     * @implNote Implementation taken from Apache StringUtils
     * @see #repeat(String, int)
     */
    private static String repeat(final char ch, final int repeat) {
        if (repeat == 0) {
            return EMPTY_STRING;
        }

        final char[] buf = new char[repeat];
        for (int i = repeat - 1; i >= 0; i--) {
            buf[i] = ch;
        }
        return new String(buf);
    }

    /**
     * <p>Repeat a String {@code repeat} times to form a
     * new String.</p>
     *
     * <pre>
     * StringUtils.repeat(null, 2) = null
     * StringUtils.repeat("", 0)   = ""
     * StringUtils.repeat("", 2)   = ""
     * StringUtils.repeat("a", 3)  = "aaa"
     * StringUtils.repeat("ab", 2) = "abab"
     * StringUtils.repeat("a", -2) = ""
     * </pre>
     *
     * @param str    the String to repeat, maybe null
     * @param repeat number of times to repeat str, negative treated as zero
     * @return a new String consisting of the original String repeated,
     * {@code null} if null String input
     * @implNote Implementation taken from Apache StringUtils
     */
    @Nullable
    public static String repeat(@Nullable final String str, final int repeat) {
        if (str == null) {
            return null;
        }
        if (repeat <= 0) {
            return EMPTY_STRING;
        }

        final int inputLen = str.length();
        if (repeat == 1 || inputLen == 0) {
            return str;
        }
        if (inputLen == 1 && repeat <= PAD_LIMIT){
            return repeat(str.charAt(0), repeat);
        }

        final int outputLen = inputLen * repeat;
        switch (inputLen) {
            case 1:
                return repeat(str.charAt(0), repeat);
            case 2:
                final char ch0 = str.charAt(0);
                final char ch1 = str.charAt(1);
                final char[] output2 = new char[outputLen];
                for (int i = repeat * 2 - 2; i >= 0; i--, i--) {
                    output2[i] = ch0;
                    output2[i + 1] = ch1;
                }
                return new String(output2);
            default:
                final StringBuilder buf = new StringBuilder(outputLen);
                for (int i = 0; i < repeat; i++) {
                    buf.append(str);
                }
                return buf.toString();
        }
    }

    /**
     * <p>Converts a String to lowercase as per {@link String#toLowerCase()}.</p>
     *
     * <p>A {@code null} input String returns {@code null}.</p>
     *
     * <pre>
     * StringUtils.lowerCase(null) = null
     * StringUtils.lowerCase("") = ""
     * StringUtils.lowerCase("aBc") = "abc"
     * </pre>
     *
     * <p><strong>Note:</strong> As described in the documentation for {@link String#toLowerCase()},
     * the result of this method is affected by the current locale.
     * For platform-independent case transformations, the method {@link #lowerCase(String)}
     * should be used with a specific locale (e.g. {@link java.util.Locale#ENGLISH}).</p>
     *
     * @param str the String to lower case, may be null
     * @return the lower cased String, {@code null} if null String input
     * @implNote Implementation taken from Apache StringUtils
     */
    @Nullable
    public static String lowerCase(@Nullable final String str) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase();
    }


    private static final int FLAGS_OFFSET = 127397;
    /**
     * <p>Convert a country ISO Code ("GB", "DE") into a flag character.
     * Correct behaviour depends on a font supporting country glyphs!
     * For more details follow documentation of EmojiCompat
     * <a href="https://developers.android.com/guide/topics/ui/look-and-feel/emoji/compat">Android Emoji Compat</a>
     * </p>
     *
     * <p>There is no validation if the input is correct country ISO Code
     * So if you pass something what is invalid countryCode, expect invalid chars as output!</p>
     *
     * @param countryCode 2 letters ISO country code
     * @return UTF encoded flag as String
     */
    @Nonnull
    public static String getEmojiFlag(@Nonnull String countryCode) {
        final StringBuilder result = new StringBuilder();
        final String country = countryCode.toUpperCase();
        for (int i = 0; i < country.length(); i++) {
            final int codePoint = FLAGS_OFFSET + (int) country.charAt(i);
            result.append(Character.toChars(codePoint));
        }
        return result.toString();
    }

    /**
     * Generate random String with the specified length.
     * @param length the length of the random String
     * @return random String
     */
    @Nonnull
    public static String generateRandomString(final int length) {
        String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        Random rand = new Random();

        for (int i = 0; i < length; i++) {
            result.append(alphaNumeric.charAt(rand.nextInt(alphaNumeric.length())));
        }
        return result.toString();
    }
}
