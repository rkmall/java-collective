package h_io_nio.nio.c_channelsbuffersselectors;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioChannelTest {

    public static void main(String[] args) {

        //channelEx();
        //transferFromChannel();
        //transferToChannel();
    }

    public static void channelEx() {
        try (FileInputStream in = new FileInputStream("file1.txt");
             FileOutputStream out = new FileOutputStream("channelTest.txt")
        ) {
            FileChannel inCh = in.getChannel();
            FileChannel outCh = out.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            while (inCh.read(buf) != -1) {
                buf.flip(); // flip the buffer for writing
                while (buf.hasRemaining()) { // check buffer is fully drained
                    outCh.write(buf);
                }
                buf.clear(); // empty buffer, ready for filling
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void transferFromChannel() {
        try (FileInputStream in = new FileInputStream("file1.txt");
             FileOutputStream out = new FileOutputStream("channelTransferFromTest.txt")
        ) {
            FileChannel inCh = in.getChannel();
            FileChannel outCh = out.getChannel();

            long position = 0;
            long count = inCh.size();
            outCh.transferFrom(inCh, position, count);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void transferToChannel() {
        try (FileInputStream in = new FileInputStream("file1.txt");
             FileOutputStream out = new FileOutputStream("channelTransferToTest.txt")
        ) {
            FileChannel inCh = in.getChannel();
            FileChannel outCh = out.getChannel();

            long position = 0;
            long count = inCh.size();
            inCh.transferTo(position, count, outCh);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
