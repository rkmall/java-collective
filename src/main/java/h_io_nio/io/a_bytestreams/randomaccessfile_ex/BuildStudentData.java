package h_io_nio.io.a_bytestreams.randomaccessfile_ex;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuildStudentData {
    // Map of key=recordId, value=pointerPosition
    // Use LinkedHaspMap so that data is in insertion order matching the order
    // of records that were written.
    private final static Map<Long, Long> indexMap = new LinkedHashMap<>();

    public static void build(String outputFileName, boolean separateIndex) {
        Path studentJson = Path.of("students.json"); // create path to src
        String dataFile = outputFileName + ".dat";        // output file name

        try {
            // Prepare .json src file
            Files.deleteIfExists(Path.of(dataFile));     // delete if file already exists
            String data = Files.readString(studentJson); // read from the src path
            data = data.replaceFirst("^(\\[)", "" ) // remove first opening bracket
                    .replaceFirst("(\\])$", "");    // remove the last closing bracket
            String[] records = data.split(System.lineSeparator());   // split records by line separator
            System.out.println("Total records = " + records.length);

            /*
             * Step 1: Store id and file position into the indexMap
             *         while writing each record to output file.
             *
             *  - If separateIndex is true, write record to the output file
             *    starting from file position 0.
             *
             *  - If false, the calculated space for storing
             *          - total no. of records
             *          - key=long
             *          - value=long
             *    is reserved and write the record content starting from the
             *    file position after the reserved space.
             */
            long filePointerStartPosition = separateIndex ? 0 : 4 + (16L * records.length);

            // Store the id and file position in the indexMap while writing the each record.
            Pattern idPattern = Pattern.compile("studentId\":([0-9]+)");
            try (RandomAccessFile raf = new RandomAccessFile(dataFile, "rw")) {
                raf.seek(filePointerStartPosition); // set file pointer to start position
                for (String record : records) {
                    Matcher m = idPattern.matcher(record);
                    if (m.find()) {
                        long id = Long.parseLong(m.group(1));
                        // Add key=id and value=current file position of the record file
                        indexMap.put(id, raf.getFilePointer());
                        // Write each record to output file, this increments the file pointer to
                        // next position ready for write operation.
                        raf.writeUTF(record);
                    }
                }

                /*
                 * Step 2: Write id and file position in the output file from the indexMap.
                 *
                 * - If the separateIndex is true, create a separate index file
                 *   and store the id and file position from the indexMap.
                 *
                 * - If false, store the id and file position in the same output
                 *   file where the calculated space for id and file position was
                 *   reserved previously.
                 */
                writeIndex((separateIndex) ?
                        new RandomAccessFile(outputFileName + ".idx", "rw") : raf, indexMap);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Step 2: Write id and file position in the output file from the indexMap.
     * @param raf the RandomAccessFile to write id and file position to.
     * @param indexMap the indexMap to read id and file position from.
     */
    private static void writeIndex(RandomAccessFile raf, Map<Long, Long> indexMap) {
        try {
            raf.seek(0);              // set file position to start of file
            raf.writeInt(indexMap.size()); // write total no. of records

            for (Map.Entry<Long, Long> student : indexMap.entrySet()) {
                raf.writeLong(student.getKey());   // write key
                raf.writeLong(student.getValue()); // write value
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
