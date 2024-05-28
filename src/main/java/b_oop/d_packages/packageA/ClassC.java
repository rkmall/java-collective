package b_oop.d_packages.packageA;

public class ClassC {
    private final String message;

    public ClassC(String message) {
        this.message = message;
    }

    // Same pkg non-subclass can't access and override public method
    public void showInfo(ClassA classA) {  // ClassA instance
        System.out.println(
                "===>Message: " + message +
                "\nId: " + classA.id +     // same pkg non-subclass access default through instance
                "\nName: " + classA.name + // same pkg non-subclass access public through instance
                "\nPath: " + classA.path   // same pkg non-subclass access protected through instance
        );
    }
}
