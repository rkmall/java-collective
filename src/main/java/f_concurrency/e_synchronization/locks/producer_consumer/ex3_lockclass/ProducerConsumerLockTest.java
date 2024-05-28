package f_concurrency.e_synchronization.locks.producer_consumer.ex3_lockclass;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerLockTest {

    public static void main(String[] args) {
        Repository repository = new Repository();   // share object
        Thread t1 = new Thread(new Reader(repository), "Reader");
        Thread t2 = new Thread(new Writer(repository), "Writer");
        t1.start();
        t2.start();
    }
}

class Writer implements Runnable {
    private Repository repository;

    private final String[] messages = new String[] {
            "Hello there.",
            "How are you?",
            "OK got it.",
            "See you later."
    };

    public Writer(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void run() {
        for (String message : messages) {
            repository.write(message);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        repository.write("Done");
    }
}

class Reader implements Runnable {
    private Repository repository;

    public Reader(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void run() {
        String latestMessage = "";
        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            latestMessage = repository.read();
            System.out.println("Received: " + latestMessage);
        } while (!latestMessage.equals("Done"));
    }
}

// Shared resource, shared between multiple threads.
class Repository {
    private String message;
    private boolean hasMessage = false;
    private final Lock lock = new ReentrantLock();

    public String read() {
        // Lock if only available
        if (lock.tryLock()) {
            try {
                // If locked, execute critical section
                while (!hasMessage) {
                    System.out.println("read holding the lock");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                hasMessage = false;
            } finally {
                // Unlock
                lock.unlock();
            }
        } else {
            hasMessage = false;
            System.out.println("read was blocked");
        }
        return message;
    }

    public void write(String message) {
        // Lock if only available
        if (lock.tryLock()) {
            // If locked, execute critical section
            try {
                while (hasMessage) {
                    System.out.println("write holding the lock");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                hasMessage = true;
                this.message = message;
            } finally {
                // Unlock
                lock.unlock();
            }
        } else {
            hasMessage = true;
            System.out.println("write was blocked");
        }
    }
}
