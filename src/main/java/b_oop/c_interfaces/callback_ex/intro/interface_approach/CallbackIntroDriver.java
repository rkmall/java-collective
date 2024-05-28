package b_oop.c_interfaces.callback_ex.intro.interface_approach;

public class CallbackIntroDriver {

    public static void main(String[] args) {
        // Approach 1
        CallbackHolder callback = new CallbackHolder();
        Caller caller = new Caller();
        caller.call(1, "aba", callback);
    }
}
