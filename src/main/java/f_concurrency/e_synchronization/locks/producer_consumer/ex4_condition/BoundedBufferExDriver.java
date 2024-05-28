package f_concurrency.e_synchronization.locks.producer_consumer.ex4_condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBufferExDriver {

    public static void main(String[] args) throws InterruptedException {
        BoundedBuffer buffer = new BoundedBuffer();
        for (int i = 0; i < 500; i++) {
            Thread producer = new Thread(() -> {
                try {
                    buffer.put(new Object());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, "P" + i);

            Thread consumer = new Thread(() -> {
                try {
                    buffer.get();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, "C" + i);
            producer.start();
            Thread.sleep(300);
            consumer.start();
        }
    }
}

class BoundedBuffer {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[100];
    int putIndex, getIndex, count;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length)
                notEmpty.await();
            System.out.println(Thread.currentThread().getName() + " putting item at: " + putIndex + ": " + x);
            items[putIndex] = x;
            if (++putIndex == items.length) putIndex = 0;
            count++;
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object get() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notFull.await();
            Object x = items[getIndex];
            System.out.println(Thread.currentThread().getName() + " getting items at: " + getIndex + ": " + x);
            if (++getIndex == items.length) getIndex = 0;
            --count;
            notEmpty.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}



