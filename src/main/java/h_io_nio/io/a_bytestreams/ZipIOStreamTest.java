package h_io_nio.io.a_bytestreams;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipIOStreamTest {

    public static void main(String[] args) {

        zipInputOutputStreamEx();
    }

    public static void zipInputOutputStreamEx() {
        String zipFile = "ziptest.zip"; // zip file to read from

        // Create dir to store unzipped files
        File zipDir = new File("ziptestresult");
        boolean dirPresent = false;
        try {
            dirPresent = zipDir.mkdir();
        } catch (SecurityException e) {
            System.out.println(e.getMessage());
        }

        try (InputStream in = new FileInputStream(zipFile);
             ZipInputStream zin = new ZipInputStream(in)
        ) {
            ZipEntry entry;
            // Iterate through each entry in the zip stream
            while ((entry = zin.getNextEntry()) != null) {
                String zipInfo = String.format("Entry: %s, size=%d, date=%s%n ",
                        entry.getName(),
                        entry.getSize(),
                        entry.getTimeLocal().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                System.out.println(zipInfo); // print zip entry information

                // Read zip entry content.
                // Once we get the entry from the stream, it is positioned to read
                // the raw data (zip content)
                if (dirPresent) {
                    String dest = zipDir + "/" + entry.getName();
                    try (FileOutputStream out = new FileOutputStream(dest)) {
                        int len;
                        while ((len = zin.read()) > 0) {
                            out.write(len);
                        }
                    }
                } else {
                    System.out.println("Directory doesn't exist");
                }
                // Close the entry once done
                zin.closeEntry();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
