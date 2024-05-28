package f_concurrency.e_synchronization.locks.customlock;

public class MyLock {
    private boolean isLocked;
    private Thread lockingThread = null;

    public synchronized void lock() {
        while (isLocked) { // Condition doesn't hold: the lock is locked by other thread
            try {          // wait
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // Condition holds: execute
        isLocked = true;
        this.lockingThread = Thread.currentThread();
    }

    public synchronized void unlock() {
        if (this.lockingThread != Thread.currentThread()) {
            throw  new IllegalMonitorStateException();
        }
        isLocked = false;
        this.lockingThread = null;
        notifyAll();
    }
}
