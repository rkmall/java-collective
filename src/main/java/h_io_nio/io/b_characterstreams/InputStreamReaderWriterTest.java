package h_io_nio.io.b_characterstreams;

import java.io.*;

public class InputStreamReaderWriterTest {

    public static void main(String[] args) {

        inputStreamReaderWriterFileEx();
        //inputStreamReaderWriterEx();
    }

    public static void inputStreamReaderWriterFileEx() {
        String src = "fileInputStreamEx1.txt";
        String dest = "outputStreamWriter.txt";
        try (InputStreamReader in = new InputStreamReader(new FileInputStream(src));
             OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(dest))
        ) {
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void inputStreamReaderWriterEx() {
        try (InputStreamReader in = new InputStreamReader(System.in);
             OutputStreamWriter out = new OutputStreamWriter(System.out)
        ) {
            int c;
            while ((c = in.read()) != 'x') {
                out.write("Writing: " + c + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
