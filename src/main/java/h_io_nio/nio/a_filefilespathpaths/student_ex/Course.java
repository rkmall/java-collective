package h_io_nio.nio.a_filefilespathpaths.student_ex;

public class Course {
    private final String courseCode;
    private final String title;

    public Course(String courseCode, String title) {
        this.courseCode = courseCode;
        this.title = title;
    }

    public String getCourseCode() { return courseCode; }

    public String getTitle() { return title; }

    public int getLectureCount() { return 15; }

    @Override
    public String toString() { return String.format("%s %s", courseCode, title); }
}
