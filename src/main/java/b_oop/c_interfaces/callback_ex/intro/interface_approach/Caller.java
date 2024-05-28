package b_oop.c_interfaces.callback_ex.intro.interface_approach;

/**
 * Caller is equivalent to Higher-Order function since it contains
 * the method that takes interface type (function pointer) as parameter.
 * It takes the interface type and calls back/executes the callback defined
 * by the interface type at some point inside it's body
 */
public class Caller {

    public void call(int option, String s, CallbackHolder callbackHolder) {
        // Do some processing before calling  callback:
        // to produce required arguments tha can be passed to the callback.
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum += s.charAt(i);
        }

        int DIVISOR = 7;
        int quotient = sum / DIVISOR;
        int modulo = sum % DIVISOR;
        System.out.println("In Caller: quotient = " + quotient + ", " + "mod = " + modulo);

        // Call the callback passing the required arguments
        // and get back the result
        int res;
        if (option == 1) {
            res = callbackHolder.add(quotient, modulo);         // call callback
        } else {
            res = callbackHolder.subtract(quotient, modulo);    // call callback
        }

        // Do something with the callback result (in this case print)
        System.out.println("In Caller: result = " + res);
    }
}
