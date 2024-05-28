package f_concurrency.g_concurrent_collections;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParallelStreamsMapsTest {

    public static void main(String[] args) {

        //serialComputation();
        //parallelComputation();
        //comparingSerialAndParallel();
        //parallelStreamOrderTest();
        //streamReduction();
        hashMapVsConcurrentHashMapWithParallelProcessing();
        //treeMapVsConcurrentMapsWithParallelProcessing();
        //arraysWithParallelProcessing();
    }

    public static void serialComputation() {
        int numLen = 100_000;
        long[] numbers = new Random().longs(numLen, 1, numLen).toArray();

        long start = System.currentTimeMillis();
        double average = Arrays.stream(numbers).average().orElseThrow();    // serial stream
        double elapsed = System.currentTimeMillis() - start;
        System.out.printf("Serial: Average = %.2f, elapsed = %.2f ms%n", average, elapsed);
    }

    public static void parallelComputation() {
        int numLen = 100_000;
        long[] numbers = new Random().longs(numLen, 1, numLen).toArray();

        long start = System.currentTimeMillis();
        double average = Arrays.stream(numbers).parallel().average().orElseThrow(); // parallel stream
        double elapsed = System.currentTimeMillis() - start;
        System.out.printf("Parallel: Average = %.2f, elapsed = %.2f ms%n", average, elapsed);
    }

    public static void comparingSerialAndParallel() {
        int numLen = 100_000_000;
        long[] numbers = new Random().longs(numLen, 1, numLen).toArray();

        double delta = 0;
        int iteration = 25;
        for (int i = 0; i < iteration; i++) {
            // Serial stream
            long start = System.currentTimeMillis();
            double averageSerial = Arrays.stream(numbers).average().orElseThrow();
            double elapsedSerial = System.currentTimeMillis() - start;

            // Parallel stream
            start = System.currentTimeMillis();
            double averageParallel = Arrays.stream(numbers).parallel().average().orElseThrow();
            double elapsedParallel = System.currentTimeMillis() - start;

            // Time diff between serial and parallel streams
            delta += (elapsedSerial - elapsedParallel);
        }
        System.out.printf("Parallel is [%.2f] ms faster on average.%n", delta);
    }

    public static void parallelStreamOrderTest() {
        /*
         * Parallel stream using forEachOrdered for sorting
         */
        System.out.println("--------Sorted using forEachOrdered--------");
        Stream.generate(Person::new)
                .limit(10)
                .parallel()
                .sorted(Comparator.comparing(Person::getLast))
                .forEachOrdered(System.out::println);

        /*
         * Sorting the serial stream first and using it as the source
         * to create sorted parallel stream using forEachOrdered.
         */
        System.out.println("--------Sorted source--------");
        Object[] src = Stream.generate(Person::new)
                .limit(10)
                .sorted(Comparator.comparing(Person::getLast))
                .toArray();
        for (Object p : src) {
            System.out.println(p);
        }
        System.out.println("--------Sorted stream order is same as sorted source--------");
        Arrays.stream(src)
                .limit(10)
                .parallel()
                .forEachOrdered(System.out::println);
    }

    public static void streamReduction() {
        /*
         * Ex: Sum of Integer range
         */
        int sum = IntStream.range(1, 101) // range: 1 to 100, upperbound is excl.
                .parallel()
                .reduce(0, Integer::sum);
        System.out.println("Sum: " + sum);

        /*
         * Ex: String joins
         */
        String line = "Hello there how are you? This method uses reduction process" +
                      "It should be quite clear.";
        List<String> words = new Scanner(line).tokens().collect(Collectors.toList());
        words.forEach(System.out::println);

        /*
         * Serial stream using StringJoiner.
         * StringJoiner is not thread-safe to be used with parallel streams.
         */
        StringJoiner convertedBackToLine = words
                .stream() // no parallel stream
                .reduce(
                        new StringJoiner(" "),
                        StringJoiner::add,
                        StringJoiner::merge
                );
        System.out.println(convertedBackToLine);

        /*
         * Parallel stream using Collectors.joining which uses StringJoiner
         * internally in a thread-safe way.
         */
        String convertedBackToLine1 = words
                .parallelStream() // parallel stream
                .collect(Collectors.joining(" "));
        System.out.println(convertedBackToLine1);

        /*
         * Parallel stream using concat.
         * Using concat with parallel stream has problem with word spacing (Not recommended).
         */
        String convertedBackToLine2 = words
                .parallelStream()
                .reduce("", (s1, s2) -> s1.concat(s2).concat(" "));
        System.out.println(convertedBackToLine2);

        /*
         * Parallel stream using Collectors.groupingBy which returns HashMap.
         */
        Map<String, Long> lastNameCounts = Stream.generate(Person::new)
                .limit(1000)
                .parallel() // parallel stream
                .collect(Collectors.groupingBy(
                        Person::getLast,        // classifier
                        Collectors.counting()   // downstream
                ));
        lastNameCounts.entrySet().forEach(System.out::println);
    }

    public static void hashMapVsConcurrentHashMapWithParallelProcessing() {
        /*
         * Parallel stream using Collectors.groupingBy which returns HashMap.
         */
        Map<String, Long> lastNameCounts = Stream.generate(Person::new)
                .limit(1000)
                .parallel() // parallel stream
                .collect(Collectors.groupingBy(
                        Person::getLast,        // classifier
                        Collectors.counting()   // downstream
                ));
        lastNameCounts.entrySet().forEach(System.out::println);
        System.out.println(lastNameCounts.getClass().getName());

        /*
         * Using Collectors.groupingBy which returns ConcurrentHashMap.
         */
        System.out.println("--------------------");
        Map<String, Long> lastNameCountsConcurrent = Stream.generate(Person::new)
                .limit(1000)
                .parallel() // parallel stream
                .collect(Collectors.groupingByConcurrent(
                        Person::getLast,        // classifier
                        Collectors.counting()   // downstream
                ));
        lastNameCountsConcurrent.entrySet().forEach(System.out::println);
        System.out.println(lastNameCountsConcurrent.getClass().getName());
    }

    public static void treeMapVsConcurrentMapsWithParallelProcessing() {
        /*
         * TreeMap with parallel stream is not thread-safe.
         * Warning: the output of this code is unpredictable, may get
         * ConcurrentModificationException when the merge method is executed
         * in the stream. The exception is thrown from the stream pipeline
         * code while it's trying to use some kind of iterator.
         * Additionally, may also get memory consistency error.
         */
        TreeMap<String, Long> lastNameCounts = new TreeMap<>();
        Stream.generate(Person::new)
                .limit(1000)
                .parallel()
                .forEach(person -> lastNameCounts.merge(
                        person.getLast(), // key
                        1L,               // initial value
                        Long::sum));      // re-mapper
        displayResults(lastNameCounts);

        /*
         * ConcurrentSkipListMap is thread-safe.
         */
        System.out.println("--------------------");
        ConcurrentSkipListMap<String, Long> lastNameCountsConcurrent = new ConcurrentSkipListMap<>();
        Stream.generate(Person::new)
                .limit(1000)
                .parallel()
                .forEach(person -> lastNameCountsConcurrent.merge(person.getLast(), 1L, Long::sum));
        displayResults(lastNameCountsConcurrent);

        /*
         * Wrapper Collections.synchronizedMap is thread-safe.
         * Collections class static method synchronizedMap() returns
         * the wrapper class SynchronizedMap.
         */
        System.out.println("--------------------");
        Map<String, Long> lastNameCountsSynchronizedMap = Collections.synchronizedMap(new TreeMap<>());
        Stream.generate(Person::new)
                .limit(1000)
                .parallel()
                .forEach(person -> lastNameCountsSynchronizedMap.merge(person.getLast(), 1L, Long::sum));
        displayResults(lastNameCountsSynchronizedMap);
    }

    private static <T> void displayResults(Map<T, Long> map) {
        System.out.println(map);
        int total = 0;
        for (long count : map.values()) {
            total += count;
        }
        System.out.println("Total = " + total);
        System.out.println(map.getClass().getName());
    }

    public static void arraysWithParallelProcessing() {
       Person[] people = Stream.generate(Person::new)
               .limit(1000)
               .parallel()
               .toArray(Person[]::new);
        System.out.println("Total: " + people.length);
    }
}

class Person {
    private final static String[] firstNames = { "Able", "Bob", "Charlie", "Donna", "Eve", "Fred" };
    private final static String[] lastNames = { "Norton", "Ohara", "Peterson", "Qincy", "Richardson", "Smith" };
    private final static Random random = new Random();
    private final String first;
    private final String last;
    private final int age;

    public Person() {
        first = firstNames[random.nextInt(firstNames.length)];
        last = lastNames[random.nextInt(lastNames.length)];
        age = random.nextInt(100 - 18) + 18;
    }

    public String getFirst() { return first; }
    public String getLast() { return last; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return String.format("%s, %s (%d)", first, last, age);
    }
}