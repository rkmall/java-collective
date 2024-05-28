package h_io_nio.util_scanner;

import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.stream.Collectors;

public class ScannerExTest {

    public static void main(String[] args) {

        //scannerConstructors();
        //scannerUserInput();
        //scannerTokens();
        //scannerUseDelimiter();
        //scannerFindAll();

        //scannerReadFromFixedWidthFile();
    }


    public static void scannerConstructors() {
        // InputStream
        try (Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream("file1.txt")))) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // File
        try (Scanner scanner = new Scanner(new File("file1.txt"))) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Path
        try (Scanner scanner = new Scanner(Path.of("file1.txt"))) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // String
        String content = "This is string content\n" +
                "First line\n" +
                "Second line\n" +
                "Last line\n";
        try (Scanner scanner = new Scanner(content)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }
    }

    public static void scannerUserInput() {
        Scanner scanner = new Scanner(System.in);   // in is an InputStream
        System.out.print("Enter something: ");

        String line;
        while (!(line = scanner.nextLine()).equals("exit")) {
            System.out.println(line);
            System.out.print("Enter something: ");
        }
    }

    public static void scannerTokens() {
        try (Scanner scanner = new Scanner(new File("file1.txt"))) {
            List<String> tokens = scanner
                    .tokens()
                    .filter(s -> s.length() > 10)
                    .distinct()
                    .collect(Collectors.toList());

            tokens.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void scannerUseDelimiter() {
        try (Scanner scanner = new Scanner(new File("file1.txt"))) {
            scanner.useDelimiter(",");
            scanner.tokens().forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void scannerFindAll() {
        try (Scanner scanner = new Scanner(new File("file1.txt"))) {
            scanner.findAll("[A-Za-z]{10,}") // match strings with length at least 10
                    .map(MatchResult::group)
                    .distinct()
                    .sorted()
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void scannerReadFromFixedWidthFile() {
        String src = "fixedWidthFile.txt";
        try (Scanner scanner = new Scanner(new File(src))) {
            // The pattern used matches the widths specified in the src file.
            // Get last group/column i.e. States from the src file
            String[] result = scanner.findAll("(.{15}(.{3})(.{12})(.{8})(.{2}).*)")
                    .skip(1) // skip header row
                    .map(match -> match.group(5))
                    .distinct()
                    .sorted()
                    .toArray(String[]::new);
            System.out.println(Arrays.toString(result));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (Scanner scanner = new Scanner(new File(src))) {
            // Get 3rd group/column i.e. Department from the src file.
            String[] result = scanner.findAll("(.{15}(.{3})(.{12})(.{8})(.{2}).*)")
                    .skip(1)
                    .map(match -> match.group(3).trim()) // trim the specified width of 3rd column
                    .distinct()
                    .sorted()
                    .toArray(String[]::new);
            System.out.println(Arrays.toString(result));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
