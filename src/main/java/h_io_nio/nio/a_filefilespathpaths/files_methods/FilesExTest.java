package h_io_nio.nio.a_filefilespathpaths.files_methods;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilesExTest {

    public static void main(String[] args) {

        //filesInfoMethods();

        //filesListDirectoryEntries(true);
        //filesWalkDirectoryEntries(true);
        //filesFindFileInDirectoryEntries(true);

        //filesNewDirectoryStreamGlobbing(true);

        //charsetGetDefault();
        filesReadFile();
        //filesReadFromFixedWidthLine();
        //filesReadFromFixedWidthLineStreams();
    }

    public static void filesInfoMethods() {
        Path path = Paths.get("file1.txt");
        try {
            Map<String, Object> attrs = Files.readAttributes(path, "*");
            attrs.entrySet().forEach(System.out::println);
            System.out.println("Content type: " + Files.probeContentType(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void filesListDirectoryEntries(boolean listAbsolute) {
        Path path = Path.of(""); // pwd
        System.out.println("pwd: " + path.toAbsolutePath());

        if (listAbsolute) { path = path.toAbsolutePath(); }

        // List directory entries (list is non-recursive)
        System.out.println("---------------List Dir Entries---------------");
        try (Stream<Path> paths = Files.list(path)) {
            paths.map(FilesExTest::markDirectories)
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void filesWalkDirectoryEntries(boolean listAbsolute) {
        Path path = Path.of("");
        System.out.println("pwd: " + path.toAbsolutePath());
        if (listAbsolute) { path = path.toAbsolutePath(); }

        // Walks directory entries (recursively walk through the directories)
        System.out.println("---------------Walk Dir Entries---------------");
        try (Stream<Path> paths = Files.walk(path, 2)) {
            paths.map(FilesExTest::markDirectories)
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void filesFindFileInDirectoryEntries(boolean listAbsolute) {
        Path path = Path.of("");
        System.out.println("pwd: " + path.toAbsolutePath());
        if (listAbsolute) { path = path.toAbsolutePath(); }

        // Finds directory entries (recursively walk through the directories)
        //  - lambda args "pth" is each stream element i.e. each entry in directory.
        //  - and "attr" is each stream element attr i.e. each entry attributes.
        System.out.println("-------------Find in Dir Entries-------------");
        try (Stream<Path> paths = Files.find(path,                           // path
                2,                                                           // depth level
                (pth, attr) -> attr.isRegularFile() && Files.isWritable(pth))// bi-predicate
        ) {
            paths.map(FilesExTest::markDirectories)
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String markDirectories(Path path) {
        try {
            boolean isDir = Files.isDirectory(path);
            FileTime time = Files.getLastModifiedTime(path);
            LocalDateTime dateTime = LocalDateTime.ofInstant(time.toInstant(), ZoneId.systemDefault());
            return String.format("%tD %tT %-8s %-12s %s",
                    dateTime, dateTime,
                    (isDir ? "<DIR>" : ""),
                    (isDir ? "" : Files.size(path)),
                    path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void filesNewDirectoryStreamGlobbing(boolean listAbsolute) {
        Path path = Path.of("");
        System.out.println("pwd: " + path.toAbsolutePath());
        if (listAbsolute) { path = path.toAbsolutePath(); }

        // Using newDirectoryStream with globbing.
        System.out.println("---------------Dir Stream---------------");
        path = path.resolve(".idea");   // append path ".idea"
        try (DirectoryStream<Path> dirs = Files.newDirectoryStream(path, "*.xml")) {
            dirs.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Using newDirectoryStream with predicate.
        System.out.println("---------------Dir Stream---------------");
        try (DirectoryStream<Path> dirs = Files.newDirectoryStream(
                path,
                pth -> pth.getFileName().toString().endsWith(".xml") && Files.size(pth) > 1000
        )) {
            dirs.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void charsetGetDefault() {
        System.out.println(System.getProperty("file.encoding"));
        System.out.println(Charset.defaultCharset());
    }

    public static void filesReadFile() {
        Path path = Path.of("fixedWidthFile.txt");
        try {
            System.out.println("----------------readAllBytes-----------------");
            System.out.println(new String(Files.readAllBytes(path)));

            System.out.println("----------------readString-----------------");
            System.out.println(Files.readString(path));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public static void filesReadFromFixedWidthLine() {
        Path path = Path.of("fixedWidthFile.txt");
        try {
            Pattern pattern = Pattern.compile("(.{15})(.{3})(.{12})(.{8})(.{2}).*");
            Set<String> values = new TreeSet<>();
            Files.readAllLines(path).forEach(s -> {
                if (!s.startsWith("Name")) {        // ignore header row
                    Matcher m = pattern.matcher(s); // pass each string read from the file
                    if (m.matches()) {
                        values.add(m.group(3).trim());
                    }
                }
            });
            System.out.println(values);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void filesReadFromFixedWidthLineStreams() {
        Path path = Path.of("fixedWidthFile.txt");
        Pattern pattern = Pattern.compile("(.{15})(.{3})(.{12})(.{8})(.{2}).*");
        try (Stream<String> strings = Files.lines(path)) {
            // Get the departments
            String[] res = strings
                    .skip(1)
                    .map(pattern::matcher)
                    .filter(Matcher::matches)
                    .map(matcher -> matcher.group(3).trim())
                    .distinct()
                    .sorted()
                    .toArray(String[]::new);
            System.out.println(Arrays.toString(res));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (Stream<String> strings = Files.lines(path)) {
            // Get departments and total number of records within the group/department.
            // Map of key=department name, value=number of record in the department.
            System.out.println("-----------------------------------------------------");
            Map<String, Long> res1 = strings
                    .skip(1)
                    .map(pattern::matcher)
                    .filter(Matcher::matches)
                    .collect(Collectors.groupingBy(matcher -> matcher.group(3).trim(),
                            Collectors.counting()));
            res1.entrySet().forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
