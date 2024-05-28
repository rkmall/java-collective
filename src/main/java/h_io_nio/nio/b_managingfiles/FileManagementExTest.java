package h_io_nio.nio.b_managingfiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManagementExTest {

    public static void main(String[] args) {

        //renameFileUsingFile();
        renameFileUsingPath();
        //renameDir();

        //createDirectoryAndRename();

        //shallowCopy();
        //deepCopyRecursivelyFiles();

        //shallowDelete();
        //deepDeleteRecursivelyFiles();
    }

    public static void renameFileUsingFile() {
        File oldFile = new File("test1.txt");
        File newFile = new File("test1_renamed.txt");

        if (oldFile.exists()) {
            boolean success = oldFile.renameTo(newFile);
            System.out.println("File renamed: " + success);
        } else {
            System.out.println("File doesn't exist");
        }
    }

    public static void renameFileUsingPath() {
        Path oldPath = Path.of("test1.txt");
        Path newPath = Path.of("text1_renamed.txt");

        try {
            Files.move(oldPath, newPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File renamed");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void renameDir() {
        Path oldPath = Path.of("filesMgmt");
        Path newPath = Path.of("resourceMgmt");

        try {
            Files.move(oldPath, newPath);
            System.out.println("Directory renamed");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createDirectoryAndRename() {
        Path oldPath = Path.of("test1.txt");
        Path newPath = Path.of("filesMgmt/text1_cpy.txt");

        try {
            Files.createDirectories(newPath.subpath(0, newPath.getNameCount() - 1));
            Files.move(oldPath, newPath);
            System.out.println(oldPath + " moved/renamed to " + newPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Only copies the directory not the dir contents.
     */
    public static void shallowCopy() {
        Path src = Path.of("filesMgmt");
        Path dest = Path.of("resourceMgmtShallow");

        try {
            Files.copy(src, dest);
            System.out.println("Directory copied to " + dest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deepCopyRecursivelyFiles() {
        Path src = Path.of("filesMgmt");
        Path dest = Path.of("resourceMgmtRecursive");

        try {
            recurseCopy(src, dest);
            System.out.println("Directory copied to " + dest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void recurseCopy(Path src, Path dest) throws IOException {
        Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
        if (Files.isDirectory(src)) {
            try (Stream<Path> childPaths = Files.list(src)) {
                childPaths.collect(Collectors.toList()).forEach(
                        pth -> {
                            try {
                                // Recursive call
                                FileManagementExTest.recurseCopy(pth, dest.resolve(pth.getFileName()));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
            }
        }
    }

    public static void shallowDelete() {
        Path path = Path.of("filesMgmt");
        try {
            // Throws DirectoryNotEmptyException if the dir contains files
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deepDeleteRecursivelyFiles() {
        Path target = Path.of("resourceMgmtRecursive");

        try {
            recurseDelete(target);
            System.out.println("Directory deleted: " + target) ;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void recurseDelete(Path target) throws IOException {
        if (Files.isDirectory(target)) {
            try (Stream<Path> childPaths = Files.list(target)) {
                childPaths.collect(Collectors.toList()).forEach(
                        pth -> {
                            try {
                                // Recursive call
                                FileManagementExTest.recurseDelete(pth);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
            }
        }
        Files.delete(target); // delete on return
    }
}
