package b_oop.d_packages;

import static java.lang.Double.parseDouble;

public class PackageEx {
    public static void main(String[] args) {
        String stringNumber = "5345";
        usingImportPackage(stringNumber);
        usingStaticImportPackage(stringNumber);
    }

    // Method using java.lang.Integer package
    public static void usingImportPackage(String input) {
        int result = Integer.parseInt(input);
        System.out.println(result);
    }

    // Method using static java.lang.Double.parseDouble package
    public static void usingStaticImportPackage(String input) {
        double result = parseDouble(input);
        System.out.println(result);
    }
}
