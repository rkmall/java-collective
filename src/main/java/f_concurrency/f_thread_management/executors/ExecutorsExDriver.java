package f_concurrency.f_thread_management.executors;

import java.util.concurrent.*;

public class ExecutorsExDriver {

    public static void main(String[] args) {
        //singleThreadExecutor();
        //singleThreadScheduledExecutor();
        //fixedThreadPool();
        //cachedThreadPool();
        //scheduledThreadPool();
    }

    public static void singleThreadExecutor() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(ExecutorsExDriver::myRunnable);
        executor.shutdown();
    }

    public static void singleThreadScheduledExecutor() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        // Schedule a task to run after 5 seconds
        executor.schedule(ExecutorsExDriver::myRunnable, 5000, TimeUnit.MILLISECONDS);
        executor.shutdown();
    }

    public static void fixedThreadPool() {
        int count = 3;
        ExecutorService executor = Executors.newFixedThreadPool(count);
        for (int i = 0; i < count; i++) {
            executor.execute(ExecutorsExDriver::myRunnable);
        }
        executor.shutdown();
    }

    public static void cachedThreadPool() {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            executor.execute(ExecutorsExDriver::myRunnable);
        }
        executor.shutdown();
    }

    public static void scheduledThreadPool() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
        // Schedule task to run after 2 s and repeat every second
        long startTime = System.currentTimeMillis();
        executor.scheduleAtFixedRate(
                () -> {
                    String tid = Thread.currentThread().getName();
                    System.out.println(tid + ": running");

                    // Shutdown if time passes 5 s
                    if (System.currentTimeMillis() - startTime > 5000) {
                        executor.shutdown();
                    }
                },
                2000,
                1000,
                TimeUnit.MILLISECONDS
                );
    }

    public static void myRunnable() {
        String tid = Thread.currentThread().getName();
        for (int i = 0; i < 10; i++) {
            System.out.println(tid + ": " + i);
        }
        System.out.println(tid + ": done");
    }
}
