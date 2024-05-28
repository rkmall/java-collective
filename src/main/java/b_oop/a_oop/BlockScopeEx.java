package b_oop.a_oop;

public class BlockScopeEx {

    public static void main(String[] args) {

    }

    // Outer block
    public static void blockScope() {
        int n = 5;

        // Inner block
        while (n-- > 0 ) {
            int k;      // OK
            //int n;      // Error: can't redefine n in inner block
        }
    }
}
