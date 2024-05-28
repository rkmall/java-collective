package e_exceptions.exception_handling;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ExceptionHandlingExDriver {

    public static void main(String[] args) {

//        try {
//            readWriteFile();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }


        //uncheckedExceptionShouldBeFixed();

//        try {
//            throwingException();
//        } catch (EOFException e) {
//            throw new RuntimeException(e);
//        }

//        try {
//            readFile();
//        } catch (FileFormatException e) {
//            throw new RuntimeException(e);
//        }


        //tryCatchBlockEx();

        //usingTryWithResources();

        //stackTracing(10);

        assertEx(10);

    }

    // Checked Exception
    public static void readWriteFile() throws FileNotFoundException {
        FileInputStream in  = new FileInputStream("file1.txt");
        FileOutputStream out = new FileOutputStream("test1.txt");

        int c;
        byte[] bytes = new byte[4096];
        try {
            while ((c = in.read(bytes)) != -1) {
                System.out.println(c);
                out.write(bytes, 0, c);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // Unchecked Exception
    // Example of bad practice declaring unchecked exception instead of fixing the issue.
    public static void uncheckedExceptionShouldBeFixed() throws ArrayIndexOutOfBoundsException { // Bad practice,
        int[] numbers = new int[10];

        for (int i = 0; i <= numbers.length; i++) {
            numbers[i] = i;   // tries to access index beyond array length
        }
    }

    // Creating and using custom exception
    public static void readFile() throws FileFormatException {
        final String src = "file1.txt";
        int c;
        try (InputStream in = new FileInputStream(src)) {
            while((c = in.read()) != -1) {
                System.out.println(c);
            }
        } catch (IOException e) {
            throw new FileFormatException("throwing custom exception");
        }
    }


    // Throwing exception
    public static void throwingException() throws EOFException {
        Scanner in = null;
        try {
            in = new Scanner(new BufferedReader(new FileReader("file1.txt")));
            while(in.hasNext()) {
                System.out.println(in.next());
                if(!in.hasNext()){
                    throw new EOFException();      // throw new Exception
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // try-catch block
    public static void tryCatchBlockEx() {
        try {
            System.out.println("Entering try block");
            throwRuntimeException();
            throwIOException();
            System.out.println("Exiting try block");
        } catch (IOException e) {
            System.out.println("Exception thrown");
        }
    }

    private static void throwIOException() throws IOException {
        throw new IOException();
    }

    private static void throwRuntimeException() throws RuntimeException {
        throw new RuntimeException();
    }


    /**
     * Using try-with-resource:
     *  - When the block exits, the resources in and out are closed automatically.
     */
    public static void usingTryWithResources() {
        try (Scanner in = new Scanner(new FileInputStream("file1.txt"), StandardCharsets.UTF_8);
            PrintWriter out = new PrintWriter("test1.txt")
        ) {
            while (in.hasNext()){
                out.println(in.next());
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void stackTracing(int x) {
        Throwable t = new Throwable();
        StackTraceElement[] frames = t.getStackTrace();

        for (StackTraceElement e : frames) {
            System.out.println(e);  // print stack trace
        }
        if (x == 0) return;
        else {
            stackTracing(x - 1);
            System.out.println(x + ": " + "Hello");
        }
    }

    public static void assertEx(int x) {
        assert x != 10;
        System.out.println("OK");
    }
}
