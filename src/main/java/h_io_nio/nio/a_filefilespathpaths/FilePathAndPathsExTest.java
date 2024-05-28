package h_io_nio.nio.a_filefilespathpaths;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

public class FilePathAndPathsExTest {

    public static void main(String[] args) {

        //fileInstanceCreate();
        //fileCreateActualFile();
        //fileCreateActualTempFile();
        //fileMethods();
        //fileToNIOPath();

        //pathInstanceCreate();
        //pathInfoMethods();
        //pathTestPath();
        //pathResolve();
        pathIteratePathElements();
    }

    //----------------------------IO File methods-------------------------------
    public static void fileInstanceCreate() {
        // Creates just a File instance (abstract representation of File).
        // This doesn't create actual file in the file-system.
        File file = new File("file1.txt");
        String absPath = file.getAbsolutePath();
        System.out.println(absPath);
    }

    public static void fileCreateActualFile() {
        File file = new File("nioFileCreate.txt");
        try {
            // Create an actual file from the File instance.
            boolean res = file.createNewFile();
            System.out.println("New file created: " + res);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void fileCreateActualTempFile() {
        String pwd = new File("").getAbsolutePath(); // get pwd
        System.out.println("pwd: " + pwd);

        File presentDir = new File(pwd); // pwd path
        File tempFile;
        try {
            // Create temp file in present working dir
            tempFile = File.createTempFile("nioTempFile", ".txt", presentDir);
            System.out.println("New temp file: " + tempFile.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Deleting temp file");
        tempFile.deleteOnExit(); // delete the temp file
    }

    public static void fileMethods() {
        File file = new File("file1.txt");
        System.out.println("exists: " + file.exists());
        System.out.println("isDirectory: " + file.isDirectory());
        System.out.println("isFile: " + file.isFile());
        System.out.println("isAbsolute: " + file.isAbsolute());
        System.out.println("isHidden: " + file.isHidden());
        System.out.println("File name: " + file.getName());
        System.out.println("File path: " + file.getPath());
        System.out.println("File absolute path: " + file.getAbsolutePath());
        System.out.println("File parent: " + file.getParent());
        System.out.println("File parent file: " + file.getParentFile());
        System.out.println("File length: " + file.length());
    }

    public static void fileToNIOPath() {
        File file = new File("file1.txt");

        // Get Path instance from the file instance.
        Path path = file.toPath();
        System.out.println("Path file name: " + path.getFileName());
    }

    //---------------------NIO Path and Paths Methods---------------------
    public static void pathInstanceCreate() {
        // Create Path instance using Path interface static method.
        Path path1 = Path.of("file1.txt");
        System.out.println(path1.getFileName());

        // Create Path instance using Paths class static method.
        Path path2 = Paths.get("file2.txt");
        System.out.println(path2.getFileName());

        // Use Files static utility methods with the Path instance
        if (!Files.exists(path2)) {
            System.out.println("File doesn't exist");
            return;
        }
        System.out.println("File exists");
        System.out.println("isDirectory: " + Files.isDirectory(path2));
        System.out.println("isFile: " + Files.isRegularFile(path2));
    }

    public static void pathInfoMethods() {
        Path path = Paths.get("file1.txt");
        System.out.println("Path: " + path);
        System.out.println("FileName: " + path.getFileName());
        System.out.println("Parent: " + path.getParent());
        System.out.println("Path root: " + path.getRoot());
        System.out.println("-------------------------------------------");

        Path absolutePath = path.toAbsolutePath();
        System.out.println("IsAbsolute path: " + path.isAbsolute());
        System.out.println("Absolute path: " + absolutePath);
        System.out.println("Path root: " + absolutePath.getRoot());
        System.out.println("Path parent: " + absolutePath.getParent());
        System.out.println("Name count: " + absolutePath.getNameCount());
        System.out.println("-------------------------------------------");

        // Get name elements of the path
        for (int i = 0; i < absolutePath.getNameCount(); i++) {
            System.out.println(i + ": " +".".repeat(i) + absolutePath.getName(i));
        }
        System.out.println("-------------------------------------------");

        // Get sub paths from the part
        int start = 0, end = 5;
        Path subPath = absolutePath.subpath(start, end);
        for (int i = 0; i < end; i++) {
            System.out.println("SubPath name element " + i + ": " + subPath.getName(i));
        }
    }

    public static void pathTestPath() {
        Path path = Paths.get("file1.txt").toAbsolutePath();
        System.out.println("start with / " + path.startsWith("/"));
        System.out.println("ends with file1.txt " + path.endsWith("file1.txt"));
        System.out.println("start with / " + path.startsWith(Paths.get("/")));
        System.out.println("ends with file1.txt " + path.endsWith(Paths.get("file1.txt")));
    }

    /**
     * This method uses NIO Path, Paths and Files classes exclusively.
     * It doesn't use IO File class.
     * It could be done by using File class instance instead of Files class
     * but using NIO Files, Path and Paths is a better approach.
     */
    public static void pathResolve() {
        Path parentPath = Paths.get("nioResolveParentDir");
        boolean fileExists = Files.exists(parentPath);
        System.out.printf("File '%s' %s%n", parentPath.getFileName(),
                fileExists ? "exits." : "doesn't exists");

        if (!fileExists) {
            // Create parent dir if not already present.
            try {
                Files.createDirectory(parentPath);
                System.out.println("New dir created.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Get absolute path with the given file
            parentPath = parentPath.toAbsolutePath();
            System.out.println("Parent path: " + parentPath);

            // Create a path that needs to be appended/resolved to the above path
            // and append/resolve the other path to this path
            Path resolvedPath = parentPath.resolve(Paths.get("nioFileCreate.txt"));
            System.out.println("Resolved path: " + resolvedPath);

            // Create an actual file from the resolved path
            try {
                if (!resolvedPath.toFile().exists()) {
                    Files.createFile(resolvedPath);
                    System.out.println("New file created with resolved path.");

                    if (Files.isWritable(resolvedPath)) {
                        System.out.println("Writing to file: " + resolvedPath);
                        Files.writeString(resolvedPath,
                                "File name: nioFileCreate.txt.\n" +
                                        "First line.\n" +
                                        "Second line.\n" +
                                        "Last line.\n");
                        Files.readAllLines(resolvedPath).forEach(System.out::println);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void pathIteratePathElements() {
        Path path = Paths.get("file1.txt").toAbsolutePath();

        // Without iterator
        for (Path value : path) { System.out.println(value); }

        // With iterator
        Iterator<Path> it = path.iterator();
        String searchItem = "Java";
        System.out.println("-----------------Searching Path----------------------");
        while (it.hasNext()) {
            if (it.next().equals(Paths.get(searchItem))) {
                System.out.println("Found path: " + searchItem);
                break;
            }
        }
    }
}
