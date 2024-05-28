package f_concurrency.f_thread_management.blockingqueue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockingQueueTest {
    private static final int QUEUE_SIZE = 10;
    private static final int SEARCH_THREADS = 100;
    private static final File POISON = new File("");
    private static BlockingQueue<File> queue = new ArrayBlockingQueue<>(QUEUE_SIZE);

    public static void main(String[] args) {

        //blockingQueueThreadsTest();
        blockingQueueWithExecutorTest();
    }

    public static void blockingQueueThreadsTest() {
        try (Scanner in = new Scanner(System.in)) {
            // Get directory to start search and keyword to search for.
            System.out.println("Enter base directory (e.g. /opt/jdk1.8.0/src)");
            String dir = in.nextLine();
            System.out.println("Enter keyword (e.g. volatile)");
            String keyword = in.next();

            // Create enumerator that adds files from dir into the queue
            Runnable enumerator = () -> {
                try {
                    enumerate(new File(dir));
                    queue.put(POISON); // put POISON at the end to signal used to signal search completion
                    // so the last file will be POISON after the enumeration completes.
                } catch (InterruptedException e) {
                    // do nothing
                }
            };
            new Thread(enumerator).start();

            // Create searcher threads that retrieves file from the queue and searches for keyword in the files
            for (int i = 0; i < SEARCH_THREADS; i++) {
                Runnable searcher = () -> {
                    try {
                        boolean done = false;
                        while (!done) {
                            File file = queue.take(); // take file from the queue
                            if (file == POISON) {     // Note: take blocks if queue is empty
                                queue.put(file); // put POISON to signal end-of-file for each thread to exit the loop
                                done = true;
                                System.out.println("Thread: " + Thread.currentThread().getName() + " terminated");
                            } else {
                                search(file, keyword);
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                };
                new Thread(searcher).start();
            }
        }
    }

    public static void blockingQueueWithExecutorTest() {
        try (Scanner in = new Scanner(System.in)) {
            // Get directory to start search and keyword to search for.
            System.out.println("Enter base directory (e.g. /opt/jdk1.8.0/src)");
            String dir = in.nextLine();
            System.out.println("Enter keyword (e.g. volatile)");
            String keyword = in.next();

            // Create enumerator that adds files from dir into the queue
            Runnable enumerator = () -> {
                try {
                    enumerate(new File(dir));
                    queue.put(POISON); // put POISON at the end to signal used to signal search completion
                    // so the last file will be POISON after the enumeration completes.
                } catch (InterruptedException e) {
                    // do nothing
                }
            };

            ExecutorService executor = Executors.newCachedThreadPool();
            executor.submit(enumerator);

            for (int i = 0; i < SEARCH_THREADS; i++) {
                Runnable searcher = () -> {
                    try {
                        boolean done = false;
                        while (!done) {
                            File file = queue.take(); // take file from the queue
                            if (file == POISON) {     // Note: take blocks if queue is empty
                                queue.put(file); // put POISON to signal end-of-file for each thread to exit the loop
                                done = true;
                                System.out.println("Thread: " + Thread.currentThread().getName() + " terminated");
                            } else {
                                search(file, keyword);
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                };
                executor.submit(searcher);
            }
            executor.shutdown();
        }
    }

    /**
     * Recursively enumerates all files in a given directory and its subdirectories.
     * @param dir the directory in which to start.
     */
    public static void enumerate(File dir) throws InterruptedException {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                enumerate(file); // if dir, enumerate (recursive call)
            } else {
                System.out.println("Putting file: " + file.getName());
                queue.put(file); // if file put to the queue
            }                    // Note: put blocks if queue is full
        }
    }

    /**
     * Searches a file for a given keyword and prints all matching lines.
     * @param file the file to search
     * @param keyword the keyword to search for
     */
    public static void search(File file, String keyword) {
        try {
            try (Scanner in = new Scanner(file, StandardCharsets.UTF_8)) {
                int lineNo = 0;
                while (in.hasNextLine()) {
                    lineNo++;
                    String line = in.nextLine();
                    if (line.contains(keyword)) {
                        System.out.format("%s. Line Number: %d, Line: %s.%n", file.getPath(), lineNo, line);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
