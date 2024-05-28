package h_io_nio.io.b_characterstreams;

import java.io.*;
import java.util.ArrayList;

public class FIleReaderWriterTest {

    public static void main(String[] args) {

        //fileReaderWriterEx();
        fileReaderWriterBufferEx();
    }

    public static void fileReaderWriterEx() {
        final String src = "fileReader.txt";
        final String dest = "fileWriter.txt";

        try(FileReader reader = new FileReader(src);
            FileWriter writer = new FileWriter(dest)) {
            int c;
            while((c = reader.read()) != -1) {
                writer.write(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fileReaderWriterBufferEx() {
        final String src = "fileReader.txt";

        try(FileReader reader = new FileReader(src)) {
            char[] buf = new char[96];
            while (reader.read(buf) > 0) {
                System.out.println(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
