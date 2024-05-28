package f_concurrency.e_synchronization.locks.producer_consumer.ex4_condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerConditionTest {

    public static void main(String[] args) {
        Repo repo = new Repo();     // shared object
        Thread reader1 = new Thread(new Reader(repo), "Reader1");
        Thread reader2 = new Thread(new Reader(repo), "Reader2");
        Thread reader3 = new Thread(new Reader(repo), "Reader3");
        Thread writer = new Thread(new Writer(repo), "Writer");
        reader1.start();
        reader2.start();
        reader3.start();
        writer.start();
    }
}

class Writer implements Runnable {
    private final Repo repository;
    private final String[] messages = new String[] {
            "Hello there.",
            "How are you?",
            "OK got it.",
            "Never mind",
            "Should be good",
            "See you later."
    };

    public Writer(Repo repository) { this.repository = repository; }

    @Override
    public void run() {
        for (String message : messages) {
            repository.write(message);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        repository.write("Done");
        System.out.println(Thread.currentThread().getName() + ": exiting");
    }
}

class Reader implements Runnable {
    private final Repo repository;

    public Reader(Repo repository) { this.repository = repository; }

    @Override
    public void run() {
        String tid = Thread.currentThread().getName();
        String latestMessage = "";
        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            latestMessage = repository.read();
            System.out.println(tid + ": received " + latestMessage);
        } while (!latestMessage.equals("Done") && !latestMessage.equals("Exit"));
        System.out.println(tid + ": exiting");
    }
}

// Shared resource, shared between multiple threads.
class Repo {
    private String message;
    private boolean hasMessage = false;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public String read() {
        // Lock
        lock.lock();
        // Execute critical section
        try {
            long startTime = System.currentTimeMillis();
            while (!hasMessage) { // Condition doesn't hold: await and release the lock
                System.out.println(Thread.currentThread().getName() + ": calling await");
                condition.await(2, TimeUnit.SECONDS);
                if (System.currentTimeMillis() - startTime > 3000) {
                    System.out.println(Thread.currentThread().getName() + ": exiting from await");
                    return "Exit";
                }
            }
            // Condition holds: execute and signal
            hasMessage = false;
            condition.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // Unlock
            lock.unlock();
        }
        return message;
    }

    public void write(String message) {
        // Lock
        lock.lock();
        // Execute critical section
        try {
            while (hasMessage) { // Condition doesn't hold: await and release the lock
                System.out.println(Thread.currentThread().getName() + ": calling await");
                condition.await();
            }
            // Condition holds: execute and signal
            hasMessage = true;
            this.message = message;
            condition.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // Unlock
            lock.unlock();
        }
    }
}

