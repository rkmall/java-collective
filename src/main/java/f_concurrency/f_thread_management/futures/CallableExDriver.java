package f_concurrency.f_thread_management.futures;

import java.util.concurrent.*;

public class CallableExDriver {

    public static void main(String[] args) {

        /*try {
            callableFutureTask();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }*/

        /*try {
            runnableFutureTask();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }*/

        /*try {
            executorAndFutureTask();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }*/

    }

    public static void callableFutureTask() throws ExecutionException, InterruptedException {
        Callable<String> callable = () -> {
            Thread.sleep(3000);
            return "hello";
        };

        /*
         * Thread takes only Runnable instance:
         *  - Therefore, Callable is wrapped into FutureTask which then
         *    can be passed to Thread because FutureTask implements Runnable.
         *    Precisely, FutureTask impl. RunnableFuture impl. Runnable.
         *
         * - FutureTask also enables to get result from the wrapped Callable.
         *   futureTask.get() can be used to retrieve the result. However,
         *   get() method is blocking. It waits for the Callable to complete/return
         *   result.
         */
        FutureTask<String> future = new FutureTask<>(callable);
        new Thread(future).start();

        String res = future.get();      // this line blocks until result is returned
        System.out.println("Result: " + res);
    }

    public static void runnableFutureTask() throws ExecutionException, InterruptedException {
        /*
         * Runnable can be directly passed to Thread or Executor.
         * This example is just to show that FutureTask can wrap Runnable
         * where we can define default result to be returned.
         */
        FutureTask<String> future = new FutureTask<>(
                () -> System.out.println("hello"),  // runnable
                "default"                           // default result
        );

        new Thread(future).start();

        String result = future.get();
        System.out.println("Default result: " + result);
    }


    public static void executorAndFutureTask() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Future1
        Future<String> future1 =  executor.submit(() -> {
           Thread.sleep(3000);
           return "hello";
        });
        String result1 = future1.get();
        System.out.println("Result1: " + result1);

        // Future 2, this will be blocked until Future1 is complete
        String result2 = executor.submit(() -> {
            Thread.sleep(1000);
            StringBuilder sb = new StringBuilder("hello");
            Thread.sleep(2000);
            sb.append(" there");
            return sb.toString();
        }).get();
        System.out.println("Result2: " + result2);

        executor.shutdown();
    }
}
