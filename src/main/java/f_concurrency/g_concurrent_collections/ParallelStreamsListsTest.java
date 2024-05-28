package f_concurrency.g_concurrent_collections;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class ParallelStreamsListsTest {

    private static final CopyOnWriteArrayList<Person> masterList;
    static
    {
        masterList = Stream.generate(Person::new)
                .distinct()
                .limit(1000)
                .collect(CopyOnWriteArrayList::new,     // supplier
                        CopyOnWriteArrayList::add,      // accumulator
                        CopyOnWriteArrayList::addAll);  // combiner
    }

    private static final ArrayBlockingQueue<Person> visitorsQueue = new ArrayBlockingQueue<>(5);

    public static void main(String[] args) {

        //producerOnlyArrayBlockingQueueTest();
        producerConsumerArrayBlockingQueueCopyOnWriteArrayListTest();
    }

    public static void producerOnlyArrayBlockingQueueTest() {
        // Producer
        Runnable producer = () -> {
            Person visitor = new Person();
            System.out.println("Adding " + visitor);
            boolean queued = false;
            try {
                System.out.println("Offering to queue");
                queued = visitorsQueue.offer(visitor, 5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                System.out.println("Interrupted Exception");
            }
            if (queued) {
                System.out.println(visitorsQueue);
            } else {
                // Drain the queue elements into ArrayList
                System.out.println("Queue is full, can't add " + visitor);
                System.out.println("Draining queue and writing data to file");
                List<Person> tempList = new ArrayList<>();
                visitorsQueue.drainTo(tempList);
                List<String> lines = new ArrayList<>();
                tempList.forEach(customer -> lines.add(customer.toString()));
                lines.add("Visitor not able to add to the queue: " + visitor);

                try {
                    Files.write(Path.of("DrainedQueue.txt"),
                            lines,
                            StandardOpenOption.CREATE,
                            StandardOpenOption.APPEND);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        ScheduledExecutorService producerExecutor = Executors.newSingleThreadScheduledExecutor();
        // Submit the periodic action repeatedly until the timeout occurs.
        producerExecutor.scheduleWithFixedDelay(producer,0, 1, TimeUnit.SECONDS);

        while (true) {
            try {
                if (!producerExecutor.awaitTermination(20, TimeUnit.SECONDS))
                    break;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        producerExecutor.shutdown();
    }

    public static void producerConsumerArrayBlockingQueueCopyOnWriteArrayListTest() {
        // Producer
        Runnable producer = () -> {
            Person visitor = new Person();
            System.out.println("Queueing " + visitor);
            boolean queued = false;
            try {
                queued = visitorsQueue.offer(visitor, 5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                System.out.println("Interrupted Exception");
            }
            if (queued) {
                System.out.println(visitorsQueue);
            } else {
                // Drain the queue elements into ArrayList
                System.out.println("Queue is full, can't add " + visitor);
                System.out.println("Draining queue and writing data to file");
                List<Person> tempList = new ArrayList<>();
                visitorsQueue.drainTo(tempList);
                List<String> lines = new ArrayList<>();
                tempList.forEach(customer -> lines.add(customer.toString()));
                lines.add("Visitor not able to add to the queue: " + visitor);

                try {
                    Files.write(Path.of("DrainedQueue.txt"),
                            lines,
                            StandardOpenOption.CREATE,
                            StandardOpenOption.APPEND);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        // Consumer
        Runnable consumer = () -> {
            String tid = Thread.currentThread().getName();
            System.out.println(tid + " Polling queue-size = " + visitorsQueue.size());
            Person visitor = null;
            try {
                visitor = visitorsQueue.poll(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (visitor != null) {
                System.out.println(tid + " " + visitor);
                if (!masterList.contains(visitor)) {
                    masterList.add(visitor);
                    System.out.println("New visitor added to master list: " + visitor);
                }
            }
            System.out.println(tid + " done: queue-size = " + visitorsQueue.size());
        };

        ScheduledExecutorService producerExecutor = Executors.newSingleThreadScheduledExecutor();
        // Submit the periodic action repeatedly until the timeout occurs.
        producerExecutor.scheduleWithFixedDelay(producer,0, 3, TimeUnit.SECONDS);

        ScheduledExecutorService consumerExecutor = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 3; i++) {
            consumerExecutor.scheduleWithFixedDelay(consumer, 6, 1, TimeUnit.SECONDS);
        }

        while (true) {
            try {
                if (!producerExecutor.awaitTermination(10, TimeUnit.SECONDS))
                    break;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        producerExecutor.shutdown();

        while (true) {
            try {
                if (!consumerExecutor.awaitTermination(3, TimeUnit.SECONDS))
                    break;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        consumerExecutor.shutdown();
    }
}
