package a_fundamentals.d_strings.stringutils;

public class Driver {

    public static void main(String[] args) {

        String rand = StringUtils.generateRandomString(10);
        System.out.println(rand);
    }


    public static void test() {
        String[] strings = new String[5];
        strings[0] = "a";
        strings[1] = "b";
        strings[2] = "c";
        strings[3] = "d";
        strings[4] = "e";

        int offSet = 0;
        strings[offSet++] = "z";

        for (int inOffset = offSet; inOffset < strings.length; ) {
            System.out.println(strings[inOffset]);
            inOffset++;
        }
    }
}

