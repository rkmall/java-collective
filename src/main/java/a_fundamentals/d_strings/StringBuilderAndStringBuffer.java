package a_fundamentals.d_strings;

public class StringBuilderAndStringBuffer {

    public static void main(String[] args) {

        //System.out.println(stringBuilderAppend("Jim"));
        //stringBuilderInsert();

        String res = stringBufferAppend(stringBuilderAppend("Jim"));
        System.out.println(res);

    }

    public static void stringBuilderInitialization() {
        // Create a StringBuilder with the specified String content
        StringBuilder sb = new StringBuilder("token");

        // Create a StringBuilder with no chars in it
        StringBuilder emptySb = new StringBuilder();

        // Create a StringBuilder with no chars in it and custom initial capacity
        // The default capacity is 16 chars
        StringBuilder sbWithCapacity = new StringBuilder(24);

        // Create a StringBuilder with the specified CharSequence content
        CharSequence cs = "kite";
        StringBuilder sbWithSequence = new StringBuilder(cs);
    }

    public static StringBuilder stringBuilderAppend(String str) {
        StringBuilder sb = new StringBuilder("Info: \n");
        char code = 'x';
        int id = 17;
        double dVal = 25.77;

        sb.append("Name: ");
        sb.append(str);
        sb.append(", Token: ");
        sb.append(code);
        sb.append(id);
        sb.append(":");
        sb.append(dVal);
        return sb;
    }

    public static void stringBuilderInsert() {
        StringBuilder sb = new StringBuilder("Hello");
        sb.insert(sb.length(), " There");   // sb.length is the index where the new string
        System.out.println(sb);                 // will be appended.
    }

    public static String stringBufferAppend(StringBuilder builder) {
        StringBuffer sb = new StringBuffer("Message\n");
        sb.append("Sequence: ");
        sb.append(builder);         // appending StringBuilder
        return sb.toString();
    }
}
