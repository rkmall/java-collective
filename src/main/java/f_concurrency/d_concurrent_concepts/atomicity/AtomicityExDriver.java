package f_concurrency.d_concurrent_concepts.atomicity;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicityExDriver {

    public static void main(String[] args) {

        //nonAtomicOperation();
        //atomicOperation();
    }

    public static void nonAtomicOperation() {
        NonAtomicCounter counter = new NonAtomicCounter();  // shared object
        Thread t1 = new Thread(() -> counter.increment(1_000_000));
        Thread t2 = new Thread(() -> counter.increment(1_000_000));
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
        System.out.println("Final count = " + counter.getCount());
    }

    public static void atomicOperation() {
        AtomicCounter counter = new AtomicCounter();  // shared object
        Thread t1 = new Thread(() -> counter.increment(1_000_000));
        Thread t2 = new Thread(() -> counter.increment(1_000_000));
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
        System.out.println("Final count = " + counter.getCount());
    }
}

class NonAtomicCounter {
    /**
     * If the instance of this class is shared between multiple threads,
     * the concurrent thread access variable count.
     */
    private volatile int count = 0;

    /**
     * The increment statement count++ is not atomic in nature.
     * It involves 3 operations:
     *  1. read count (move count to register)  ==> reg = count;
     *  2. increment count                      ==> reg = reg + 1;
     *  3. write count (move count to memory)   ==> count = reg
     * <p>
     * Since there are 3 operations involved in the increment statement,
     * there is a problem with the primitive integer variable.
     * The threads can interleave between those operations for the
     * following reasons:
     *  1. Time slicing: OS dependent.
     *  2. Interleaving: Time slicing result in interleaving of threads as the
     *     execution of multiple threads happens in arbitrary order.
     * </p>
     * @param n the max number to loop.
     */
    public void increment(int n) {
        for (int i = 0; i < n; i++)
            count++;
    }

    public int getCount() { return count; }
}

class AtomicCounter {
    /**
     * If the instance of this class is shared between multiple threads,
     * the concurrent threads access variable count.
     */
    private volatile AtomicInteger count = new AtomicInteger(0);

    /**
     * Atomic getAndIncrement() atomically increments by one the current value.
     * It acts as a synchronized code block that gets the current value,
     * increments by one and writes the new value back to memory.
     * <p>
     * It is recommended to use volatile along with the Atomic objects
     * so that the atomic operation is visible to other threads.
     * @param n the max number to loop.
     *</p>
     */
    public void increment(int n) {
        for (int i = 0; i < n; i++)
            count.getAndIncrement();    // this method acts as synchronized block
    }                                   // so no interleaving happens

    public int getCount() { return count.get(); }
}
