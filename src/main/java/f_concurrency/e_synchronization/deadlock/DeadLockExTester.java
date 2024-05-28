package f_concurrency.e_synchronization.deadlock;

public class DeadLockExTester {

    public static final Object LOCK1 = new Object();
    public static final Object LOCK2 = new Object();

    public static void main(String[] args) {

        //differentLockOrder();
        sameLockOrder();

    }


    public static void differentLockOrder() {
        Thread t1 = new Thread(() -> {
            synchronized (LOCK1) {
                System.out.println("T1 has LOCK1");
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    System.out.println("T1 was interrupted");
                }
                System.out.println("T1 waiting for LOCK2" );

                synchronized (LOCK2){
                    System.out.println("T1 has LOCK2");
                }
                System.out.println("T1 released LOCK2");
            }
            System.out.println("T1 released LOCK1");
        });

        Thread t2 = new Thread(() -> {
            synchronized (LOCK2) {
                System.out.println("T2 has LOCK2");
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    System.out.println("T2 was interrupted");
                }
                System.out.println("T2 waiting for LOCK1" );

                synchronized (LOCK1){
                    System.out.println("T2 has LOCK1");
                }
                System.out.println("T2 released LOCK1");
            }
            System.out.println("T2 released LOCK2");
        });

        t1.start();
        t2.start();
    }

    public static void sameLockOrder() {
        Thread t1 = new Thread(() -> {
            synchronized (LOCK1) {
                System.out.println("T1 has LOCK1");
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    System.out.println("T1 was interrupted");
                }
                System.out.println("T1 waiting for LOCK2" );

                synchronized (LOCK2){
                    System.out.println("T1 has LOCK2");
                }
                System.out.println("T1 released LOCK2");
            }
            System.out.println("T1 released LOCK1");
        });

        Thread t2 = new Thread(() -> {
            synchronized (LOCK1) {
                System.out.println("T2 has LOCK1");
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    System.out.println("T2 was interrupted");
                }
                System.out.println("T2 waiting for LOCK2" );

                synchronized (LOCK2){
                    System.out.println("T2 has LOCK2");
                }
                System.out.println("T2 released LOCK2");
            }
            System.out.println("T2 released LOCK1");
        });
        t1.start();
        t2.start();
    }
}
