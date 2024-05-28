package f_concurrency.c_join_threads;

import java.util.concurrent.atomic.AtomicBoolean;

public class JoinThreadsExDriver {

    public static void main(String[] args) {

        //withoutJoin();
        //withJoin();
        //withTimedJoin();

        /*try {
            joinInSequence();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/

        joinInSequenceTest();
    }

    public static void withoutJoin() {
        System.out.println("Started: " + Thread.currentThread().getName());
        Thread t =  joinableThread(5);  // new Thread sleeps for 5 s
        t.start(); // start new thread
        System.out.println("main exiting...");
    }

    public static void withJoin() {
        System.out.println("Started: " + Thread.currentThread().getName());
        Thread t =  joinableThread(5);  // new Thread sleeps for 5 s
        t.start();      // start new thread

        try {
            t.join();   // wait for the target thread to terminate
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("main exiting...");
    }

    public static void withTimedJoin() {
        System.out.println("Started: " + Thread.currentThread().getName());
        Thread t =  joinableThread(1000);  // new Thread sleeps for 1000 s
        t.start(); // start new thread

        try {
            t.join(5000);            // wait for max 5 s for the target thread to terminate
        } catch (InterruptedException e) { // otherwise, control returns to this calling thread
            throw new RuntimeException(e);
        }

        System.out.println("Control returns to main, interrupting new thread");
        t.interrupt(); // interrupt the new thread

        System.out.println("main exiting...");
    }

    public static Thread joinableThread(int s) {
        Runnable run = () -> {
            String tid = Thread.currentThread().getName();
            System.out.println("Started: " + tid);
            try {
                Thread.sleep(s * 1000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted: " + tid);
                return;
            }
            System.out.println("Exiting: " + tid);
        };
        return new Thread(run);
    }

    public static void joinInSequence() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("Starting installation");
                for (int i =0; i < 10; i++) {
                    System.out.print(". ");
                    Thread.sleep(300);
                }
                System.out.println();
            } catch (InterruptedException e) {
                System.out.println("T1 interrupted");
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                for (int i =0; i < 5; i++) {
                    System.out.println("Installing: " + (i + 1));
                    Thread.sleep(500);
                }
                System.out.println("Installation completed");
            } catch (InterruptedException e) {
                System.out.println("T2 interrupted");
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                System.out.println("Cleaning up");
                for (int i =0; i < 10; i++) {
                    System.out.print(". ");
                    Thread.sleep(300);
                }
                System.out.println("\nComplete");
            } catch (InterruptedException e) {
                System.out.println("T3 interrupted");
            }
        });
        // Start T1 and join
        t1.start();
        t1.join();
        // Start T2 and join
        t2.start();
        t2.join();
        // Start T3 and join
        t3.start();
        t3.join();
    }

    public static void joinInSequenceTest() {
        System.out.println("Started main");
        AtomicBoolean threadInterrupted = new AtomicBoolean(false); // isInterrupted flag
        // Thread 1
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.print(" . ");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("\nInterrupted: " + Thread.currentThread().getName());
                    Thread.currentThread().interrupt(); // reset the interrupted status to true again
                    if (Thread.currentThread().isInterrupted()) {
                        threadInterrupted.set(true);    // if interrupted, set true
                    }
                    return;
                }
            }
            System.out.println("\nExiting: " + Thread.currentThread().getName());
        });

        // Thread 2
        Thread monitorThread = new Thread(() -> {
            long now = System.currentTimeMillis();
            while (thread.isAlive()) {
                try {
                    Thread.sleep(1000);
                    if (System.currentTimeMillis() - now > 2000) {
                        thread.interrupt();
                    }
                } catch (InterruptedException e) {
                    System.out.println("Interrupted: " + Thread.currentThread().getName());
                }
            }
        }, "MonitorThread");

        // Thread 3
        Thread installThread = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(250);
                    System.out.println("Installation step " + (i + 1) + " is completed.");
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted: " + Thread.currentThread().getName());
            }
            System.out.println("\nExiting: " + Thread.currentThread().getName());
        }, "Install thread");

        System.out.println("Starting threads ");
        thread.start();
        monitorThread.start();

        // Join the previous thread
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Only run installThread if tickerThread is not interrupted.
        if (!threadInterrupted.get()) {
            installThread.start();
        } else {
            System.out.println("Previous thread was interrupted" +
                    installThread.getName() + "can't run."
            );
        }
    }
}
