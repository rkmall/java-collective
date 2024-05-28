package h_io_nio.io.a_bytestreams;

import java.io.*;
import java.util.*;

public class DataIOStreamTest {

    public static void main(String[] args) {

        dataInputStream();
    }

    public static void dataInputStream() {
        // Data to be written to the file
        HashMap<String, Double> items = new HashMap<>();
        items.put("apple", 2.50);
        items.put("banana", 3.50);
        items.put("oranges", 4.50);
        items.put("grapes", 5.50);

        // Write data to the file
        String file = "dataOutputStream.dat";
        try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
            for (Map.Entry<String, Double> item  : items.entrySet()) {
                out.writeUTF(item.getKey());        // note the order of data type stored
                out.writeDouble(item.getValue());   // in this case: key=String, value=Double
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Read data from the file
        HashMap<String, Double> result = new HashMap<>();
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            String name;
            double price;
            while ((name = in.readUTF()) != null) { // match the order of stored data type
                price = in.readDouble();            // in this case: key=String, value=Double
                result.put(name, price);
            }
        } catch (IOException e) {
            if (e instanceof EOFException) {
                System.out.println("EOF reached");  // exception will be thrown when EOF reached
            } else {                                // since check is done using while != null
                System.out.println(e.getMessage());
            }
        }

        // Test the read data
        System.out.println("Data read from file");
        for (Map.Entry<String, Double> item : result.entrySet()) {
            System.out.println(item);
        }
    }
}
