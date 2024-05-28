package b_oop.c_interfaces.callback_ex.intro.usecase;

import java.util.ArrayList;
import java.util.function.Consumer;

public class UseCaseDriver {

    public static void main(String[] args) throws InterruptedException {

        final int[] currentProgress = {0};

        final ArrayList<Integer> progressValues = new ArrayList<>();

        /*
         * Implication of Callback is tied to Callback parameters.
         * - Caller declares Callback with parameters, if any.
         *   Passing the Callback to the Caller means the Callback will be
         *   executed in the Caller function scope in this case loadProgress.
         *
         * - Benefit of executing Callback in the scope of Caller is that the
         *   declared Callback parameter is accessible to the Callback
         *   which the Callback can capture and make accessible to the
         *   function(this function) that invoked the caller(loadProgress).
         */
        loadProgress( progress -> {
            currentProgress[0] = progress;
            progressValues.add(progress);
            System.out.println(currentProgress[0]);
        });

        // Use the captured values
        progressValues.forEach( values -> System.out.print(values + " "));

    }

    public static void loadProgress(Consumer<Integer> consumer) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            consumer.accept(i);
            Thread.sleep(100);
        }
    }
}
