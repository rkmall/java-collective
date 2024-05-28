package b_oop.d_packages.packageB;

import b_oop.d_packages.packageA.ClassA;

public class ClassD extends ClassA {
    private final String message;

    public ClassD(int id, String name, String message) {
        super(id, name);
        this.message = message;
    }

    // Different pkg subclass can access and override public method inherently
    @Override
    public void showInfo() {
        System.out.println(
                "===>Message: " + message +
                //"\nId: " + id +       // different pkg, subclass can't access default
                "\nName: " + name +     // different pkg, subclass can access public inherently
                "\nPath: " + path       // different pkg, subclass can access protected inherently
        );
    }

    // Different pkg subclass can access and override protected method inherently
    // Notice: protected method in super is not changed to public in this class,
    //         to be able to call this method in non-subclass anywhere.
    @Override
    public void updatePath(String newPath) {
        super.updatePath(newPath);
        path = path + ": " + message;
    }
}
