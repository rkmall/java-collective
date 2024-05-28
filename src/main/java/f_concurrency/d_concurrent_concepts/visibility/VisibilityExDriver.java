package f_concurrency.d_concurrent_concepts.visibility;

public class VisibilityExDriver {

    public static void main(String[] args) {

        //nonVolatileDataTest();
        //volatileDataTest();
        volatileDoNotAddressAtomicity();

    }

    public static void nonVolatileDataTest() {
        NonVolatileData data = new NonVolatileData();     // shared object

        Thread writer = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.toggleFlag();  // toggle flag after 1 s
            System.out.println("Writer: flag set to " + data.getFlag());
        });

        Thread reader = new Thread(() -> {
            while (!data.getFlag()) {
                // busy waiting until flag becomes true
            }
            System.out.println("Reader: flag is " + data.getFlag());
        });
        writer.start();
        reader.start();
    }

    public static void volatileDataTest() {
        VolatileData data = new VolatileData();     // shared object

        Thread writer = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.toggleFlag();  // toggle flag after 1 s
            System.out.println("Writer: flag set to " + data.getFlag());
        });

        Thread reader = new Thread(() -> {
            while (!data.getFlag()) {
                // busy waiting until flag becomes true
            }
            System.out.println("Reader: flag is " + data.getFlag());
        });
        writer.start();
        reader.start();
    }

    public static void volatileDoNotAddressAtomicity() {
        VolatileDoNotAddressAtomicity counter = new VolatileDoNotAddressAtomicity();  // shared object
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

class NonVolatileData {
    private boolean flag = false;

    public void toggleFlag() { flag = !flag;}

    public boolean getFlag() { return flag; }
}

class VolatileData {
    /**
     * Volatile variable ensures that the variable is read from and written to
     * main memory instead of cache.
     */
    private volatile boolean flag = false;

    public void toggleFlag() { flag = !flag;}

    public boolean getFlag() { return flag; }
}

class VolatileDoNotAddressAtomicity {
    private volatile int count = 0; // adding volatile here doesn't guarantee atomicity

    public void increment(int n) {
        for (int i = 0; i < n; i++)
            count++; // increment operation is non-atomic
    }

    public int getCount() { return count; }
}
