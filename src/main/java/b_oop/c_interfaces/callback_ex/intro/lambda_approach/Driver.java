package b_oop.c_interfaces.callback_ex.intro.lambda_approach;

public class Driver {
    public static void main(String[] args) {
        // Approach 2

        CallerX callerX = new CallerX();
        callerX.call("aba", (option, x, y) -> {
            System.out.println(x + "," + y);
            return option == 1 ? x : x - y;
        });
    }
}
