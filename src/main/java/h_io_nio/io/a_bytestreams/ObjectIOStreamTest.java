package h_io_nio.io.a_bytestreams;

import h_io_nio.io.Company;
import h_io_nio.io.Guitar;
import h_io_nio.io.GuitarStore;

import java.io.*;
import java.util.*;

public class ObjectIOStreamTest {

    public static void main(String[] args) {

        serializeDeserializeObject();
        //serializeDeserializeCollection();
    }

    public static void serializeDeserializeObject() {
        // Serialize Guitar object
        String dest = "objectOutputStream1.ser";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(dest))) {
            Company company = new Company("Fender", "US");       // Company obj
            Guitar gtr = new Guitar("Strat", "Fender", company);  // Guitar obj
            out.writeObject(gtr);    // write to a file
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Deserialize Guitar object
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(dest))) {
            Guitar gtr = (Guitar) in.readObject();
            System.out.println(gtr.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void serializeDeserializeCollection() {
        // Store guitars in the guitar store
        GuitarStore store = new GuitarStore();
        Guitar gtr1 = new Guitar("Strat","Fender", new Company("Fender", "US"));
        Guitar gtr2 = new Guitar("Tele","Fender", new Company("Fender", "US"));
        Guitar gtr3 = new Guitar("Les Paul","Gibson", new Company("Gibson", "US"));

        ArrayList<Guitar> list = new ArrayList<>() {{
            add(gtr1); add(gtr2); add(gtr3);
        }};
        store.addAllGuitar(list);

        // Serialize guitar collection
        String file = "objectOutputStream2.ser";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            Collection<Guitar> guitars = store.getAllGuitars().values();
            for (Guitar gtr : guitars) {
                out.writeObject(gtr); // write object to ObjectOutputStream
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Deserialize guitar collection
        List<Guitar> resultList = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Guitar gtr;
            while ((gtr = (Guitar) in.readObject()) != null) { // read object from ObjectInputStream
                resultList.add(gtr);
            }
        } catch (IOException e) {
            if (e instanceof EOFException) {       // exception will be thrown when EOF reached
                System.out.println("EOF reached"); // since check is done using while != null
            } else {
                System.out.println(e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Printing deserialized Guitar objects...");
        for (Guitar gtr : resultList) {
            System.out.println(gtr);
        }
    }
}