package h_io_nio.nio.d_jsonrequest;

import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JsonRequestExTest {

    public static void main(String[] args) {

        //jsonRequestToSystemOut();
        //jsonRequestToFile();
        //jsonRequestToCSV();
    }

    public static void jsonRequestToSystemOut() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        URI uri = URI.create(url);

        try (InputStream urlIn = uri.toURL().openStream()) {
            urlIn.transferTo(System.out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void jsonRequestToFile() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        URI uri = URI.create(url);
        Path jsonRequestPath = Path.of("jsonRequest.txt");

        try (InputStreamReader in = new InputStreamReader(uri.toURL().openStream());
             BufferedWriter out = Files.newBufferedWriter(jsonRequestPath)
        ) {
            in.transferTo(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void jsonRequestToCSV() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        URI uri = URI.create(url);
        String jsonRequestPath = "jsonRequest.csv";

        try (InputStreamReader in = new InputStreamReader(uri.toURL().openStream());
             PrintWriter out = new PrintWriter(jsonRequestPath);
        ) {
            in.transferTo(new Writer() {
                @Override
                public void write(char[] cbuf, int off, int len) throws IOException {
                    String jsonString = new String(cbuf, off, len).trim();
                    jsonString = jsonString.replace("[", "");     // remove '['
                    jsonString = jsonString.replace("]", "");     // remove ']'
                    jsonString = jsonString.replaceAll("\\{", "");// remove '{'
                    jsonString = jsonString.replaceAll("\\}", "");// remove '}'
                    out.write(jsonString);
                }

                @Override
                public void flush() throws IOException { out.flush(); }

                @Override
                public void close() throws IOException { out.close(); }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
