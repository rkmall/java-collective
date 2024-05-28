package d_generics.generic_vs_raw;

import d_generics.generic_class.KeyValPair;

public class GenericVsRawExDriver {

    public static void main(String[] args) {

        KeyValPair<Integer, String> pair = new KeyValPair<>(1, "One");
        String val = pair.getValue();
        System.out.println(val);
    }
}
