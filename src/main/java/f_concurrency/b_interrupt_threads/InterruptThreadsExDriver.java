package f_concurrency.b_interrupt_threads;

public class InterruptThreadsExDriver {

    public static void main(String[] args) {

        //interruptThread();
        threadState();
    }

    public static void interruptThread() {
        System.out.println("Started main");
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println(i);         // sleep is interruptible i.e. it throws
                    Thread.sleep(1000);      // InterruptedException and resets the
                } catch (InterruptedException e) { // thread interrupted status flag to false
                    System.out.println("Interrupted: " + Thread.currentThread().getName());
                    Thread.currentThread().interrupt(); // re-interrupt thread
                    return;
                }
            }
        });
        System.out.println("Starting: " + t.getName());
        t.start();

        // Check time and interrupt thread
        long startTime = System.currentTimeMillis();
        while (t.isAlive()) {
            System.out.println("waiting for thread to finish");
            try {
                Thread.sleep(1000);
                if (System.currentTimeMillis() - startTime > 2000) {
                    t.interrupt(); // interrupt if the elapsed time passes 2s
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void threadState() {
        System.out.println("Started: main");
        Thread t1 = new Thread(() -> {
            String tid = Thread.currentThread().getName();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.print(" * ");
                    Thread.sleep(500);
                    System.out.println("Thread state inside: " + Thread.currentThread().getState());
                }
            } catch (InterruptedException e) {
                    System.out.println("Interrupted: " + tid +
                            "\nThread state inside after interruption: " + Thread.currentThread().getState()
                    );
                    return;
            }
            System.out.println("\nExiting: " + tid);
        });

        System.out.println("Starting: " + t1.getName());
        t1.start(); // start new thread

        long startTime = System.currentTimeMillis();
        while (t1.isAlive()) {
            System.out.println("Waiting for thread to complete");
            try {
                Thread.sleep(1000);
                System.out.println("Thread state outside:" + t1.getState());

                // If elapsed time is more than 2 s, interrupt the thread
                if (System.currentTimeMillis() - startTime > 2000) {
                    t1.interrupt();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread state outside: " + t1.getState());
    }
}
