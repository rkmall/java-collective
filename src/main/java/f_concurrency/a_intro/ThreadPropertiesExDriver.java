package f_concurrency.a_intro;

public class ThreadPropertiesExDriver {

    public static void main(String[] args) {

        //setThreadPriority();
        //setAsDaemonThread();
        setUncaughtExceptionHandler();
    }

    public static void setThreadPriority() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 100; i < 105; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        });
        t1.setPriority(Thread.NORM_PRIORITY - 4);   // Priority: 5-4 = 1, Low
        t2.setPriority(Thread.NORM_PRIORITY + 4);   // Priority: 5+4 = 9, High
        t1.start();
        t2.start();
    }

    public static void setAsDaemonThread() {
        Thread t = new Thread(() -> {
            System.out.println("I am daemon");
        });
        t.setDaemon(true);
        System.out.println(t.getName() +  " is daemon: " + t.isDaemon());
        t.start();
    }

    public static void setUncaughtExceptionHandler() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.print("* ");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException();// re-throwing exception here (uncaught exception)
                }
            }
        });

        // Set UncaughtExceptionHandler
        t1.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName() + " threw " + e.getCause());
            }
        });

        long startTime = System.currentTimeMillis();
        Thread t2 = new Thread(() -> {
           while (t1.isAlive()) {
               System.out.println("t2 waiting for t1 to terminate");
               try {
                   Thread.sleep(500);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
               if (System.currentTimeMillis() - startTime > 2000) {
                   t1.interrupt();
               }
           }
        });

        t1.start();
        t2.start();
    }
}
