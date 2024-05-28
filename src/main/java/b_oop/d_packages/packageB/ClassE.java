package b_oop.d_packages.packageB;

import b_oop.d_packages.packageA.ClassA;

public class ClassE {
    private final String message;

    public ClassE(String message) {
        this.message = message;
    }

    // Same pkg non-subclass can't access and override public method
    public void showInfo(ClassA classA) {   // ClassA instance
        System.out.println(
                "===>Message: " + message +
                //"\nId: " + classA.id +    // different pkg, non-subclass can't access default
                "\nName: " + classA.name    // different pkg, non-subclass can't access public through instance
                //"\nPath: " + classA.path  // different pkg, non-subclass can't access protected
        );
    }
}



