package f_concurrency.f_thread_management.futures;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureTest {

    public static void main(String[] args) {
        try(Scanner in = new Scanner(System.in)) {
            System.out.print("Enter base directory (e.g. /user/local/jdk8.0/src): ");
            String directory = in.nextLine();
            System.out.print("Enter keyword (e.g. volatile): ");
            String keyword = in.nextLine();

            // Create callable object
            MatchCounter counter = new MatchCounter(new File(directory), keyword);

            // Create a FutureTask and pass the Callable as Runnable
            FutureTask<Integer> task = new FutureTask<>(counter);
            new Thread(task).start();   // start thread

            try{
                System.out.println(task.get() + " matching files");
            }catch (ExecutionException | InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

class MatchCounter implements Callable<Integer> {
    private File dir;
    private String keyword;

    public MatchCounter(File dir, String keyword) {
        this.dir = dir;
        this.keyword = keyword;
    }

    @Override
    public Integer call() throws Exception {
        int count = 0;
        try {
            File[] files = dir.listFiles();
            List<Future<Integer>> results = new ArrayList<>();

            for (File file : files) {
                if (file.isDirectory()) {
                    MatchCounter counter = new MatchCounter(file, keyword);
                    // Convert Callable to Runnable so that it can be passed the new Thread
                    FutureTask<Integer> task = new FutureTask<>(counter);
                    results.add(task);
                    Thread t = new Thread(task);
                    t.start();
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
