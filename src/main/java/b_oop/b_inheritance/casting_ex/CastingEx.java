package b_oop.b_inheritance.casting_ex;

public class CastingEx {

    public static void main(String[] args) {
        //primitiveTypeCasting();
        upCasting();
        downCasting();

    }

    public static void primitiveTypeCasting() {
        double x = 3.405;
        int y = (int) x;    // converts x to int, discards fractional parts
    }

    public static void upCasting() {
        Parent p = new Child(1, "Child");   // done implicitly by compiler

        // Since the object variable is Parent type only methods specific
        // to Parent class and methods overridden by Child class are accessible.
        // E.g., updateName() and getName() are not accessible as they are specific
        //       to Child class.
        p.info();
    }

    public static void downCasting() {
        //Child c = new Parent(1);          // ERROR: compile-time error
        //Child c = (Child)new Parent(1);   // ERROR: run-time error: ClassCastException
        Parent p = new Child(1, "Child");

        if (p instanceof Child) {   // always check before casting
            Child c = (Child) p;
            c.info();
        }
    }
}

class Parent {
    private int id;

    public Parent(int id) {
        this.id = id;
    }
    public int getId() { return id; }
    public void info() { System.out.println("Id: " + id); }
}

class Child extends Parent {
    private String name;

    public Child(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() { return name; }
    public void updateName(String name) { this.name = name; }

    @Override
    public void info() {
        super.info();
        System.out.println("Name: " + name);
    }
}


