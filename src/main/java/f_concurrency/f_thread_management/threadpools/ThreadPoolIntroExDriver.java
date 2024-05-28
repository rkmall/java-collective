package f_concurrency.f_thread_management.threadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolIntroExDriver {

    public static void main(String[] args) {

        threadPoolTasksExecutedAsynchronously();
    }

    public static void threadPoolTasksExecutedAsynchronously() {
        Runnable task1 = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("hello");
            }
        };
        Runnable task2 = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("there");
            }
        };
        Runnable task3 = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("how are you?");
            }
        };
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(task1);
        executor.submit(task2);
        executor.submit(task3);
        executor.shutdown();
    }
}
