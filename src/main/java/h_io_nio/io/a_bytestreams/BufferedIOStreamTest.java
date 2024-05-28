package h_io_nio.io.a_bytestreams;

import java.io.*;

public class BufferedIOStreamTest {

    public static void main(String[] args) {

        bufferedInputOutputEx();
    }

    public static void bufferedInputOutputEx() {
        String src = "test_vid.mp4";
        String dest = "test_vid_cpy.mp4";
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(src));
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dest))
        ) {
            byte[] buffer = new byte[4096];
            int offset = 0;
            int len;

            while ((len = in.read(buffer)) > 0) {
                System.out.println("Writing buffer: " + len);
                out.write(buffer, offset,  len);
                out.flush();    // write data in the buffer to output/dest file
            }
            System.out.println("Copy complete");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
