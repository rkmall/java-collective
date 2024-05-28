package b_oop.c_interfaces.callback_ex.intro.interface_approach;

/**
 * CallbackHolder holds the implementation of the Callback interfaces.
 * This class is equivalent to Function Pointer in C since it
 * hold the implementation of callback
 */
public class CallbackHolder implements CallbackAdd, CallbackSubtract {
    @Override
    public int add(int x, int y) {
        int res = x + y;
        System.out.println("In CallbackAdd: result = " + res);
        return res;
    }

    @Override
    public int subtract(int x, int y) {
        int res = x - y;
        System.out.println("In CallbackSubtract: result = " + res);
        return res;
    }
}
