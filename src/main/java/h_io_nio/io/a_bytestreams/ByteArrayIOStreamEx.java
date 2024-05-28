package h_io_nio.io.a_bytestreams;

import java.io.*;

public class ByteArrayIOStreamEx {

    public static void main(String[] args) {

        //readFromSrcBytes();
        //readFromSrcStringBytes();


        //readBytesArrayAndWriteToFile();
        readBytesArrayAndWriteToFileInChunk();

        //byteArrayIOWrite1();
        //byteArrayIOWrite2();

    }

    public static void readFromSrcBytes() {
        byte[] bytes = new byte[10];
        bytes[0] = 1;
        bytes[1] = 2;
        bytes[2] = 3;

        int res;
        try (ByteArrayInputStream in = new ByteArrayInputStream(bytes)) {
            while ((res = in.read()) != -1) {
                System.out.println(res);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readFromSrcStringBytes() {
        String src = "Hello there, how are you?";

        int res;
        try (ByteArrayInputStream in = new ByteArrayInputStream(src.getBytes())) {
            while ((res = in.read()) != -1) {
                System.out.println(res);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void readBytesArrayAndWriteToFile() {
        String src = "This is a test, This is a test, This is a test, This is a test, This is a test, This is a test.";
        String dest = "byteArrayIOResult.txt";

        int res;
        try (ByteArrayInputStream in = new ByteArrayInputStream(src.getBytes());
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dest))
        ) {
            while ((res = in.read()) != -1) {
                out.write(res);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readBytesArrayAndWriteToFileInChunk() {
        String src = "Hello there, how are you?";
        String dest = "byteArrayIOResult1.txt";

        byte[] buffer = new byte[10];
        try (ByteArrayInputStream in = new ByteArrayInputStream(src.getBytes());
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dest))
        ) {
            // Read in chunk of 10 bytes
            while (in.available() > 10) {
                in.read(buffer);
                out.write(buffer);
            }

            // Read and write the remaining part
            int res;
            while ((res = in.read()) != -1) {
                out.write(res);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void byteArrayIOWrite2() {
        byte[] res;
        SomeClass someClass = new SomeClass(1, "SomeName");
        /*
         * The use case of ByteArrayOutputStream:
         * - We could directly write object of SomeClass into a file without ByteArrayOutputStream like:
         *      SomeClass obj = new SomeClass();
         *      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("file.txt"));
         *      out.writeObject(obj);
         *
         *  - But there are cases when we need to write the object to byte array instead of
         *    File or Socket because we need to use the byte later or there may be constraint
         *    where we can't write Object directly due to its size.
         *      In this case we use ByteArrayOutputStream can be used.
         */
        try (ByteArrayOutputStream bOut = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bOut)
        ) {
            out.writeObject(someClass); // write Object to byte array
            res = bOut.toByteArray();   // get the byte array for the written object
        } catch (IOException e) {
            throw new RuntimeException();
        }

        try (ByteArrayInputStream bIn = new ByteArrayInputStream(res); // read ByteArray
             ObjectInputStream in = new ObjectInputStream(bIn)
        ) {
            SomeClass obj = (SomeClass) in.readObject();    // read object from byte array
            System.out.println(obj.getId() + obj.getName());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

class SomeClass implements Serializable {
    private int id;
    private String name;

    public SomeClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
