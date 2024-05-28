package h_io_nio.nio.c_channelsbuffersselectors;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class NioBufferTest {

    public static void main(String[] args) {

        bufferEx();
    }

    public static void bufferEx() {
        ByteBuffer buf = ByteBuffer.allocate(1024); // allocate buffer
        bufferInfo(buf);

        buf.put((byte) 1);               // relative put, increments position
        buf.put(1, (byte) 2);      // absolute put, doesn't increment position
        System.out.println(buf.get());   // relative get, increments position
        bufferInfo(buf);
        System.out.println(buf.get(3));  // absolute get, doesn't increment position
        bufferInfo(buf);

        byte[] bytes = new byte[] { 10, 20, 30, 40, 50 };
        buf.put(bytes);                  // relative bulk put, increments position

        byte[] dest = new byte[20];
        buf.rewind();                    // rewind before get/read
        buf.get(dest);                   // relative bulk get, increments position
        System.out.println(Arrays.toString(dest));
        bufferInfo(buf);

        buf.put(bytes, 0, 5);// relative bulk get, increments position
        dest = new byte[25];
        buf.rewind();                     // rewind before get/read
        buf.get(dest, 0, 5); // relative bulk get, increments position
        System.out.println(Arrays.toString(dest));
        bufferInfo(buf);
    }

    private static void bufferInfo(Buffer buf) {
        System.out.println("Buffer: capacity = " + buf.capacity() +
                ", limit = " + buf.limit() +
                ", position = " + buf.position());
    }
}
