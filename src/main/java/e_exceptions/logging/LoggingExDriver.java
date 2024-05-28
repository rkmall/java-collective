package e_exceptions.logging;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingExDriver {

    private static final Logger logger = Logger.getLogger("com.mycompany.myapp");

    public static void main(String[] args) {

        //setUpBasicLogger();
        setupLogger();
        readFile();
    }

    public static void setUpBasicLogger() {
        Logger.getLogger("com.mycompany.myapp").log(Level.SEVERE, "Starting logger");
        Logger.getLogger("com.mycompany.myapp ").info("Starting logger");
    }

    public static void setupLogger() {
        logger.setLevel(Level.FINE);
        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.FINE);
        logger.setUseParentHandlers(false);
        logger.addHandler(handler);
    }

    public static void readFile() {
        int length;
        byte[] bytes = new byte[4096];
        try ( FileInputStream in = new FileInputStream("file1.txt");
              FileOutputStream out = new FileOutputStream("test1.txt");
        ) {
            while ((length = in.read(bytes)) != -1){
                out.write(bytes, 0, length);
            }
        }catch (IOException e){
            logger.log(Level.FINE, "Explanation", e);
        }
    }
}


