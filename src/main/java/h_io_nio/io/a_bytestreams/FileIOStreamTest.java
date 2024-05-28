package h_io_nio.io.a_bytestreams;

import java.io.*;

public class FileIOStreamTest {

    public static void main(String[] args) {

        fileInputOutputStreamEx1();
        //fileInputOutputStreamEx2();
        //fileInputOutputStreamEx3();
    }

    public static void fileInputOutputStreamEx1() {
        String src = "fileInputStreamEx1.txt";
        String dest = "fileOutputStreamsEx1.txt";

        // try-with-resource
        try (FileInputStream in = new FileInputStream(src);     // input stream reading from src file
             FileOutputStream out = new FileOutputStream(dest)  // output stream writing to dest file
        ) {
            int c; // var to hold the next byte read
            while ((c = in.read()) > -1) { // read ranges from 0 to 255 i.e. 2^8. -1 is returned when EOF
                System.out.println("Writing: " + c);
                out.write(c); // only 8-bit (low order) are written, while 24 high-order bits are ignored
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } // no need of finally block as the resources will be auto-closed
    }     // when the operation completes using try-with-resource


    public static void fileInputOutputStreamEx2() {
        String src = "fileInputStreamEx1.txt";
        try (FileInputStream in = new FileInputStream(src)) {
            /*
             * E.g., the source file "fileInputStreamEx1.txt" is a .txt file
             * with 50 characters in total.
             * 1 byte = 8-bit i.e. 2^8 = 256 ranging from -128 to 127,
             * i.e. 1 byte is enough to store each char in the byte array.
             * read() method reads all 50 bytes (array of bytes)
             * storing them in the buffer and returns integer 50.
             */
            int c;
            byte[] buffer = new byte[56];
            while ((c = in.read(buffer, 0, 50)) > 0) {
                System.out.println("Read: " + c);
            }
            for (Byte b : buffer) {
                System.out.format("Char: %c%n", b);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void fileInputOutputStreamEx3() {
        String src = "fileInputStreamEx1.txt";
        try (FileInputStream in = new FileInputStream(src)) {
            int c;
            byte[] buffer = new byte[56];
            int offset = 0;
            int length = 30;
            while ((c = in.read(buffer, offset, length)) != -1) {
                System.out.println("Read: " + c);
            }
            for (byte b : buffer) {
                System.out.format("byte: %c%n", b);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

