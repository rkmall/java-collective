package h_io_nio.nio.a_filefilespathpaths.files_methods;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileWalkTreeExTest {

    public static void main(String[] args) {

        //filesFileWalkerStream();
        filesFileWalker1();
        //filesFileWalker2();

    }

    public static void filesFileWalkerStream() {
        Path startPath = Path.of("..");
        int index = startPath.getNameCount();
        try (Stream<Path> paths = Files.walk(startPath, Integer.MAX_VALUE)) {
            paths
                    .filter(Files::isRegularFile)
                    .collect(Collectors.groupingBy(
                            pth -> pth.subpath(index, index + 1),
                            Collectors.summarizingLong(
                                    pth -> {
                                        try {
                                            return Files.size(pth);
                                        } catch (IOException e)  {
                                            throw new RuntimeException(e);
                                        }
                                    })))
                    .entrySet()
                    .stream()
                    .sorted(Comparator.comparing(entry -> entry.getKey().toString()))
                    .filter(entry -> entry.getValue().getSum() > 50_000)
                    .forEach((entry) -> System.out.printf("[%s] %,d bytes, %d files%n",
                            entry.getKey(),
                            entry.getValue().getSum(),
                            entry.getValue().getCount()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void filesFileWalker1() {
        Path startPath = Path.of(".");  // current folder
        FileVisitor<Path> statsVisitor = new StatsVisitor(2);
        try {
            Files.walkFileTree(startPath, statsVisitor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void filesFileWalker2() {
        Path startPath = Path.of("ziptestresult");
        FileVisitor<Path> sizeVisitor = new SizeVisitor(2);
        try {
            Files.walkFileTree(startPath, sizeVisitor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class StatsVisitor implements FileVisitor<Path> {
        private static final String DIR_COUNT = "DirCount";
        private static final String FILE_COUNT = "FileCount";
        private static final String FILE_SIZE = "FileSize";

        private Path startPath = null;
        private final Map<Path, Map<String, Long>> dirStats = new LinkedHashMap<>();
        private int startPathLevel;
        private final int printLevel;

        public StatsVisitor(int printLevel) {
            this.printLevel = printLevel;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
            Objects.requireNonNull(dir);
            Objects.requireNonNull(attrs);

            if (startPath == null) {
                startPath = dir;
                startPathLevel = dir.getNameCount();
            } else {
                int relativeLevel = dir.getNameCount() - startPathLevel;
                if (relativeLevel == 1) {
                    dirStats.clear();
                }
                dirStats.put(dir, new HashMap<>());
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            Objects.requireNonNull(file);
            Objects.requireNonNull(attrs);

            Map<String, Long> parentMap = dirStats.get(file.getParent());
            if (parentMap != null) {
                long fileSize = attrs.size();
                parentMap.merge(FILE_SIZE, fileSize, (oldVal, newVal) -> oldVal + newVal);
                parentMap.merge(FILE_COUNT, 1L, Math::addExact);
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
            Objects.requireNonNull(dir);

            if (dir.equals(startPath)) {
                return FileVisitResult.TERMINATE;
            }

            int relativeLevel = dir.getNameCount() - startPathLevel;
            if (relativeLevel == 1) {
                dirStats.forEach((k, v) -> {
                    int level = k.getNameCount() - startPathLevel - 1;
                    if (level < printLevel) {
                        long size = v.getOrDefault(FILE_SIZE, 0L);
                        System.out.printf("%s[%s] - %,d bytes, %d files, %d directories%n",
                                "\t".repeat(level), k.getFileName(), size,
                                v.getOrDefault(FILE_COUNT, 0L),
                                v.getOrDefault(DIR_COUNT, 0L));
                    }
                });
            } else {
                Map<String, Long> parentMap = dirStats.get(dir.getParent());
                Map<String, Long> childMap = dirStats.get(dir);
                long dirCount = childMap.getOrDefault(DIR_COUNT, 0L);
                long fileSize = childMap.getOrDefault(FILE_SIZE, 0L);
                long fileCount = childMap.getOrDefault(FILE_COUNT, 0L);

                parentMap.merge(DIR_COUNT, dirCount + 1, (oldVal, newVal) -> oldVal + newVal);
                parentMap.merge(FILE_SIZE, fileSize, Math::addExact);
                parentMap.merge(DIR_COUNT, fileCount, Math::addExact );
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            Objects.requireNonNull(file);
            if (exc != null) {
                System.out.println(exc.getClass().getSimpleName() + ": " + file);
            }
            return FileVisitResult.CONTINUE;
        }
    }

    private static class SizeVisitor extends SimpleFileVisitor<Path> {
        private Path startPath = null;

        /**
         * Map of Path and Long to keep track of dir and their sizes
         * which represents the cumulative size of the directory i.e.
         * accumulated sizes of the siblings.
         *  - LinkedHashMap to maintain the insertion order to print
         *    the directories in order.
         */
        private final Map<Path, Long> dirSizes = new LinkedHashMap<>();

        /*
         * Path's name count to track the level.
         * This count is for the start path and all paths
         * will be relative to it.
         */
        private int startPathLevel;

        private final int printLevel;

        public SizeVisitor(int printLevel) {
            this.printLevel = printLevel;
        }

        /**
         * Invoked for a directory before entries in the directory are visited.
         */
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
            Objects.requireNonNull(dir);
            Objects.requireNonNull(attrs);

            // The startPath is always null when this method is called for first time.
            // setting startPath to dir and startPathLevel to dirs name count.
            if (startPath == null) {
                startPath = dir;
                startPathLevel = dir.getNameCount();
            } else {
                int relativeLevel = dir.getNameCount() - startPathLevel;

                // Before visiting each first level subdir, clear the map.
                if (relativeLevel == 1) {
                    dirSizes.clear(); // empty map when relative level is first level
                }

                // Initial setup for each new dir with key=dir and value=0L
                dirSizes.put(dir, 0L);// initial setup for each new dir with 0L size
            }

            /*
             * The return value determines how the walk is processed after this method.
             * In this case, the walk continues. All available options are:
             *  - CONTINUE
             *  - TERMINATE
             *  - SKIP_SUBTREE
             *  - SKIP_SIBLINGS
             */
            return FileVisitResult.CONTINUE;
        }

        /**
         * Invoked for a file in directory
         * i.e. this method is only invoke if a file is not directory.
         */
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            Objects.requireNonNull(file);
            Objects.requireNonNull(attrs);

            /*
             * Merge method puts a new entry in the map where:
             *  - if the key doesn't exist, with the default value specified 0L.
             *  - if the key already exist, then execute the bi-function adding
             *    the size of the current FILE to the parent dir running total.
             *      - Hence, file.getParent() used to accumulate the sizes in
             *        parent dir.
             *      - Adding size of the current DIR to the parent DIR running total
             *        is done in postVisitDirectory() method.
             *
             * Merge BiFunction args:
             *  - first arg is the existing value in the map.
             *  - second arg is the new value which is the specified
             *    default value 0L in this case.
             */
            dirSizes.merge(file.getParent(), 0L, (oldVal, newVal) -> oldVal + attrs.size());
            return FileVisitResult.CONTINUE;
        }

        /**
         * Invoked for a directory after entries in the directory, and all of their
         * descendants, have been visited.
         */
        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
            Objects.requireNonNull(dir);

            if (dir.equals(startPath)) {
                return FileVisitResult.TERMINATE;
            }

            int relativeLevel = dir.getNameCount() - startPathLevel;
            if (relativeLevel == 1) {
                // If first level sub-folder
                dirSizes.forEach((k, v) -> {
                    int level = k.getNameCount() - startPathLevel - 1;
                    if (level < printLevel) {
                        System.out.printf("%s[%s] - %,d bytes%n",
                                "\t".repeat(level), k.getFileName(), v);
                    }
                });
            } else {
                // If not first level subdir, add the size of current dir
                // to parent dir running total
                long dirSize = dirSizes.get(dir);
                dirSizes.merge(dir.getParent(), 0L, (oldVal, newVal) -> oldVal + dirSize);
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return super.visitFileFailed(file, exc);
        }
    }
}
