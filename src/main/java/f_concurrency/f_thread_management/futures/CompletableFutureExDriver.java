package f_concurrency.f_thread_management.futures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureExDriver {

    public static void main(String[] args) {

        //completableFutureEx1();
        //chainingCompletableFutures();
        //exceptionHandlingCompletable();
        //multipleCompletableFutures();

        //supplyApplyConsumeAsync();
        //runAsync();
        //composeAsync();


        /*try {
            singleRequest();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/

        try {
            multipleRequest();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Future and CompletableFuture both represent future result but the differences are:
     *  - Future is a blocking API. Future.get() method used to retrieve result is blocking.
     *
     *  - FutureCompletable is non-blocking. It has non-blocking methods such as thenAccept(),
     *    theApply() and join().
     *      - Enables to chain multiple asynchronous operations.
     *      - Enables better exception handling.
     *      - Enables explicit cancel or completion of the future.
     */
    public static void completableFutureEx1() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "hello";
        });

        // In thenAccept, do something with the result
        future.thenAccept(result -> System.out.println("Result: " + result));

        System.out.println("Exiting...");
    }

    public static void chainingCompletableFutures() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 10)
                .thenApplyAsync(result -> result * 2)
                        .thenApplyAsync(result -> result + 10);

        // In thenAccept, do something with the result
        future.thenAccept(result -> System.out.println("Result: " + result));

        System.out.println("Exiting...");
    }

    public static void exceptionHandlingCompletable() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 10/0); // throw ArithmeticException

        future.exceptionally(ex -> {
            System.out.println("Caught exception: " + ex.getMessage());
            return 0;   // default value in case of exception
        }).thenAccept(result -> System.out.println("Result: " + result));

        System.out.println("Exiting...");
    }


    public static void multipleCompletableFutures() {
        // Start all 3 futures in parallel
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "hello");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "there");

        CompletableFuture<Void> futures = CompletableFuture.allOf(future1, future2, future3);

        futures.exceptionally(ex -> {
            System.out.println("Caught exception: " + ex.getMessage());
            return null;   // default value in case of exception
        }).thenRun(() -> {
            Integer result1 = future1.join();
            String result2 = future2.join();
            String result3 = future3.join();
            System.out.println(result1 + ", " + result2 + ", " + result3);
        });

        System.out.println("Exiting...");
    }

    public static void supplyApplyConsumeAsync() {
        // Takes Supplier, used to return CompletableFuture asynchronously (separate thread).
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello");

        // Takes Function, used to process result and return CompletableFuture asynchronously (separate thread).
        CompletableFuture<Integer> transformedFuture = future.thenApplyAsync( str -> {
            System.out.println(Thread.currentThread().getName());
            return str.length();
        });

        // Takes Consumer, used to process result asynchronously (separate thread).
        transformedFuture.thenAcceptAsync(len -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("Result: " + len);
        });
    }

    public static void runAsync() {
        // Takes Runnable, used to execute Runnables asynchronously (separate thread).
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("hello");
        });
    }

    public static void composeAsync() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello");

        // Enables to return CompletableFuture of returned type.
        future.thenApplyAsync(str -> {
            System.out.println(Thread.currentThread().getName());
            return str.length();
        }).thenAccept(result -> System.out.println("Result: " + result));


        // Enables to specify the chain multiple asynchronous tasks in a non-blocking way.
        future.thenComposeAsync(str -> CompletableFuture.supplyAsync(() -> str + "there" ))
                .thenAccept(result -> System.out.println("Result: " + result));

    }

    public static void singleRequest() throws InterruptedException {
        CompletableFuture<List<AppVersion>> result = getAppVersion();

        result.thenAccept(appVersions -> {
            for (AppVersion version : appVersions) {
                System.out.println("Version: " + version.versionNumber + ", Name: " + version.versionName);
            }
        });
    }

    public static void multipleRequest() throws InterruptedException {
        CompletableFuture<List<AppVersion>> result = getAppVersion(); // first call

        result.thenComposeAsync(appVersions -> {
            return getAppFeature(appVersions.get(0).versionNumber); // second call
        }).thenAccept(appFeature -> {
            for (String feature : appFeature.features) {
                System.out.println(feature);
            }
        });
    }

    public static CompletableFuture<List<AppVersion>> getAppVersion() {
        List<AppVersion> appVersions = new ArrayList<>();
        appVersions.add(new AppVersion(27, "Oreo"));
        appVersions.add(new AppVersion(28, "Pie"));
        appVersions.add(new AppVersion(29, "10"));

        return CompletableFuture.supplyAsync(() -> appVersions);
    }


    public static CompletableFuture<AppFeature> getAppFeature(int versionNumber) {
        AppVersion oreo = new AppVersion(27, "Oreo");
        AppVersion pie = new AppVersion(28, "Pie");
        AppVersion ten = new AppVersion(29, "10");

        List<String> oreoFeatures = new ArrayList<>();
        oreoFeatures.add("Oreo feature1");
        oreoFeatures.add("Oreo feature2");
        oreoFeatures.add("Oreo feature3");

        List<String> pieFeatures = new ArrayList<>();
        pieFeatures.add("Pie feature1");
        pieFeatures.add("Pie feature2");
        pieFeatures.add("Pie feature3");

        List<String> tenFeatures = new ArrayList<>();
        tenFeatures.add("Ten feature1");
        tenFeatures.add("Ten feature2");
        tenFeatures.add("Ten feature3");

        AppFeature oreoFeature = new AppFeature(oreo, oreoFeatures);
        AppFeature pieFeature = new AppFeature(pie, pieFeatures);
        AppFeature tenFeature = new AppFeature(ten, tenFeatures);

        switch (versionNumber) {
            case 27: return CompletableFuture.supplyAsync(() -> oreoFeature);
            case 28: return CompletableFuture.supplyAsync(() -> pieFeature);
            case 29: return CompletableFuture.supplyAsync(() -> tenFeature);
            default: return null;
        }
    }
}

class AppVersion {
    int versionNumber;
    String versionName;

    public AppVersion(int versionNumber, String versionName) {
        this.versionNumber = versionNumber;
        this.versionName = versionName;
    }
}

class AppFeature {
    AppVersion version;
    List<String> features;

    public AppFeature(AppVersion version, List<String> features) {
        this.version = version;
        this.features = features;
    }
}
