package f_concurrency.f_thread_management.threadpools;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class ThreadPoolTest {

    public static void main(String[] args) {
        try(Scanner in = new Scanner(System.in)) {
            System.out.print("Enter base directory (e.g. /user/local/jdk8.0/src): ");
            String directory = in.nextLine();
            System.out.print("Enter keyword (e.g. volatile): ");
            String keyword = in.nextLine();

            ExecutorService executor = Executors.newCachedThreadPool();

            Matcher matcher = new Matcher(new File(directory), keyword, executor);
            Future<Integer> result = executor.submit(matcher);

            try {
                System.out.println(result.get() + " matching files.");
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
            executor.shutdown();

            int largestPoolSize = ((ThreadPoolExecutor) executor).getLargestPoolSize();
            System.out.println("Largest pool size: " + largestPoolSize);
        }
    }
}

class Matcher implements Callable<Integer> {
    private final File dir;
    private final String keyword;
    private final ExecutorService executor;
    private int count;

    public Matcher(File dir, String keyword, ExecutorService executor) {
        this.dir = dir;
        this.keyword = keyword;
        this.executor = executor;
    }

    @Override
    public Integer call() {
        count = 0;
        try {
            File[] files = dir.listFiles();
            List<Future<Integer>> results = new ArrayList<>();

            for (File file : files) {
                if (file.isDirectory()) {
                    Matcher matcher = new Matcher(file, keyword, executor);
                    Future<Integer> result = executor.submit(matcher);
                    results.add(result);
                } else {
                    if (search(file))
                        count++;
                }
            }

            for (Future<Integer> res : results) {
                try {
                    count += res.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return count;
    }

    public boolean search(File file) {
        try {
            try (Scanner in = new Scanner(file, StandardCharsets.UTF_8)) {
                boolean found = false;
                while (!found && in.hasNextLine()) {
                    String line = in.nextLine();
                    if (line.contains(keyword)) {
                        found = true;
                    }
                }
                return found;
            }
        } catch (IOException e) {
            return false;
        }
    }
}

