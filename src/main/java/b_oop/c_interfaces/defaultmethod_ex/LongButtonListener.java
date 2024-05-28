package b_oop.c_interfaces.defaultmethod_ex;

interface LongButtonListener {

    default void buttonLongClicked(String event) {      // default method
        System.out.println("Long button clicked: " + event);
    }
}
