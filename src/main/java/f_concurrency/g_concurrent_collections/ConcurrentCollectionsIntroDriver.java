package f_concurrency.g_concurrent_collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

public class ConcurrentCollectionsIntroDriver {

    public static void main(String[] args) {
        /*try {
            concurrentHashMapUpdateValueProblem();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/

        /*try {
            concurrentHashMapUpdateValueSolution();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/

        //concurrentHashMapMethodsTest();

        concurrentHashMapReduceTest();

        //concurrentHashSet();

        /*try {
            longAdderEx();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
    }

    public static void concurrentHashMapUpdateValueProblem() throws InterruptedException {
        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>() {{
                put("A", 0L);
                put("B", 11L);
                put("C", 30L);
                put("D", 80L);
                put("E", 20L);
            }};

        String key = "C";
        for (int i = 0; i < 20; i++) { // multiple threads updating the map value
            new Thread(() -> updateMapValueNotAtomic(map, key)).start();
        }
        Thread.sleep(1000);
        System.out.println("Updated value: " + map.get(key));
    }

    private static void updateMapValueNotAtomic(ConcurrentHashMap<String, Long> map, String key) {
        Long oldVal = map.get(key);
        Long newVal = oldVal == null ? 10 : oldVal + 1;
        map.put(key, newVal);
    }

    public static void concurrentHashMapUpdateValueSolution() throws InterruptedException {
        ConcurrentHashMap<String, LongAdder> map = new ConcurrentHashMap<>();
        LongAdder adder = new LongAdder();
        map.put("A", adder);
        map.put("B", adder);
        map.put("C", adder);

        String key = "B";
        for (int i = 0; i < 20; i++) {
            new Thread(() -> updateMapValueAtomic(map, key)).start();
        }

        Thread.sleep(1000);
        System.out.println("Updated val: " + map.get(key).sum());
    }

    private static void updateMapValueAtomic(ConcurrentHashMap<String, LongAdder> map, String key) {
        map.computeIfAbsent(key, k -> new LongAdder()).increment();
    }

    public static void concurrentHashMapMethodsTest() {
        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>() {{
            put("A", 0L);
            put("B", 11L);
            put("C", 30L);
            put("D", 80L);
            put("E", 20L);
        }};

        map.computeIfAbsent("F", k -> 100L);
        map.forEach((key, val) -> System.out.println("Key: " + key + ", Value: " + val));

        System.out.println("--------------------");
        map.forEach(3L, (key, val) -> System.out.println("Key: " + key + ", Value: " + val));

        System.out.println("--------------------");
        map.forEach(3L,
                (s, aLong) -> {                                 // filter & transformer
                    List<String> filtered = new ArrayList();
                    if (aLong > 20L) {
                        String item = s + "->" + aLong;
                        filtered.add(item);
                    }
                    return filtered;
                },
                result -> System.out.println("Code: " + result) // consumer
        );
    }

    public static void concurrentHashMapReduceTest() {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>() {{
            put("A", 1);
            put("B", 1);
            put("C", 1);
            put("D", 1);
            put("E", 1);
        }};

        int result = map.reduce(1L,
                (k, v) -> {                     // transformer
                    int len = k.length();
                    System.out.println("Trans: " + len);
                    return len;
                },
                (l1, l2) -> {                   // accumulator
                    System.out.println("Acc: " + l1 + ": " + l2);
                    return l1 + l2;
                }
        );
        System.out.println("Result: " + result);
    }

    public static void concurrentHashSet() {
        Set<String> keys = ConcurrentHashMap.newKeySet(); // get Set from ConcurrentHashMap
        keys.add("C");
        keys.add("C++");
        keys.add("Java");
        keys.add("C#");
        keys.forEach(System.out::println);
    }

    public static void longAdderEx() throws InterruptedException {
        LongAdder counter = new LongAdder();
        ExecutorService executor = Executors.newCachedThreadPool();

        int noOfThreads = 5;
        int count = 100;
        for (int i = 0; i < noOfThreads; i++) {
            executor.execute(() -> {
                for (int j = 0; j < count; j++) {
                    counter.increment();
                }
            });
        }
        executor.shutdown();
        Thread.sleep(3000);
        System.out.println("Result: " + (counter.sum() == count * noOfThreads));
    }
}
