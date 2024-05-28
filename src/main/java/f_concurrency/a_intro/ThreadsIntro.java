package f_concurrency.a_intro;

public class ThreadsIntro {

    public static void main(String[] args) {
        //createThreadBySubclassing();
        //createThreadByImplementingRunnable(10);

        sleepEx();
    }

    public static void createThreadBySubclassing() {
        System.out.println(Thread.currentThread().getName());
        MyThread t = new MyThread(10);  // create instance
        t.start(); // start the thread
    }

    public static void createThreadByImplementingRunnable(int num) {
        System.out.println(Thread.currentThread().getName());
        Runnable run = new Runnable() {    // create instance
            int n = num;
            @Override
            public void run() {
                n *= 2;
                System.out.println(Thread.currentThread().getName() + ": " + n);
            }
        };
        new Thread(run).start();    // pass the runnable and start the thread
    }

    // Thread.sleep() example -------------------------------------------------------
    public static void sleepEx() {
        Thread t1 = new Thread(() -> {
            String tid = Thread.currentThread().getName();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.print(" * ");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted: " + tid);
            }
            System.out.println("\nExiting: " + tid);
        });

        Thread t2 = new Thread(() -> {
            String tid = Thread.currentThread().getName();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.print(" - ");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted: " + tid);
            }
            System.out.println("\nExiting: " + tid);
        });

        // Start both threads
        System.out.println("Starting new threads");
        t1.start();
        t2.start();
        System.out.println("Exiting main");
    }
}

class MyThread extends Thread {
    private int n;

    public MyThread(int n) { this.n = n; }

    @Override
    public void run() {
        n *= 2;
        System.out.println(Thread.currentThread().getName() + ": " + n);
    }
}