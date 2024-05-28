package f_concurrency.e_synchronization.locks.producer_consumer.ex2_waitnotify;

import java.util.Random;


public class ProducerConsumerWaitNotifyTest {

    public static void main(String[] args) {
        Message repository = new Message();

        Thread reader1 = new Thread(new Reader(repository), "Reader1");
        Thread reader2 = new Thread(new Reader(repository), "Reader2");
        Thread writer = new Thread(new Writer(repository), "Writer");
        reader1.start();
        reader2.start();
        writer.start();
    }
}

class Writer implements Runnable {
    private Message repository;

    private final String[] messages = new String[] {
            "Hello there.",
            "How are you?",
            "OK got it.",
            "Never mind",
            "Should be good",
            "See you later."
    };

    public Writer(Message repository) {
        this.repository = repository;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < messages.length; i++) {
            repository.write(messages[i]);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        repository.write("Done");
        System.out.println(Thread.currentThread().getName() + ": exiting");
    }
}

class Reader implements Runnable {
    private Message repository;

    public Reader(Message repository) {
        this.repository = repository;
    }

    @Override
    public void run() {
        String tid = Thread.currentThread().getName();
        Random random = new Random();
        String latestMessage = "";
        do {
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            latestMessage = repository.read();
            System.out.println(tid + " received: " + latestMessage);
        } while (!latestMessage.equals("Done"));
        System.out.println(tid + ": exiting");
    }
}

// Shared resource, shared between multiple threads.
class Message {
    private String message;
    private boolean hasMessage = false;

    public String read() {
        String tid = Thread.currentThread().getName();
        // Implicit lock
        synchronized (this) {
            // Execute critical section
            long started = System.currentTimeMillis();
            while (!hasMessage) { // condition: doesn't hold => wait and release lock
                try {
                    System.out.println(tid + ": calling wait");
                    wait(1000);
                    if (System.currentTimeMillis() - started > 3000) {
                        System.out.println(tid + ": exiting");
                        return null;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            // Perform action on appropriate condition
            hasMessage = false; // set the condition state
            notify();           // notify other thread
            return message;
        } // Unlock
    }

    public void write(String message) {
        // Implicit lock
        synchronized (this) {
            // Execute critical section
            while (hasMessage) {// condition: doesn't hold => wait and release lock
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            // Perform action on appropriate condition
            hasMessage = true;  // set the condition state
            notify();           // notify other thread
            this.message = message;
        } // Unlock
    }
}

