package h_io_nio.nio.a_filefilespathpaths.student_ex;

public class StudentDemographics {
    private final String countryCode;
    private final int enrolledMonth;
    private final int enrolledYear;
    private final int ageAtEnrollment;
    private final String gender;
    private final boolean previousProgrammingExperience;

    public StudentDemographics(String countryCode,
                               int enrolledMonth,
                               int enrolledYear,
                               int ageAtEnrollment,
                               String gender,
                               boolean previousProgrammingExperience) {
        this.countryCode = countryCode;
        this.enrolledMonth = enrolledMonth;
        this.enrolledYear = enrolledYear;
        this.ageAtEnrollment = ageAtEnrollment;
        this.gender = gender;
        this.previousProgrammingExperience = previousProgrammingExperience;
    }

    public String getCountryCode() { return countryCode; }

    public int getEnrolledMonth() { return enrolledMonth; }

    public int getEnrolledYear() { return enrolledYear; }

    public int getAgeAtEnrollment() { return ageAtEnrollment; }

    public String getGender() { return gender; }

    public boolean isPreviousProgrammingExperience() { return previousProgrammingExperience; }

    @Override
    public String toString() {
        return String.format("%s,%d,%d,%d,%s,%b", countryCode,
                enrolledMonth, enrolledYear, ageAtEnrollment,
                gender, previousProgrammingExperience);
    }
}
