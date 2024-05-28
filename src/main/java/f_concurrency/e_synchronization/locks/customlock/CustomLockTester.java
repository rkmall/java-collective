package f_concurrency.e_synchronization.locks.customlock;

public class CustomLockTester {
    private static final int target = 1_000_000;

    public static void main(String[] args) {
        CounterWithCustomLock counter = new CounterWithCustomLock();
        Thread t1 = new Thread(() -> counter.lockIncrement(target), "L1");
        Thread t2 = new Thread(() -> counter.lockIncrement(target), "L2");
        Thread t3 = new Thread(() -> counter.lockIncrement(target), "L3");
        t1.start();
        t2.start();
        t3.start();
    }
}

class CounterWithCustomLock {
    private int count = 0;
    MyLock lock = new MyLock();

    // Explicit use of lock object to lock and unlock the critical section
    public void lockIncrement(int target) {
        // Lock
        lock.lock();

        // Execute the critical section
        for (int i = 0; i < target; i++) {
            count++;
        }
        System.out.println(Thread.currentThread().getName() + " count = " + count);

        // Unlock
        lock.unlock();
    }
}
