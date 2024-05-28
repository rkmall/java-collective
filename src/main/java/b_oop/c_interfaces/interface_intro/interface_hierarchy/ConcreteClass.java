package b_oop.c_interfaces.interface_intro.interface_hierarchy;

public class ConcreteClass implements ChildInterface {
    @Override
    public void call() {
        System.out.println("calling...");
    }

    @Override
    public void invoke() {
        System.out.println("invoking...");
    }

    @Override
    public void execute() {
        System.out.println("executing...");
    }
}
