package f_concurrency.f_thread_management.blockingqueue;

import java.util.LinkedList;
import java.util.List;

public class MyBlockingQueue<T> {
    private final List<T> queue = new LinkedList<>();
    private final int capacity;

    public MyBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void add(T item) throws InterruptedException {
        while (queue.size() >= capacity) { // Condition doesn't hold: wait & release
            wait();
        }
        // Condition holds: execute and notify
        queue.add(item);
        notifyAll();
    }

    public synchronized T remove() throws InterruptedException {
        while (queue.size() == 0) { // Condition doesn't hold: wait & release
            wait();
        }
        // Condition holds: execute and notify
        notifyAll();
        return queue.remove(0); // remove first item
    }
}
