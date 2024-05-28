package b_oop.c_interfaces.callback_ex.intro.returningfromcaller;

import b_oop.c_interfaces.callback_ex.intro.lambda_approach.CallbackX;

public class Driver {
    public static void main(String[] args) {
        CallerReturns caller = new CallerReturns();
        int result = returningValueFromCaller(caller);
        System.out.println("In main: " + result);
    }

    public static int returningValueFromCaller(CallerReturns caller) {
        // return from Caller to this method
        return caller.call("aba", (option, x, y) -> {
            return option == 1 ? x + y : x - y;     // return from Callback to Caller
        });
    }
}
