package b_oop.d_packages.packageA;

public class ClassB extends ClassA {
    private final String message;

    public ClassB(int id, String name, String message) {
        super(id, name);
        this.message = message;
    }

    // Same pkg subclass can access and override public method inherently
    @Override
    public void showInfo() {
        System.out.println(
                "===>Message: " + message +
                "\nId: " + id +     // same pkg subclass can access default inherently
                "\nName: " + name + // same pkg subclass can access public inherently
                "\nPath: " + path   // same pkg subclass can access protected inherently
        );
    }

    // Same pkg subclass can access and override protected method inherently
    // Notice: protected method in super is now changed to public in this class,
    //         to be able to call this method in non-subclass anywhere.
    @Override
    public void updatePath(String newPath) {
        super.updatePath(newPath);
        path = path + ": " + message;
    }
}

