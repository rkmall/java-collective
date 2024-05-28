package f_concurrency.d_concurrent_concepts.happensbefore;

public class HappensBeforeExDriver {

    private static volatile int count = 0;

    public static void main(String[] args) {
        volatileHappenBefore();
    }

    public static void volatileHappenBefore() {
        Thread consumer = new Thread(() -> {
            int var = -1;
            while (true) {
                if (var != count) {    // read
                    System.out.println("Consumer: detected change, count = " + count); // read
                    var = count;       // read
                }
                if (count >= 5) break; // read
            }
            System.out.println("Consumer: exiting");
        });

        Thread producer = new Thread(() -> {
            while (count < 5) {  // read
                int var = count; // read
                var++;
                System.out.println("Producer: incremented count = " + var);
                count = var; // write to volatile var happens-before subsequent read from the var, hence,
                try {        // any change to volatile var is guaranteed visible to the subsequent read
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            }
            System.out.println("Producer: exiting");
        });
        consumer.start();

        // Sleep for 1s
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        producer.start();
    }
}
