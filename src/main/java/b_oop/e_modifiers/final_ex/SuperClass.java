package b_oop.e_modifiers.final_ex;

public class SuperClass {
    private final String name;

    public SuperClass(String name) {
        this.name = name;
    }

    // Final method
    public final int calculate(final int x, int y) {
        int local = 7;
        //x = local * 10;   // error: cannot modify final parameter
        y = local * 10;
        return x + y + local;
    }

    public void info() {
        System.out.println("Name: " + name);
    }
}
