package h_io_nio.nio.a_filefilespathpaths.student_ex;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentExDriver {

    public static final String header = "Student Id, Country Code, Enrolled Year, " +
            "Age, Gender, Experienced, Course Code, Engagement Month, " +
            "Engagement Year, Engagement Type";

    public static void main(String[] args) {

        //writeDataToFileNotEfficient();
        //writeDataToFileEfficient();

        //writeDataToFileBufferedWriter();
        //writeDataToFileFileWriter();
        //writeDataToFilePrintWriter();
    }

    /*
     * This approach is not efficient because write() method is
     * called for each student opening and closing the file.
     */
    public static void writeDataToFileNotEfficient() {
        List<Student> students = generateRandomStudents();
        Path path = Path.of("students.csv");
        try {
            Files.writeString(path, header);
            for (Student student : students) {
                // write() is called for each student each time opening and closing the file
                Files.write(path, student.getEngagementRecords(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * This approach is efficient since write() method is called only
     * once, hence, opening and closing the file only once.
     */
    public static void writeDataToFileEfficient() {
        List<Student> students = generateRandomStudents();
        Path path = Path.of("students.csv");
        try {
            List<String> data = new ArrayList<>();
            data.add(header);
            for (Student student : students) {
                data.addAll(student.getEngagementRecords());
            }
            // write() is called only once
            Files.write(path, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeDataToFileBufferedWriter() {
        List<Student> students = generateRandomStudents();
        Path path = Path.of("studentsBuffered.csv");
        try (BufferedWriter writer = new BufferedWriter(Files.newBufferedWriter(path))) {
            writer.write(header);
            writer.newLine();
            for (Student student : students) {
                for (String record : student.getEngagementRecords()) {
                    writer.write(record);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeDataToFileFileWriter() {
        List<Student> students = generateRandomStudents();
        try (FileWriter writer = new FileWriter("studentsFileWriter.csv")) {
            writer.write(header);
            writer.write(System.lineSeparator());
            for (Student student : students) {
                for (String record : student.getEngagementRecords()) {
                    writer.write(record);
                    writer.write(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * PrintWriter is useful when we need more flexibility to
     * format and print texts.
     */
    public static void writeDataToFilePrintWriter() {
        List<Student> students = generateRandomStudents();
        try (PrintWriter writer = new PrintWriter("studentsPrintWriter.csv")) {
            writer.println(header);
            for (Student student : students) {
                for (String record : student.getEngagementRecords()) {
                    String[] recordData = record.split(",");
                    writer.printf(String.format("%-12d%-5s%2d%4d%3d%-1s",
                            student.getStudentId(),
                            student.getCountry(),
                            student.getEnrollmentYear(),
                            student.getEnrollmentMonth(),
                            student.getEnrollmentAge(),
                            student.getGender()));
                    writer.printf(String.format("%-1s", student.hasExperience() ? 'Y' : 'N'));
                    writer.format("%-3s%10.2f%-10s%-4s%-30s",
                            recordData[7],   // course code
                            student.getPercentComplete(recordData[7]),
                            recordData[8],
                            recordData[9],
                            recordData[10]);
                    writer.println();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Student> generateRandomStudents() {
        Course jmc = new Course("JMC", "Java Masterclass");
        Course pmc = new Course("PMC", "Python Masterclass");
        return Stream
                .generate(() -> Student.getRandomStudent(jmc, pmc))
                .limit(5)
                .collect(Collectors.toList());
    }
}
