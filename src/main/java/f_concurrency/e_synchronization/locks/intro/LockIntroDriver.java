package f_concurrency.e_synchronization.locks.intro;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockIntroDriver {

    private static int target = 1_000_000;

    public static void main(String[] args) {

        //syncMethodTest();
        //syncBlockTest();
        lockTest();
    }

    public static void syncMethodTest() {
        Counter counter = new Counter();
        Thread t1 = new Thread(() -> counter.syncMethodIncrement(target), "M1");
        Thread t2 = new Thread(() -> counter.syncMethodIncrement(target), "M2");
        t1.start();
        t2.start();
    }

    public static void syncBlockTest() {
        Counter counter = new Counter();
        Thread t1 = new Thread(() -> counter.syncBlockIncrement(target), "B1");
        Thread t2 = new Thread(() -> counter.syncBlockIncrement(target), "B2");
        t1.start();
        t2.start();
    }

    public static void lockTest() {
        Counter counter = new Counter();
        Thread t1 = new Thread(() -> counter.lockIncrement(target), "L1");
        Thread t2 = new Thread(() -> counter.lockIncrement(target), "L2");
        Thread t3 = new Thread(() -> counter.lockIncrement(target), "L3");
        t1.start();
        t2.start();
        t3.start();
    }

}

class Counter {
    private int count = 0;
    Lock lock = new ReentrantLock();

    // The current instance is implicitly locked by synchronized method.
    public synchronized void syncMethodIncrement(int target) {
        // Execute critical section
        for (int i = 0; i < target; i++) {
            count++;
        }
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    } // Unlock

    // The current instance is passed as argument to synchronized block i.e.
    // this instance is explicitly locked
    public void syncBlockIncrement(int target) {
        // Lock
        synchronized (this) {
            // Execute critical section
            for (int i = 0; i < target; i++) {
                count++;
            }
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        } // Unlock
    }

    // Explicit use of lock object to lock and unlock the critical section
    public void lockIncrement(int target) {
        // Lock
        lock.lock();

        // Execute critical section
        for (int i = 0; i < target; i++) {
            count++;
        }
        System.out.println(Thread.currentThread().getName() + " count = " + count);

        // Unlock
        lock.unlock();
    }
}
