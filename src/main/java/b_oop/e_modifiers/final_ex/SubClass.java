package b_oop.e_modifiers.final_ex;

import java.awt.*;

public final  class SubClass extends SuperClass {
    public static final String MESSAGE = "SubClass";
    private final int subclassId;

    public SubClass(String name, int subclassId) {
        super(name);
        this.subclassId = subclassId;
    }

    @Override
    public void info() {
        super.info();
        System.out.println("Message: " + MESSAGE + ", Id: " + subclassId);
    }

    //public int calculate(final int x, int y) {}     // error: cannot override final method
}

// Error: final class can't be extended
//class SubSubClass extends SubClass {
//    public SubSubClass(String name, int subclassId) {
//        super(name, subclassId);
//    }
//}