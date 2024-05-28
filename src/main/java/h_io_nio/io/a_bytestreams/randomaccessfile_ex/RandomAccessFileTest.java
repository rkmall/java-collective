package h_io_nio.io.a_bytestreams.randomaccessfile_ex;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class RandomAccessFileTest {

    // Map with key=recordId, value=pointerPosition of the stored record in the file.
    private static final Map<Long, Long> indexMap = new LinkedHashMap<>();

    private static int totalNoOfRecords = 0;

    // Client code that would use the studentData.
    public static void main(String[] args) {

        BuildStudentData.build("studentRAF", true);
        useStudentDataFromSingleDatFile();
        useStudentDataFromDatFileIdxFile();
    }

    /**
     * Load the id and file position to indexMap from ".dat" file.
     * Then, query records from the same ".dat" file corresponding
     * to the file position in indexMap.
     */
    private static void useStudentDataFromSingleDatFile() {
        // Src file contains the id, file position and record content all at one place.
        String srcFile = "studentRAF.dat";

        try (RandomAccessFile raf = new RandomAccessFile(srcFile, "r")) {
            loadIndexToMap(raf, 0);
            queryStudentData(raf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Load the id and file position to indexMap from ".idx" file.
     * Then, query records from ".dat" file corresponding to the
     * file position in indexMap.
     */
    public static void useStudentDataFromDatFileIdxFile() {
        String srcIdxFile = "studentRAF.idx";   // srcIdxFile contains id and file position
        String srcDataFile = "studentRAF.dat";  // srcDataFile contains record content
        try (RandomAccessFile indexRaf = new RandomAccessFile(srcIdxFile, "r");
             RandomAccessFile dataRaf = new RandomAccessFile(srcDataFile, "r")
        ) {
            loadIndexToMap(indexRaf, 0); // load index from srcIdxFile
            queryStudentData(dataRaf);             // query data from srcDatafile
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Read records corresponding to the file position stored in indexMap
     * @param raf the RandomAccessFile to read the records from.
     * @throws IOException the exception
     */
    private static void queryStudentData(RandomAccessFile raf) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a Student Id or 0 to quit");
        while (scanner.hasNext()) {
            long studentId = Long.parseLong(scanner.nextLine());
            if (studentId < 1) {
                break;
            }
            // Get the file position (value) of the student fromI the indexMap
            // and read the student record.
            raf.seek(indexMap.get(studentId));
            String targetedRecord = raf.readUTF();
            System.out.println(targetedRecord);
            System.out.println("Enter a Student Id or 0 to quit");
        }
    }

    /**
     * Load id and file position from the source file:
     *  - first, laod total no. of records count i.e. the first element from src file.
     *  - then, load id and file position to the indexMap from src file.
     * @param raf the RandomAccessFile to read id and file position from.
     * @param filePosition the file position to start reading the file.
     */
    private static void loadIndexToMap(RandomAccessFile raf, int filePosition) {
        try {
            raf.seek(filePosition);          // set seek to 0 or start of the file
            totalNoOfRecords = raf.readInt(); // get the totalNoOfRecords i.e. the first element from the file
            System.out.println("Total number of records: " + totalNoOfRecords);
            for (int i = 0; i < totalNoOfRecords; i++) {
                // Read key and file position from the file to indexMap
                indexMap.put(raf.readLong(), raf.readLong());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
