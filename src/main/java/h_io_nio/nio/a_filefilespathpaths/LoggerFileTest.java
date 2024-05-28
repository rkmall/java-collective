package h_io_nio.nio.a_filefilespathpaths;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;

public class LoggerFileTest {

    public static void main(String[] args) {

        //Path path1 = Paths.get("nioLoggerDir/nioLoggerTest");
        //logStatement(path1);

        Path path2 = Paths.get("nioLoggerDir1/nioDir2/nioDir3/nioDir4/nioLoggerTest");
        logStatementMultiParentDirs(path2);
    }

    private static void logStatement(Path path) {
        try {
            // First create a dir if not already exist.
            Path parent = path.getParent();
            if (!Files.exists(parent)) {
                // Create single parent dir
                Files.createDirectory(parent);
            }
            /*
             * writeString() is a variation of write() which by default
             * creates an actual file to write if the files doesn't
             * already exist.
             */
            Files.writeString(path,
                    "Time: " + Instant.now() + "\n",
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void logStatementMultiParentDirs(Path path) {
        try {
            // First create a dir if not already exist.
            Path parent = path.getParent();
            if (!Files.exists(parent)) {
                // Create multiple parent dirs
                Files.createDirectories(parent);
            }
            /*
             * writeString() is a variation of write() which by default
             * creates an actual file to write if the files doesn't
             * already exist.
             */
            Files.writeString(path,
                    "Time: " + Instant.now() + "\n",
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
