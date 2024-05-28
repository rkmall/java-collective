package h_io_nio.io.b_characterstreams;

import java.io.*;
import java.util.Scanner;

public class BufferedReaderWriterTest {

    public static void main(String[] args) {

        //bufferedReaderWriterEx();
        bufferedReaderWriterLineEx();

    }

    public static void bufferedReaderWriterEx() {
        String src = "bufferedReader.txt";
        try(BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(src)))) {
            char[] buf = new char[96];
            while ((in.read(buf)) > 0) {
                System.out.println(buf);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void bufferedReaderWriterLineEx() {
        final String src = "bufferedReader.txt";
        final String dest = "bufferedWriter.txt";

        try(BufferedReader in = new BufferedReader(new FileReader(src));
            BufferedWriter out = new BufferedWriter(new FileWriter(dest))) {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                out.write(line);
                out.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
