package f_concurrency.a_intro;

public class UserVsDaemonThread {

    public static void main(String[] args) {


        //runAsUserThread();

        runAsDaemonThread();

        System.out.println("Exiting main...");

    }

    /**
     * Regular user thread will continue to run even after main terminates.
     */
    public static void runAsUserThread() {
        Thread t = new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName());
            }
        });
        t.start();
    }

    /**
     * Daemon thread will terminate when all non-daemon thread die and main thread terminates.
     */
    public static void runAsDaemonThread() {
        Thread t = new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName());
            }
        });
        t.setDaemon(true);
        t.start();
    }
}