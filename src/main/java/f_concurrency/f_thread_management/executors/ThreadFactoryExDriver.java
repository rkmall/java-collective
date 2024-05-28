package f_concurrency.f_thread_management.executors;

import java.util.List;
import java.util.concurrent.*;

public class ThreadFactoryExDriver {

    public static void main(String[] args) {

        //runExecutorsInSequence();
        //fixedThreadPoolWithFactory();
        //threadPoolUsingExecute();
        //threadPoolUsingSubmit();
        //threadPoolUsingInvokeAll();
        //threadPoolUsingInvokeAny();
    }

    public static void runExecutorsInSequence() {
        // Run Blue first
        ExecutorService blueExecutor = Executors.newSingleThreadExecutor(
                new ColoredThreadFactory(ThreadColor.ANSI_BLUE)
        );
        blueExecutor.execute(ThreadFactoryExDriver::count);
        blueExecutor.shutdown();

        // Check if Blue is done
        boolean isDone;
        try {
            isDone = blueExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // If done run Yellow
        if (isDone) {
            ExecutorService yellowExecutor = Executors.newSingleThreadExecutor(
                    new ColoredThreadFactory(ThreadColor.ANSI_YELLOW)
            );
            yellowExecutor.execute(ThreadFactoryExDriver::count);
            yellowExecutor.shutdown();

            // Check if Yellow is done
            try {
                isDone = yellowExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // If done run Red
            if (isDone) {
                ExecutorService redExecutor = Executors.newSingleThreadExecutor(
                        new ColoredThreadFactory(ThreadColor.ANSI_RED)
                );
                redExecutor.execute(ThreadFactoryExDriver::count);
                redExecutor.shutdown();
            }
        }
    }

    private static void fixedThreadPoolWithFactory() {
        int count = 3;
        ExecutorService multiExecutor = Executors.newFixedThreadPool(count, new ColoredThreadFactory());

        for (int i = 0; i < count; i++) {
            multiExecutor.execute(ThreadFactoryExDriver::count);
        }
        multiExecutor.shutdown();
    }

    private static void threadPoolUsingExecute() {
        ExecutorService multiExecutor = Executors.newCachedThreadPool();
        try {
            multiExecutor.execute(() -> ThreadFactoryExDriver.sum(1, 10, 1, "red"));
            multiExecutor.execute(() -> ThreadFactoryExDriver.sum(10, 100, 10, "blue"));
            multiExecutor.execute(() -> ThreadFactoryExDriver.sum(2, 20, 2, "green"));

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw  new RuntimeException();
            }

            System.out.println("Executing rest of the tasks");
            for (String color : new String[] { "red", "blue", "green", "yellow" }) {
                multiExecutor.execute(() -> ThreadFactoryExDriver.sum(1, 10, 1, color));
            }
        } finally {
            multiExecutor.shutdown();
        }
    }

    private static void threadPoolUsingSubmit() {
        ExecutorService multiExecutor = Executors.newCachedThreadPool();
        try {
            Future<Integer> resRed = multiExecutor.submit(
                    () -> ThreadFactoryExDriver.sum(1, 10, 1, "red")
            );
            Future<Integer> resBlue = multiExecutor.submit(
                    () -> ThreadFactoryExDriver.sum(10, 100, 10, "blue")
            );
            Future<Integer> resGreen = multiExecutor.submit(
                    () -> ThreadFactoryExDriver.sum(2, 20, 2, "green")
            );
            System.out.println("Red result: " + resRed.get());
            System.out.println("Blue result: " + resBlue.get());
            System.out.println("Green result: " + resGreen.get());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            multiExecutor.shutdown();
        }
    }

    private static void threadPoolUsingInvokeAll() {
        ExecutorService multiExecutor = Executors.newCachedThreadPool();
        List<Callable<Integer>> taskList = List.of(
                () -> ThreadFactoryExDriver.sum(1, 10, 1, "red"),
                () -> ThreadFactoryExDriver.sum(10, 100, 10, "blue"),
                () -> ThreadFactoryExDriver.sum(2, 20, 2, "green")
        );

        try {
            List<Future<Integer>> results = multiExecutor.invokeAll(taskList);

            for (Future<Integer> result : results) {
                System.out.println(result.get(500, TimeUnit.MILLISECONDS));
            }
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            throw new RuntimeException(e);
        } finally {
            multiExecutor.shutdown();
        }
    }

    private static void threadPoolUsingInvokeAny() {
        ExecutorService multiExecutor = Executors.newCachedThreadPool();
        List<Callable<Integer>> taskList = List.of(
                () -> ThreadFactoryExDriver.sum(1, 10, 1, "red"),
                () -> ThreadFactoryExDriver.sum(10, 100, 10, "blue"),
                () -> ThreadFactoryExDriver.sum(2, 20, 2, "green")
        );

        try {
            Integer result = multiExecutor.invokeAny(taskList);
            System.out.println("Result: " + result);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            multiExecutor.shutdown();
        }
    }

    // Runnables/Callables --------------------------------------------------------------------------
    private static void count() {
        String tid = Thread.currentThread().getName();
        ThreadColor threadColor = ThreadColor.ANSI_RESET;
        try {
            threadColor = ThreadColor.valueOf(tid.toUpperCase()); // set color as per thread name
        } catch (IllegalArgumentException e) {
            // Ignore exception
        }
        String color = threadColor.color();  // get color
        for (int  i = 0; i < 10; i++) {
            System.out.println(color + tid + ": " + i); // use color
        }
    }

    private static int sum(int start, int end, int delta, String colorString) {
        String tid = Thread.currentThread().getName();
        ThreadColor threadColor = ThreadColor.ANSI_RESET;
        try {
            threadColor = ThreadColor.valueOf("ANSI_" + colorString.toUpperCase());
        } catch (IllegalArgumentException e) {
            // Ignore exception
        }

        String color = threadColor.color();
        int sum = 0;
        for (int i = start; i < end; i += delta) {
            sum += i;
        }
        System.out.println(color + tid + ": " + sum);
        return sum;
    }
}

/**
 * Factory methods for creating a new threads.
 * Using this the ExecutorService will create new threads
 * with the properties set in this factory class instead
 * of the default thread settings.
 */
class ColoredThreadFactory implements ThreadFactory {
    private String tName;

    private int colorValue = 1;

    public ColoredThreadFactory(ThreadColor color) { tName = color.name(); }

    public ColoredThreadFactory() {}

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        String name = tName;
        if (tName == null) { // when no-arg constructor is used
            name = ThreadColor.values()[colorValue].name();
        }
        if (++colorValue > ThreadColor.values().length) {
            colorValue = 1;
        }
        t.setName(name);   // set thread name to be color name
        return t;
    }
}

// Enum class of Thread colors
enum ThreadColor {
    ANSI_RESET("\u001B[0m"),
    ANSI_BLACK( "\u001B[30m"),
    ANSI_WHITE("\u001b[37m"),
    ANSI_BLUE("\u001B[34m"),
    ANSI_CYAN("\u001B[36m"),
    ANSI_GREEN( "\u001B[32m"),
    ANSI_PURPLE("\u001B[35m"),
    ANSI_RED("\u001B[31m"),
    ANSI_YELLOW("\u001b[33m");

    private final String color;

    ThreadColor(String color) { this.color = color; }

    public String color() { return color; }
}