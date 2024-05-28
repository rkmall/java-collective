package b_oop.c_interfaces.defaultmethod_ex;

public interface ButtonListener {
    // By default, do nothing; but can be overridden by the implementing class
    default void buttonClicked(String event) {}
    default void buttonLongClicked(String event) {}
}
