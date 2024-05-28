package f_concurrency.e_synchronization.locks.producer_consumer.ex1_busywaiting_deadlock;

import java.util.Random;

public class ProducerConsumerBusyWaitingTest {

    public static void main(String[] args) {
        MessagesRepository repository = new MessagesRepository();   // shared object

        Thread reader = new Thread(new MessageReader(repository), "Reader");
        Thread writer = new Thread(new MessageWriter(repository), "Writer");
        reader.start();
        writer.start();

    }
}

class MessageWriter implements Runnable {
    private MessagesRepository repository;

    private final String[] messages = new String[] {
        "Hello there.",
        "How are you?",
        "OK got it.",
        "See you later."
    };

    public MessageWriter(MessagesRepository repository) { this.repository = repository; }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < messages.length; i++) {
            repository.write(messages[i]);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        repository.write("Done");
    }
}

class MessageReader implements Runnable {
    private MessagesRepository repository;

    public MessageReader(MessagesRepository repository) { this.repository = repository; }

    @Override
    public void run() {
        Random random = new Random();
        String latestMessage = "";
        do {
            try {
                Thread.sleep(random.nextInt(2000));
            }catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            latestMessage = repository.read();
            System.out.println(latestMessage);
        } while (!latestMessage.equals("Done"));
    }
}

// Shared between multiple threads
class MessagesRepository {
    private String message;
    private boolean hasMessage = false;

    public synchronized String read() {
        while(!hasMessage) {
            System.out.println(Thread.currentThread().getName() +
                    ": waiting for message");
        }
        hasMessage = false;
        return message;
    }

    public synchronized void write(String message) {
        while(hasMessage) {
            System.out.println(Thread.currentThread().getName() +
                    ": waiting for message to be consumed");
        }
        hasMessage = true;
        this.message = message;
    }
}
