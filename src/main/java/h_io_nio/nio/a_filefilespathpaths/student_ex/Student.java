package h_io_nio.nio.a_filefilespathpaths.student_ex;

import java.time.LocalDate;
import java.util.*;

public class Student {
    private static long lastStudentId = 1;
    private final long studentId;
    private final StudentDemographics demographics;
    private final List<Course> coursesEnrolled;

    private final Map<String, CourseEngagement> engagementMap;

    public Student(String country,
                   int month,
                   int year,
                   int age,
                   String gender,
                   boolean hasExperience,
                   Course... courses) {
        studentId = lastStudentId++;
        demographics = new StudentDemographics(country,
                month, year,
                age,
                gender,
                hasExperience);

        coursesEnrolled = new ArrayList<>(Arrays.asList(courses));
        engagementMap = new HashMap<>();

        for (Course course : courses) {
            engagementMap.put(course.getCourseCode(),
                    new CourseEngagement(course.getCourseCode(), month, year,
                            "Enrollment"));
        }
    }

    public long getStudentId() { return studentId; }

    public int getAge() {
        int currentYear = LocalDate.now().getYear();
        int enrolledYear = demographics.getEnrolledYear();
        int age = demographics.getAgeAtEnrollment();
        return age + (currentYear - enrolledYear);
    }

    public int getEnrollmentAge() { return demographics.getAgeAtEnrollment(); }

    public String getGender() { return demographics.getGender(); }

    public int getEnrollmentYear() { return demographics.getEnrolledYear(); }

    public int getEnrollmentMonth() { return demographics.getEnrolledMonth(); }

    public String getCountry() { return demographics.getCountryCode(); }

    public boolean hasExperience() { return demographics.isPreviousProgrammingExperience(); }

    public int getInactiveMonths(String courseCode) {
        CourseEngagement info = engagementMap.get(courseCode);
        return info.getInactiveMonths();
    }

    public int getInactiveMonths() {
        int inactiveMonths = 300;  // 25 years
        for (String key : engagementMap.keySet()) {
            int mos = getInactiveMonths(key);
            if  (mos < inactiveMonths) {
                inactiveMonths = mos;
            }
        }
        return inactiveMonths;
    }

    public double getPercentComplete(String courseCode) {
        CourseEngagement info = engagementMap.get(courseCode);
        for (Course course : coursesEnrolled) {
            if (course.getCourseCode().equals(courseCode)) {
                return info.getPercentComplete(course.getLectureCount());
            }
        }
        System.out.println("Could not find " + courseCode);
        return 0;
    }

    public void addCourse(Course newCourse) {
        LocalDate now = LocalDate.now();
        engagementMap.put(newCourse.getCourseCode(),
                new CourseEngagement(newCourse.getCourseCode(), now.getMonthValue(),
                        now.getYear(), "Enrollment"));
        coursesEnrolled.add(newCourse);
    }

    public void startVideo(String courseCode, int lectureNumber, int month, int year) {
        CourseEngagement activity = engagementMap.get(courseCode);
        activity.recordLastActivity(lectureNumber, month, year);
    }

    @Override
    public String toString() {
        StringBuilder engagementData = new StringBuilder();
        for (CourseEngagement engagement : engagementMap.values()) {
            engagementData.append(engagement.toString());
        }
        return String.format("Student = %s %n%s%nCourses: %s%n%s",
                studentId,
                demographics,
                coursesEnrolled,
                engagementData);
    }

    public List<String> getEngagementRecords() {
        List<String> engagementData = new ArrayList<>();
        for (CourseEngagement engagement: engagementMap.values()) {
            engagementData.add(String.format("%s,%s,%s",
                    studentId,      // int
                    demographics,   // StudentDemographics toString()
                    engagement));   // CourseEngagement toString()
        }
        return engagementData;
    }

    public static Student getRandomStudent(Course... courses) {
        Random random = new Random();
        String countryCode = List.of("AU", "CN", "GB", "IN","US")
                .get(random.nextInt(5));
        String gender = List.of("M", "F", "U").get(random.nextInt(3));

        int minYear = 2015;
        int maxYear = LocalDate.now().getYear() + 1;
        Student student = new Student(countryCode,
                random.nextInt(13 - 1) + 1,
                random.nextInt(maxYear - minYear) + minYear,
                random.nextInt(90 - 18) + 18,
                gender,
                random.nextBoolean(),
                courses);

        int yearEnrolled = student.getEnrollmentYear();

        List.of(courses).forEach(c ->
                student.startVideo(c.getCourseCode(),
                        random.nextInt(c.getLectureCount() - 1) -1,
                        random.nextInt(13 - 1) + 1,
                        random.nextInt(maxYear - yearEnrolled) + yearEnrolled)
        );
        return student;
    }
}
