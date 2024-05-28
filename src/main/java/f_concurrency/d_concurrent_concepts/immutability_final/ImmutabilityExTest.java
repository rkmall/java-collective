package f_concurrency.d_concurrent_concepts.immutability_final;

import java.util.HashMap;

public class ImmutabilityExTest {

    public static void main(String[] args) {

        immutableTest();
    }

    public static void immutableTest() {
        SharedMap map = new SharedMap();        // shared object with final field

        Thread t1 = new Thread(() -> map.addItem(0, "OFF"));
        Thread t2 = new Thread(() -> map.addItem(1, "ON"));
        Thread t3 = new Thread(() -> map.addItem(2, "DEF"));
        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        System.out.println("Reached here");
        HashMap<Integer, String > res = map.getMap();
        res.forEach((key, val) -> System.out.println(key + ":" + val));
    }
}

class SharedMap {
    // Using final ensure only visibility
    private final HashMap<Integer, String> map = new HashMap<>();

    // Operations on map still need to be synchronized
    public void addItem(int id, String val) {
        synchronized (this) {
            if (id > -1 && !val.isEmpty()) {
                map.put(id, val);
            }
        }
    }

    public HashMap<Integer, String> getMap() {
        return map;
    }
}