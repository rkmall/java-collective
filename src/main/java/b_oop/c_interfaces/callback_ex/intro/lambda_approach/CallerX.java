package b_oop.c_interfaces.callback_ex.intro.lambda_approach;

public class CallerX {
    private static int DIVISOR = 7;
    private int option = 1;
    public void call(String s, CallbackX callbackX) {
        // Do some processing before calling  callback:
        // to produce required arguments tha can be passed to the callback.
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum += s.charAt(i);
        }

        int quotient = sum / DIVISOR;
        int modulo = sum % DIVISOR;
        System.out.println("In Caller: quotient = " + quotient + ", " + "mod = " + modulo);

        // Call the callback passing the required arguments
        // and get back the result
        int res = callbackX.execute(option, quotient, modulo);     // call callback

        // Do something with the callback result (in this case print)
        System.out.println("In Caller: result = " + res);
    }
}
